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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import edu.cmu.sei.pacc.perf.icm.IcmComponent;
import edu.cmu.sei.pacc.perf.model.Mutex;
import edu.cmu.sei.pacc.perf.icm.IcmFactory;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.icm.SSComponent;
import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.icm.ElementInstance;
import edu.cmu.sei.pacc.perf.icm.PinInstance;
import edu.cmu.sei.pacc.perf.icm.IcmService;
import edu.cmu.sei.pacc.perf.icm.Scenario;
import edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm;
import edu.cmu.sei.pacc.perf.icm.SinkPinInstance;
import edu.cmu.sei.pacc.perf.icm.SinkPinMode;
import edu.cmu.sei.pacc.perf.icm.SourcePinInstance;
import edu.cmu.sei.pacc.perf.icm.SourcePinMode;

import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.Constant;
import edu.cmu.sei.pacc.perf.model.Distribution;
import edu.cmu.sei.pacc.perf.model.Exponential;
import edu.cmu.sei.pacc.perf.model.Normal;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.SSTask;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.ModelFactory;
import edu.cmu.sei.pacc.perf.model.Uniform;
import edu.cmu.sei.pacc.perf.model.Unknown;
import edu.cmu.sei.pacc.perf.util.Error;

/**
 * Interpretation for the performance RF. Interpretation is performed in 3 steps:
 * <ol>
 * <li>Visitor code visits the entire CCL AST, building an intermediate constructive model. The
 * intermediate model is an abstraction of the CCL elements that are relevant to the performance RF.
 * It consists of the components, clocks and respective pins with reactions and interactions, as
 * well as performance specific annotations.
 * <li>The intermediate model is transformed into the performance model, which consists of tasks
 * and subtasks.
 * <li>The performance model is persisted to an XML file.
 * </ol>
 * This class corresponds to steps 2 and 3.
 * 
 * @author Paulo Merson
 */
public class JavaInterpretation implements Interpretation {

    /** list of Strings containing error messages */
    private List<Error>        _errors;

    /** The performance model (result of interpretation) */
    private PerformanceModel model;
    
    /**
     * abstraction of the constructive assembly that contains just the
     * information that is relevant to the performance RF
     */
    private AssemblyInstance assemblyInstance;

    /** EMF resource set used to persist the performance model as an XMI file */
    private ResourceSet      resourceSet;
    
    /** scenario */
    private Integer scenario;
    
    /** next bypass number to use in a downsampling sink */
    private int nextBypass;
    
    /**
     * Source file where problem markers will be put.
     * Generally, this is the main CCL file from which the model was generated,
     * but it could also be the ICM if the interpretation was started by the 
     * user from an ICM
     */
    private IFile sourceFile;

    /**
     * Constructor. Just instantiates an empty list for errors.
     * 
     * @note It is protected to force the use of the factory.
     */
    protected JavaInterpretation() {
        resourceSet = new ResourceSetImpl();
        _errors = new ArrayList();
    }

    /**
	 * @see edu.cmu.sei.pacc.perf.Interpretation#doInterpretation(edu.cmu.sei.pacc.perf.icm.AssemblyInstance, java.lang.Integer)
	 */
    public PerformanceModel doInterpretation(AssemblyInstance assemblyInstance, Integer scenario) {
        this.assemblyInstance = assemblyInstance;
        this.scenario = scenario;

        if (assemblyInstance == null) return null;
        model = ModelFactory.eINSTANCE.createPerformanceModel();
        sourceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
				Path.fromPortableString(assemblyInstance.getSourceFile()));
        model.setSourceFile(assemblyInstance.getSourceFile());

