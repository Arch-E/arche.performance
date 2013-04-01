/*
 * ArchE
 * Copyright (c) 2012 Carnegie Mellon University.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following acknowledgments and disclaimers.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. All advertising materials for third-party software mentioning features or
 * use of this software must display the following disclaimer:
 *
 * “Neither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this software”
 *
 * 4. The names “Carnegie Mellon University,” and/or “Software Engineering
 * Institute" shall not be used to endorse or promote products derived from
 * this software without prior written permission. For written permission,
 * please contact permission@sei.cmu.edu.
 *
 * 5. Redistributions of any form whatsoever must retain the following
 * acknowledgment:
 *
 * Copyright 2012 Carnegie Mellon University.
 *
 * This material is based upon work funded and supported by the United States
 * Department of Defense under Contract No. FA8721-05-C-0003 with Carnegie
 * Mellon University for the operation of the Software Engineering Institute, a
 * federally funded research and development center.
 *
 * NO WARRANTY
 *
 * THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE MATERIAL
 * IS FURNISHED ON AN “AS-IS” BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package edu.cmu.sei.pacc.perf.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import edu.cmu.sei.pacc.perf.ITransformToIcm;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.ReasoningFramework;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;

/**
 * Provides the basic new project wizard, with PECT-specific options. <b>When
 * the user selects sample project, default CCL options are used on the second
 * page; when he selects blank project, CCL options are blank. As user can go
 * back and forth in the wizard and change sample/blank project, we update ccl
 * options accordingly. If user edits the ccl options box once, it won't be
 * changed if he goes back and change sample/blank selection.
 * 
 * @author Paulo Merson
 */
public class BatchPerfAnalysisWizard extends Wizard {

	private IWorkbench workbench;

	private IStructuredSelection selection;

	private PerfAnalysisPredictorsPage predictorsPage;

	private Map<EvaluationProcedure, WizardPage> evaluationProcedurePages = new HashMap<EvaluationProcedure, WizardPage>();

	/** the input design files (e.g., a set of .ccl file) */
	private IStructuredSelection designFiles;

	/**
	 * reference to object in another plug-in that is able to translate the
	 * design file to ICM
	 */
	private ITransformToIcm designToIcmTranslator;

	public BatchPerfAnalysisWizard(IStructuredSelection designFiles,
			ITransformToIcm designToIcmTranslator) {
		this.designFiles = designFiles;
		this.designToIcmTranslator = designToIcmTranslator;
		setDefaultPageImageDescriptor(getBannerImage());

		setDialogSettings(PerformanceRFPlugin.getDefault().getDialogSettings());
		setWindowTitle("Performance Analysis");
		setNeedsProgressMonitor(true);
	}

	class BatchAnalysisRunner implements IRunnableWithProgress {

		protected MessageConsoleStream out;

		public BatchAnalysisRunner(MessageConsoleStream consoleStream) {
			out = consoleStream;
		}

		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException {
			try {
				monitor.beginTask("Batch Analysis", designFiles.size() * 300 + 100);
				/* no scenarios used in batch analysis */
				Map<Integer, Boolean> scenarios = new HashMap<Integer, Boolean>();
	
				for (Iterator it = designFiles.iterator(); it.hasNext();) {
					IFile designFile = (IFile) it.next();
					
					monitor.setTaskName("Analyzing " + designFile.getName());
	
					/* translate to ICM or load the ICM */
					AssemblyInstance assembly = null;
					if (designToIcmTranslator != null) {
						assembly = designToIcmTranslator
								.translateDesignToIcm(designFile);
						out.println(designToIcmTranslator.getConsoleMessages());
					} else {
						assembly = ReasoningFramework
								.loadConstructiveAssembly(designFile);
					}
					if (assembly == null) {
						out.println("Batch Analysis: failed to load or translate "
								+ designFile);
					}
					monitor.worked(100);
					if (monitor.isCanceled()) {
						throw new InterruptedException();
					}
					if (assembly != null) {
	
						// do analysis
						String modelFileName = designFile.getRawLocation()
								.removeFileExtension().addFileExtension("model")
								.toOSString();
	
						// Creating a job instance for doing interpretation and
						// analysis
						InterpretationAndAnalysis analysisHelper = new InterpretationAndAnalysis(
								assembly, modelFileName,
								scenarios, predictorsPage.getSelectedProcedure(),
								getOptions());
	
						IStatus status = analysisHelper.doInterpretation(
								new SubProgressMonitor(monitor, 100));
						if (monitor.isCanceled()) {
							throw new InterruptedException();
						}
						if (status.getSeverity() == IStatus.OK && !analysisHelper.hasInterpretationErrors()) {
							status = analysisHelper.doAnalysis(new SubProgressMonitor(monitor, 100));
						}
						if (monitor.isCanceled()) {
							throw new InterruptedException();
						}
					} else {
						monitor.worked(200);
						if (monitor.isCanceled()) {
							throw new InterruptedException();
						}
					}
				}
				
				// refresh files
				try {
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE,
							new SubProgressMonitor(monitor, 100));
				}
				catch (CoreException e) {} // do nothing
			}
			finally {
				monitor.done();
			}
		}

	}

	/**
	 * Finish the wizard and perform the interpretation/analysis.
	 * 
	 * @return true to indicate the finish request was accepted, and false to
	 *         indicate that the finish request was refused
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		IConsoleManager consoleMgr = ConsolePlugin.getDefault().getConsoleManager();
		MessageConsole console = PerformanceRFPlugin.getDefault().getConsole();
		consoleMgr.showConsoleView(console);
		MessageConsoleStream out = console.newMessageStream();

		try {
			getContainer().run(false, true, new BatchAnalysisRunner(out));
		} catch (InvocationTargetException e) {
			//TODO show error message
			e.getCause().printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	/**
	 * Call canFinish on the mainPage.
	 * 
	 * @return true if the wizard could be finished, and false otherwise
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		return predictorsPage.canFinish();
	}

	/**
	 * Add pages to the wizard.
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		ImageDescriptor img = getBannerImage();
		predictorsPage = new PerfAnalysisPredictorsPage("predictors");
		predictorsPage.setImageDescriptor(img);
		addPage(predictorsPage);

		// add evaluation procedure option pages
		for (Iterator it = PerformanceRFPlugin.getDefault()
				.getEvaluationProcedures().iterator(); it.hasNext();) {
			EvaluationProcedure procedure = (EvaluationProcedure) it.next();
			WizardPage page = procedure.getWizardOptionsPage();
			if (page != null) {
				evaluationProcedurePages.put(procedure, page);
				addPage(page);
			}
		}
	}

	/**
	 * Goes over all the trouble to get the image file to show in the dialog
	 * title area.
	 * 
	 * @return ImageDescriptor
	 */
	private ImageDescriptor getBannerImage() {
		URL imgUrl = PerformanceRFPlugin.getDefault().getBundle().getEntry(
				"/icons/chrono.jpg");
		return ImageDescriptor.createFromURL(imgUrl);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == predictorsPage) {
			IWizardPage next = evaluationProcedurePages.get(predictorsPage
					.getSelectedProcedure());
			return next;
		}

		return null;
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		if (page != predictorsPage) { // it's an options page
			return predictorsPage;
		}
		return super.getPreviousPage(page);
	}

	public Object getOptions() {
		EvaluationProcedureOptionsPage optionsPage = (EvaluationProcedureOptionsPage) evaluationProcedurePages
				.get(predictorsPage.getSelectedProcedure());
		return (optionsPage != null) ? optionsPage.getOptions() : null;
	}

}