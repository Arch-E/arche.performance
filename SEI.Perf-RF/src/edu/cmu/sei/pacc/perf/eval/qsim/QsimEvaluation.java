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

package edu.cmu.sei.pacc.perf.eval.qsim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.WizardPage;
import org.osgi.framework.Bundle;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.eval.LambdaABAUtils;
import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.util.ExecuteExternalProgram;

public class QsimEvaluation implements EvaluationProcedure {

	private static final String QSIM_EXE = "/tools/qsim/qsim.exe";

	private Boolean qsimInstalled = null;
	
	public String getName() {
		return "qsim";
	}

	public String getDescription() {
		return "Discrete Event Simulation";
	}

	public boolean isEnabled() {
		if (qsimInstalled == null) {
			qsimInstalled = new Boolean(false);
			try {
				URL qsimExeUrl = PerformanceRFPlugin.getDefault().getBundle().getEntry(
					QSIM_EXE);
				qsimExeUrl = FileLocator.resolve(qsimExeUrl);
				if ((new File(qsimExeUrl.getFile())).exists()) {
					qsimInstalled = new Boolean(true);
				}
			} catch (Exception e) {};
		}
		return qsimInstalled.booleanValue();
	}

	public WizardPage getWizardOptionsPage() {
		return null;
	}

	public IStatus performEvaluation(PerformanceModel perfModel,
			String modelFileName, ConsolePrinter consolePrinter, Object options)
			throws CoreException, IOException {
		Bundle bundle = PerformanceRFPlugin.getDefault().getBundle();
		
		/* create output directory */
		IPath modelPath = new Path(modelFileName);
		String baseFileName = modelPath.removeFileExtension().lastSegment();
		IPath resultsPath = PerformanceRFPlugin.createOutputFolder(modelFileName);

		/* create qsim model */
		File qsimFile = resultsPath.append(baseFileName + ".mq").toFile();
		QsimModelGenerator qsimModelGenerator = new QsimModelGenerator();
		BufferedWriter qsimFileWriter = new BufferedWriter(new FileWriter(
				qsimFile));
		qsimFileWriter.write(qsimModelGenerator.generate(perfModel));
		qsimFileWriter.close();

		/* determine if there are aperiodic tasks */
		boolean hasAperiodics = false;
		for (Iterator it = perfModel.getTasks().iterator(); it.hasNext();) {
			if (it.next() instanceof AperiodicTask) {
				hasAperiodics = true;
				break;
			}
		}

		/* compute simulation length */
		double simulationLength;
		if (hasAperiodics) {
			simulationLength = 10000.; // TODO compute something meaningful
		} else {
			
			// TODO: compute simulation length accounting for downsampling and
			// steady state HP
			double hp = LambdaABAUtils.computeHyperperiod(perfModel.getTasks());
			simulationLength = LambdaABAUtils.maxOffset(perfModel.getTasks()) + hp;
		}

		
		/* run qsim */
		URL qsimExeUrl = bundle.getEntry(QSIM_EXE);
		qsimExeUrl = FileLocator.resolve(qsimExeUrl);
		String qsimExeFullName = (new File(qsimExeUrl.getFile()))
				.getAbsolutePath();

		File outputFile = resultsPath.append(baseFileName + "_qsim" + ".txt")
				.toFile();

		StringBuffer command = new StringBuffer();
		command.append(qsimExeFullName);
		command.append(" -e ");
		command.append(simulationLength);
		command.append(" -o \"");
		command.append(outputFile.getAbsolutePath());
		command.append("\" \"");
		command.append(qsimFile.getAbsolutePath());
		command.append('"');

		final String commandLine = command.toString();

		ExecuteExternalProgram qsimPredictor = new ExecuteExternalProgram(
				commandLine);
		int result = qsimPredictor.exec();
		if (qsimPredictor.getStdErr().length() > 0) {
			consolePrinter.printToConsole(qsimPredictor.getStdErr());
		}
		consolePrinter.printToConsole(qsimPredictor.getStdOut());
		if (result == 0) {
			final String LATENCY_KEYWORD = "latency";
			final String MIN_LATENCY_KEYWORD = "minlatency";
			final String MAX_LATENCY_KEYWORD = "maxlatency";
			final String JOBS_KEYWORD = "sink";
			Properties properties = new Properties();
			properties.load(new FileInputStream(outputFile));
			for (Iterator it = perfModel.getTasks().iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				StringBuffer resultLine = new StringBuffer();
				resultLine.append("Task ");
				resultLine.append(task.getName());
				resultLine.append(" Observed Latency");

				resultLine.append(" Best = ");
				String key = task.getName().replace('.', '_') + '.' + MIN_LATENCY_KEYWORD;
				String latency = properties.getProperty(key);
				resultLine.append((latency != null) ? latency : "n/a");

				resultLine.append(" Average = ");
				key = task.getName().replace('.', '_') + '.' + LATENCY_KEYWORD;
				latency = properties.getProperty(key);
				resultLine.append((latency != null) ? latency : "n/a");
				
				resultLine.append(" Worst = ");
				key = task.getName().replace('.', '_') + '.' + MAX_LATENCY_KEYWORD;
				latency = properties.getProperty(key);
				resultLine.append((latency != null) ? latency : "n/a");
				
				key = task.getName().replace('.', '_') + '.' + JOBS_KEYWORD;
				String jobs = properties.getProperty(key);
				if (jobs != null) {
					resultLine.append(" (based on ");
					resultLine.append(jobs);
					resultLine.append(" job(s))");
				}
				consolePrinter.printToConsole(resultLine.toString());
			}
		} else {
			String message = "qsim ended with status " + result;
			consolePrinter.printToConsole(message);
			IStatus status = new Status(IStatus.ERROR, PerformanceRFPlugin
					.getDefault().getName(), 0, message, new Exception(message));
			PerformanceRFPlugin.getDefault().getLog().log(status);
			return status;
		}
		return Status.OK_STATUS;
	}
}
