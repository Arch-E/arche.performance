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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import edu.cmu.sei.pacc.perf.eval.LambdaABAUtils;
import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.model.Task;

public class LPICalculator {
	
	/**
	 * Generates the Longest Path Information (LPI) file for a given performance
	 * model. The LPI is necessary so that the assembly measurement infrastructure
	 * knows how to measure a task, specially when the assembly is purely periodic
	 * and has a hyperperiod (HP).
	 * 
	 * The LPI file is a text file with one line per task. The format of each line is
	 * 	taskId numberOfJobsInHp numberOfJobsToSkip firstComponent firstPin
	 * 	lastComponent lastPin timesToSkipLastEvent
	 * 
	 * The fields numberOfJobsInHp numberOfJobsToSkip are meaningful only if the assembly
	 * is purely periodic. If not, the value of these fields is 0.
	 * 
	 * @param model performace model
	 * @param lpiPath path of the LPI file
	 * @return true if successfuly generated the LPI file
	 */
	static public boolean createLPI(PerformanceModel model, String lpiPath) {
		try {
			PrintWriter lpi = new PrintWriter(new FileWriter(lpiPath));
			
			/* determine if the assembly is purely periodic */
	        boolean isPeriodic = true;
	        for (Iterator it = model.getTasks().iterator(); it.hasNext();) {
	            if (it.next() instanceof AperiodicTask) {
	            	isPeriodic = false;
	                break;
	            }
	        }
	
	        /* compute hyperperiod if periodic */
	    	double hp =0;
	    	double hpStart = 0;
	        if (isPeriodic) {
	        	hp = LambdaABAUtils.computeHyperperiod(model.getTasks());
	        	hpStart = hp - LambdaABAUtils.minPeriod(model.getTasks());
	        }
	
	        for (Iterator it = model.getTasks().iterator(); it.hasNext();) {
	        	Task task = (Task) it.next();
	        	int jobsToSkip = 0;
	        	int jobsInHp = 0;
	        	
	        	/* if assembly is periodic we can compute how many to skip and measure */
	        	if (isPeriodic) {
	        		PeriodicTask periodicTask = (PeriodicTask) task;
	
	        		/* compute next arrival after hpStart */
	        		double firstStableArrival = 
	        			Math.ceil((hpStart - periodicTask.getOffset()) / periodicTask.getPeriod())
	        			* periodicTask.getPeriod() + periodicTask.getOffset();
	        		
	        		jobsToSkip = (int) ((firstStableArrival - periodicTask.getOffset()) 
	        			/ periodicTask.getPeriod());
	        		jobsInHp = (int) (hp / periodicTask.getPeriod());
	        	}
	        		
	    		/* Compute how many times the last event needs to be skipped.
	    		 * The same event may appear several times in a task once it
	    		 * has been transformed to a linear sequence. If the last event
	    		 * in the task appears more than once, we need to tell the 
	    		 * measurement infrastructure how many times to skip it before
	    		 * seeing the last one
	    		 */
	    		int skipEventCount = -1; // because the last one must not be skipped
	    		Subtask lastSubtask = (Subtask) task.getSubtasks().get(task.getSubtasks().size() - 1);
	    		String lastSubtaskName = lastSubtask.getName();
	    		for (Iterator it2 = task.getSubtasks().iterator(); it2.hasNext();) {
	    			Subtask subtask = (Subtask) it2.next();
	    			if (subtask.getName().equals(lastSubtaskName)) {
	    				skipEventCount++;
	    			}
	    		}

	    		String firstComponentName = task.getName().substring(0, task.getName().indexOf('.'));
	    		String lastComponentName = lastSubtaskName.substring(0, lastSubtaskName.indexOf('.'));
	    		
	    		/* the pinId for the first event is alwasy -1 because source services emit -1 as
	    		 * pinId.
	    		 */
	    		lpi.format("%d %d %d %s %d %s %d %d%n", task.getTaskId(), jobsInHp, jobsToSkip,
	    				firstComponentName, -1, lastComponentName, lastSubtask.getPinId(), skipEventCount);
	        }
	        lpi.close();
		} catch (IOException e) {
			return false;
		}
        
		return true;
	}

}
