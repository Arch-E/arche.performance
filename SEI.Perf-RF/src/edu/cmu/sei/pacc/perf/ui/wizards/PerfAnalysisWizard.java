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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.progress.IProgressService;

import edu.cmu.sei.pacc.perf.ITransformToIcm;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.ReasoningFramework;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;

/**
 * Provides the basic new project wizard, with PECT-specific options. <b>When the user selects
 * sample project, default CCL options are used on the second page; when he selects blank project,
 * CCL options are blank. As user can go back and forth in the wizard and change sample/blank
 * project, we update ccl options accordingly. If user edits the ccl options box once, it won't be
 * changed if he goes back and change sample/blank selection.
 * 
 * @author Paulo Merson
 */
public class PerfAnalysisWizard extends Wizard {

    private IWorkbench                     workbench;

    private IStructuredSelection           selection;

    private PerfAnalysisInterpretationPage interpPage;

    private PerfAnalysisPredictorsPage     predictorsPage;
    
    private Map<EvaluationProcedure, WizardPage> evaluationProcedurePages 
    	= new HashMap<EvaluationProcedure, WizardPage>();

    /** the input design file (e.g., a .ccl file) */
    private IFile                          designFile;

    /** assembly object that represents the ICM model created prior to calling this wizard */
    private AssemblyInstance               assembly;
    
    /** reference to object in another plug-in that is able to translate the design file to ICM */
    private ITransformToIcm                designToIcmTranslator;

    /**
     * Constructor that takes the design file selected by the user and right away transforms it into
     * an ICM model that will be used later.
     * 
     * @param designFile
     * @throws IOException
     */
    public PerfAnalysisWizard(IFile designFile, ITransformToIcm designToIcmTranslator) {
        this.designFile = designFile;
        this.designToIcmTranslator = designToIcmTranslator;
        if (designToIcmTranslator != null) {
	        if (!translateDesignToIcm()) {
	        	this.dispose();
	        }
        } else {
        	assembly = ReasoningFramework.loadConstructiveAssembly(designFile);
        	if (assembly == null) {
        		this.dispose();
        	}
        }
    }

    /**
     * Performs the design translation with progress
     * @author gmoreno
     */
    class DesignTranslationRunner implements IRunnableWithProgress {
    	MessageConsoleStream out;

    	public DesignTranslationRunner(MessageConsoleStream consoleStream) {
    		out = consoleStream;
    	}
		
    	public void run(IProgressMonitor pm) {
	        try {
	            assembly = designToIcmTranslator.translateDesignToIcm(designFile);
	            if (pm.isCanceled()) {
	            	throw new InterruptedException();
	            }
	            out.println(designToIcmTranslator.getConsoleMessages());
	        } catch (Exception e) {
	            assembly = null;
	        }
		}
    }
    
    /**
     * Translates a design file into the corresponding ICM model using the plug-in available to do
     * that translation.
     * 
     * @throws IOException
     */
    private boolean translateDesignToIcm() {
        MessageConsoleStream out = PerformanceRFPlugin.getDefault().getConsole().newMessageStream();
		IWorkbench wb = PlatformUI.getWorkbench();
		IProgressService ps = wb.getProgressService();
        try {
    		ps.busyCursorWhile(new DesignTranslationRunner(out));
        } catch (InvocationTargetException e) {
        	assembly = null;
        	out.println("Error while creating ICM model. Cause: " 
        			+ e.getCause().getMessage());
        } catch (InterruptedException e) {
        	assembly = null;
        	return false;
        } finally {
        	try {
	            out.flush();
	            out.close();
        	} catch (IOException e) {
                // nothing to do
            }
        }
        
        if (assembly == null) {
            String msg = "Unable to successfully create an ICM model from "
                    + designFile.getName();
            out.println(msg);
        	try {
	            out.flush();
	            out.close();
        	} catch (IOException e) {
                // nothing to do
            }
            throw new RuntimeException(msg);
        }

        return (assembly != null);
    }

