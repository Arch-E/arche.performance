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

package edu.cmu.sei.pacc.perf.eval;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.WizardPage;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.model.PerformanceModel;

/**
 * Interface to an evaluation procedure.
 *
 * Classes implementing this interface must be stateles since the class is going to be reused.
 * 
 * @author Gabriel Moreno
 */
public interface EvaluationProcedure {
    
    /**
     * Name of the Evaluation procedure.
     * Has to be short enough to be useful and shown as a radio button label in a dialog. 
     *
     * @return the name
     */
    public String getName();
    
    /**
     * Description of the procedure.
     * It is usually displayed in a tooltip.
     * 
     * @return the description
     */
    public String getDescription();
    
    /**
     * Indicates whether the evaluation procedure is available and enabled
     * 
     * @return true if it is enabled
     */
    public boolean isEnabled();
    
    /**
     * Wizard page to configure options for the evaluation procedure.
     * 
     * @return wizard page or null if no options are needed
     */
    public WizardPage getWizardOptionsPage();
    
    /**
     * Performs the evaluation procedure.
     * 
     * @param perfModel performance model to be analyzed
     * @param modelFileName name of the model file name (it may have a .model extension or no
     *            extension)
     * @param consolePrinter ConsolePrinter instance to print out progress messages and results
     * @return IStatus. Should return Status.OK if everything went ok.
     * @throws CoreException
     * @throws IOException
     */
    public IStatus performEvaluation(PerformanceModel perfModel, String modelFileName,
            ConsolePrinter consolePrinter, Object options) throws CoreException, IOException;
}
