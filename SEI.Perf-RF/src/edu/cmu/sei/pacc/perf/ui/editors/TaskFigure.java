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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.SSTask;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.perf.ui.preferences.PerformanceRFPreferenceInitializer;

/**
 * This is a draw2d Figure that corresponds to a task in the performance model. It's shown as a
 * yellow rectangle with the task properties listed on the top left as text.
 * 
 * @author Paulo Merson
 * @author Tanmay Sinha
 */
public class TaskFigure extends Figure implements IStructuredSelection, IPropertySource{

    private Color       color = new Color(null, PreferenceConverter
                                      .getColor(PerformanceRFPlugin.getDefault().getPreferenceStore(),
                                                PerformanceRFPreferenceInitializer.TASK_COLOR_PREFERENCE));

    private int         height;

    private int         width;

    private Task        task;

    /** used in the structured selection passed to the properties view */
    private List        taskFigures;
    
    private static Font FONT  = new Font(null, "Arial Narrow", 9, SWT.NORMAL);

    /** used to pass properties data to Properties view */
    private IPropertyDescriptor[] propertyDescriptors;

    /** ID of property name for the properties view */
    private static final String   PROPERTY_NAME     = "name";
    
    /** ID of property task id for the properties view */
    private static final String   PROPERTY_TASK_ID     = "taskid";
    
    /** ID of property period (if periodic task) for the properties view */
    private static final String   PROPERTY_PERIOD    = "period";
    
    /** ID of property offset (if periodic task) for the properties view */
    private static final String   PROPERTY_OFFSET    = "offset";
    
    /** ID of property budget (if aperiodic task) for the properties view */
    private static final String   PROPERTY_BUDGET   = "budget";
    
    /** ID of property replenishment period (if aperiodic task) for the properties view */
    private static final String   PROPERTY_REPLENISHMENT_PERIOD   = "replenishmentperiod";
    
    /** ID of property interarrival distribution (if aperiodic task) for the properties view */
    private static final String   PROPERTY_INTERARRIVAL_DISTRIBUTION   = "interarrivaldistribution";
    
    /** ID of property background priority (if aperiodic task) for the properties view */
    private static final String   PROPERTY_BACKGROUND_PRIORITY   = "backgroundpriority";
   
    public TaskFigure() {
        this(null);
    }

    public TaskFigure(Task task) {
        this.task = task;
        taskFigures = new ArrayList(1);
        taskFigures.add(this);

        ToolbarLayout layout = new ToolbarLayout();
        layout.setStretchMinorAxis(false);
        layout.setSpacing(2);

        setLayoutManager(layout);

        setBorder(new LineBorder(ColorConstants.black, 1));
        setBackgroundColor(color);
        setOpaque(true);

        width = 0;
        height = 130;

        if (task != null) {
            layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
            // Create labels for task properties
            Label taskId = new Label("Task ID: " + task.getTaskId());
            taskId.setFont(FONT);
            taskId.setBorder(new MarginBorder(3, 5, 1, 5));
            add(taskId);

            Label nameLabel = new Label("Name: " + task.getName());
            nameLabel.setFont(FONT);
            nameLabel.setBorder(new MarginBorder(1, 5, 1, 5));
            add(nameLabel);

            if (task instanceof PeriodicTask) {
                Label periodLabel = new Label("Period: " + ((PeriodicTask) task).getPeriod());
                periodLabel.setFont(FONT);
                periodLabel.setBorder(new MarginBorder(1, 5, 1, 5));
                add(periodLabel);
                Label offsetLabel = new Label("Offset: " + ((PeriodicTask) task).getOffset());
                offsetLabel.setFont(FONT);
                offsetLabel.setBorder(new MarginBorder(1, 5, 3, 5));
                add(offsetLabel);
            } else if (task instanceof AperiodicTask) {
                if (task instanceof SSTask) {
                    Label budgetLabel = new Label("Budget: " + ((SSTask) task).getBudget());
                    budgetLabel.setFont(FONT);
                    budgetLabel.setBorder(new MarginBorder(1, 5, 1, 5));
                    add(budgetLabel);
                    Label backgroundPriorityLabel = new Label("Background Priority: " + ((SSTask) task).getBackgroundPriority());
                    backgroundPriorityLabel.setFont(FONT);
                    backgroundPriorityLabel.setBorder(new MarginBorder(1, 5, 3, 5));
                    add(backgroundPriorityLabel);
                    Label replenishmentPeriodLabel = new Label("Replenishment Period: " + ((SSTask) task).getReplenishmentPeriod());
                    replenishmentPeriodLabel.setFont(FONT);
                    replenishmentPeriodLabel.setBorder(new MarginBorder(1, 5, 5, 5));
                    add(replenishmentPeriodLabel);
                }
                Label intervalMeanLabel = new Label("Interval Mean: "
                        + ((AperiodicTask) task).getInterarrivalDistribution());
                intervalMeanLabel.setFont(FONT);
                intervalMeanLabel.setBorder(new MarginBorder(1, 5, 7, 5));
                add(intervalMeanLabel);

            }
        } else {
            // used in the legend
            layout.setMinorAlignment(FlowLayout.ALIGN_CENTER);
            Label legend = new Label("Task");
            legend.setFont(FONT);
            legend.setBorder(new MarginBorder(14, 5, 1, 5));
            add(legend);
        }
    }

