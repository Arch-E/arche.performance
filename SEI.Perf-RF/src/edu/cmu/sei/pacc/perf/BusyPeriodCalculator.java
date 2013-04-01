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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.Constant;
import edu.cmu.sei.pacc.perf.model.Distribution;
import edu.cmu.sei.pacc.perf.model.ModelFactory;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.perf.model.Uniform;
import edu.cmu.sei.pacc.perf.model.Unknown;

/**
 * Computes the busy period for a given task using the varying priorities
 * technique (HKL paper)
 * @author gmoreno
 */
public class BusyPeriodCalculator {

	private PerformanceModel model = null;
	
	/** copy of the model but with the tasks in canonical form */
	private PerformanceModel canonical = null;
	
	public BusyPeriodCalculator(PerformanceModel model) {
		this.model = model;
	}
	
	
	private int getMinimumPriority(List<Subtask> subtasks) {
		int minimum = Integer.MAX_VALUE;
		for (Iterator<Subtask> it = subtasks.iterator(); it.hasNext();) {
			int priority = it.next().getPriority();
			if (priority < minimum) {
				minimum = priority;
			}
		}
		return minimum;
	}
	
	private int getMaximumPriority(List<Subtask> subtasks) {
		int maximum = 0;
		for (Iterator<Subtask> it = subtasks.iterator(); it.hasNext();) {
			int priority = it.next().getPriority();
			if (priority > maximum) {
				maximum = priority;
			}
		}
		return maximum;
	}
	
	/**
	 * Compute H set
	 * @param i task index
	 * @param j action index
	 * @return
	 */
	private List<Task> computeH(int i, int j) {
		List<Task> H = new ArrayList<Task>();
		
		/* priority of the jth subtask in the reference task */
		int Pij = canonical.getTasks().get(i).getSubtasks().get(j).getPriority();
		
		for (int k = 0; k < model.getTasks().size(); k++) {
			if (k == i) continue;
			Task kTask = model.getTasks().get(k);
			if (getMinimumPriority(kTask.getSubtasks()) >= Pij) {
				H.add(kTask);
			}
		}
		return H;
	}
	
	/**
	 * Compute HL set
	 * @param i task index
	 * @param j action index
	 * @return
	 */
	private List<Task> computeHL(int i, int j) {
		List<Task> HL = new ArrayList<Task>();
		
		/* priority of the jth subtask in the reference task */
		int Pij = canonical.getTasks().get(i).getSubtasks().get(j).getPriority();
		
		for (int k = 0; k < model.getTasks().size(); k++) {
			if (k == i) continue;
			Task kTask = model.getTasks().get(k);
			if (kTask.getSubtasks().get(0).getPriority() >= Pij &&
					getMinimumPriority(kTask.getSubtasks()) < Pij) {
				HL.add(kTask);
			}
		}
		return HL;
	}
	
	/**
	 * Compute LH set
	 * @param i task index
	 * @param j action index
	 * @return
	 */
	private List<Task> computeLH(int i, int j) {
		List<Task> LH = new ArrayList<Task>();
		
		/* priority of the jth subtask in the reference task */
		int Pij = canonical.getTasks().get(i).getSubtasks().get(j).getPriority();
		
		for (int k = 0; k < model.getTasks().size(); k++) {
			if (k == i) continue;
			Task kTask = model.getTasks().get(k);
			for (int l = 1; l < kTask.getSubtasks().size(); l++) {
				if (kTask.getSubtasks().get(l - 1).getPriority() < Pij
						&& kTask.getSubtasks().get(l).getPriority() >= Pij) {
					LH.add(kTask);
				}
			}
		}
		return LH;
	}
	
	/**
	 * Returns the number of jobs in the busy period of a given task
	 * @param taskIndex
	 * @return number of jobs in the busy period
	 */
	public int computeBusyPeriodForTask(int taskIndex) {
		if (canonical == null) {
			if (!convertToCanonicalForm()) return 0;
		}
		
		PeriodicTask task = (PeriodicTask) canonical.getTasks().get(taskIndex);
		
		/* categorize event sequences */
		Vector<List<Task>> H = new Vector<List<Task>>();
		Vector<List<Task>> HL = new Vector<List<Task>>();
		Vector<List<Task>> LH = new Vector<List<Task>>();
		H.add(computeH(taskIndex, 0));
		HL.add(computeHL(taskIndex, 0));
		LH.add(computeLH(taskIndex, 0));
	
		double Bi = 0; // ignore blocking for now
		double S = Bi + TaskMath.max(LH.elementAt(0), new HSeg(task, 0))
			+ TaskMath.sum(HL.get(0), new FSeg(task, 0));
		double a = S + task.getComputedExecutionMean() + TaskMath.sum(H.get(0), new C());
		double lastA;
		do {
			lastA = a;
			a = S + Math.ceil(lastA / task.getPeriod()) * task.getComputedExecutionMean()
				+ TaskMath.sum(H.get(0), new TaskExecution(lastA));
			
		} while (a != lastA);
		
		int N = (int) Math.ceil(a / task.getPeriod());
		
		
		return N;
	}
	