    /**
     * Finish the wizard and perform the interpretation/analysis.
     * 
     * @return true to indicate the finish request was accepted, and false to indicate that the
     *         finish request was refused
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     */
    public boolean performFinish() {

        try {
            // copy scenario selection to separate data structure because UI checkboxes will
            // be disposed
            Map<Integer, Boolean> scenarios = new HashMap<Integer, Boolean>();
            Map<Integer, Button> scenarioButtons = interpPage.getScenarios();
            if (scenarioButtons != null) {
                for (Map.Entry<Integer, Button> entry : scenarioButtons.entrySet()) {
                    scenarios.put(entry.getKey(), entry.getValue().getSelection());
                }
            }
            // Creating a job instance for doing interpretation and analysis
            InterpretationAndAnalysisJob job = new InterpretationAndAnalysisJob(assembly,
                    interpPage.getModelFileText().getText(), scenarios, getShell(),
                    predictorsPage.getSelectedProcedure(), getOptions());

            // adding job listener to notify when execution is complete
            job.addJobChangeListener(new JobChangeAdapter() {

                public void done(IJobChangeEvent event) {
                    final IJobChangeEvent jobEvent = event;
                    Display.getDefault().asyncExec(new Runnable() {

                        public void run() {
                            String msg = "--> Interpretation and analysis finished";
                            MessageConsoleStream out = PerformanceRFPlugin.getDefault()
                                    .getConsole().newMessageStream();
                            out.println(msg);
                            try {
                                out.flush();
                                out.close();
                            } catch (IOException e) {
                                // nothing to do
                            }
                            if (jobEvent.getResult().isOK()) {
                                MessageDialog
                                        .openInformation(getShell(), "Performance Analysis",
                                                         "Interpretation and analysis finished."
                                                                 + "\nCheck console for details.");
                            }
                        }
                    });

                }
            });

            job.setUser(true); // run as a user job
            job.schedule(); // start as soon as possible (will call run)

        } catch (Throwable e) {
            MessageDialog.openError(getShell(), "Performance Interpretation and Analysis",
                                    "An error has occurred:\n" + e.getMessage()
                                            + "\n\nClick OK and check the .log file for details.");
            throw new RuntimeException(
                    "Could not execute performance interpretation and analysis.", e);
        } finally {
            ConsolePlugin plugin = ConsolePlugin.getDefault();
            IConsoleManager conMan = plugin.getConsoleManager();
            conMan.showConsoleView(PerformanceRFPlugin.getDefault().getConsole());
        }
        return true;
    }

    /**
     * Initializes this creation wizard using the passed workbench and object selection.
     * 
     * @param workbench the current workbench
     * @param selection current object selection
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setDefaultPageImageDescriptor(getBannerImage());

        setDialogSettings(PerformanceRFPlugin.getDefault().getDialogSettings());
        setWindowTitle("Performance Analysis");
        setNeedsProgressMonitor(true);
    }

    /**
     * Call canFinish on the mainPage.
     * 
     * @return true if the wizard could be finished, and false otherwise
     * @see org.eclipse.jface.wizard.IWizard#canFinish()
     */
    public boolean canFinish() {
        return interpPage.canFinish() && predictorsPage.canFinish();
    }

    /**
     * Add pages to the wizard. 
     * 
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        ImageDescriptor img = getBannerImage();
        interpPage = new PerfAnalysisInterpretationPage("interp", designFile, assembly);
        interpPage.setImageDescriptor(img);
        predictorsPage = new PerfAnalysisPredictorsPage("predictors");
        predictorsPage.setImageDescriptor(img);
        addPage(interpPage);
        addPage(predictorsPage);
        
        // add evaluation procedure option pages
        for (Iterator it = PerformanceRFPlugin.getDefault().getEvaluationProcedures().iterator(); 
        		it.hasNext();) {
		    EvaluationProcedure procedure = (EvaluationProcedure) it.next();
		    WizardPage page = procedure.getWizardOptionsPage();
		    if (page != null) {
		    	evaluationProcedurePages.put(procedure, page);
		    	addPage(page);
		    }
		}
    }

    /**
     * Goes over all the trouble to get the image file to show in the dialog title area.
     * 
     * @return ImageDescriptor
     */
    private ImageDescriptor getBannerImage() {
        URL imgUrl = PerformanceRFPlugin.getDefault().getBundle().getEntry("/icons/chrono.jpg");
        return ImageDescriptor.createFromURL(imgUrl);
    }

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == interpPage) {
			return predictorsPage;
		}
		
		if (page == predictorsPage) {
			IWizardPage next = evaluationProcedurePages.get(predictorsPage.getSelectedProcedure());
			return next;
		}

		return null;
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		if (page != interpPage && page != predictorsPage) { // it's an options page
			return predictorsPage;
		}
		return super.getPreviousPage(page);
	}
	
	public Object getOptions() {
		EvaluationProcedureOptionsPage optionsPage = 
			(EvaluationProcedureOptionsPage) evaluationProcedurePages.get(
					predictorsPage.getSelectedProcedure());
		return (optionsPage != null) ? optionsPage.getOptions() : null;
	}
    
}