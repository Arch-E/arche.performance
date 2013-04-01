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

package edu.cmu.sei.pacc.perf.eval.matlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Display;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.eval.model.Evaluations;
import edu.cmu.sei.pacc.perf.eval.model.FileInfo;
import edu.cmu.sei.pacc.perf.eval.model.MatlabEvaluationInfo;
import edu.cmu.sei.pacc.perf.eval.viewers.EvaluationResultViewer;
import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.Exponential;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.SSTask;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.util.ExecuteExternalProgram;

public class MatlabEvaluation implements EvaluationProcedure {

	private static final int MATLAB_ANALYSIS_ERROR = 100;

	private static double avgQTime;

	private static double avgServiceTime;

	private static double avgLatency;

	private static String cclFileName;

	private String cclFilePath;

	private Vector fileInfo;

	public String getName() {
		return "Closed form (SS latency)";
	}

	public String getDescription() {
		return "Closed for prediction for Sporadic Server latency based on Lambda-SS";
	}

	public boolean isEnabled() {
		return true;
	}

	public WizardPage getWizardOptionsPage() {
		return null;
	}

	public IStatus performEvaluation(PerformanceModel perfModel,
			String modelFileName, ConsolePrinter consolePrinter, Object options)
			throws CoreException, IOException {
		final String PLUGIN_ID = PerformanceRFPlugin.getDefault().getName();
		URL lssWrapperExeUrl = Platform.getBundle(PLUGIN_ID).getEntry(
				"/tools/lsswrapper.exe");
		lssWrapperExeUrl = FileLocator.resolve(lssWrapperExeUrl);
		String lssWrapperExeFullName = (new File(lssWrapperExeUrl.getFile()))
				.getAbsolutePath();

		/* check for l-ss assumptions */
		fileInfo = PerformanceRFPlugin.getEvaluationFileInfos();

		/*
		 * find SS task and check there is only one and that there are no other
		 * aperiodics
		 */
		/*
		 * in the same loop, compute the utilization of the periodics, which is
		 * needed later
		 */
		SSTask ssTask = null;
		double utilization = 0;
		for (Iterator it = perfModel.getTasks().iterator(); it.hasNext();) {
			Task task = (Task) it.next();
			if (task instanceof SSTask) {
				if (ssTask == null) {
					ssTask = (SSTask) task;
					if (!(ssTask.getInterarrivalDistribution() instanceof Exponential)) {
						return new Status(
								IStatus.ERROR,
								PLUGIN_ID,
								MATLAB_ANALYSIS_ERROR,
								"The sporadic server analysis only supports exponential interarrival distribution.",
								null);
					}
				} else {
					return new Status(
							IStatus.ERROR,
							PLUGIN_ID,
							MATLAB_ANALYSIS_ERROR,
							"The assembly has more than one sporadic server task.",
							null);
				}
			} else if (task instanceof AperiodicTask) {
				return new Status(IStatus.ERROR, PLUGIN_ID,
						MATLAB_ANALYSIS_ERROR,
						"The assembly can have at most one aperiodic task and it "
								+ " must be a sporadic server task.", null);
			} else if (task instanceof PeriodicTask) {
				utilization += task.getComputedExecutionMean()
						/ ((PeriodicTask) task).getPeriod();
			}
		}

		if (ssTask == null) {
			return new Status(
					IStatus.ERROR,
					PLUGIN_ID,
					MATLAB_ANALYSIS_ERROR,
					"The assembly doesn't have a sporadic server task to predict.",
					null);
		}

		if (ssTask.getBudget() != ssTask.getComputedExecutionMean()) {
			return new Status(
					IStatus.ERROR,
					PLUGIN_ID,
					MATLAB_ANALYSIS_ERROR,
					"The Sporadic Server budget must be equal to the SS task execution time",
					null);
		}

		/*
		 * TODO: we should check that the background priority is less than all
		 * the priorities used in the assembly, or enhance
		 * getComputedExecutionMean to account only for those subtasks with
		 * higher priority than the SS background in order to compute the right
		 * Up
		 */

		/* create output directory and compute output file name */
		IPath modelPath = new Path(modelFileName);

		cclFilePath = modelPath.toOSString();

		cclFileName = modelPath.removeFileExtension().lastSegment();

		String baseFileName = modelPath.removeFileExtension().lastSegment();
		IPath resultsPath = PerformanceRFPlugin.createOutputFolder(modelFileName);

		// append "_task<ID>.csv"
		File outputFile = resultsPath.append(
				baseFileName + "_task" + ssTask.getTaskId() + ".csv").toFile();

		StringBuffer command = new StringBuffer();
		command.append(lssWrapperExeFullName);
		command.append(' ');
		command.append(utilization); // Up
		command.append(' ');
		command.append(ssTask.getComputedExecutionMean()); // Sa
		command.append(' ');
		command.append(ssTask.getInterarrivalDistribution().getComputedMean()); // Ta
		command.append(' ');
		command.append(ssTask.getReplenishmentPeriod()); // Tss
		command.append(" \"");
		command.append(outputFile.getAbsolutePath());
		command.append('"');

		final String commandLine = command.toString();

		consolePrinter
				.printToConsole("Performing analysis with parameters: Up="
						+ utilization
						+ ", Sa=Ss="
						+ ssTask.getComputedExecutionMean()
						+ ", Ta="
						+ ssTask.getInterarrivalDistribution()
								.getComputedMean() + ", Tss="
						+ ssTask.getReplenishmentPeriod());

		ExecuteExternalProgram matlabPredictor = new ExecuteExternalProgram(
				commandLine);
		int result = matlabPredictor.exec();
		if (matlabPredictor.getStdErr().length() > 0) {
			consolePrinter.printToConsole(matlabPredictor.getStdErr());
		}
		consolePrinter.printToConsole(matlabPredictor.getStdOut());
		if (result == 0) {

			/*
			 * parse csv file and show the results. The format is E(Q), E(Ss),
			 * E(W)
			 */
			try {
				BufferedReader resultsFile = new BufferedReader(new FileReader(
						outputFile));
				String line = resultsFile.readLine();
				resultsFile.close();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				consolePrinter
						.printToConsole("Prediction results for the sporadic server task:");

				String avgQ = tokenizer.nextToken();
				String avgS = tokenizer.nextToken();
				String avgL = tokenizer.nextToken();

				consolePrinter.printToConsole("\tAverage Queuing Time E[Q] = "
						+ avgQ);
				avgQTime = Double.parseDouble(avgQ);

				consolePrinter.printToConsole("\tAverage Service Time E[Ss] = "
						+ avgS);
				avgServiceTime = Double.parseDouble(avgS);

				consolePrinter.printToConsole("\tAverage Latency E[W] = "
						+ avgL);
				avgLatency = Double.parseDouble(avgL);

				// Store the information for the session

				FileInfo fInfo = new FileInfo();

				fInfo.setFilePath(cclFilePath);
				fInfo.setFileName(cclFileName);

				MatlabEvaluationInfo matEvalInfo = new MatlabEvaluationInfo();

				matEvalInfo.setAvgLat(avgLatency);
				matEvalInfo.setAvgQ(avgQTime);
				matEvalInfo.setAvgSer(avgServiceTime);

				Integer index = getFileInfo(cclFilePath);
				Vector evals;

				if (index == null) {
					evals = new Vector();
					evals.add(matEvalInfo);
					fInfo.setEvaluations(evals);
					fileInfo.add(fInfo);
				} else {

					FileInfo standIn = (FileInfo) fileInfo.get(index);
					evals = standIn.getEvaluations();
					Integer evalIndex = isMatlabDone(index);

					if (evalIndex != null) {
						evals.set(evalIndex.intValue(), matEvalInfo);
					} else {
						evals.add(matEvalInfo);
					}

					fInfo.setEvaluations(evals);
					fileInfo.set(index.intValue(), fInfo);
				}

			} catch (Exception e) {
				String message = "An error occurred while reading prediction results from "
						+ outputFile + ": " + e.getMessage();
				consolePrinter.printToConsole(message);
				IStatus status = new Status(IStatus.ERROR, PerformanceRFPlugin
						.getDefault().getName(), 0, message, e);
				PerformanceRFPlugin.getDefault().getLog().log(status);
				return status;
			}
		} else {
			String message = "lsswrapper ended with status " + result;
			consolePrinter.printToConsole(message);
			IStatus status = new Status(IStatus.ERROR, PerformanceRFPlugin
					.getDefault().getName(), 0, message, new Exception(message));
			PerformanceRFPlugin.getDefault().getLog().log(status);
			return status;
		}

		
		//Synchronous execution with the UI thread for refreshing the viewer
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				try {
					EvaluationResultViewer.refreshViewer();
				} catch (Exception e) {
					// Nothing for now
				}
			}
		});

		return Status.OK_STATUS;
	}

	// Methods to get the values of the evaluation result to be displayed on the
	// view

	private Integer getFileInfo(String cclFilePath2) {
		Integer count = null;

		for (int i = 0; i < fileInfo.size(); i++) {
			FileInfo temp = (FileInfo) fileInfo.get(i);
			if (temp.getFilePath().equals(cclFilePath2)) {
				count = i;
			}
		}
		return count;
	}

	private Integer isMatlabDone(Integer index) {

		FileInfo standIn = (FileInfo) fileInfo.get(index);
		Vector evalsStandIn = standIn.getEvaluations();

		for (int i = 0; i < evalsStandIn.size(); i++) {
			Evaluations evalTemp = (Evaluations) evalsStandIn.get(i);
			if (evalTemp.getEvaluationName().equals("Matlab")) {
				return i;
			}
		}
		return null;
	}
}
