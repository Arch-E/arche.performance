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

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.icm.Scenario;
import edu.cmu.sei.pacc.perf.ui.Messages;

/**
 * First page of the Performance Analysis wizard. It allows the user to select what scenarios to to
 * use in the interpretation and select the name of the persisted model file.
 * 
 * @author Paulo Merson
 */
public class PerfAnalysisInterpretationPage extends WizardPage {

    /** the input design file (e.g., a .ccl file) */
    private IFile                designFile;

    /** assembly object that represents the ICM model created prior to calling this wizard */
    private AssemblyInstance     assembly;

    /** Read only text field to show the design file that will be analyzed */
    private Text                 designFileText;

    /** Text field where user indicates the folder to store design artifacts. */
    private Text                 modelFileText;

    /** map of scenario numbers to screen checkboxes that indicate if the scenarios are selected */
    private Map<Integer, Button> scenarios;

    /**
     * Creates the (only) page for the new PECT project wizard.
     * 
     * @param name just a string that identifies the page.
     */
    public PerfAnalysisInterpretationPage(String name, IFile designFile, AssemblyInstance assembly) {
        super(name);
        this.designFile = designFile;
        this.assembly = assembly;
        this.setTitle(Messages.getString("page1.title")); //$NON-NLS-1$
        this.setDescription(Messages.getString("page1.desc")); //$NON-NLS-1$
    }

    /**
     * Creates the widget in the main area of the page.
     * 
     * @param parent the parent control
     */
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE); // (Composite) getControl();
        GridLayout layout = new GridLayout(3, false);
        composite.setLayout(layout);

        // design file (read only)
        Label designFileLabel = new Label(composite, SWT.NONE);
        designFileLabel.setText("Design File: ");
        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gd.horizontalSpan = 1;
        designFileLabel.setLayoutData(gd);

        designFileText = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        designFileText.setText(designFile.getName());
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gd.horizontalSpan = 2;
        designFileText.setLayoutData(gd);

        // Group to select what scenario(s) to use in interpretation
        Group scenariosGroup = new Group(composite, SWT.NONE);
        scenariosGroup.setLayout(new GridLayout(3, false));
        scenariosGroup.setText("Scenarios");
        gd = new GridData(SWT.FILL);
        gd.horizontalSpan = 3;
        gd.widthHint = 330;
        scenariosGroup.setLayoutData(gd);

        int numScenarios = assembly.getScenarios().size();
        if (numScenarios > 0) {
            gd = new GridData(SWT.NONE);
            gd.horizontalSpan = 3;
            gd.widthHint = 200;
            scenarios = new HashMap(numScenarios);
            for (Iterator it = assembly.getScenarios().iterator(); it.hasNext();) {
                Scenario scenario = (Scenario) it.next();
                Button bt = new Button(scenariosGroup, SWT.CHECK);
                bt.setLayoutData(gd);
                bt.setText(scenario.getNumber() + " - " + scenario.getName());
                bt.setSelection(true);
                scenarios.put(scenario.getNumber(), bt);
            }
        } else {
            Label noScenariosLabel = new Label(scenariosGroup, SWT.NONE);
            noScenariosLabel.setText("No scenarios defined");
            gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
            gd.verticalSpan = 3;
            noScenariosLabel.setLayoutData(gd);
        }

        Label modelFileLabel = new Label(composite, SWT.NONE);
        modelFileLabel.setText("Model File: ");
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        modelFileLabel.setLayoutData(gd);

        modelFileText = new Text(composite, SWT.SINGLE | SWT.BORDER);

        // String modelFileName = designFile.getFullPath().removeFileExtension().toString() +
        // ".model";
        String modelFileName = designFile.getRawLocation().removeFileExtension().toOSString()
                + ".model";

        modelFileText.setText(modelFileName);
        modelFileText.setToolTipText(Messages.getString("modelFileHint"));
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gd.widthHint = 200;
        modelFileText.setLayoutData(gd);
        modelFileText.setFocus();

        Button modelFileBrowseButton = new Button(composite, SWT.PUSH);
        modelFileBrowseButton.setText("Browse...");
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        modelFileBrowseButton.setLayoutData(gd);
        modelFileBrowseButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent event) {
                String text = handleBrowseButtonPressed(modelFileText.getText(),
                                                              "Save Performance Model As");
                modelFileText.setText(text);
            }
        });

        setControl(composite);
        pageChanged();
    }

    /**
     * Called by the wizard class to check if it's all right to enable the finish button.
     * 
     * @return true if finish can be enabled or false otherwise.
     */
    public boolean canFinish() {
        String modelFileName = modelFileText.getText();
        if (modelFileName == null || modelFileName.trim().length() == 0) {
            return false;
        }
        // TODO: check that at least one scenario was selected
        return true;
    }

    /**
     * If the user changed the selection between blank and sample project, we need to set the value
     * of the built ins in the second page of the wizard, but only if that value was not modified by
     * the user yet. In addition, we validate this page.
     */
    private void pageChanged() {
        setPageComplete(canFinish());
    }

    /**
     * @return Returns the modelFileName.
     */
    public Text getModelFileText() {
        return modelFileText;
    }

    /**
     * Shows File dialog box where the user selects a file.
     * 
     * @param file
     * @param title
     * @param message
     * @return
     */
    protected String handleBrowseButtonPressed(String file, String title) {
        FileDialog dialog = new FileDialog(getShell());
        dialog.setText(title);
        dialog.setFileName(file);
        dialog.setFilterNames(new String[] { "Performance model | *.model", "All files | *.*"});
        dialog.setFilterExtensions(new String[] { "*.model", "*.*"});
        String res = dialog.open();
        if (res != null) {
            return res;
        }
        return file;
    }

    /**
     * @return Returns the scenarios.
     */
    public Map<Integer, Button> getScenarios() {
        return scenarios;
    }

}