        nextBypass = 1;
        if (checkPriorityCeiling() & checkSSUsage()) {
            mapClocksToTasks();
            computeOffsets();
        }
        //dumpModel(tasks); _errors.add("model dumped");
       return model;
    }
    
    protected void dumpModel(List tasks) {
    	for (Iterator it = tasks.iterator(); it.hasNext(); ) {
    		Task task = (Task) it.next();
    		dumpSubtasks("Task " + task.getName(), task.getSubtasks());
    	}
    }
    
    protected void dumpSubtasks(String heading, List subtasks) {
		System.out.println(heading + ":");
		for (Iterator it2 = subtasks.iterator(); it2.hasNext(); ) {
			Subtask subtask = (Subtask) it2.next();
			Distribution execTime = subtask.getExecTimeDistribution();
			if (!(execTime instanceof Constant) 
					|| ((Constant) execTime).getValue() > 0) {
				System.out.println("  " + subtask.getName() 
						+ " (" + subtask.getPriority() + ")");
			}
		}
    	
    }
    
   

    /**
     * PeriodicTask instances have a property that is "offset". It is calculated as the clock period
     * minus the min period among all periodics. This offset corresponds to the offset of a task, which
     * is the arrival time of the task's first job. One could imagine that all tasks are ready to
     * run at time zero and hence the offset could be zero for all tasks, but that's not exactly
     * what happens: in the Pin implementation, all tasks are indeed started at the same time, but
     * due to the way timers work, the first clock tick happens after 1 period has elapsed, not at
     * the beginning of the first period.
     * This method computes the right offset for each periodic task to match the Pin runtime.
     * HOWEVER, if a non-zero offset is specified for at least one periodic task, then this
     * method will not change the offsets.
     */
    private void computeOffsets() {
    	boolean explicitOffset = false;
    	
		// find the smallest period
		double minPeriod = Double.MAX_VALUE;
		Set periodicTasks = new HashSet();
		for (Iterator it = model.getTasks().iterator(); it.hasNext();) {
		    Task task = (Task) it.next();
		    if (task instanceof PeriodicTask) {
		    	PeriodicTask periodic = (PeriodicTask) task;
		    	if (periodic.getOffset() != 0) {
		    		explicitOffset = true;
		    		break;
		    	}
		    	double period = periodic.getPeriod();
                if (period > 0 && period < minPeriod) {
                    minPeriod = period;
                }
                periodicTasks.add(periodic);
		    }
		}

		if (!explicitOffset) { // set the offsets
			for (Iterator it = periodicTasks.iterator(); it.hasNext();) {
				PeriodicTask periodic = (PeriodicTask) it.next();
				periodic.setOffset(periodic.getPeriod() - minPeriod);
			}
		}
    }

    /**
     * Verifies conformance to the priority ceiling protocol (PCP). This function checks that the
     * assembly is compliant with the priority ceiling requirement. For all sinks, it looks at all
     * the sources connected to it and check that the priority of the sink is greater than or equal
     * to the priority of the sources. It's checked only if there's more than one calling source. It
     * only works for mutexed synchronous pins, assuming they run at their own priority.
     * 
     * @return true if the assembly conforms to priority ceiling.
     */
    boolean checkPriorityCeiling() {
        boolean ok = true;
        
        // check each component
        for (Iterator it = assemblyInstance.getElements().iterator(); it.hasNext();) {
            ElementInstance component = (ElementInstance) it.next();

                // check each sink
                for (Iterator it2 = component.getPins().iterator(); it2.hasNext();) {
                    PinInstance pin = (PinInstance) it2.next();
                    if (!(pin instanceof SinkPinInstance)) continue;
                    SinkPinInstance sink = (SinkPinInstance) pin;

                    // get the highest priority of the sources that call this sink
                    // has to check only if there's more than one calling source
                    // and the sink is mutexed
                    if (sink.getLinkSources().size() > 1 && sink.getMode() == SinkPinMode.MUTEX_LITERAL) {
                        int maxSourcePriority = -1;
                        for (Iterator it3 = sink.getLinkSources().iterator(); it3.hasNext();) {
                            SourcePinInstance source = (SourcePinInstance) it3.next();
                            int sourcePriority = -1;
                            
                            /* service sources have priority */
                            if (source.getElementInstance() instanceof IcmService) {
                                sourcePriority = ((IcmService) source.getElementInstance())
                                    .getPriority();
                                if (sourcePriority > maxSourcePriority) {
                                    maxSourcePriority = sourcePriority;
                                }
                            } else { // need to find priority from the driving sinks
                                for (Iterator it4 = source.getReactSinks().iterator(); it4.hasNext();) {
                                    SinkPinInstance reactSink = (SinkPinInstance) it4.next();
                                    sourcePriority = reactSink.getPriority();
                                    if (sourcePriority > maxSourcePriority) {
                                        maxSourcePriority = sourcePriority;
                                    }
                                }
                            }
                        }
                        if (maxSourcePriority > sink.getPriority()) {
                            _errors
                                    .add(new Error("Assembly does not comply to the priority ceiling protocol. "
                                            + "Sink pin "
                                            + sink.getElementInstance().getName()
                                            + "."
                                            + sink.getName()
                                            + " has priority "
                                            + sink.getPriority()
                                            + " and is called by source pin with priority "
                                            + maxSourcePriority + ".",
                                            IMarker.SEVERITY_ERROR, 0, sourceFile));
                            ok = false;
                        }
                    }
                }
        }
        return ok;
    }

    
    /**
     * Checks if an element has periodic nature.
     * 
     * A service has periodic nature if it is source of periodic events.
     * A component has periodic nature if it is activated periodically. 
     * It is considered to have periodic nature even if it is directly or indirectly connected
     * to both periodic and aperiodic sources.
     * 
     * @param element element to be checked
     * @return true if the element has periodic nature
     */
    private boolean hasPeriodicNature(ElementInstance element) {
        if (element instanceof IcmService) {
            IcmService service = (IcmService) element;
            for (Iterator it = service.getPins().iterator(); it.hasNext();) {
                PinInstance pin = (PinInstance) it.next();
                if (pin instanceof ServiceSourcePinIcm) {
                    if ( ((ServiceSourcePinIcm) pin).getEventDistribution() instanceof Constant) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            IcmComponent component = (IcmComponent) element;
            for (Iterator it = component.getPins().iterator(); it.hasNext();) {
                PinInstance pin = (PinInstance) it.next();
                if (pin instanceof SinkPinInstance) {
                    SinkPinInstance sink = (SinkPinInstance) pin;
                    for (Iterator it2 = sink.getLinkSources().iterator(); it2.hasNext(); ) {
                        SourcePinInstance source = (SourcePinInstance) it2.next();
                        if (hasPeriodicNature(source.getElementInstance())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
    
    
    /**
     * Checks assumptions about SS components usage.
     * 
     * Checks that no SS component is connected to a periodic source.
     * Checks that no SS component has connected sources.
     * 
     * @return true if checks passed
     */
    protected boolean checkSSUsage() {
        boolean ok = true;
        for (Iterator it = assemblyInstance.getElements().iterator(); it.hasNext(); ) {
            ElementInstance element = (ElementInstance) it.next();
            if (!(element instanceof SSComponent)) continue;
            
            /* check that is is not periodic */
            if (hasPeriodicNature(element)) {
                ok = false;
                _errors
                .add(new Error("Sporadic Server component " + element.getName() 
                     + " is connected (maybe indirectly) to a periodic source.",
                     IMarker.SEVERITY_ERROR, 0, sourceFile));
            }
            
            /* check that it has no connected sources */
            for (Iterator it2 = element.getPins().iterator(); it2.hasNext();) {
                PinInstance pin = (PinInstance) it2.next();
                if (pin instanceof SourcePinInstance) {
                    if (((SourcePinInstance) pin).getSinks().size() > 0) {
                        ok = false;
                        _errors
                        .add(new Error("Sporadic Server component " + element.getName() 
                             + " cannot have connected sources.",
                             IMarker.SEVERITY_ERROR, 0, sourceFile));
                    }
                }
                
            }
        }
        return ok;
    }
    

    /**
     * Each SimpleClock or DistClock instance in the assembly (constructive world) becomes a task
     * (analytic world). This method does that translation.
     */
    private void mapClocksToTasks() {
        Task newTask = null;
        int taskId = 0;
        boolean hasAperiodic = false;

        for (Iterator it = assemblyInstance.getElements().iterator(); it.hasNext();) {
            ElementInstance element = (ElementInstance) it.next();
            
            // create a task for each source of a clock */
            if (element instanceof IcmService) {
            	IcmService service = (IcmService) element;

                for (Iterator it2 = service.getPins().iterator(); it2.hasNext();) {
                	PinInstance pin = (PinInstance) it2.next();
                	if (!(pin instanceof ServiceSourcePinIcm)) continue;
                	Subtask serviceSubtask = null;
                	ServiceSourcePinIcm serviceSource = 
                		(ServiceSourcePinIcm) pin;

                	Distribution eventDistribution = getModelDistribution(
                						serviceSource.getEventDistribution());
	                if (eventDistribution instanceof Constant) {
	 	               
 	                    // create periodic task
 	                    newTask = ModelFactory.eINSTANCE.createPeriodicTask();
 	                    
 	                    ((PeriodicTask) newTask).setPeriod(
 	                    		((Constant) eventDistribution).getValue());
 	                    
 	                    /* offset is not carried to the Model distribution, so we need to get
 	                     * it from the ICM distribution
 	                     */
 	                    edu.cmu.sei.pacc.perf.icm.Constant icmConstant = 
 	                    	(edu.cmu.sei.pacc.perf.icm.Constant) serviceSource.getEventDistribution();
 	                   ((PeriodicTask) newTask).setOffset(icmConstant.getOffset());
 	                } else { // it's aperiodic, and may be SS
                        
                        /*
                         * If the component connected to the serviceSource is an SSComponent,
                         * then, it is an SSTask and we have to look at the properties of the
                         * component to set the attributes of the SSTask.
                         */
                        SinkPinInstance sink = (SinkPinInstance) serviceSource.getSinks().get(0);
                        if (sink.getElementInstance() instanceof SSComponent) {
                            SSComponent ss = (SSComponent) sink.getElementInstance();
                            SSTask ssTask = ModelFactory.eINSTANCE.createSSTask();
                            ssTask.setBudget(ss.getBudget());
                            ssTask.setReplenishmentPeriod(ss.getReplenishmentPeriod());
                            ssTask.setBackgroundPriority(ss.getBackgroundPriority());
                            newTask = ssTask;
                        } else {
                            newTask = ModelFactory.eINSTANCE.createAperiodicTask();
                        }
 	                    
 	                    ((AperiodicTask) newTask).setInterarrivalDistribution(eventDistribution);
 	                    hasAperiodic = true;
 	                }

                    /* set common properties */
                    newTask.setTaskId(++taskId);
                    newTask.setName(service.getName() + "." + serviceSource.getName());
                    if (serviceSource.eIsSet(IcmPackage.eINSTANCE.getServiceSourcePinIcm_Deadline())) {
                    	newTask.setDeadline(serviceSource.getDeadline());
                    }
                    
                    // if the serviceSource has exec time, we add a subtask to model it
                    Distribution execTime = getModelDistribution( 
                                serviceSource.getExecTimeDistribution());
                    if (execTime != null) {
                        
                        /*
                         * create a subtask for the service to be added later
                         * only if there are other subtasks. The assumption is that if
                         * the service source pin was masked by a scenario, the intention 
                         * was to mask the whole service.
                         */
                        serviceSubtask = ModelFactory.eINSTANCE.createSubtask();
                        serviceSubtask.setActivationSynchronous(true);
                        serviceSubtask.setExecTimeDistribution(execTime);
                        serviceSubtask.setName(newTask.getName());
                        serviceSubtask.setPriority(service.getPriority());
                        
                        /* for source services, we use -1 as pin index because the measurement
                         * events don't have an associated sink pin and they have to emit -1
                         * We put -1 here so that the match.
                         */
                        serviceSubtask.setPinId(-1); 
                    }
                        
                    // Map all subtasks that are part of this task, i.e., all components called
                    // directly or indirectly from this clock. (CLOCK_PRIORITY is the max priority)
	            	List subtasks = newTask.getSubtasks();
	            	mapSource(serviceSource, service.getPriority()  , subtasks, null, 0);

                    // At this point, all subtasks corresponding to the activation of this clock
                    // source pin are in subtasks. Now we copy these subtasks into newTask.
                    //               float taskExecTime = 0.;
                    //               for (Task::SubtaskVector::iterator subtask = subtasks.begin();
                    //                    subtask != subtasks.end();
                    //                    subtask++) {
                    //                   newTask->addSubtask(subtask->Name, subtask->Execution.getDouble(),
                    // subtask->Priority, subtask->StdDev);
                    //                   taskExecTime += subtask->Execution.getDouble();
                    //               }
	            	
	            	/* if the task has no subtasks, then it does nothing. We ignore it. */
	            	if (!subtasks.isEmpty()) {
	            		if (serviceSubtask != null) {
 	                    	subtasks.add(0, serviceSubtask);            			
	            		}
                        
                        /* remove all constant zero-exec-time subtasks unless they are part of
                         * mutexes or downsamplings
                         */
                        for (int i = subtasks.size() - 1; i >= 0; i--) {
                            Subtask subtask = (Subtask) subtasks.get(i);
                            execTime = subtask.getExecTimeDistribution();
                            if (execTime instanceof Constant && execTime.getComputedMean() == 0
                            		&& subtask.getMutexes().isEmpty() 
                            		&& subtask.getDownsamplingFactor() == 0) {
                                subtasks.remove(i);
                            }
                        }

                        model.getTasks().add(newTask);
	            	}
	            	
//	            	System.out.print(newTask.getTaskName() + ": ");
//	            	for (Iterator it3 = newTask.getSubtasks().iterator(); it3.hasNext();) {
//	            		Subtask s = (Subtask) it3.next();
//	            		System.out.print(((s.isActivationSynchronous()) ? ">" : ">>")
//	            				+ s.getName() + "(" + s.getPriority() + ")");
//	            	}
//	            	System.out.println();
//	            	_errors.add("aborting");
                }
            }
        }
    }
   
   /**
    * Determines if a pin belongs has to be included in the interpretation
    * 
    * If no scenario was specified for the interpretation, all pins are included.
    * If a scenario was specified, then a pin is included if it is not associated
    * with any specific scenario or if one of the specified scenarios matches the
    * scenario of the interpretation.
    * This implementation assumes bitmasked scenarios
    * 
    * @param pin
    * @return true if the pin is part of the scenario
    */
   private boolean isPartOfScenario(PinInstance pin) {
   		// original implementation
		//		return (scenario == null || pin.getScenarios().isEmpty()
		//    		|| pin.getScenarios().contains(scenario);
   	
   		if (scenario == null || pin.getScenarios().isEmpty()) {
   			return true;
   		}
   		
   		for (Iterator it = pin.getScenarios().iterator(); it.hasNext(); ) {
   			Scenario pinScenario = (Scenario) it.next();
   			if ((pinScenario.getNumber() & scenario.intValue()) != 0) return true;
   		}
   		
   		return false;
   }

    /**
     * Given a specific source pin, this function adds subtasks to the sequence of subtasks being
     * created. Subtasks added correspond to the components called by the given source pin.
     * Moreover, subtasks are added in the order that they are scheduled to run.
     * 
     * @param sourcePin source pin object that will be mapped
     * @param priority priority of the component that contains that source pin (i.e. the caller)
     * @param Sequence vector to be constructed that will hold the sequence of subtasks within a
     *            task that initiates in a clock component.
     * @param anchorSt if not null, this is the 2nd (or 3rd,...) source in the same component and we
     *            have to account for preemptible time after the previous source returns.
     */
    private void mapSource(SourcePinInstance sourcePin, int priority, List sequence,
            Subtask anchorSt, int bypass) {

        List subSequence = null;
        Subtask anchorAsync = null;
        String sinkIdBefore = null;
        boolean multipleSinks = false;
        boolean usedAnchorSt = false;
        
        // if this source pin is not in the scenario being analyzed, then ignore it
        // if a pin does not have a scenario assigned, then it belongs to all scenarios
        if (!isPartOfScenario(sourcePin)) {
        	return;
        }
        
        // Iterate through all sink pins connected to the given source pin in ascending order.
        for (Iterator it = sourcePin.getSinks().iterator(); it != null && it.hasNext();) {
            // A subtask corresponds to the activation of a sink pin.
            // The name of the subtask is <name of component>.<id of called sink pin>
            // Execution time and stddev for the subtasks are the avg exec. time and stddev
            // for the sink pin, which were read from the ccl spec of the component
            SinkPinInstance connectedSink = (SinkPinInstance) it.next();
            
            // make sure the sink has an execution time (it's an annotation in the design file)
            if (connectedSink.getExecTimeDistribution() == null) {
                String msg = "Execution time is not specified for sink pin: " + 
                    connectedSink.getElementInstance() + "." + connectedSink.getName();
                _errors.add(new Error(msg, IMarker.SEVERITY_ERROR, 0, sourceFile));
                throw new IllegalStateException(msg);
            }
            
            // if this sink pin is not in the scenario being analyzed, then ignore it
            // if a pin does not have a scenario assigned, then it belongs to all scenarios
            if (!isPartOfScenario(connectedSink)) {
            	continue;
            }

            Subtask st = ModelFactory.eINSTANCE.createSubtask();
            st.setName(connectedSink.getElementInstance().getName() + "."
                    + connectedSink.getName());
            st.setPinId(connectedSink.getId());
            Distribution execTime = getModelDistribution(
            					connectedSink.getExecTimeDistribution());
            execTime.add(assemblyInstance.getConnectionOverhead());
            st.setExecTimeDistribution(execTime);
            st.setRetAnchorUsed(false);
            
            /* add mutexes */
            for (Iterator mutexIt = connectedSink.getMutexes().iterator();
            		mutexIt.hasNext();) {
            	edu.cmu.sei.pacc.perf.icm.Mutex usedMutex = (edu.cmu.sei.pacc.perf.icm.Mutex) mutexIt.next();
            	Mutex theMutex = null;
            	for (Iterator perfMutexIt = model.getMutexes().iterator(); perfMutexIt.hasNext();) {
            		Mutex perfMutex = (Mutex) perfMutexIt.next();
            		if (perfMutex.getName().equals(usedMutex.getName())) {
            			theMutex = perfMutex;
            			break;
            		}
            	}
            	if (theMutex == null) {
            		theMutex = ModelFactory.eINSTANCE.createMutex();
            		theMutex.setName(usedMutex.getName());
            		model.getMutexes().add(theMutex);
            	}
            	st.getMutexes().add(theMutex);
            }
            
            
            /*
             * for now, a subtask can be affected by only one downsampling sink
             * however, there can be several downsampling sinks in an assembly as long
             * as they do not overlap
             * For now, if a subtask has downsamplingFactor > 0, it's bypass attribute
             * is the bypass number created by this downsampling sink, but the subtask itself
             * is not affected by that bypass
             */
            st.setDownsamplingFactor(connectedSink.getDownsamplingFactor());
            if (st.getDownsamplingFactor() > 0) {
            	bypass = nextBypass++;
            }
            st.setBypass(bypass);

            if (multipleSinks) {
                // We have asynchronous source connected to more than one sink. We need an anchor
                // subtask to represent the fraction of time after the activation of Nth sink and
                // the N-1th sink.
                anchorAsync = ModelFactory.eINSTANCE.createSubtask();
                
                /*
                 * The anchor name has the instance between | | for easy parsing.
                 * This will be replaced by a property with the originating reaction later
                 */
                anchorAsync.setName("Anchor|" 
                		+ sourcePin.getElementInstance().getName() 
						+ "|" + sinkIdBefore + ">"
                        + sourcePin.getElementInstance().getName() + "."
                        + sourcePin.getName() + ">" + st.getName());
                Constant zero = ModelFactory.eINSTANCE.createConstant();
                zero.setValue(0);
                anchorAsync.setExecTimeDistribution(zero);
                anchorAsync.setPriority(priority); // effective priority
                anchorAsync.setActivationSynchronous(true);
                anchorAsync.setRetAnchorUsed(false);
            }

            //
            // Determine subtask's priority based on pin mode of sink pin
            //
            // Reentrant synchronous: runs at the same priority and in the same thread
            //                        of the caller
            //
            // Mutexed synchronous : runs at its own priority. Priority ceiling should be
            //                        honered by construction.
            //
            // Asynchronous : runs at its own priority
            //
            // Note: RTMAX is the max priority for subtasks and is only below clock priority
            //
            if (connectedSink.getMode() == SinkPinMode.REENTER_LITERAL) {
                st.setPriority(priority); // caller's priority
            } else if (connectedSink.getMode() == SinkPinMode.MUTEX_LITERAL) {
                st.setPriority(connectedSink.getPriority());
                // TODO: distinguish between threaded mutex and non-threaded mutex
            } else {
                // ASYNCHRONOUS
                st.setPriority(connectedSink.getPriority());
            }

            // Loop through all source pins that are called by reactions of the sink pin and call
            // mapSource recursively to build the sequence of subtasks for each source pin.
            subSequence = new ArrayList();
            Subtask addMe = null;
            boolean usedAddMe = false;
            for (Iterator it2 = connectedSink.getReactSources().iterator(); it2 != null
                    && it2.hasNext();) {
                SourcePinInstance sourceInReaction = (SourcePinInstance) it2.next();
                if (sourceInReaction.getSinks() != null && sourceInReaction.getSinks().size() != 0) {
                    mapSource(sourceInReaction, st.getPriority(), subSequence, addMe, bypass);
                    if (addMe != null) {
                        usedAddMe = true;
                    }
                    if (subSequence.size() > 0) {
                        addMe = ModelFactory.eINSTANCE.createSubtask();
                        addMe
                                .setName("Anchor|" + getReaction(st) + "|" 
                                		+ st.getName() + "After"
                                        + sourceInReaction.getName());
                        Constant zero = ModelFactory.eINSTANCE.createConstant();
                        zero.setValue(0);
                        addMe.setExecTimeDistribution(zero);
                        addMe.setPriority(st.getPriority());
                        addMe.setActivationSynchronous(true);
                        addMe.setRetAnchorUsed(false);
                        usedAddMe = false;
                    }
                }
            }
            if (addMe != null && usedAddMe == false) {
                // variable usedAddMe is a hack because I dunno how to test the end condition
                // that is in the for loop out of the for loop
                addMe = null;
            }

            if (sourcePin.getMode() == SourcePinMode.SYNCH_LITERAL) {
                st.setActivationSynchronous(true);
            } else {
                // normal asynchronous connection
                st.setActivationSynchronous(false);
                st.setCallingThreadPriority(priority);
            }

            // At this point, subsequence has the sequence of subtasks that correspond to all
            // connections from source pins that react with the sink pin being processed in
            // the loop. So, we add the subtask that is the execution of the sink pin itself
            // at the head of the subsequence.
            subSequence.add(0, st);

            if (anchorAsync != null) {
                subSequence.add(0, anchorAsync);
            }
            if (anchorSt != null) {
                subSequence.add(0, anchorSt);
                usedAnchorSt = true;
            }

            // Now we use the "combine" operation to integrate the subsequence to the previously
            // computed sequence.
            List aux = combine(sequence, subSequence);
            sequence.clear();
            sequence.addAll(aux);

            sinkIdBefore = st.getName();
            multipleSinks = true;
        }
    }
    
    
    /**
     * Gets the reaction originating a subtask
     * 
     * For now, it is the name of the instance--parsed from the subtask's name--but
     * in the future it'll be the reaction
     * and this method will be replace with a getter in Subtask
     * 
     * @param st subtask
     * @return the name of the reaction or null if cannot be determined
     */
    private String getReaction(Subtask st) {
    	String reaction = null;
    	
    	String name = st.getName();
    	if (name.startsWith("Anchor")) {
    		
    		// format should be Anchor|instance|blahblah
    		String[] parts = name.split("|");
    		if (parts.length > 1) {
    			reaction = parts[1];
    		}
    	} else { // format is Instance.pin
    		reaction = name.split("\\.")[0];
    	}
    	
    	return reaction;
    }

    /**
     * Combine operation. Use the prepend operation until it finds an asynchronous connection. When
     * it reaches an asynchronous connection, there are two threads (i.e. two components ready to
     * run), and the algorithm uses the merge operation henceforth.
     * 
     * @param seqA
     * @param seqB
     * @return list of Subtask objects result of the combine/merge of the two lists.
     */
    private List combine(List seqA, List seqB) {
        List seq = new ArrayList();

        int anchorPrio = 9999;
        Subtask anchored = null;
        
//        System.out.println("<<<Combine>>>");
//        dumpSubtasks("SeqA", seqA);
//        dumpSubtasks("SeqB", seqB);

        // prepend up to an async activated subtask
        while (seqA.size() > 0 && ((Subtask) seqA.get(0)).isActivationSynchronous()) {
            seq.add(seqA.get(0));
            int seqAHeadPrio = ((Subtask) seqA.get(0)).getPriority();
            if (seqAHeadPrio < anchorPrio) {
                anchorPrio = seqAHeadPrio;
                anchored = (Subtask) seqA.get(0);
            }
            seqA.remove(0);
        }
        
        
        // Check if there's an async call to component Y after any sync call to a component X
        // where priority of X is less than priority of Y
        if (seqA.size() > 0 && anchorPrio < ((Subtask) seqA.get(0)).getPriority()
                && seqB.size() > 0 && ((Subtask) seqA.get(0)).isRetAnchorUsed() == false) {
            // Add anchor subtask that will let component X be activated when control returns
            // to component Y. The anchor subtask has the low priority anchorPrio and is placed
            // at the beginning of seqB.
            Subtask anchorSt = ModelFactory.eINSTANCE.createSubtask();
            anchorSt.setName("Anchor" + anchored.getName() + "Ret");
            Constant zero = ModelFactory.eINSTANCE.createConstant();
            zero.setValue(0);
            anchorSt.setExecTimeDistribution(zero);
            anchorSt.setPriority(anchorPrio);
            anchorSt.setActivationSynchronous(true);
            anchorSt.setRetAnchorUsed(false);
            seqB.add(0, anchorSt);
            ((Subtask) seqA.get(0)).setRetAnchorUsed(true);
        }

        // continue merging the two sequences
        while (seqA.size() > 0 && seqB.size() > 0) {
        	Subtask subtaskA = ((Subtask) seqA.get(0));
        	Subtask subtaskB = ((Subtask) seqB.get(0));

            if (subtaskB.getPriority() > subtaskA.getPriority()) {
                seq.add(seqB.get(0));
                seqB.remove(0);
            } else if (subtaskB.getPriority() < subtaskA.getPriority()) {
                seq.add(seqA.get(0));
                seqA.remove(0);
            } else { // same priority
                	
            	// this situation is valid only if the two subtasks correspond to the
            	// same reaction
            	// which means that two sink activations are queued one after the other
        		// the order is decided by the priority of the calling thread
            	// TODO: check if this is broken by anchors
            	// since we don't have sink to reaction mapping, we assume one reaction per
            	// component
            	String reactionA = getReaction(subtaskA);
            	String reactionB = getReaction(subtaskB);
            	
            	if (reactionA.equals(reactionB) && !subtaskA.isActivationSynchronous()
            			&& !subtaskB.isActivationSynchronous()) {
            		if (subtaskB.getCallingThreadPriority() > subtaskA.getCallingThreadPriority()) {
                        seq.add(seqB.get(0));
                        seqB.remove(0);
            		} else {
            			
            			/*
            			 * if priorities are equal, we choose the order in the tree because they must
            			 * have been activated by the same thread
            			 */
                        seq.add(seqA.get(0));
                        seqA.remove(0);
            		}
            	} else {
                	_errors.add(new Error(
                			"lambda-ABA constraint violated: Subtasks " + subtaskA.getName() + " and "
                			+ subtaskB.getName()
    						+ " have the same priority. Can't decide which one goes first.",
    						IMarker.SEVERITY_ERROR, 0, sourceFile));
            		
            		// default to order in the tree
                    seq.add(seqA.get(0));
                    seqA.remove(0);
            	}
            }
        }
        if (seqA.size() > 0) {
            seq.addAll(seqA); // append seqA to the end of seq
        } else {
            seq.addAll(seqB); // append seqB to the end of seq
        }
        
//        dumpSubtasks("Result", seq);
        return seq;
    }

    /**
	 * @see edu.cmu.sei.pacc.perf.Interpretation#getErrors()
	 */
    public List<Error> getErrors() {
        return _errors;
    }

    /* (non-Javadoc)
	 * @see edu.cmu.sei.pacc.perf.Interpretation#persistModel(java.lang.String)
	 */
    public void persistModel(String fileName) throws CoreException {
    	
    	try {
	        URI fileURI = URI.createFileURI(fileName);
	        Resource xmiFile = resourceSet.createResource(fileURI); 
	        List contents = xmiFile.getContents();
	        contents.add(getModel());
	        xmiFile.save(null);
        } catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					PerformanceRFPlugin.getDefault().getBundle().getSymbolicName(),
					2, "Problem persisting Performance Model", e));
        }

    }
    
    
	/* (non-Javadoc)
	 * @see edu.cmu.sei.pacc.perf.Interpretation#getModel()
	 */
	public PerformanceModel getModel() {
		return model;
    }
	
	private Distribution getModelDistribution(
							edu.cmu.sei.pacc.perf.icm.Distribution icmDist) {
		Distribution modelDist = null;
		if (icmDist instanceof edu.cmu.sei.pacc.perf.icm.Normal) {
			edu.cmu.sei.pacc.perf.icm.Normal from =
				(edu.cmu.sei.pacc.perf.icm.Normal) icmDist;
			Normal to = ModelFactory.eINSTANCE.createNormal();
			to.setMean(from.getMean());
			to.setStdDev(from.getStdDev());
			modelDist = to;
		} else if (icmDist instanceof edu.cmu.sei.pacc.perf.icm.Exponential) {
			edu.cmu.sei.pacc.perf.icm.Exponential from =
				(edu.cmu.sei.pacc.perf.icm.Exponential) icmDist;
			Exponential to = ModelFactory.eINSTANCE.createExponential();
			to.setMean(from.getMean());
			modelDist = to;
		} else if (icmDist instanceof edu.cmu.sei.pacc.perf.icm.Constant) {
			edu.cmu.sei.pacc.perf.icm.Constant from =
				(edu.cmu.sei.pacc.perf.icm.Constant) icmDist;
			Constant to = ModelFactory.eINSTANCE.createConstant();
			to.setValue(from.getValue());
			modelDist = to;
		} else if (icmDist instanceof edu.cmu.sei.pacc.perf.icm.Unknown) {
			edu.cmu.sei.pacc.perf.icm.Unknown from =
				(edu.cmu.sei.pacc.perf.icm.Unknown) icmDist;
			Unknown to = ModelFactory.eINSTANCE.createUnknown();
			to.setMean(from.getMean());
			to.setMin(from.getMin());
			to.setMax(from.getMax());
			modelDist = to;
		} else if (icmDist instanceof edu.cmu.sei.pacc.perf.icm.Uniform) {
			edu.cmu.sei.pacc.perf.icm.Uniform from =
				(edu.cmu.sei.pacc.perf.icm.Uniform) icmDist;
			Uniform to = ModelFactory.eINSTANCE.createUniform();
			to.setMin(from.getMin());
			to.setMax(from.getMax());
			modelDist = to;
		}
		return modelDist;
	}
 
    /**
     * @see edu.cmu.sei.pacc.perf.Interpretation#getNumErrors()
     */
    public int getNumErrors() {
        int errorCount = 0;
        
        for (Iterator it = _errors.iterator(); it.hasNext();) {
            if (((Error) it.next()).getSeverity() == IMarker.SEVERITY_ERROR) {
                errorCount++;
            }
        }
        
        return errorCount;
    }
    
    /**
     * @see edu.cmu.sei.pacc.perf.Interpretation#getNumWarnings()
     */
    public int getNumWarnings() {
        int warningCount = 0;
        
        for (Iterator it = _errors.iterator(); it.hasNext();) {
            if (((Error) it.next()).getSeverity() == IMarker.SEVERITY_WARNING) {
                warningCount++;
            }
        }
        
        return warningCount;
    }
    
}