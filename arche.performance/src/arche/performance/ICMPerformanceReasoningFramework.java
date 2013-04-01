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
 * â€œNeither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this softwareâ€�
 *
 * 4. The names â€œCarnegie Mellon University,â€� and/or â€œSoftware Engineering
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
 * IS FURNISHED ON AN â€œAS-ISâ€� BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package arche.performance;

/**
 * An integration of the schedulability analysis for ICM models used by PACC.
 * This is an 'analysis-only' reasoning framework, because it just analyzes
 * the architecture for performance but it doesn't support tactics.
 * <p>
 * Note: This example is still under experimentation. 
 * 
 * @author Andres Diaz-Pace
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.ui.console.MessageConsoleStream;

import arche.performance.hibernate.ArchECoreResponsibilityStructure;
import arche.performance.hibernate.vo.ArchEResponsibilityReactionRelationVO;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.external.data.ArchEArchitecture;
import edu.cmu.sei.arche.external.data.ArchERelation;
import edu.cmu.sei.arche.external.data.ArchERequirementModel;
import edu.cmu.sei.arche.external.data.ArchEResponsibility;
import edu.cmu.sei.arche.external.data.ArchEResponsibilityStructure;
import edu.cmu.sei.arche.external.data.ArchEScenario;
import edu.cmu.sei.arche.external.data.ArchEView;
import edu.cmu.sei.arche.external.reasoningframework.ArchEAnalysisProblem;
import edu.cmu.sei.arche.external.reasoningframework.ArchEAnalysisResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEEvaluationResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEReasoningFramework;
import edu.cmu.sei.arche.external.reasoningframework.ArchETryTacticResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEUserQuestion;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.ReasoningFramework;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.eval.mast.MastOptions;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;

public class ICMPerformanceReasoningFramework extends ArchEReasoningFramework {
   
	//---- Names of parameters used by this reasoning framework
	public static final String  PARAMETER_EXECUTION_TIME = "P_ExecutionTime";

	//---- Default values of parameters
	public static final double DEFAULT_EXECUTION_TIME 	= 1.0;
	private static final double MIN_EXECUTION_TIME 		= 0.0;
	private static final double MAX_EXECUTION_TIME 		= 10.0;
	
	protected static final double INVALID_RESPONSE = Double.MAX_VALUE/2; // An arbitrary high value

	//---- Questions and warning defined in the question file by this reasoning framework
	public static final String FIX_ANALYSIS_PARAMETERS_WARNING 			= "fixAnalysisParameters";

	//---- Performance-specific types of errors
	private static final int EXECUTION_TIME_OUT_OF_LIMITS_ERROR = 1;
	
	private boolean enableMASTViewer = false;

	public ICMPerformanceReasoningFramework() {
    	super();
    	
    	// set data provider
    	setDataProvider(new ICMPerformanceRFDataProvider());
	    	
    	// configure this RF with the configuration file 
		try {     	   
			URL url = FileLocator.find(Platform.getBundle("SEI.ArchE.Performance"), new Path("/config"), null);
			String installPathName = FileLocator.resolve(url).getPath();
			String rfconfigFullPath = installPathName + "perficm-rfconfig.xml";
			configure(rfconfigFullPath);
		} catch (MalformedURLException e1){
			e1.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		}                             
    }	    		
			
	/**
	 * Note that any transformations for analysis must be done within this method
	 * (e.g., ArchE to ICM, ICM to ICMPerf Performance Model, etc.)
	 * 
	 * @param architecture current architecture model (assumed consistent)
	 * @param currentScenario scenario to be analyzed on the architecture
	 * @throws ArchEException
	 */
	@Override
	public ArchEEvaluationResult analyze(ArchEArchitecture architecture,
				ArchEScenario currentScenario) throws ArchEException {

		ICMWrapper wrapper = (ICMWrapper)(architecture.getView()); 
					
		printLog(2, Level.INFO, "Running analysis for the scenario \"" + currentScenario.getDescription() + "\"");

		double response = INVALID_RESPONSE;//Double.NaN;
		double measureVal = 0.0; // The minimum worst case execution time for performance
		if (currentScenario.getMeasureValue() != null)
			measureVal = currentScenario.getMeasureValue();
			
		if (this.isResponsibilityStructureValid()) {
				
			// This code calls the API for the ICMPerformance ReasoningFramework developed by PACC
			ReasoningFramework rfperf = new ReasoningFramework();				
			
			// Transformation of the ICM to tasks
			AssemblyInstance assembly = wrapper.getICMModel();
			HashMap<Integer,Boolean> map = wrapper.getScenarioMapFor(null); // Null means to analyze all the scenarios at once!!
			IStatus statusInterpretation = rfperf.doInterpretation(assembly,map);
			printLog(2, Level.INFO, "Interpretation procedure: " + assembly.getName()+" status: "+statusInterpretation.getMessage());
			PerformanceModel tasks = rfperf.getPerformanceModel();				
			if (rfperf.getNumInterpretationErrors() > 0) {
				//this.setAnalysisStatus(RF_ERROR);
				this.setAnalysisStatus(RF_WARNING); // Because it is just an error on the alternative and not in the original architecture
				//throw new ArchEException("Errors in the interpretation of ICM model "+wrapper.getICMModel());					
			}

			Double worstCaseAvg = null;							
			if (statusInterpretation.isOK()) {
					
				// Evaluation of the task model
				List<EvaluationProcedure> procedures = PerformanceRFPlugin.getDefault().getEvaluationProcedures();
				// I simply select any of the available procedures (hopefully, it works)
				// at 0: Simulation (Extend)
				// at 1: Closed form (SS latency)
				// at 2: MAST v1.3.6
				// at 3: SIM-MAST
					
				// Application of MAST
				EvaluationProcedure evalproc = (EvaluationProcedure)(procedures.get(2));				
				MastOptions options = new MastOptions();
				options.useMastResultsViewer = enableMASTViewer;
				options.templateArgs.setModel(tasks);
					
				// Note that if the filename for the ICM assembly is not set, the evaluation doesn't work
				String icmFilename = assembly.getSourceFile();
				printLog(2, Level.INFO, "ICM filename: "+icmFilename);	
				if (icmFilename == null) 
					printLog(2, Level.INFO, "ICM filename: null");	
				IStatus statusEvaluation = rfperf.doEvaluation(evalproc, tasks, icmFilename,new MyDefaultConsolePrinter(),options);
				printLog(2, Level.INFO, "Evaluation procedure: " + evalproc.getName()+" status: "+statusEvaluation.getMessage());				
				//printLog(2, Level.INFO, "Deleting temporal ICM file: " + icmFilename+" ... "+wrapper.clearICMAssembly());

				// If the system is NOT schedulable, the status is still OK but the
				// the MAST file shouldn't be read (because it's empty)
				if (statusEvaluation.isOK()) {
					// Access the MAST results from a .csv file
					IPath modelPath = new Path(icmFilename);
					String baseFilename = modelPath.removeFileExtension().lastSegment();
			        IPath mastPath = modelPath.removeLastSegments(1).append("performance").append(baseFilename);
					printLog(3, Level.INFO, "Generated MAST filename: "+mastPath.toString()+"_mast.csv");	
					//worstCaseAvg = CSVMastFileReader.getWorstCaseAvgParameter(mastPath.toString()+"_mast.csv");
					String k = wrapper.getServicePinNameFor(currentScenario);
					worstCaseAvg = CSVMastFileReader.getWorstCaseParameter(mastPath.toString()+"_mast.csv", k);				
					printLog(3, Level.INFO, "MAST result = " + worstCaseAvg +"  ( "+k+" )");
				}
				if (worstCaseAvg == null) { // Something failed with the analysis				
					response = INVALID_RESPONSE; // This is invalid number for computations
					printLog(2, Level.INFO, "Evaluation result = " + response +" (no improvements)"+" reference= "+measureVal);
					//this.setAnalysisStatus(RF_ERROR);
					this.setAnalysisStatus(RF_WARNING); // Because it is just an error on the alternative and not in the original architecture
				}
				else {
					response = worstCaseAvg;
					this.setAnalysisStatus(RF_OK);
					if (worstCaseAvg < measureVal) 
						printLog(2, Level.INFO, "Evaluation result = " + response +" (improved)"+" reference= "+measureVal);
				}										
					
			}
			else { // the responsibility structure is OK, but the interpretation/evaluation had errors
				printLog(3, Level.INFO, "Interpretation: Error(s) ocurred when interpreting ICM Model ... "+wrapper.getICMModel());			
				printLog(3, Level.INFO, "Evaluation result = " + response);		
				//this.setAnalysisStatus(RF_ERROR);
				this.setAnalysisStatus(RF_WARNING); // Because it is just an error on the alternative and not in the original architecture
			}				
		}					
		else { // The responsibility structure had problems, so analysis couldn't be performed
			printLog(3, Level.INFO, "Pre-Analysis: Error(s) ocurred when checking the responsibility structure ...");			
			printLog(3, Level.INFO, "Analysis result = " + response);		
			//this.setAnalysisStatus(RF_ERROR);			
			this.setAnalysisStatus(RF_WARNING); // Because it is just an error on the alternative and not in the original architecture
		}			

		ArchEEvaluationResult evaluationResult = new ArchEEvaluationResult();
		evaluationResult.setScenario(currentScenario.getFactId());
		
		// Set the difference with the value from the previous round of analysis (if any)
		ArchEAnalysisResult previousAnalysisResult = this.restoreAnalysisResult(currentScenario.getFactId());
		double diff = 0.0;
		if (previousAnalysisResult != null)  
			diff = response - previousAnalysisResult.getValue();
		evaluationResult.setChange(diff);
		
		if (response <= measureVal) // This estimation of utility is just a possible example  
			evaluationResult.setUtility(1.0);
		else
			evaluationResult.setUtility(0.0);		

		evaluationResult.setResult(response);
		return (evaluationResult);
	}

	/**
	 * It simply invokes the same analysis than before, returning no tactics
	 * 
	 * @param architecture current architecture model (assumed consistent)
	 * @param currentScenario scenario to be analyzed on the architecture
	 * @param outTactics the list of suggested tactics (output parameter)
	 * @throws ArchEException
	 */
	@Override
	public ArchEAnalysisResult analyzeAndSuggest(
			ArchEArchitecture architecture, ArchEScenario currentScenario,
			List<ArchETryTacticResult> outTactics) throws ArchEException {
		
		// It invokes the analyze() method above and returns no candidate tactics
		enableMASTViewer = true;
		ArchEEvaluationResult evaluationResult = this.analyze(architecture, currentScenario);
		enableMASTViewer = false;
		double worstCaseAvg = evaluationResult.getResult();
			
		if (this.getAnalysisStatus() == RF_WARNING)
			this.setAnalysisStatus(RF_ERROR); // Because it's the analysis of the current architecture
			
		double measureVal = 0.0; // The minimum worst case execution time for performance
		if (currentScenario.getMeasureValue() != null)
			measureVal = currentScenario.getMeasureValue();
		ArchEAnalysisResult analysisResult = new ArchEAnalysisResult();
		analysisResult.setValue(worstCaseAvg);
		analysisResult.setOwner(currentScenario.getFactId());
		analysisResult.setReasoningFramework(this.getID());
		if (this.isAnalysisValid() && (worstCaseAvg <= measureVal))
			analysisResult.setSatisfied(true);
		else
			analysisResult.setSatisfied(false);			
		analysisResult.setQuality(this.getQuality());

		// Set the value from the previous round of analysis (if any)
		ArchEAnalysisResult previousAnalysisResult = this.restoreAnalysisResult(currentScenario.getFactId());
		if (previousAnalysisResult != null) 
			analysisResult.setOldValue(previousAnalysisResult.getValue());
		else 
			analysisResult.setOldValue(0.0);

		return (analysisResult);			
	}

	@Override
	public boolean applySuggestedTactic(ArchEArchitecture architecture,
			ArchETryTacticResult suggestedTactic)
			throws ArchEException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean applyTacticByUserQuestion(
			ArchEArchitecture architecture, ArchEUserQuestion userQuestion)
			throws ArchEException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Every time it's invoked, we create new assemblies for the ICM view out 
	 * of the responsibilities and dependencies among them
	 * 
	 * @param view an empty ICM view
	 * @param responsibilityStructure the current responsibility structure
	 * @param requirementModel the current requirement model (not used here)
	 * @throws ArchEException
	 */
	@Override
	public boolean initializeView(ArchEView view,
			ArchEResponsibilityStructure responsibilityStructure,
			ArchERequirementModel requirementModel)
			throws ArchEException {
			
		boolean changed = false;
			
		ICMWrapper myICMView = (ICMWrapper)view;
		printLog(1, Level.INFO, "Initialize an architectural view");		
		
		List<ArchEScenario> scenarios = requirementModel.getScenariosByReasoningFramework(this);	

		// Create an ICM assembly instance, based on the responsibilities, and the sets of 
		// reactions and interactions among them
		String icmName = myICMView.getParent().getCurrentVersion().getVersionName();
		myICMView.setInputInformation(icmName,scenarios, responsibilityStructure);	
		changed = true;
			
		printLog(2, Level.INFO, "Creating temporal ICM file ... " + icmName+".icm");
		printLog(1, Level.INFO, "After building \"" + myICMView + "\"");			
			
		return (changed);
			
	}
	
	@Override
	public ArchEUserQuestion describeTactic(
			ArchETryTacticResult tactic)
			throws ArchEException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Every time it's invoked, we check that the parameters of responsibilities
	 * and relationships are appropriately configured
	 * 
	 * @param requirementModel the current requirement model
	 * @param responsibilityStructure the current responsibility structure
	 * @throws ArchEException
	 */		
	@Override
	public boolean checkRFDependencies(ArchERequirementModel requirementModel,
			ArchEResponsibilityStructure responsibilityStructure)
			throws ArchEException {

		boolean changed = false;
		ArchECoreResponsibilityStructure coreStructure = ((ArchECoreResponsibilityStructure)responsibilityStructure);

		// Check for missing parameters in the responsibilities
		double executionTime = DEFAULT_EXECUTION_TIME;
		ArchEResponsibility itemResp = null;
		for (Iterator<ArchEResponsibility> itResps = coreStructure.getResponsibilities().iterator(); itResps.hasNext();) {			
			itemResp = itResps.next();
				
			if (!itemResp.hasParameter(PARAMETER_EXECUTION_TIME)) {
				// In case no executionTime parameter exists, the responsibility 
				// is modified to include this parameter
				itemResp.defineParameter(PARAMETER_EXECUTION_TIME, executionTime);				
				changed = true;
			} 

			// The value executionTime must be within the range [min .. max]
			double value = itemResp.getDoubleParameter(PARAMETER_EXECUTION_TIME);
			if ((value > MAX_EXECUTION_TIME) || (value < MIN_EXECUTION_TIME)) {

				// Error reporting
				String message = "Parameter "+PARAMETER_EXECUTION_TIME+" must be in range ["
					+ MIN_EXECUTION_TIME +" - "
					+ MAX_EXECUTION_TIME + "] (value= "+ value + " !?)";
				ArchEAnalysisProblem problem = new ArchEAnalysisProblem(message, itemResp);
				this.setResponsibilityStructureStatus(RF_ERROR, EXECUTION_TIME_OUT_OF_LIMITS_ERROR, problem);
				
				this.printLog(1, Level.INFO, message);
				//throw new ArchEException(message);
			}
				
		} // End iteration over responsibilities

		// Check and delete orphan reactions among responsibilities
		String reactionTypeVO = ArchEResponsibilityReactionRelationVO.class.getName();
		ArchEResponsibilityReactionRelationVO respReactionVO = null;

		for (Iterator<ArchERelation> itDependencies = coreStructure.getRelations(reactionTypeVO).iterator(); itDependencies.hasNext();) {			
			respReactionVO = (ArchEResponsibilityReactionRelationVO)(itDependencies.next());			
			if ( !coreStructure.containResponsibility(respReactionVO.getParent()) 
					|| !coreStructure.containResponsibility(respReactionVO.getChild())) {
				// This is an orphan dependency that must be deleted from the responsibility structure
				coreStructure.deleteRelation(respReactionVO);
				changed = true;
			}				
		}

		// So far, we don't handle relation properties, so this consistency checking does nothing	
		
		return (changed);
	}		
	
