package edu.cmu.sei.pacc.perf.eval.qsim;

import edu.cmu.sei.pacc.perf.model.*;
import java.util.*;

public class QsimModelGenerator
{
  protected static String nl;
  public static synchronized QsimModelGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    QsimModelGenerator result = new QsimModelGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "link L1 {A > B} queue=static service=size(1)" + NL + "" + NL + "# Tasks";
  protected final String TEXT_3 = NL + "\t# Task ";
  protected final String TEXT_4 = " (";
  protected final String TEXT_5 = ")" + NL + "\t" + NL + "\t";
  protected final String TEXT_6 = " " + NL + "\t>>> SSTask not supported yet\t" + NL + "\t";
  protected final String TEXT_7 = NL + "\tbegin flow ";
  protected final String TEXT_8 = " { A > B}" + NL + "\t\t";
  protected final String TEXT_9 = NL + "\t    \tarrival=";
  protected final String TEXT_10 = ":";
  protected final String TEXT_11 = NL + "\t    \tdeadline=";
  protected final String TEXT_12 = NL + "\t    ";
  protected final String TEXT_13 = " " + NL + "\t        arrival=";
  protected final String TEXT_14 = NL + "\t\t";
  protected final String TEXT_15 = NL + "\t   dlmanager=static" + NL + "\t";
  protected final String TEXT_16 = NL + "\t" + NL + "\t";
  protected final String TEXT_17 = NL + "\t\t# Subtask ";
  protected final String TEXT_18 = NL + "\t   subflow ";
  protected final String TEXT_19 = " " + NL + "\t     size=";
  protected final String TEXT_20 = NL + "\t     priority=";
  protected final String TEXT_21 = NL + "\t   endsubflow" + NL + "\t";
  protected final String TEXT_22 = NL + "\tend\t";
  protected final String TEXT_23 = NL + NL + "             " + NL;
  protected final String TEXT_24 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     PerformanceModel model = (PerformanceModel) argument; 
    stringBuffer.append(TEXT_2);
     for(Iterator it = model.getTasks().iterator(); it.hasNext(); ) { 
		Task task = (Task) it.next();
	if (task.getSubtasks().isEmpty()) continue;
	int taskId = task.getTaskId(); 
    stringBuffer.append(TEXT_3);
    stringBuffer.append( task.getName() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( taskId );
    stringBuffer.append(TEXT_5);
     if (task instanceof SSTask) { 
    stringBuffer.append(TEXT_6);
     } else { 
    stringBuffer.append(TEXT_7);
    stringBuffer.append( task.getName().replace('.', '_') );
    stringBuffer.append(TEXT_8);
     if (task instanceof PeriodicTask) {
	    	PeriodicTask periodic = (PeriodicTask) task; 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(periodic.getPeriod());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(periodic.getOffset());
    stringBuffer.append(TEXT_11);
    stringBuffer.append( periodic.getPeriod());
    stringBuffer.append(TEXT_12);
     } else if (task instanceof AperiodicTask) {
	    	AperiodicTask aperiodic = (AperiodicTask) task;
	    	Distribution interarrival = aperiodic.getInterarrivalDistribution(); 
    stringBuffer.append(TEXT_13);
    stringBuffer.append( QsimUtils.formatDistribution(interarrival) );
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
     } 
    stringBuffer.append(TEXT_16);
     for (Iterator it2 = task.getSubtasks().iterator(); it2.hasNext(); ) { 
			Subtask subtask = (Subtask) it2.next();  
    stringBuffer.append(TEXT_17);
    stringBuffer.append( subtask.getName() );
    stringBuffer.append(TEXT_18);
    stringBuffer.append( subtask.getName() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append( QsimUtils.formatDistribution(subtask.getExecTimeDistribution()) );
    stringBuffer.append(TEXT_20);
    stringBuffer.append( 256 - subtask.getPriority() );
    stringBuffer.append(TEXT_21);
     } 
    stringBuffer.append(TEXT_22);
     } 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
