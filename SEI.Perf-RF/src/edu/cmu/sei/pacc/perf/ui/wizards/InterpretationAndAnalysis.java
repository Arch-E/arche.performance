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
 * This class implements the basic operations of interpretation and analysis
 * These operations can be used in Jobs or IRunnableWithProgress
 * 
 * @author gmoreno
 */
public class InterpretationAndAnalysis {

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
    public InterpretationAndAnalysis(AssemblyInstance assembly, String modelFileName,
            Map<Integer, Boolean> scenarios, EvaluationProcedure procedure,
            Object options) {
        this.assembly = assembly;
        this.modelFileName = modelFileName;
        this.scenarios = scenarios;
        this.evaluationProcedure = procedure;
        this.options = options;
        reasoningFramework = new ReasoningFramework();
        console = reasoningFramework.new DefaultConsolePrinter();
    }

    /**
     * Performs interpretation to translate the ICM into the performance model.
     * 
     * @param monitor cannot be null
     * @return
     */
    public IStatus doInterpretation(IProgressMonitor monitor) {
        
    	IStatus status = Status.CANCEL_STATUS; // default to not OK
    	
        //
        // 1) Execute interpretation (ICM to performance model) and persist the model.
        //
        
    	try {
	    	String msg = "Translating ICM to performance model";
	        monitor.beginTask(msg, 10);
	        console.printToConsole(msg);
	        
	        status = reasoningFramework.doInterpretation(assembly, scenarios);
	        monitor.worked(8);
	
	        if (status.getSeverity() == IStatus.OK
					&& reasoningFramework.getNumInterpretationErrors() == 0) {
	        	perfModel = reasoningFramework.getPerformanceModel();
	        	status = reasoningFramework.persistPerformanceModel(modelFileName);
	        	monitor.worked(1);
	        	
	        	if (status.getSeverity() == IStatus.OK) {
	        		
		        	/* write the LPI */
		        	/* Replace last .model from modelFileName with .lpi and put it in the performance folder*/
		        	IPath performanceFolder = PerformanceRFPlugin.createOutputFolder(modelFileName);
		        	File lpiFile = performanceFolder.append(
		        				new Path(modelFileName).removeFileExtension().addFileExtension("lpi").lastSegment())
		        			.toFile();
		        	LPICalculator.createLPI(perfModel, lpiFile.toString());
	        	} 
	        	monitor.worked(1);
	
	        }
	
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
    	}
    	finally {
    		monitor.done();
    	}
        
        return status;
    }

    /**
     * Calls the selected evaluation procedure to perform analysis.
     * 
     * @param monitor cannot be null
     * @return
     */
    public IStatus doAnalysis(IProgressMonitor monitor) {
    	
        // Execute the selected evaluation procedure (if any)
        IStatus status = Status.OK_STATUS;
        try {
	        monitor.beginTask("Performance model evaluation", 10);
	        if (evaluationProcedure == null) return status;
	        
	        String msg = "Performance model evaluation" + " using " + evaluationProcedure.getName();
	        //monitor.setTaskName(msg);
	        console.printToConsole(msg);
	        status = reasoningFramework.doEvaluation(evaluationProcedure,
	        		perfModel, modelFileName, console, options);
	        monitor.worked(10);
	        if (status.getSeverity() != IStatus.OK) {
	        	Throwable e = status.getException();
	        	if (e != null) {
	        		console.printToConsole(e.getMessage());
	        	}
	        }
        }
        finally {
        	monitor.done();
        }

        return status;
    }

    /**
     * @return Returns the perfModel.
     */
    public PerformanceModel getPerfModel() {
        return perfModel;
    }

    /**
     * Checks if there were interpretation errors
     * 
     * @return true if there were interpretation errors
     */
    public boolean hasInterpretationErrors() {
    	return reasoningFramework.getNumInterpretationErrors() > 0;
    }
}
