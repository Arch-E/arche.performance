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

package edu.cmu.sei.pacc.perf.eval.mast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;

import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.model.Task;

/**
 * Class to read MAST results and save them in a CSV file
 * @author gmoreno
 */
public class MastResultReader {
	protected BufferedReader input;
	protected PrintWriter output;
	protected PerformanceModel model;

	public class Transaction {
		public String name;
		public Double worst;
		public Double average;
		public Double best;
		public Double deadline;
	
		public static final String DEADLINE_MET_NO = "n";
		public static final String DEADLINE_MET_YES = "y";
		public static final String DEADLINE_MET_UNKNOWN = "u";
		
		public static final String HEADER_LINE = 
			"Transaction,Best,Average,Worst,Deadline,DeadlineMet";
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			if (name != null) { sb.append(name); }
			sb.append(',');
			if (best != null) { sb.append(best); }
			sb.append(',');
			if (average != null) { sb.append(average); }
			sb.append(',');
			if (worst != null) { sb.append(worst); }
			sb.append(',');
			if (deadline != null) { sb.append(deadline); }

			sb.append(',');
			if (worst != null) {
				if (deadline != null) {
					if (worst.compareTo(deadline) > 0) { // worst > deadline
						sb.append(DEADLINE_MET_NO);
					} else {
						sb.append(DEADLINE_MET_YES);
					}
				} else { // if there's no deadline, it's "met"
					sb.append(DEADLINE_MET_YES);
				}
			} else {
				sb.append(DEADLINE_MET_UNKNOWN);
			}

			return sb.toString();
		}
		
		
	}

	/**
	 * Constructs the results reader.
	 * @param model The performance model for which the results are being read
	 */
	public MastResultReader(PerformanceModel model) {
		this.model = model;
	}
	
	/**
	 * Reads the results from MAST and translates the to a CSV file
	 * @param inputFile MAST results file
	 * @param outputFile CSV file where the translated results should be saved
	 * @return true if no errors
	 */
	public boolean translateResults(File inputFile, File outputFile) {
		boolean rval = false;
		try {
			input = new BufferedReader(new FileReader(inputFile));
			output = new PrintWriter(new FileWriter(outputFile));
			output.println(Transaction.HEADER_LINE);
			rval = parseFile();
		} catch(IOException e) {
			rval = false;
		} finally {
			try {
				input.close();
			} catch(Exception e) {
				rval = false;
			};
			try {
				output.close();
			} catch(Exception e) {
				rval = false;
			};
		}
		
		return rval;
	}
	
	/**
	 * This method can be used to generate a results file when MAST
	 * produces no output, for example if it doesn't run the analysis
	 * because the utilization is too high.
	 * 
	 * @param outputFile
	 * @return true if no errors
	 */
	public boolean writeOutputForUnknowResults(File outputFile) {
		boolean rval = false;
		try {
			output = new PrintWriter(new FileWriter(outputFile));
			output.println(Transaction.HEADER_LINE);
			for (Iterator it = model.getTasks().iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				Transaction transaction = new Transaction();
				transaction.name = task.getName();
				transaction.deadline = getDeadlineForTransaction(transaction.name);
				output.println(transaction);
			}
			rval = true;
		} catch(IOException e) {
			rval = false;
		} finally {
			try {
				output.close();
			} catch(Exception e) {
				rval = false;
			};
		}
		
		return rval;
	}

	protected boolean parseFile() throws IOException {
		String line;
		while ((line = input.readLine()) != null) {
			if (line.trim().startsWith("Transaction")) {
				Transaction transaction;
				if ((transaction = parseTransaction()) == null) {
					return false;
				}
				output.println(transaction);
			} 
		}
		return true;
	}
	
	protected Transaction parseTransaction() throws IOException {
		Transaction transaction = new Transaction();
		String line;
		while ((line = input.readLine()) != null) {
			if (line.trim().startsWith("Name")) {
				StringTokenizer tokens = new StringTokenizer(line.trim(), ">,)");
				tokens.nextToken(); // skip "Name"
				transaction.name = tokens.nextToken().trim();
				transaction.deadline = getDeadlineForTransaction(transaction.name);
			} 
			if (line.endsWith(";")) {
				break;
			} else if (line.indexOf("Event_Name") >= 0 && line.indexOf("_done") >= 0) {
				parseEvent(transaction);
			} 
		}
		return transaction;
	}
	
	protected void parseEvent(Transaction transaction) throws IOException {
		String line;
		while ((line = input.readLine()) != null) {
			if (line.indexOf("Worst_Global_Response_Times") >= 0) {
				transaction.worst = parseTimeValue();
			} else if (line.indexOf("Avg_Global_Response_Times") >= 0) {
				transaction.average = parseTimeValue();
			} else if (line.indexOf("Best_Global_Response_Times") >= 0) {
				transaction.best = parseTimeValue();
				break; // assume best is always the last one
			}
		}
	}
	
	protected Double parseTimeValue() throws IOException {
		String line;
		while ((line = input.readLine()) != null) {
			if (line.indexOf("Time_Value") >=0) {
				return new Double(line.substring(line.indexOf('>') + 2,
						  line.indexOf(')')));
			}
		}
		return null;
	}
	
	protected Double getDeadlineForTransaction(String name) {
		Double deadline = null;
		for (Iterator it = model.getTasks().iterator(); it.hasNext();) {
			Task task = (Task) it.next();
			if (task.getName().equalsIgnoreCase(name)) {
				deadline = task.getEffectiveDeadline();
				break;
			}
		}
		
		return deadline;
	}
}
