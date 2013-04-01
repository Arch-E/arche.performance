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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.cmu.sei.pacc.perf.ui.wizards.EvaluationProcedureOptionsPage;

public class SimMastOptionsPage extends WizardPage implements EvaluationProcedureOptionsPage {

	private SimMastOptionsPageContents contents = null;
	private SimMastOptions options = null;

	/**
     * @param name just an ID for the page (required by Eclipse)
     */
    public SimMastOptionsPage(String name) {
        super(name);
        
        // TODO use Messages
        this.setTitle("SIM-MAST Options"); //$NON-NLS-1$
        this.setDescription("Options for the simulation based prediction using SIM-MAST"); //$NON-NLS-1$
        
        /* create default options
         * This will come from persisted values in the future, do defaults for now
         */
        options = new SimMastOptions();
    }

    public void createControl(Composite parent) {
    	contents = new SimMastOptionsPageContents(parent, SWT.NONE); // (Composite) getControl();
    
    	/* set control values */
    	switch (options.mode) {
    	case VERIFICATION:
    		contents.verificationButton.setSelection(true);
    		break;
    	case EXHAUSTIVE:
    		contents.exhaustiveButton.setSelection(true);
    		break;
    	}
    	
    	if (options.templateArgs.getIgnoreExecTimeDistribution()) {
    		switch(options.templateArgs.getIgnoreDistributionUsing()) {
    		case USE_MIN:
    			contents.execTimeCombo.select(1);
    			break;
    		case USE_AVG:
    			contents.execTimeCombo.select(2);
    			break;
    		case USE_MAX:
    			contents.execTimeCombo.select(3);
    			break;
    		}
    	} else {
    		contents.execTimeCombo.select(0);
    	}
    	
    	contents.simLength.setText(Double.toString(options.simulationLength));
    	contents.useOffsetsCheckBox.setSelection(options.templateArgs.getUseOffsets());
		contents.useMastResultsViewer.setSelection(options.useMastResultsViewer);
        setControl(contents);
    }

    /**
     * Called by the wizard class to check if it's all right to enable the finish button.
     * 
     * @return true if finish can be enabled or false otherwise.
     */
    public boolean canFinish() {
        return true;
    }

    
    /**
     * @return Returns the value of useOffsets.
     */
    public Object getOptions() {
    	
    	/* get control values */
    	if (contents.verificationButton.getSelection()) {
    		options.mode = SimMastOptions.SimMastMode.VERIFICATION;
    	} else {
    		options.mode = SimMastOptions.SimMastMode.EXHAUSTIVE;
    	}
    	
    	switch (contents.execTimeCombo.getSelectionIndex()) {
    	case 0:
    		options.templateArgs.setIgnoreExecTimeDistribution(false);
    		break;
    	case 1:
    		options.templateArgs.setIgnoreExecTimeDistribution(true);
    		options.templateArgs.setIgnoreDistributionUsing(
    				MastTemplateArguments.DistributionParameter.USE_MIN);
    		break;
    	case 2:
    		options.templateArgs.setIgnoreExecTimeDistribution(true);
    		options.templateArgs.setIgnoreDistributionUsing(
    				MastTemplateArguments.DistributionParameter.USE_AVG);
    		break;
    	case 3:
    		options.templateArgs.setIgnoreExecTimeDistribution(true);
    		options.templateArgs.setIgnoreDistributionUsing(
    				MastTemplateArguments.DistributionParameter.USE_MAX);
    		break;
    	}
    	
    	options.simulationLength = Double.parseDouble(contents.simLength.getText());
    	options.templateArgs.setUseOffsets(contents.useOffsetsCheckBox.getSelection());
    	options.useMastResultsViewer = contents.useMastResultsViewer.getSelection();

        return options;
    }

   
}