//	public boolean mergeResponsibilityRelationships(
//			ArchEResponsibilityStructure newResponsibilityStructure,
//			ArchEResponsibilityStructure parentResponsibilityStructure) {
//
//		boolean changed = true;
//		ICMPerformanceResponsibilityStructure newStructure = ((ICMPerformanceResponsibilityStructure)newResponsibilityStructure);
//		ICMPerformanceResponsibilityStructure previousStructure = ((ICMPerformanceResponsibilityStructure)parentResponsibilityStructure);
//		
//		String relationTypeVO = ArchEResponsibilityReactionRelationVO.class.getName();
//		System.out.println("NEW STRUCTURE: "+newStructure.getRelations(relationTypeVO).size());
//		System.out.println("PARENT STRUCTURE: "+previousStructure.getRelations(relationTypeVO).size());
//		
//		return (changed);
//	}


	@Override
	public List<ArchEUserQuestion> describeOtherThanTactic(
			ArchERequirementModel requirementModel,
			ArchEArchitecture architecture) throws ArchEException {

		List<ArchEUserQuestion> warningQuestions = new ArrayList<ArchEUserQuestion>();
		ArchEUserQuestion question = null;

		if (!this.isAnalysisValid()) {

			Integer key = null;
			ArchEAnalysisProblem problem = null;
			String text = null;

			for (Enumeration<Integer> enumErrors = reportedProblems.keys(); enumErrors.hasMoreElements();) {
				key = enumErrors.nextElement();
				problem = reportedProblems.get(key);
				text = problem.getMesssage()+" ( error= "+key+" )";

				question = new ArchEUserQuestion();
				question.setQuestionID(FIX_ANALYSIS_PARAMETERS_WARNING);
				question.setParent(problem.getSource());				
				question.setAffectedFacts(null);
				
				List parameters = new ArrayList();
				parameters.add(text);
				question.setParameters(parameters);	
					
				warningQuestions.add(question);
			}

			// This code is now called by ReasoningFrameworkExecutor after finishing its cycle
//			this.clearAnalysisStatus();
//			this.clearResponsibilityStructureStatus();
		}
			
		return (warningQuestions);
			
	}

	@Override
	public void beginLearning() throws ArchEException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArchEUserQuestion> describeWhatLearned()
			throws ArchEException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void learnBy(int typeOfLearning, Object[] args)
			throws ArchEException {
		// TODO Auto-generated method stub
		
	}
				
    /**
     * Implementation of ConsolePrinter that allows printing messages
     * from the evaluation procedures directly to the console.
     * 
     */
    class MyDefaultConsolePrinter implements ConsolePrinter {
	    	
        /**
         * @see edu.cmu.sei.pacc.perf.ConsolePrinter#printToConsole(java.lang.String)
         */
//        public void printToConsole(String msg) {
//            final String msgToPrint = msg == null ? "" : msg;
//            Display.getDefault().asyncExec(new Runnable() {
//
//    	public void run() {
//    		MessageConsoleStream out = ArchEUIPlugin.getDefault().getConsole().newMessageStream();
//    		assert out != null : "ERROR - MessageConsoleStream is null when printing:\n" + msgToPrint;
//	        System.out.println("msgToPrint: " + msgToPrint);
//	        out.println(msgToPrint);
//	        try {
//	        	out.flush();
//	            out.close();
//	        } catch (IOException e) {
//	        	// nothing to do
//	        }
//	    }
//	    });
//	    }
	    	
        public void printToConsole(String msg) {
			printLog(2, Level.INFO, msg);			
        }       	
    	
    } // End MyDefaultConsolePrinter
}

	