	private boolean convertToCanonicalForm() {
		canonical = null; // not set until successfully transformed
		PerformanceModel canonicalModel = ModelFactory.eINSTANCE.createPerformanceModel();
		canonicalModel.setName(model.getName());
		for (Iterator<Task> it = model.getTasks().iterator(); it.hasNext();) {
			Task task = it.next();
			
			/* we map all to equivalent periodic tasks */
			PeriodicTask canonicalTask = ModelFactory.eINSTANCE.createPeriodicTask();
			canonicalTask.setTaskId(task.getTaskId());
			canonicalTask.setName(task.getName());
			canonicalTask.setDeadline(task.getDeadline());
			double periodicEquivalentPeriod = 0; // default to unbounded
			if (task instanceof PeriodicTask) {
				periodicEquivalentPeriod = ((PeriodicTask) task).getPeriod();
			} else if (task instanceof AperiodicTask) {
				Distribution interarrival = ((AperiodicTask) task).getInterarrivalDistribution();
				if (interarrival instanceof Constant) {
					periodicEquivalentPeriod = ((Constant) interarrival).getValue();
				} else if (interarrival instanceof Uniform) {
					periodicEquivalentPeriod = ((Uniform) interarrival).getMin();
				} else if (interarrival instanceof Unknown) {
					periodicEquivalentPeriod = ((Unknown) interarrival).getMin();
				}				
			}
			if (periodicEquivalentPeriod == 0) {
				return false;
			}
			canonicalTask.setPeriod(periodicEquivalentPeriod);
			
			for (Iterator<Subtask> subtasks = task.getSubtasks().iterator(); subtasks.hasNext();) {
				Subtask subtask = subtasks.next();
				Subtask subtaskCopy = ModelFactory.eINSTANCE.createSubtask();
				subtaskCopy.setName(subtask.getName());
				subtaskCopy.setPriority(subtask.getPriority());
				
				/* get WCET
				 * There may be a disconnect from the WCET selected for analysis
				 * because the analysis wizard allows forcing the WCET to be the 
				 * average. This "forcing" happens when the performance model is
				 * translated to MAST...it should happen when creating the performance
				 * model so that all other analyzes are consistent
				 */
				Distribution execTime = subtask.getExecTimeDistribution();
				Constant wcet = ModelFactory.eINSTANCE.createConstant();
				if (execTime instanceof Constant) {
					wcet.setValue(((Constant) execTime).getValue());
				} else if (execTime instanceof Uniform) {
					wcet.setValue(((Uniform) execTime).getMax());
				} else if (execTime instanceof Unknown) {
					wcet.setValue(((Unknown) execTime).getMax());
				} else {
					return false;
				}
				subtaskCopy.setExecTimeDistribution(wcet);
				canonicalTask.getSubtasks().add(subtaskCopy);
			}
			
			/* transform subtasks priorities to canonical form */
			EList<Subtask> subtasks = canonicalTask.getSubtasks();
			for (int j = subtasks.size() - 1; j > 0; j--) {
				Subtask subtask = subtasks.get(j);
				Subtask preceedingSubtask = subtasks.get(j - 1);
				if (preceedingSubtask.getPriority() > subtask.getPriority()) {
					preceedingSubtask.setPriority(subtask.getPriority());
				}
			}
		
			canonicalModel.getTasks().add(canonicalTask);
		}
		
		canonical = canonicalModel;
		
    	try {
    		ResourceSet resourceSet = new ResourceSetImpl();
	        URI fileURI = URI.createFileURI(new Path(model.getSourceFile()).removeFileExtension().addFileExtension("canonical").addFileExtension("model").toOSString());
	        Resource xmiFile = resourceSet.createResource(fileURI); 
	        List contents = xmiFile.getContents();
	        contents.add(canonical);
	        xmiFile.save(null);
        } catch (IOException e) {
			return false;
        }
		
		return true;
	}

	interface TaskOperation {
		public double compute(Task task);
	}

	static class TaskMath {
		static public double max(List<Task> tasks, TaskOperation operation) {
			double max = Double.MIN_VALUE;
			for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
				double newValue = operation.compute(it.next());
				if (newValue > max) max = newValue;
			}
			
			return max;
		}

		static public double sum(List<Task> tasks, TaskOperation operation) {
			double value = 0;
			for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
				value += operation.compute(it.next());
			}
			
			return value;
		}
	}
	
	static class FSeg implements TaskOperation {
		private int priority;
		
		public FSeg(Task referenceTask, int subtaskIndex) {
			this.priority = referenceTask.getSubtasks().get(subtaskIndex).getPriority();
		}
		
		public double compute(Task kTask) {
			List<Subtask> kSubtasks = kTask.getSubtasks();
			double execTime = 0;
			for (Iterator<Subtask> it = kSubtasks.iterator(); it.hasNext();) {
				Subtask subtask = it.next();
				if (subtask.getPriority() <= priority) break; // not in the segment
				execTime += subtask.getExecTimeDistribution().getComputedMean();
			}
			return execTime;
		}
	}
	
	static class HSeg implements TaskOperation {
		private int priority;
		
		public HSeg(Task referenceTask, int subtaskIndex) {
			this.priority = referenceTask.getSubtasks().get(subtaskIndex).getPriority();
		}
		
		public double compute(Task kTask) {
			List<Subtask> kSubtasks = kTask.getSubtasks();
			double execTime = 0;
			for (int j = 1; j < kSubtasks.size(); j++) {
				Subtask subtask = kSubtasks.get(j);
				if (subtask.getPriority() <= priority) break; // not in the segment
				execTime += subtask.getExecTimeDistribution().getComputedMean();
			}
			return execTime;
		}
	}
	
	static class C implements TaskOperation {
		public double compute(Task kTask) {
			return kTask.getComputedExecutionMean();
		}
	}

	static class TaskExecution implements TaskOperation {
		private double a;
		
		public TaskExecution(double a) {
			this.a = a;
		}
		
		public double compute(Task kTask) {
			return Math.ceil(a / ((PeriodicTask) kTask).getPeriod()) * kTask.getComputedExecutionMean();
		}
	}
	
}
