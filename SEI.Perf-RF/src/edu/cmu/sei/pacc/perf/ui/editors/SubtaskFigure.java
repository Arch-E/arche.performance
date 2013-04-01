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

package edu.cmu.sei.pacc.perf.ui.editors;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.ui.preferences.PerformanceRFPreferenceInitializer;

public class SubtaskFigure extends RoundedRectangle implements IStructuredSelection,
        IPropertySource {

    private Color                 color                            = new Color(
                                                                           null,
                                                                           PreferenceConverter
                                                                                   .getColor(
                                                                                             PerformanceRFPlugin
                                                                                                     .getDefault()
                                                                                                     .getPreferenceStore(),
                                                                                             PerformanceRFPreferenceInitializer.SUBTASK_COLOR_PREFERENCE));

    private int                   height;

    private int                   width;

    private Subtask               subtask;

    /** used in the structured selection passed to the properties view */
    private List                  subtaskFigures;

    private static Font           FONT                             = new Font(null, "", 8,
                                                                           SWT.NORMAL);

    private static Font           FONTBOLD                         = new Font(null, "", 8, SWT.BOLD);

    /** used to pass properties data to Properties view */
    private IPropertyDescriptor[] propertyDescriptors;

    /** ID of property name for the properties view */
    private static final String   PROPERTY_NAME                    = "name";

    /** ID of property priority for the properties view */
    private static final String   PROPERTY_PRIORITY                = "priority";

    /** ID of property ret anchor used for the properties view */
    private static final String   PROPERTY_RET_ANCHOR_USED         = "retanchorused";

    /** ID of property activation synchronous for the properties view */
    private static final String   PROPERTY_ACTIVATION_SYNCHRONOUS  = "activationsynchronous";

    /** ID of property exec distribution for the properties view */
    private static final String   PROPERTY_EXEC_DISTRIBUTION       = "execdistribution";

    /** ID of property bypass for the properties view */
    private static final String   PROPERTY_BYPASS                  = "bypass";

    /** ID of property downsampling factor for the properties view */
    private static final String   PROPERTY_DOWNSAMPLING_FACTOR     = "downsampling";

    /** ID of property calling thread priority for the properties view */
    private static final String   PROPERTY_CALLING_THREAD_PRIORITY = "callingthreadpriority";

    private static NumberFormat   formatter;
    static {
        formatter = NumberFormat.getInstance();
        if (formatter instanceof DecimalFormat) {
            ((DecimalFormat) formatter).setMaximumFractionDigits(6);
            ((DecimalFormat) formatter).setDecimalSeparatorAlwaysShown(true);
        }
    }

    public SubtaskFigure() {
        this(null);
    }

    /**
     * The constructor sets the color of the figure and adds the labels corresponding to the subtask
     * properties.
     * 
     * @param subtask
     */
    public SubtaskFigure(Subtask subtask) {
        this.subtask = subtask;
        subtaskFigures = new ArrayList(1);
        subtaskFigures.add(this);

        ToolbarLayout layout = new ToolbarLayout();
        layout.setSpacing(2);

        setLayoutManager(layout);
        setBackgroundColor(color);
        setOpaque(true);
        setCornerDimensions(new Dimension(30, 30));

        if (subtask != null) {
            // Create labels for the subtask properties
            layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
            Label subtaskLabel = new Label(subtask.getName());
            subtaskLabel.setFont(FONTBOLD);
            subtaskLabel.setBorder(new MarginBorder(3, 6, 1, 6));
            add(subtaskLabel);

            Label priorityLabel = new Label("Priority: " + subtask.getPriority());
            priorityLabel.setFont(FONT);
            priorityLabel.setBorder(new MarginBorder(1, 6, 1, 6));
            add(priorityLabel);

            Label execTimeLabel = new Label("execTime: " + subtask.getExecTimeDistribution());
            execTimeLabel.setFont(FONT);
            execTimeLabel.setBorder(new MarginBorder(1, 6, 3, 6));
            add(execTimeLabel);

            if (subtask.getDownsamplingFactor() != 0) {
                Label downSamplingLabel = new Label("downSampling: "
                        + subtask.getDownsamplingFactor());
                downSamplingLabel.setFont(FONT);
                downSamplingLabel.setBorder(new MarginBorder(1, 6, 5, 6));
                add(downSamplingLabel);
            }

        } else {
            // used in the legend
            layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
            Label legend = new Label("Subtask");
            legend.setFont(FONT);
            legend.setBorder(new MarginBorder(16, 6, 1, 6));
            add(legend);
        }

    }

    /**
     * @return Returns the color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color The color to set.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return Returns the height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height The height to set.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return Returns the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width The width to set.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return Returns the subtask.
     */
    public Subtask getSubtask() {
        return subtask;
    }

    /**
     * @see org.eclipse.jface.viewers.ISelection#isEmpty()
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#getFirstElement()
     */
    public Object getFirstElement() {
        return subtaskFigures.get(0);
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#iterator()
     */
    public Iterator iterator() {
        return subtaskFigures.iterator();
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#size()
     */
    public int size() {
        return subtaskFigures.size();
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#toArray()
     */
    public Object[] toArray() {
        return subtaskFigures.toArray();
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#toList()
     */
    public List toList() {
        return subtaskFigures;
    }

    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {

        String category = "Subtask attributes";

        if (propertyDescriptors == null) {

            PropertyDescriptor namePropertyDescriptor = new PropertyDescriptor(PROPERTY_NAME,
                    "Name");
            namePropertyDescriptor.setCategory(category);

            PropertyDescriptor priorityPropertyDescriptor = new PropertyDescriptor(
                    PROPERTY_PRIORITY, "Priority");
            priorityPropertyDescriptor.setCategory(category);

            PropertyDescriptor retAnchorUsedPropertyDescriptor = new PropertyDescriptor(
                    PROPERTY_RET_ANCHOR_USED, "Ret Anchor Used");
            retAnchorUsedPropertyDescriptor.setCategory(category);

            PropertyDescriptor activationSynchronousPropertyDescriptor = new PropertyDescriptor(
                    PROPERTY_ACTIVATION_SYNCHRONOUS, "Activation Synchronous");
            activationSynchronousPropertyDescriptor.setCategory(category);

            PropertyDescriptor execDistributionPropertyDescriptor = new PropertyDescriptor(
                    PROPERTY_EXEC_DISTRIBUTION, "Exec Distribution");
            execDistributionPropertyDescriptor.setCategory(category);

            PropertyDescriptor bypassPropertyDescriptor = new PropertyDescriptor(PROPERTY_BYPASS,
                    "Bypass");
            bypassPropertyDescriptor.setCategory(category);

            PropertyDescriptor downSamplingPropertyDescriptor = new PropertyDescriptor(
                    PROPERTY_DOWNSAMPLING_FACTOR, "Downsampling Factor");
            downSamplingPropertyDescriptor.setCategory(category);

            PropertyDescriptor callingThreadPriorityPropertyDescriptor = new PropertyDescriptor(
                    PROPERTY_CALLING_THREAD_PRIORITY, "Calling Thread Priority");
            callingThreadPriorityPropertyDescriptor.setCategory(category);

            propertyDescriptors = new IPropertyDescriptor[] { namePropertyDescriptor,
                    priorityPropertyDescriptor, retAnchorUsedPropertyDescriptor,
                    activationSynchronousPropertyDescriptor, execDistributionPropertyDescriptor,
                    bypassPropertyDescriptor, downSamplingPropertyDescriptor,
                    callingThreadPriorityPropertyDescriptor};
        }
        return propertyDescriptors;
    }

    /**
     * Subtask properties are not editable.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return null;
    }

    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(Object id) {
        if (id.equals(PROPERTY_NAME)) {
            return getSubtask().getName();
        }
        if (id.equals(PROPERTY_PRIORITY)) {
            return new Integer(getSubtask().getPriority());
        }
        if (id.equals(PROPERTY_RET_ANCHOR_USED)) {
            return new Boolean(getSubtask().isRetAnchorUsed());
        }
        if (id.equals(PROPERTY_ACTIVATION_SYNCHRONOUS)) {
            return new Boolean(getSubtask().isActivationSynchronous());
        }
        if (id.equals(PROPERTY_EXEC_DISTRIBUTION)) {
            return getSubtask().getExecTimeDistribution();
        }
        if (id.equals(PROPERTY_BYPASS)) {
            return new Integer(getSubtask().getBypass());
        }
        if (id.equals(PROPERTY_DOWNSAMPLING_FACTOR)) {
            return new Integer(getSubtask().getDownsamplingFactor());
        }
        if (id.equals(PROPERTY_CALLING_THREAD_PRIORITY)) {
            return new Integer(getSubtask().getCallingThreadPriority());
        }
        return null;
    }

    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        return false;
    }

    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        // nothing to do here
    }

    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        // nothing to do here
    }

}