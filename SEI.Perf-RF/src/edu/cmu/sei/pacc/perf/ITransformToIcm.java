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

import org.eclipse.core.resources.IFile;

import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;

/**
 * The implementing class is able to translate a design especified in some design language (e.g.,
 * CCL, AADL, UML) to an ICM model. ICM stands for Intermediate Constructive Model and it's a
 * metamodel used in the SEI Performance Reasoning Framework that abstracts design information
 * necessary to reason about real-time performance.
 * 
 * @author Paulo Merson
 */
public interface ITransformToIcm {

    /**
     * This is the method to execute the translation of the design file into an ICM model.
     * 
     * @param designFile file containing the design description to be translated
     * @return an instance of an assembly, which represents an ICM model
     */
    AssemblyInstance translateDesignToIcm(IFile designFile);

    /**
     * Optionally, the implementing class can record the steps and/or the result of the execution of
     * the translation to a String and return it through this method.
     * 
     * @return Messages that should be printed to the console or to a log file after the execution
     *         of translateDesignToIcm().
     */
    String getConsoleMessages();
}
