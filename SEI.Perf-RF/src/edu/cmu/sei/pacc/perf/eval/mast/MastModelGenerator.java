package edu.cmu.sei.pacc.perf.eval.mast;

import edu.cmu.sei.pacc.perf.model.*;
import java.util.*;

public class MastModelGenerator
{
  protected static String nl;
  public static synchronized MastModelGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    MastModelGenerator result = new MastModelGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "-- Real-Time System Model: MAST" + NL + "" + NL + "-- Processor" + NL + "Processing_Resource (" + NL + "    Type => Fixed_Priority_Processor," + NL + "\tName => CPU_1," + NL + "\tMax_Priority => 400," + NL + "    Min_Priority => 1," + NL + "    Max_Interrupt_Priority  => 403," + NL + "    Min_Interrupt_Priority  => 402);" + NL + "    " + NL + "-- Mutexes";
  protected final String TEXT_3 = NL + "\tShared_Resource (" + NL + "\t\tType => Priority_Inheritance_Resource," + NL + "\t\tName => ";
  protected final String TEXT_4 = NL + "\t);";
  protected final String TEXT_5 = NL + "\t    " + NL + "" + NL + "-- Tasks";
  protected final String TEXT_6 = NL + "\t-- Task ";
  protected final String TEXT_7 = " (";
  protected final String TEXT_8 = ")" + NL + "\t" + NL + "\tScheduling_Server (" + NL + "\t        Type                            => Fixed_Priority," + NL + "\t        Name                            => Task_";
  protected final String TEXT_9 = "," + NL + "\t        Server_Processing_Resource      => CPU_1," + NL + "\t";
  protected final String TEXT_10 = NL + "\t\t    Server_Sched_Parameters => (" + NL + "\t\t            Type => Sporadic_Server_Policy," + NL + "\t\t            Normal_Priority => ";
  protected final String TEXT_11 = "," + NL + "\t\t            Background_Priority => ";
  protected final String TEXT_12 = "," + NL + "\t\t            Initial_Capacity => ";
  protected final String TEXT_13 = "," + NL + "\t\t            Replenishment_Period => ";
  protected final String TEXT_14 = "," + NL + "\t\t            Max_Pending_Replenishments => 1000)" + NL + "\t";
  protected final String TEXT_15 = NL + "\t        Server_Sched_Parameters => (" + NL + "\t                Type => Fixed_Priority_Policy," + NL + "\t                The_Priority => ";
  protected final String TEXT_16 = ")" + NL + "\t";
  protected final String TEXT_17 = NL + "\t);" + NL + "\t" + NL + "\t";
  protected final String TEXT_18 = NL + "\t\t-- Subtask ";
  protected final String TEXT_19 = NL + "\t\tOperation (" + NL + "\t\t\tType => Simple," + NL + "\t\t\tName => ";
  protected final String TEXT_20 = "," + NL + "\t\t\t";
  protected final String TEXT_21 = "Shared_Resources_List => (";
  protected final String TEXT_22 = ")," + NL + "\t\t\t";
  protected final String TEXT_23 = "New_Sched_Parameters => (" + NL + "\t\t\t\tType => Overridden_Permanent_FP," + NL + "\t\t        The_Priority => ";
  protected final String TEXT_24 = NL + "\t\t\t)," + NL + "\t\t\t";
  protected final String TEXT_25 = NL + "\t\t); " + NL + "\t";
  protected final String TEXT_26 = NL + "\t\t" + NL + "Transaction (" + NL + "    Type => Regular," + NL + "    Name => ";
  protected final String TEXT_27 = "," + NL + "    External_Events => (";
  protected final String TEXT_28 = NL + "            (Type   => Periodic," + NL + "             Name   => ";
  protected final String TEXT_29 = "," + NL + "             Period => ";
  protected final String TEXT_30 = "," + NL + "             Phase  => ";
  protected final String TEXT_31 = ")";
  protected final String TEXT_32 = NL + "            (Type   => Unbounded," + NL + "             Name   => ";
  protected final String TEXT_33 = "," + NL + "             Avg_Interarrival => ";
  protected final String TEXT_34 = "," + NL + "             Distribution => Poisson)";
  protected final String TEXT_35 = NL + "            (Type   => Sporadic," + NL + "             Name   => ";
  protected final String TEXT_36 = "," + NL + "             Avg_Interarrival => ";
  protected final String TEXT_37 = "," + NL + "             Distribution => Uniform," + NL + "             Min_Interarrival => ";
  protected final String TEXT_38 = ")";
  protected final String TEXT_39 = NL + "         \t(Type => Unsupported_Interarrival_";
  protected final String TEXT_40 = "," + NL + "         \t Name   => ";
  protected final String TEXT_41 = ")";
  protected final String TEXT_42 = NL + "\t";
  protected final String TEXT_43 = NL + "    )," + NL + "    Internal_Events => (" + NL + "\t\t";
  protected final String TEXT_44 = NL + "            (Type   => regular," + NL + "             name   => O_";
  protected final String TEXT_45 = "_";
  protected final String TEXT_46 = "_";
  protected final String TEXT_47 = ")," + NL + "    \t";
  protected final String TEXT_48 = NL + "            (Type   => regular," + NL + "             name   => O_";
  protected final String TEXT_49 = "_";
  protected final String TEXT_50 = "_done";
  protected final String TEXT_51 = NL + "             ,Timing_Requirements => (" + NL + "                     Type             => Hard_Global_Deadline," + NL + "                     Deadline         => ";
  protected final String TEXT_52 = "," + NL + "                     Referenced_Event => ";
  protected final String TEXT_53 = ")";
  protected final String TEXT_54 = NL + "             )" + NL + "    )," + NL + "" + NL + "" + NL + "    Event_Handlers => (" + NL + "" + NL + "\t\t";
  protected final String TEXT_55 = NL + "\t\t\t\t\t," + NL + "\t\t\t\t";
  protected final String TEXT_56 = NL + "            (Type               => Activity," + NL + "             Input_Event        => ";
  protected final String TEXT_57 = "," + NL + "             Output_Event       => O_";
  protected final String TEXT_58 = "_";
  protected final String TEXT_59 = "_";
  protected final String TEXT_60 = "," + NL + "             Activity_Operation => ";
  protected final String TEXT_61 = "," + NL + "             Activity_Server    => Task_";
  protected final String TEXT_62 = NL + "            )" + NL + "\t\t\t";
  protected final String TEXT_63 = NL + "     )" + NL + ");";
  protected final String TEXT_64 = NL + NL + "             " + NL;
  protected final String TEXT_65 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     	MastTemplateArguments args = (MastTemplateArguments) argument;
	PerformanceModel model = args.getModel(); 
	Map<String, Integer> operations = new HashMap<String, Integer>();

