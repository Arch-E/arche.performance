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

package edu.cmu.sei.pacc.perf.ui.preferences;


import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.ui.Messages;

/**
 * Preference page for the performance RF part
 *
 * @author Tanmay Sinha
 */
public class PerformanceRFPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    private ColorFieldEditor taskEditor;
    private ColorFieldEditor subtaskEditor;
    private IntegerFieldEditor widthEditor;
    
    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */

       protected Control createContents(Composite parent) {

            Composite composite = new Composite(parent, SWT.NONE);

            //Create a data that takes up the extra space in the dialog .
            GridLayout gridLayout = new GridLayout(2, false);
            composite.setLayout(gridLayout);

            widthEditor = new IntegerFieldEditor(PerformanceRFPreferenceInitializer.WIDTH_PREFERENCE, 
                                                 Messages.getString("PerformanceRFPreferencePage.labelWidth")
                                                 , composite);
            
            Group colorGroup = new Group(composite, SWT.SHADOW_ETCHED_IN);
            
            colorGroup.setLayout(gridLayout);
            colorGroup.setText("Colors");
            
            colorGroup.setLayout(new GridLayout());

            taskEditor =
                new ColorFieldEditor(
                    PerformanceRFPreferenceInitializer.TASK_COLOR_PREFERENCE,
                    Messages.getString("PerformanceRFPreferencePage.task"),
                    colorGroup);                
                
            subtaskEditor = new ColorFieldEditor(
                                                 PerformanceRFPreferenceInitializer.SUBTASK_COLOR_PREFERENCE,
                                                 Messages.getString("PerformanceRFPreferencePage.subtask"),
                                                 colorGroup);  
            //Set the editor up to use this page
            taskEditor.setPage(this);
            taskEditor.setPreferenceStore(getPreferenceStore());
            taskEditor.load();

            subtaskEditor.setPage(this);
            subtaskEditor.setPreferenceStore(getPreferenceStore());
            subtaskEditor.load();
            
            widthEditor.setPage(this);
            widthEditor.setPreferenceStore(getPreferenceStore());
            widthEditor.load();
            
            return composite;
        
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        setPreferenceStore(PerformanceRFPlugin.getDefault().getPreferenceStore());
   
    }

    /**
     * Handles the restore defaults button
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults() {
        taskEditor.loadDefault();
        subtaskEditor.loadDefault();
        widthEditor.loadDefault();
    }

    /**
     * Store in the Preference Store
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk() {
        
        taskEditor.store();
        subtaskEditor.store();
        widthEditor.store();
        return super.performOk();
    }

}
