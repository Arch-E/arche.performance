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

package edu.cmu.sei.pacc.perf.eval.mast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.util.ExecuteExternalProgram;

public class MastEvaluation implements EvaluationProcedure {

	private static final String MAST_ANALYSIS_EXE = "/tools/mast/mast_analysis.exe";

	private Boolean mastInstalled = null;
	
	public String getName() {
		return "MAST";
	}

	public String getDescription() {
		return "Modeling and Analysis Suite for Real-Time Applications";
	}

	public boolean isEnabled() {
		if (mastInstalled == null) {
			mastInstalled = new Boolean(false);
			try {
				URL mastExeUrl = PerformanceRFPlugin.getDefault().getBundle().getEntry(
					MAST_ANALYSIS_EXE);
				mastExeUrl = FileLocator.resolve(mastExeUrl);
				if ((new File(mastExeUrl.getFile())).exists()) {
					mastInstalled = new Boolean(true);
				}
			} catch (Exception e) {};
		}
		return mastInstalled.booleanValue();
	}

	public WizardPage getWizardOptionsPage() {
		return new MastOptionsPage("MastOptions");
	}

	public IStatus performEvaluation(PerformanceModel perfModel,
			String modelFileName, ConsolePrinter consolePrinter, Object options)
			throws CoreException, IOException {
		Bundle bundle = PerformanceRFPlugin.getDefault().getBundle();
		
		/* create output directory */
		IPath modelPath = new Path(modelFileName);
		String baseFileName = modelPath.removeFileExtension().lastSegment();
		IPath resultsPath = PerformanceRFPlugin.createOutputFolder(modelFileName);

		/* get options */
		MastOptions mastOptions  = (MastOptions) options;
		if (mastOptions == null) {
			mastOptions = new MastOptions();
		}
		
		/* create MAST model */
		mastOptions.templateArgs.setModel(perfModel);
		File mastFile = resultsPath.append(baseFileName + ".mast").toFile();
		MastModelGenerator mastModelGenerator = new MastModelGenerator();
		BufferedWriter mastFileWriter = new BufferedWriter(new FileWriter(
				mastFile));
		mastFileWriter.write(mastModelGenerator.generate(mastOptions.templateArgs));
		mastFileWriter.close();

		/* run mast_analysis */
		URL mastExeUrl = bundle.getEntry(MAST_ANALYSIS_EXE);
		mastExeUrl = FileLocator.resolve(mastExeUrl);
		String mastExeFullName = (new File(mastExeUrl.getFile()))
				.getAbsolutePath();

		File outputFile = resultsPath.append(baseFileName + "_mast" + ".txt")
				.toFile();

		/* remove csv results file */
		File csvFile = new Path(outputFile.getAbsolutePath())
			.removeFileExtension().addFileExtension("csv").toFile();
		csvFile.delete();

		StringBuffer command = new StringBuffer();
		command.append(mastExeFullName);
		command.append(' ');
		switch (mastOptions.mode) {
		case OFFSET_BASED_OPTIMIZED:
			command.append("offset_based_optimized");
			break;
		default:
			command.append("varying_priorities");
		}
		command.append(" \"");
		command.append(mastFile.getAbsolutePath());
		command.append("\" \"");
		command.append(outputFile.getAbsolutePath());
		command.append('"');

		final String commandLine = command.toString();
		consolePrinter.printToConsole("Mast cmd line: " + commandLine);

		ExecuteExternalProgram mastPredictor = new ExecuteExternalProgram(
				commandLine);
		int result = mastPredictor.exec();
		if (mastPredictor.getStdErr().length() > 0) {
			consolePrinter.printToConsole(mastPredictor.getStdErr());
		}
		consolePrinter.printToConsole(mastPredictor.getStdOut());
		MastResultReader resultReader = new MastResultReader(perfModel);
		if (result == 0) {
			resultReader.translateResults(outputFile, csvFile);
			if (mastOptions.useMastResultsViewer) {
				mastExeUrl = bundle.getEntry("/tools/mast/gmastresults.exe");
				mastExeUrl = Platform.resolve(mastExeUrl);
				mastExeFullName = (new File(mastExeUrl.getFile()))
						.getAbsolutePath();
				command = new StringBuffer();
				command.append(mastExeFullName);
				command.append(" \"");
				command.append(mastFile.getAbsolutePath());
				command.append("\" \"");
				command.append(outputFile.getAbsolutePath());
				command.append('"');
				ExecuteExternalProgram mastResults = new ExecuteExternalProgram(
						command.toString());
				mastResults.exec();
			}
		} else {
			if (mastPredictor.getStdOut().indexOf("Utilization_Too_High") >= 0) {
				resultReader.writeOutputForUnknowResults(csvFile);
				consolePrinter.printToConsole("The system is not schedulable");
				if (mastOptions.useMastResultsViewer) {
					Display display = new Display();
					Shell shell = new Shell(display);
					shell.forceActive();
	//				MessageDialog.openError(
	//					shell,
	//					"Not Schedulable", "The system is not schedulable");
					MastResultsNE results = new MastResultsNE(shell);
					results.open();
				}
			} else {
				csvFile.delete(); 
				String message = "mast_analysis ended with status " + result;
				consolePrinter.printToConsole(message);
				IStatus status = new Status(IStatus.ERROR, PerformanceRFPlugin
						.getDefault().getName(), 0, message, new Exception(message));
				PerformanceRFPlugin.getDefault().getLog().log(status);
				return status;
			}
		}
		return Status.OK_STATUS;
	}

	
	protected void getResults() {
		
	}
}
