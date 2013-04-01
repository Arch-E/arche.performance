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

package edu.cmu.sei.pacc.perf.eval.extend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Display;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.eval.LambdaABAUtils;
import edu.cmu.sei.pacc.perf.eval.model.Evaluations;
import edu.cmu.sei.pacc.perf.eval.model.ExtendEvaluationInfo;
import edu.cmu.sei.pacc.perf.eval.model.ExtendTaskInfo;
import edu.cmu.sei.pacc.perf.eval.model.FileInfo;
import edu.cmu.sei.pacc.perf.eval.viewers.EvaluationResultViewer;
import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.Subtask;

public class ExtendEvaluation implements EvaluationProcedure {

	private static int tasksSize;

	private static double bestCase[];

	private static double average[];

	private static double worstCase[];

	private static String cclFileName;

	private Vector fileInfo;

	private String cclFilePath;

	public String getName() {
		return "Simulation (Extend V6)";
	}

	public String getDescription() {
		return "Simulation-based prediction for Lambda-ABA";
	}

	public boolean isEnabled() {
		return PerformanceRFPlugin.getDefault().isExtendInstalled();
	}

	public WizardPage getWizardOptionsPage() {
		return null;
	}

	public IStatus performEvaluation(PerformanceModel perfModel,
			String modelFileName, ConsolePrinter consolePrinter, Object options)
			throws CoreException, IOException {
		boolean closeExtend = false;
		double hpStart = 0.0;
		double startTime;
		double endTime;

		fileInfo = PerformanceRFPlugin.getEvaluationFileInfos();

		/* create output directory and compute output file name */
		IPath modelPath = new Path(modelFileName);

		cclFilePath = modelPath.toOSString();

		cclFileName = modelPath.removeFileExtension().lastSegment();

		String baseFileName = modelPath.removeFileExtension().lastSegment()
				+ "_Extend";
		IPath resultsPath = PerformanceRFPlugin
				.createOutputFolder(modelFileName);

		String extendFileName = resultsPath.append(baseFileName).toOSString(); // Extend
		// will
		// add
		// .mox

		/* determine if there are aperiodic tasks */
		boolean hasAperiodics = false;
		for (Iterator it = perfModel.getTasks().iterator(); it.hasNext();) {
			if (it.next() instanceof AperiodicTask) {
				hasAperiodics = true;
				break;
			}
		}

		tasksSize = perfModel.getTasks().size();

		bestCase = new double[tasksSize];
		average = new double[tasksSize];
		worstCase = new double[tasksSize];

		String extenCdOutputFileNames[] = new String[tasksSize];

		if (hasAperiodics) {
			startTime = 0.;
			endTime = 10000.;
		} else {
			// TODO: compute simulation length accounting for downsampling and
			// steady state HP
			double hp = LambdaABAUtils.computeHyperperiod(perfModel.getTasks()); // LCM of
			// periods
			double hpEnd = LambdaABAUtils.maxOffset(perfModel.getTasks()) + hp;
			startTime = 0;
			endTime = hpEnd;
		}
		ExtendSolver extHelper = new ExtendSolver();
		extHelper.setConsolePrinter(consolePrinter);

		int ans = extHelper.doIt(extendFileName, perfModel.getTasks(), hpStart,
				startTime, endTime, closeExtend);

		/* parse results */
		consolePrinter.printToConsole("Prediction results:");
		for (int i = 0; i < tasksSize; i++) {
			StringBuffer sb = new StringBuffer();
			sb.append("Task ");
			sb.append(i + 1);
			sb.append(": Observed Latency ");
			String extendOutputFileName = extendFileName + "_task" + (i + 1)
					+ ".csv";
			File extendOutputFile = new File(extendOutputFileName);
			if (extendOutputFile.exists()) {
				try {
					BufferedReader resultsFile = new BufferedReader(
							new FileReader(extendOutputFile));
					int samples = 0;
					average[i] = 0;
					worstCase[i] = 0;
					bestCase[i] = Double.MAX_VALUE;
					String line = resultsFile.readLine();
					while (line != null) {
						StringTokenizer tokenizer = new StringTokenizer(line,
								",");
						if (tokenizer == null || tokenizer.countTokens() < 3)
							break;
						tokenizer.nextToken();
						tokenizer.nextToken();
						double latency = Double.parseDouble(tokenizer
								.nextToken());
						samples++;
						if (latency > worstCase[i]) {
							worstCase[i] = latency;
						}
						if (latency < bestCase[i]) {
							bestCase[i] = latency;
						}
						average[i] = (average[i] * (samples - 1) + latency)
								/ samples;
						line = resultsFile.readLine();
					}
					if (samples > 0) {
						sb.append("Best = ");
						sb.append(String.format("%g", bestCase[i]));
						sb.append(" Average = ");
						sb.append(String.format("%g", average[i]));
						sb.append(" Worst = ");
						sb.append(String.format("%g", worstCase[i]));
						sb.append(" (based on ");
						sb.append(samples);
						sb.append(" jobs)");
					} else {
						sb
								.append("No data produced. Probably the task was not schedulable.");
					}
					resultsFile.close();

				} catch (Exception e) {
					String message = "An error occurred while reading prediction results from "
							+ extendOutputFile + ": " + e.getMessage();
					consolePrinter.printToConsole(message);
					IStatus status = new Status(IStatus.ERROR,
							PerformanceRFPlugin.getDefault().getName(), 0,
							message, e);
					PerformanceRFPlugin.getDefault().getLog().log(status);
					return status;
				}
			} else {
				sb.append("results not available");
			}
			consolePrinter.printToConsole(sb.toString());
		}

		// STORE THE INFORMATION ABOUT THE EVALUATION

		FileInfo fInfo = new FileInfo();

		fInfo.setFilePath(cclFilePath);
		fInfo.setFileName(cclFileName);

		ExtendEvaluationInfo extEvalInfo = new ExtendEvaluationInfo();

		Vector evaluationTasks = new Vector();

		for (int i = 0; i < tasksSize; i++) {
			ExtendTaskInfo standIn = new ExtendTaskInfo();

			standIn.setAvg(average[i]);
			standIn.setBest(bestCase[i]);
			standIn.setWorst(worstCase[i]);

			evaluationTasks.add(standIn);

		}

		extEvalInfo.setNumberOftasks(evaluationTasks);

		Integer index = getFileInfo(cclFilePath);
		Vector evals;

		if (index == null) {
			evals = new Vector();
			evals.add(extEvalInfo);
			fInfo.setEvaluations(evals);
			fileInfo.add(fInfo);
		} else {

			FileInfo standIn = (FileInfo) fileInfo.get(index);
			evals = standIn.getEvaluations();
			Integer evalIndex = isExtendDone(index);

			if (evalIndex != null) {
				evals.set(evalIndex.intValue(), extEvalInfo);
			} else {
				evals.add(extEvalInfo);
			}
			fInfo.setEvaluations(evals);
			fileInfo.set(index.intValue(), fInfo);
		}

		PerformanceRFPlugin.setEvaluationFileInfos(fileInfo);

		// Synchronous execution with the UI thread for refreshing the viewer
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

	private Integer isExtendDone(Integer index) {

		FileInfo standIn = (FileInfo) fileInfo.get(index);
		Vector evalsStandIn = standIn.getEvaluations();

		for (int i = 0; i < evalsStandIn.size(); i++) {
			Evaluations evalTemp = (Evaluations) evalsStandIn.get(i);
			if (evalTemp.getEvaluationName().equals("Extend")) {
				return i;
			}
		}

		return null;
	}

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
}
