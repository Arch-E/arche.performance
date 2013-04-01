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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;

/**
 * Defines the default values for preferences pages of the performance RF.
 */
public class PerformanceRFPreferenceInitializer extends AbstractPreferenceInitializer {

    public static final String TASK_COLOR_PREFERENCE    = "taskcolor";

    public static final RGB    DEFAULT_TASK_COLOR       = new RGB(255, 255, 175);

    public static final String SUBTASK_COLOR_PREFERENCE = "subtaskcolor";

    public static final RGB    DEFAULT_SUBTASK_COLOR    = new RGB(140, 165, 230);

    public static final String WIDTH_PREFERENCE         = "width";

    public static final int    DEFAULT_WIDTH_CANVAS     = 1000;

    /**
     * Initializer for the performance RF preferences
     */
    public PerformanceRFPreferenceInitializer() {
        super();
    }

    /**
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = PerformanceRFPlugin.getDefault().getPreferenceStore();
        store.setDefault(WIDTH_PREFERENCE, DEFAULT_WIDTH_CANVAS);
        PreferenceConverter.setDefault(store, TASK_COLOR_PREFERENCE, DEFAULT_TASK_COLOR);
        PreferenceConverter.setDefault(store, SUBTASK_COLOR_PREFERENCE, DEFAULT_SUBTASK_COLOR);
    }

}
