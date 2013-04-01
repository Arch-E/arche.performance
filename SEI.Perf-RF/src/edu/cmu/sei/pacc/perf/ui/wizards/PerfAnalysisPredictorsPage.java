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

package edu.cmu.sei.pacc.perf.ui.wizards;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.eval.EvaluationProcedure;
import edu.cmu.sei.pacc.perf.ui.Messages;

/**
 * PECT New Project wizard (2nd page). Has widgets to let the user enter the folder names for design
 * artifacts and generated code. The user can also inform CCL compiler options (e.g. built-in
 * components that should be ignored in code generation).
 * 
 * @author Paulo Merson
 * @author Tanmay Sinha
 */
public class PerfAnalysisPredictorsPage extends WizardPage {

    private Map<Button, EvaluationProcedure> buttonToEvaluation 
                                                = new HashMap<Button, EvaluationProcedure>();
    
    private EvaluationProcedure selectedProcedure = null;

    /**
     * @param name just an ID for the page (required by Eclipse)
     */
    public PerfAnalysisPredictorsPage(String name) {
        super(name);
        this.setTitle(Messages.getString("page2.title")); //$NON-NLS-1$
        this.setDescription(Messages.getString("page2.desc")); //$NON-NLS-1$
    }

    private Listener procedureSelectionListener = new Listener() {
        
        public void handleEvent(Event e) {
            if (buttonToEvaluation.containsKey(e.widget)) {
                selectedProcedure = buttonToEvaluation.get(e.widget);
            } else {
                selectedProcedure = null;
            }
            setPageComplete(true);
        }
    };
    
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE); // (Composite) getControl();
        GridLayout layout = new GridLayout(4, false);
        composite.setLayout(layout);

        // Group to select an evaluation procedure (or none)
        Group predictorsGroup = new Group(composite, SWT.NONE);
        predictorsGroup.setLayout(new GridLayout(1, false));
        predictorsGroup.setText("Evaluation Procedures");
        GridData gd = new GridData(SWT.FILL);
        gd.widthHint = 300;
        predictorsGroup.setLayoutData(gd);

        for (Iterator it = PerformanceRFPlugin.getDefault().getEvaluationProcedures().iterator(); 
                it.hasNext();) {
            EvaluationProcedure procedure = (EvaluationProcedure) it.next();
            Button button = new Button(predictorsGroup, SWT.RADIO);
            button.setText(procedure.getName());
            button.setToolTipText(procedure.getDescription());
            button.setEnabled(procedure.isEnabled());
            button.setSelection(false);
            buttonToEvaluation.put(button, procedure);
            button.addListener(SWT.Selection, procedureSelectionListener);            
        }

//        matlabButton = new Button(predictorsGroup, SWT.RADIO);
//        matlabButton.setText("Closed form (SS latency)");
//        // TODO precision param
//        matlabButton.setSelection(false);

        Button noneButton = new Button(predictorsGroup, SWT.RADIO);
        noneButton.setText("No predictor (run interpretation only)");
        noneButton.setSelection(true);
        noneButton.addListener(SWT.Selection, procedureSelectionListener);
        
        setControl(composite);
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
     * @return Returns the selectedProcedure.
     */
    public EvaluationProcedure getSelectedProcedure() {
        return selectedProcedure;
    }

}