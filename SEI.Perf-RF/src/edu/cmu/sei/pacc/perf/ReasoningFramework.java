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

package edu.cmu.sei.pacc.perf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsoleStream;

import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.util.Error;
import edu.cmu.sei.pacc.perf.util.MarkerHelper;

/**
 * Facade to use the RF programmatically
 * 
 * @author gmoreno
 */
public class ReasoningFramework {

    private final String PLUGIN_ID = PerformanceRFPlugin.getDefault().getName();
	
	private Interpretation interpretation = null;
	private PerformanceModel performanceModel = null;

	public final int RF_STATUS_PERSIST_ERROR = 1;
	public final int RF_STATUS_INTERPRETATION_FAILED = 2;
	public final int RF_STATUS_INTERPRETATION_ERRORS = 3;
	public final int RF_STATUS_ANALYSIS_FAILED = 4;
	
	/**
	 * Loads a constructive assembly from an ICM model
	 * 
	 * @param assembly model file
	 * @return top-level instance of the model
	 */
	public static AssemblyInstance loadConstructiveAssembly(IFile assemblyFile) {
    	ResourceSet resourceSet = new ResourceSetImpl();

		/* Register factory and package */
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				Resource.Factory.Registry.DEFAULT_EXTENSION, 
				new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(IcmPackage.eNS_URI, 
				IcmPackage.eINSTANCE);

    	URI fileURI = URI.createFileURI(assemblyFile.getLocation().toOSString());
    	Resource resource = resourceSet.getResource(fileURI, true);
    	return (AssemblyInstance) resource.getContents().get(0);
	}
	
	/**
	 * Performs the interpretation. The resulting performance model can be
	 * persisted with persistPerformanceModel() or obtained with
	 * getPerformanceModel().
	 * 
	 * @param assembly constructive assembly in ICM model
	 * @param scenarios Map with pairs <s, i> indicating that scenario s is included
	 * 	in the interpretation only if i is true. 
	 * @return status. If its severity is IStatus.OK the interpretation was carried out.
	 *		Even if the interpretation reported interpretation errors and warnings, the
	 *		severity will still be OK. IStatus.ERROR is used only to report exceptions. 
	 */
	public IStatus doInterpretation(AssemblyInstance assembly,
									Map<Integer, Boolean> scenarios) {
        interpretation = InterpretationFactory.createInstance();

        // determine scenarios
        Integer scenario = null;
        int scenarioBitMask = 0;
        for (Map.Entry<Integer, Boolean> entry : scenarios.entrySet()) {
            if (entry.getValue()) {
                scenarioBitMask |= entry.getKey();
            }
        }
        if (scenarioBitMask > 0) {
            scenario = new Integer(scenarioBitMask);
        }

        try {
        	performanceModel = interpretation.doInterpretation(assembly, scenario);
    		MarkerHelper.addMarkers(interpretation.getErrors(), PLUGIN_ID);
        } catch (Exception e) {
            return new Status(IStatus.ERROR, PLUGIN_ID, RF_STATUS_INTERPRETATION_FAILED,
                    "Performance interpretation failed: " + e.getMessage(), e);
        }
        
        return Status.OK_STATUS;
	}
	
	/**
	 * Returns the performance model obtained after interpretation
	 * 
	 * @return
	 */
	public PerformanceModel getPerformanceModel() {
		return performanceModel;
	}
	
	
	/**
	 * Persists the performance model that resulted from the interpretation.
	 * 
	 * @param modelFileName model file path
	 * @return Status.OK_STATUS if successful
	 */
	public IStatus persistPerformanceModel(String modelFileName) {
        try {
        	if (performanceModel == null) {
        		throw new Exception("Performance Model is null");
        	}
        	interpretation.persistModel(modelFileName);
        } catch (Exception e) {
            return new Status(IStatus.ERROR, PLUGIN_ID, RF_STATUS_PERSIST_ERROR,
                    "Could not persist performance model.", e);
        }
        return Status.OK_STATUS;
	}

	/**
	 * Returns the list of interpretation errors
	 * 
	 * @return error list or null if the interpretation wasn't performed
	 */
	public List<Error> getInterpretationErrors() {
		List<Error> rval = null;
		if (interpretation != null) {
			rval = interpretation.getErrors();
		}
		return rval;
	}
	
    /**
     * Returns the number if interpretation errors.
     * 
     * @return number of errors with severity = ERROR detected during intepretation.
     */
    public int getNumInterpretationErrors() {
    	int rval = 0;
    	if (interpretation != null) {
    		rval = interpretation.getNumErrors();
    	}
    	return rval;
    }

    /**
     * Returns the number if interpretation errors warnings.
	 *
     * @return number of warnings detected during intepretation.
     */
    public int getNumInterpretationWarnings() {
    	int rval = 0;
    	if (interpretation != null) {
    		rval = interpretation.getNumWarnings();
    	}
    	return rval;
    }

    
    /**
     * Performs the evaluation procedure.
     * 
     * @param perfModel performance model to be analyzed
     * @param modelFileName name of the model file name (it may have a .model extension or no
     *            extension). This filename is only used to determine the location
     *            of auxiliary files. The model is not loaded from this file.
     * @return IStatus. Returns Status.OK_STATUS if everything went ok.
     */
    public IStatus doEvaluation(EvaluationProcedure evaluationProcedure,
    		PerformanceModel perfModel, String modelFileName, Object options) {
    	
    	return doEvaluation(evaluationProcedure, perfModel, modelFileName,
    			new DefaultConsolePrinter(), options);
    }
    
    /**
     * Performs the evaluation procedure.
     * 
     * @param perfModel performance model to be analyzed
     * @param modelFileName name of the model file name (it may have a .model extension or no
     *            extension). This filename is only used to determine the location
     *            of auxiliary files. The model is not loaded from this file.
     * @param consolePrinter ConsolePrinter instance to print out progress messages and results.
     * 		The user of this method can provide their own ConsolePrinter implementation to
     * 		capture the messages that would be otherwise printed out to the console.
     * @param options options passed to the RF
     * @return IStatus. Returns Status.OK_STATUS if everything went ok.
     */
    public IStatus doEvaluation(EvaluationProcedure evaluationProcedure,
    		PerformanceModel perfModel, String modelFileName,
            ConsolePrinter consolePrinter, Object options) {
    	IStatus status;
    	
        try {
            status = evaluationProcedure.performEvaluation(perfModel,
            			modelFileName, consolePrinter, options);
        } catch (Exception e) {
            return new Status(IStatus.ERROR, PLUGIN_ID, RF_STATUS_ANALYSIS_FAILED,
            		"Analysis using " + evaluationProcedure.getName() 
            		+ " failed:\n" + e.getMessage(), e);
        }
        
        return status;
    }
    
    
    /**
     * Implementation of ConsolePrinter that allows printing messages
     * from the evaluation procedures directly to the console.
     * 
     */
    public class DefaultConsolePrinter implements ConsolePrinter {
    	
        /**
         * @see edu.cmu.sei.pacc.perf.ConsolePrinter#printToConsole(java.lang.String)
         */
        public void printToConsole(String msg) {
            final String msgToPrint = msg == null ? "" : msg;
            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    MessageConsoleStream out = PerformanceRFPlugin.getDefault().getConsole()
                            .newMessageStream();
                    assert out != null : "ERROR - MessageConsoleStream is null when printing:\n"
                            + msgToPrint;
                    System.out.println("msgToPrint: " + msgToPrint);
                    out.println(msgToPrint);
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        // nothing to do
                    }
                }
            });
        }
    	
    }
}
