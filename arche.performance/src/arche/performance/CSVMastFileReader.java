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

package arche.performance;

// Utility class based on the CSVReader library

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

public class CSVMastFileReader {
	
	private static final String HEADER1 = "Transaction";
	private static final String HEADER2 = "Best";
	private static final String HEADER3 = "Average";
	private static final String HEADER4 = "Worst";
	private static final String HEADER5 = "Deadline";
	private static final String HEADER6 = "DeadlineMet";
	
	public static void parseCsv(String csvFilename) {
		
		CsvReader reader = null;
		try {
			reader = new CsvReader(csvFilename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			reader.readHeaders();
			while (reader.readRecord())
			{
				String transactionValue = reader.get(HEADER1);
				String bestValue = reader.get(HEADER2);
				//double averageValue = Double.parseDouble(reader.get(HEADER3));
				double worstValue = Double.parseDouble(reader.get(HEADER4));
				String deadlineValue = reader.get(HEADER5);
				String deadlineMetValue = reader.get(HEADER6);
				
				// perform program logic here
				System.out.print("ROW --> ");
				System.out.print(transactionValue+" ");
				System.out.print(bestValue+" ");
				//System.out.print(averageValue+" ");
				System.out.print(worstValue+" ");
				System.out.print(deadlineValue+" ");
				System.out.print(deadlineMetValue+" ");
				System.out.println();

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reader.close();
	}

	public static Double getWorstCaseAvgParameter(String csvFilename) {
		return (getAvgParameter(csvFilename, HEADER4));
	}

	public static Double getAvgParameter(String csvFilename, String parameter) {	

		CsvReader reader = null;
		try {
			reader = new CsvReader(csvFilename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (null);
		}

		double avg = 0;
		double n = 0;

		try {
			reader.readHeaders();
			String[] values = null;
			int fieldIndex = reader.getIndex(parameter);
			//System.out.println(fieldIndex);
			while (reader.readRecord())
			{
				values = reader.getValues();
				if (values[fieldIndex].length() > 0)
					avg += Double.parseDouble(values[fieldIndex]);
				else
					return (null);
				n++;

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reader.close();
			return (null);
		}

		reader.close();
		return (avg/n);
	}

	public static Double getWorstCaseParameter(String csvFilename, String firstEntry) {
		return (getParameter(csvFilename, HEADER4, firstEntry));
	}

	public static Double getParameter(String csvFilename, String parameter, String firstEntry) {	

		CsvReader reader = null;
		try {
			reader = new CsvReader(csvFilename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (null);
		}

		try {
			reader.readHeaders();
			String[] values = null;
			int fieldIndex = reader.getIndex(parameter);
			//System.out.println(fieldIndex);
			String transactionValue = null;
			while (reader.readRecord())
			{
				values = reader.getValues();
				transactionValue = reader.get(HEADER1);
				if (transactionValue.equals(firstEntry)) {
					if (values[fieldIndex].length() > 0)
						return Double.parseDouble(values[fieldIndex]);
					else
						return (null);
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reader.close();
			return (null);
		}

		reader.close();
		return (null);
	}

	public static void main(String[] args) {
		
		//CsvMASTReader.parseCsv("bigTest_mast.csv");
		double value = CSVMastFileReader.getAvgParameter("bigTest_mast.csv","Best");
		System.out.println(value);
		return;
	}
	

}