    /**
     * @return Returns the background color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color The background color to set.
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
     * @return Returns the task.
     */
    public Task getTask() {
        return task;
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

    public boolean isEmpty() {
        // Nothing to do
        return false;
    }

    
    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#getFirstElement()
     */
    public Object getFirstElement() {
        return taskFigures.get(0);
    }
    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#iterator()
     */
    public Iterator iterator() {
        return taskFigures.iterator();
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#size()
     */
    public int size() {
        return taskFigures.size();
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#toArray()
     */
    public Object[] toArray() {
        return taskFigures.toArray();
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredSelection#toList()
     */
    public List toList() {
        return taskFigures;
    }

    
    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        
        String category = "Task attributes";
        
        if (propertyDescriptors == null) {

            PropertyDescriptor namePropertyDescriptor = new PropertyDescriptor(PROPERTY_NAME,
                    "Name");
            namePropertyDescriptor.setCategory(category);
            
            PropertyDescriptor taskIDPropertyDescriptor = new PropertyDescriptor(PROPERTY_TASK_ID,
                    "ID");
            taskIDPropertyDescriptor.setCategory(category);
            
            if(getTask() instanceof PeriodicTask) {
                PropertyDescriptor periodPropertyDescriptor = new PropertyDescriptor(
                        PROPERTY_PERIOD, "Period");
                periodPropertyDescriptor.setCategory(category);

                PropertyDescriptor offsetPropertyDescriptor = new PropertyDescriptor(
                        PROPERTY_OFFSET, "Offset");
                offsetPropertyDescriptor.setCategory(category);

                propertyDescriptors = new IPropertyDescriptor[] { namePropertyDescriptor,
                        taskIDPropertyDescriptor, periodPropertyDescriptor,
                        offsetPropertyDescriptor};
            }
            
            if(getTask() instanceof AperiodicTask) {
                
                PropertyDescriptor budgetPropertyDescriptor = new PropertyDescriptor(
                        PROPERTY_BUDGET, "Budget");
                budgetPropertyDescriptor.setCategory(category);
                
                PropertyDescriptor replenishmentPeriodPropertyDescriptor = new PropertyDescriptor(
                        PROPERTY_REPLENISHMENT_PERIOD, "Replenishment Period");
                replenishmentPeriodPropertyDescriptor.setCategory(category);
                
                PropertyDescriptor interarrivalDistributionPropertyDescriptor = new PropertyDescriptor(
                        PROPERTY_INTERARRIVAL_DISTRIBUTION, "Interarrival Distrbution");
                interarrivalDistributionPropertyDescriptor.setCategory(category);
                      
                PropertyDescriptor backgroundPriorityPropertyDescriptor = new PropertyDescriptor(
                        PROPERTY_BACKGROUND_PRIORITY, "Background Priority");
                backgroundPriorityPropertyDescriptor.setCategory(category);
                                                                                                                                                            
                propertyDescriptors = new IPropertyDescriptor[] { namePropertyDescriptor, 
                        taskIDPropertyDescriptor, budgetPropertyDescriptor, 
                        replenishmentPeriodPropertyDescriptor, 
                        interarrivalDistributionPropertyDescriptor, 
                        backgroundPriorityPropertyDescriptor };
                
            }
            
        }
        return propertyDescriptors;
    }


    /**
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(Object id) {
        if (id.equals(PROPERTY_NAME)) {
            return getTask().getName();
        }
        if (id.equals(PROPERTY_TASK_ID)) {
            return new Integer (getTask().getTaskId());
        }
        
        if (getTask() instanceof PeriodicTask) {
            
            PeriodicTask pt = (PeriodicTask) getTask();
            if (id.equals(PROPERTY_PERIOD)) {
                return new Double (pt.getPeriod());
            }
            if (id.equals(PROPERTY_OFFSET)) {
                return new Double (pt.getOffset());
            }
        }
        
        if(getTask() instanceof AperiodicTask) {
            
            AperiodicTask at = (AperiodicTask) getTask();
            
            if (id.equals(PROPERTY_INTERARRIVAL_DISTRIBUTION)) {
                return at.getInterarrivalDistribution();
            }
            if (at instanceof SSTask) {
                SSTask ssTask = (SSTask) at;
                if (id.equals(PROPERTY_BUDGET)) {
                    return new Double (ssTask.getBudget());
                }
                if (id.equals(PROPERTY_REPLENISHMENT_PERIOD)) {
                    return new Integer (ssTask.getReplenishmentPeriod());
                }
                if (id.equals(PROPERTY_BACKGROUND_PRIORITY)) {
                    return new Integer (ssTask.getBackgroundPriority()); 
                }
            }
        }
        return null;
    }

    /**
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        // Nothing to do
        return false;
    }

    /**
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        // Nothing to do
        
    }

    /**
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        // Nothing to do
        
    }
    
    /**
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        //Nothing to do
        return null;
    }
}