    stringBuffer.append(TEXT_2);
     for(Iterator it = model.getMutexes().iterator(); it.hasNext();) {
	Mutex mutex = (Mutex) it.next(); 
    stringBuffer.append(TEXT_3);
    stringBuffer.append( mutex.getName() );
    stringBuffer.append(TEXT_4);
     } 
    stringBuffer.append(TEXT_5);
     for(Iterator it = model.getTasks().iterator(); it.hasNext(); ) { 
		Task task = (Task) it.next();
	if (task.getSubtasks().isEmpty()) continue;
	int taskId = task.getTaskId(); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_9);
     if (task instanceof SSTask) { 
		SSTask ssTask = (SSTask) task; 
    stringBuffer.append(TEXT_10);
    stringBuffer.append( ((Subtask) task.getSubtasks().get(0)).getPriority() );
    stringBuffer.append(TEXT_11);
    stringBuffer.append( ssTask.getBackgroundPriority() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( ssTask.getBudget() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( ssTask.getReplenishmentPeriod() );
    stringBuffer.append(TEXT_14);
     } else { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append( ((Subtask) task.getSubtasks().get(0)).getPriority() );
    stringBuffer.append(TEXT_16);
     } 
    stringBuffer.append(TEXT_17);
     	String subtaskToOperationName[] = new String[task.getSubtasks().size()];
		for (int idx2 = 0; idx2 < task.getSubtasks().size(); idx2++) { 
			Subtask subtask = (Subtask) task.getSubtasks().get(idx2);  
			String operationBaseName = "OP_" + taskId + "_" + subtask.getName() + "_";
			Integer operationIndex = operations.get(operationBaseName);
			if (operationIndex != null) {
				 operationIndex = new Integer(operationIndex.intValue() + 1);
			} else {
				operationIndex = new Integer(1);
			}
			operations.put(operationBaseName, operationIndex);
			String operationName = operationBaseName + operationIndex; 
			subtaskToOperationName[idx2] = operationName; 
    stringBuffer.append(TEXT_18);
    stringBuffer.append( subtask.getName() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append( operationName );
    stringBuffer.append(TEXT_20);
     if (!subtask.getMutexes().isEmpty()) { 
    stringBuffer.append(TEXT_21);
    
				for (int mutexIdx = 0; mutexIdx < subtask.getMutexes().size(); mutexIdx++) { 
    stringBuffer.append( 
					((mutexIdx == 0) ? "" : ", ") +((Mutex) subtask.getMutexes().get(mutexIdx)).getName() );
     
				} 
    stringBuffer.append(TEXT_22);
    	} 
    stringBuffer.append(TEXT_23);
    stringBuffer.append( subtask.getPriority() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( args.formatExecTimeDistribution(subtask.getExecTimeDistribution()) );
    stringBuffer.append(TEXT_25);
     } 
    stringBuffer.append(TEXT_26);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_27);
    	if (task instanceof PeriodicTask) {
    	PeriodicTask periodic = (PeriodicTask) task; 
	
    stringBuffer.append(TEXT_28);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append( periodic.getPeriod() );
     if (args.getUseOffsets()) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append( periodic.getOffset() );
    }
    stringBuffer.append(TEXT_31);
     } else if (task instanceof AperiodicTask) {
    	AperiodicTask aperiodic = (AperiodicTask) task;
    	Distribution interarrival = aperiodic.getInterarrivalDistribution(); 
    	if (interarrival instanceof Exponential) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( interarrival.getComputedMean() );
    stringBuffer.append(TEXT_34);
     } else if (interarrival instanceof Uniform) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_36);
    stringBuffer.append( interarrival.getComputedMean() );
    stringBuffer.append(TEXT_37);
    stringBuffer.append( ((Uniform) interarrival).getMin() );
    stringBuffer.append(TEXT_38);
     } else { 
    stringBuffer.append(TEXT_39);
    stringBuffer.append( interarrival.toString() );
    stringBuffer.append(TEXT_40);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_41);
     } 
    stringBuffer.append(TEXT_42);
     } 
    stringBuffer.append(TEXT_43);
     for (int i = 1; i < task.getSubtasks().size(); i++) { 
    stringBuffer.append(TEXT_44);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_45);
    stringBuffer.append( i );
    stringBuffer.append(TEXT_46);
    stringBuffer.append( ((Subtask) task.getSubtasks().get(i)).getName() );
    stringBuffer.append(TEXT_47);
     } 
    stringBuffer.append(TEXT_48);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_49);
    stringBuffer.append( task.getSubtasks().size() );
    stringBuffer.append(TEXT_50);
     Double deadline = task.getEffectiveDeadline();
              if (deadline != null) { 
    stringBuffer.append(TEXT_51);
    stringBuffer.append( deadline );
    stringBuffer.append(TEXT_52);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    	for (int i = 0; i < task.getSubtasks().size(); i++ ) { 
				String event;
				if (i == 0) {
					event = task.getName();
				} else {
					event = "O_" + taskId + "_" + i + "_" + ((Subtask) task.getSubtasks().get(i)).getName(); 
    stringBuffer.append(TEXT_55);
     }
    stringBuffer.append(TEXT_56);
    stringBuffer.append( event );
    stringBuffer.append(TEXT_57);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_58);
    stringBuffer.append( i + 1 );
    stringBuffer.append(TEXT_59);
    stringBuffer.append( (i+1 < task.getSubtasks().size()) ? ((Subtask) task.getSubtasks().get(i + 1)).getName() : "done" );
    stringBuffer.append(TEXT_60);
    stringBuffer.append( subtaskToOperationName[i] );
    stringBuffer.append(TEXT_61);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_62);
     } 
    stringBuffer.append(TEXT_63);
     } 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(TEXT_65);
    return stringBuffer.toString();
  }
}
