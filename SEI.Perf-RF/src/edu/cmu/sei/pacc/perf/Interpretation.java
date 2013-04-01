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

import java.util.List;

import org.eclipse.core.runtime.CoreException;

import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;
import edu.cmu.sei.pacc.perf.util.Error;


/**
 * Interface for the interpretations in the performance reasoning framework.
 * Classes implementing this interface can transform an ICM instance into a
 * performance model. 
 */
public interface Interpretation {

    /** max priority for a task */
    int CLOCK_PRIORITY = 127;

    /**
     * @return number of errors with severity = ERROR detected during intepretation.
     */
    public int getNumErrors();

    /**
     * @return number of warnings detected during intepretation.
     */
    public int getNumWarnings();

    /**
	 * Execute interpretation translating the intermediate constructive model
	 * ICM to a performance model.
	 * <p>
	 * This operation assumes the connections in the assembly are valid. That 
	 * means conditions such as 'source pins only interact with sink pins that 
	 * have compatible mode/signature' were checked by the CCL parser.
	 * 
	 * @param assemblyInstance the AssemblyInstance from the ICM model
	 * @param scenario bitmask of scenarios to be included in the
	 * 	interpretation. If it is null, scenarios are ignored during
	 * 	interpretation.
	 * 
	 * @return the performance model resulting of interpretation. If there were
	 * 	errors (more severe than warnings) during the interpretation, the result
	 * 	is null.
	 * @see getErrors()
	 */
	public PerformanceModel doInterpretation(AssemblyInstance assemblyInstance, Integer scenario);

	/**
	 * Returns a List of Errors including Info, Warnings, and Errors.
     * 
	 * @return list of Errors
	 * @see doInterpretation()
	 */
	public List<Error> getErrors();

	/**
	 * Saves the result of interpretation to an XMI file using EMF persistence. 
	 * If the model is empty, does not do anything.
	 * 
	 * @param fileName path to the file in which the model is to be persisted.
	 * @throws CoreException
	 */
	public void persistModel(String fileName) throws CoreException;

	/**
	 * Returns the performance model resulting from the intepretation
	 * 
	 * @return the performance model
	 * @see doInterpretation()
	 */
	public PerformanceModel getModel();

}