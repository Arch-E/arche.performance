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

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressConstants;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.LPICalculator;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.ReasoningFramework;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;

/**
 * Eclipse Job that executes performance analysis, which consists of the following steps
 * <li> Transform the ICM model into a performance model (interpretation)
 * <li> Call the selected predictor. If not predictor is selected, it does just interpretation.
 * 
 * @author Paulo Merson
 */
public class InterpretationAndAnalysisJob extends Job {

    /** shell used to display message dialogs */
    private Shell                 shell;

    /** assembly object that represents the ICM model created */
    private AssemblyInstance      assembly;

    /** object that represents performance model created as result of interpretation */
    private PerformanceModel      perfModel;

    /** name of the file where the performance model will be persisted */
    private String                modelFileName;

    private EvaluationProcedure evaluationProcedure;
    
    private ReasoningFramework reasoningFramework;

    /**
     * map of scenario numbers to boolean that indicate if the scenarios were selected or not by the
     * user
     */
    private Map<Integer, Boolean> scenarios;
    
    private final String PLUGIN_ID = PerformanceRFPlugin.getDefault().getName();
    
    private ConsolePrinter console;
    
    /** Evaluation procedure options */
    private Object options;

    /**
     * 
     * @param assembly  The ICM that is input to interpretation
     * @param modelFileName name of the file where the performance model will be persisted
     * @param scenarios map of scenario numbers to boolean that indicate if the scenarios were 
     * selected or not by the user
     * @param shell
     * @param runExtend
     * @param runMatlab
     */
    public InterpretationAndAnalysisJob(AssemblyInstance assembly, String modelFileName,
            Map<Integer, Boolean> scenarios, Shell shell, EvaluationProcedure procedure,
            Object options) {
        super("Performance Interpretation and Analysis");
        this.assembly = assembly;
        this.modelFileName = modelFileName;
        this.scenarios = scenarios;
        this.shell = shell;
        this.evaluationProcedure = procedure;
        this.options = options;
        reasoningFramework = new ReasoningFramework();
        console = reasoningFramework.new DefaultConsolePrinter();
    }

    /**
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected IStatus run(IProgressMonitor monitor) {

        // notifying that the main task has begun. The number of units of work is just an arbitrary
        // number selected to provide apt progress view.
        monitor.beginTask("Task: " + getName(), 5000);

        // STEP 1 - INTERPRETATION
        IStatus status = doInterpretation(monitor);
        if (status.getSeverity() == IStatus.OK && reasoningFramework.getNumInterpretationErrors() == 0) {
            // STEP 2 - ANALYSIS
            status = doAnalysis(monitor);
        }

        //
        // Refresh navigator to show any newly generated file(s)
        //
        SubProgressMonitor subMonitor = new SubProgressMonitor(monitor, 3000);
        subMonitor.beginTask("Refreshing navigator view ", IProgressMonitor.UNKNOWN);
        setName(getName() + "--> Refreshing navigator view");
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE,
                                                                  new NullProgressMonitor());
        } catch (Exception e) {
            status = new Status(IStatus.ERROR, PLUGIN_ID, 6,
                    "Could not refresh navigator view.", e);
        }
        subMonitor.done();
        monitor.worked(3000); // notify that navigator refresh is complete

        monitor.done();
        // for keeping the job in the UI after it has finished
        setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);
        // to show an icon on the status bar (bottom right of the screen), which will
        // pop up a message dialog with the job result on clicking
        setProperty(IProgressConstants.ACTION_PROPERTY, getInterpretationJobCompletedAction());

        return status;
    }

    /**
     * Performs interpretation to translate the ICM into the performance model.
     * 
     * @param monitor cannot be null
     * @return
     */
    private IStatus doInterpretation(IProgressMonitor monitor) {
        String msg = null;
        SubProgressMonitor subMonitor = null;
        
        //
        // 1) Execute interpretation (ICM to performance model) and persist the model.
        //
        subMonitor = new SubProgressMonitor(monitor, 1000);
        subMonitor.beginTask("Interpretation ", IProgressMonitor.UNKNOWN);
        msg = "--> Translating ICM to performance model";
        setName(getName() + msg);
        console.printToConsole(msg);
        
        IStatus status = reasoningFramework.doInterpretation(assembly, scenarios);
        monitor.worked(800);

        if (status.getSeverity() == IStatus.OK
				&& reasoningFramework.getNumInterpretationErrors() == 0) {
        	perfModel = reasoningFramework.getPerformanceModel();
        	status = reasoningFramework.persistPerformanceModel(modelFileName);
        }

        monitor.worked(900);
        
        if (status.getSeverity() != IStatus.OK) {
	    	Throwable e = status.getException();
	    	if (e != null) {
	    		console.printToConsole(e.getMessage());
	    	}
        }
        
        for (Iterator it = reasoningFramework.getInterpretationErrors().iterator();
        		it != null && it.hasNext();) {
        	console.printToConsole(it.next().toString());
        }
        
        msg = "Interpretation finished with " + reasoningFramework.getNumInterpretationErrors() 
        	+ " error(s) and "
            + reasoningFramework.getNumInterpretationWarnings() + " warning(s).\n";
        console.printToConsole(msg);
        subMonitor.done();
        monitor.worked(1000); // notify that interpretation is complete
        
        return status;
    }

    /**
     * Calls the selected evaluation procedure to perform analysis.
     * 
     * @param monitor cannot be null
     * @return
     */
    private IStatus doAnalysis(IProgressMonitor monitor) {
    	
    	/* write the LPI */
    	/* Replace last .model from modelFileName with .lpi and put it in the performance folder*/
    	IPath performanceFolder = PerformanceRFPlugin.createOutputFolder(modelFileName);
    	File lpiFile = performanceFolder.append(
    				new Path(modelFileName).removeFileExtension().addFileExtension("lpi").lastSegment())
    			.toFile();
    	LPICalculator.createLPI(perfModel, lpiFile.toString());

        //
        // 2) Execute the selected evaluation procedure
        // 
        IStatus status = Status.OK_STATUS;
        if (evaluationProcedure == null) return status;

        SubProgressMonitor subMonitor = new SubProgressMonitor(monitor, 1000);
        subMonitor.beginTask("Analysis - " + evaluationProcedure.getName(),
                             IProgressMonitor.UNKNOWN);
        String msg = "--> Performance analysis using " + evaluationProcedure.getName();
        setName(getName() + msg);
        console.printToConsole(msg);
        status = reasoningFramework.doEvaluation(evaluationProcedure,
        		perfModel, modelFileName, console, options);
        if (status.getSeverity() != IStatus.OK) {
        	Throwable e = status.getException();
        	if (e != null) {
        		console.printToConsole(e.getMessage());
        	}
        }
        subMonitor.done();

        return status;
    }

    /**
     * This action takes place when when the user clicks the icon on the status bar (bottom right of
     * the screen) that indicates the status of the job.
     * 
     * @return an action which indicates the user that the job is done by popping a message dialog
     *         box
     */
    protected Action getInterpretationJobCompletedAction() {
        return new Action("Interpretation job status") {

            public void run() {
                MessageDialog.openInformation(shell, "Interpretation Complete",
                                              "The interpretation has been completed");
            }
        };
    }

    /**
     * @return Returns the assembly.
     */
    public AssemblyInstance getAssembly() {
        return assembly;
    }

    /**
     * @return Returns the perfModel.
     */
    public PerformanceModel getPerfModel() {
        return perfModel;
    }


}
