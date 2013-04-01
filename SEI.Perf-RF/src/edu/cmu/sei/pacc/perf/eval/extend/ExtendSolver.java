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

package edu.cmu.sei.pacc.perf.eval.extend;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import edu.cmu.sei.pacc.perf.ConsolePrinter;
import edu.cmu.sei.pacc.perf.PerformanceRFPlugin;
import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.Constant;
import edu.cmu.sei.pacc.perf.model.Distribution;
import edu.cmu.sei.pacc.perf.model.Exponential;
import edu.cmu.sei.pacc.perf.model.ModelFactory;
import edu.cmu.sei.pacc.perf.model.Normal;
import edu.cmu.sei.pacc.perf.model.PeriodicTask;
import edu.cmu.sei.pacc.perf.model.SSTask;
import edu.cmu.sei.pacc.perf.model.Subtask;
import edu.cmu.sei.pacc.perf.model.Task;
import edu.cmu.sei.pacc.perf.model.Uniform;

public class ExtendSolver {

    private static final String SEI_LIB               = "SEI-2.lix";

    private static final String BPR_LIB               = "BPR.lix";

    private static final String UTILITIES_LIB         = "Utilities.lix";

    private static final String DISCRETE_LIB          = "Discrete Event.lix";

    private static final String GENERIC_LIB           = "Generic.lix";

    private static final String PLOTTER_LIB           = "Plotter.lix";
    
    private static final String DE_EQUATION_LIB		  = "DE_Equation_10.lix";

    private static final int    SSTASK_QL             = 4;

    private static final int    FP_CPU_SS_QL          = 0;

    private static final int    ADD_OUT               = 3;

    private static final int    CPU_NUM               = 1;

    private ExtendMetaBridge    extend;

    /** position for blocks */
    private int                 xPos;

    private int                 yPos;

    /*
     * These are used to add the QL output of all the SSTasks and feed that to the CPU
     */
    private int                 queueLengthAdderBlock = 0;

    private int                 queueLengthAdderPort;

    private int                 cpuBlock;
    
    private ConsolePrinter consolePrinter = null;

    public int doIt(String modelName, List tasks, double hpStart, double startTime, double endTime,
            boolean closeExtend) throws CoreException {
        if (!PerformanceRFPlugin.getDefault().isExtendInstalled()) return 0;
        boolean hasAperiodic = false;
        for (Iterator it = tasks.iterator(); it.hasNext();) {
            if (it.next() instanceof AperiodicTask) {
                hasAperiodic = true;
                break;
            }
        }

        /* handle Extend launch problem more graciously */
        try {
            extend = new ExtendMetaBridge();
        } catch (Exception e) {
            throw new CoreException(new Status(IStatus.ERROR, 
                       PerformanceRFPlugin.getDefault().getName(),
                       0, "Extend could not be launched. Probably it's because Extend is not installed.", e));
        }
        
        try {
            String worksheetName = new File(modelName).getName() + ".mox";
            extend.closeModel(worksheetName); // in case it's still open from a previous run
            extend.newModel();
            setupModel(hasAperiodic);
            yPos = 0;

            /* add tasks to Extend */
            for (Iterator it = tasks.iterator(); it.hasNext();) {
                Task task = (Task) it.next();
                addTask(task, hpStart, modelName);
            }

            /* connect QL to the CPU */
            if (hasAperiodic && queueLengthAdderBlock > 0) {
                extend.makeConnection(queueLengthAdderBlock, ADD_OUT, cpuBlock, FP_CPU_SS_QL);
            }

            /** ** num of sims is hardcoded to 1 for now ** */
            extend.runSimulation(startTime, endTime, 1);

            extend.saveModel(modelName);

            if (closeExtend) {
                // Close the Extend tool.
                // Extend takes theses commands asynchronously and sometimes a
                // command doesn't
                // work because Extend is busy processing the previous one.
                // Thus, we add
                // a 10second delay here to avoid closeExtend to fail.
                Thread.sleep(10000);
                extend.closeExtend();
            }
        } catch (Exception e) {
            throw new CoreException(new Status(IStatus.ERROR, 
                    PerformanceRFPlugin.getDefault().getBundle().getSymbolicName(), 2,
                    "An error occurred while evaluating the model", e));
        } finally {
            if (extend != null) {
                extend.releaseExtend();
            }
        }

        return 1; // TODO what's this?
    }

    // ------------------------------------------------------------------------------
    // Method: SetupModel
    //
    // Description: Adds the Executive block and CPU to the current Extend
    // model.
    //
    // *** By adding the Executive block, it is assumed that all models are
    // *** Discrete Event models.
    //
    // Parameters:
    // none
    //
    // Return:
    // true no errors occured
    // false error occured
    //
    // ------------------------------------------------------------------------------

    /**
     * Load libraries and adds the Executive block and CPU to the current Extend model
     * 
     * @param hasAperiodic indicates whether there is an aperiodic in the model
     * @throws Exception
     */
    protected void setupModel(boolean hasAperiodic) throws Exception {

        // load libraries
        String deEquationLibraryPath = new File(FileLocator.resolve(
                PerformanceRFPlugin.getDefault()
                        .getBundle()
                        .getEntry("/tools/" + DE_EQUATION_LIB))
                .getFile()).getAbsolutePath();
        String seiLibraryPath = new File(FileLocator.resolve(
                                                          PerformanceRFPlugin.getDefault()
                                                                  .getBundle()
                                                                  .getEntry("/tools/" + SEI_LIB))
                .getFile()).getAbsolutePath();
        extend.openLibrary(deEquationLibraryPath);
        extend.openLibrary(seiLibraryPath);
        extend.openLibrary(BPR_LIB);
        extend.openLibrary(UTILITIES_LIB);
        extend.openLibrary(DISCRETE_LIB);
        extend.openLibrary(GENERIC_LIB);
        extend.openLibrary(PLOTTER_LIB);
        // Executive Block
        extend.placeBlock("Executive", DISCRETE_LIB, 0, 0, ExtendMetaBridge.PLACE_NO_NEIGH,
                          ExtendMetaBridge.PLACE_LEFT);

        // SEI Init Block
        extend.placeBlock("Initialize", SEI_LIB, 120, 0, ExtendMetaBridge.PLACE_NO_NEIGH,
                          ExtendMetaBridge.PLACE_LEFT);

        // CPU
        String blockName = null;
        if (hasAperiodic) {
            blockName = "CPU, FP SS";
            cpuBlock = extend.placeBlock(blockName, SEI_LIB, 300, 10,
                                         ExtendMetaBridge.PLACE_NO_NEIGH,
                                         ExtendMetaBridge.PLACE_LEFT);
            queueLengthAdderBlock = extend.placeBlock("Add", GENERIC_LIB, 10, 0, cpuBlock,
                                                      ExtendMetaBridge.PLACE_RIGHT);
            queueLengthAdderPort = 0;

        } else {
            blockName = "CPU, Fixed Priority";
            cpuBlock = extend.placeBlock(blockName, SEI_LIB, 300, 10,
                                         ExtendMetaBridge.PLACE_NO_NEIGH,
                                         ExtendMetaBridge.PLACE_LEFT);
        }
    }

    protected void setGeneratorDistribution(int block, Distribution distribution, Task task)
            throws Exception {
        int distType;
        double param1;
        double param2;

        if (distribution instanceof Constant) {
            distType = ExtendMetaBridge.DIST_CONSTANT;
            Constant dist = (Constant) distribution;
            param1 = dist.getValue();
            param2 = 0;
        } else if (distribution instanceof Exponential) {
            distType = ExtendMetaBridge.DIST_EXPONENTIAL;
            Exponential dist = (Exponential) distribution;
            param1 = dist.getMean();
            param2 = 0;
        } else if (distribution instanceof Normal) {
            distType = ExtendMetaBridge.DIST_NORMAL;
            Normal dist = (Normal) distribution;
            param1 = dist.getMean();
            param2 = dist.getStdDev();
        } else if (distribution instanceof Uniform) {
            distType = ExtendMetaBridge.DIST_UNIFORM;
            Uniform dist = (Uniform) distribution;
            param1 = dist.getMin();
            param2 = dist.getMax();
        } else {
            throw new Exception("Distribution " + distribution + " not supported in Extend");
        }

        extend.pokeInt("distType", block, distType);
        extend.pokeDouble("meanDist", block, param1);
        extend.pokeDouble("argDist", block, param2);

        // set parameter "no item at time zero" to FALSE
        extend.pokeInt("noItem", block, 0);

        /*
         * if a generator has mean=0, then disable it by setting the max number of items generated
         * to 0
         */
        if (param1 == 0) {
            printToConsole("               Dissabling Generator block for task "
                    + task.getName() + "(id=" + task.getTaskId() + ")"
                    + "To enable, set distribution and clear the \"Maximum number "
                    + "of items generated\" check.");

            extend.pokeInt("UseMaxGen", block, 1);
            extend.pokeInt("MaxGen", block, 0);
        }
    }

    // ------------------------------------------------------------------------------
    // Method: AddTask
    //
    // Description: Adds a task to the current Extend model.
    //
    // *** It is assumed that a task consists of the following blocks:
    // *** - generator block
    // *** - task block (periodic or aperiodic)
    // *** - subtask block(s)
    // *** - task end block
    // *** - file output block (for measurement purposes)
    // *** - Exit block
    //
    // Parameters:
    // task task to add to the model
    // taskNum task's number
    // jobsToSkip number of jobs that start before the 1st critical instant and
    // hence must be skipped.
    //
    // Return:
    // true no errors occured
    // false error occured
    //
    // ------------------------------------------------------------------------------
    protected void addTask(Task task, double hpStart, String modelName) throws Exception {

        xPos = 10;

        int taskBlockNum;
        int delayBlockNum;
        int constBlockNum;
        int lastSubtaskBlockNum = 0;
        int thisSubtaskBlockNum;
        int taskEndBlockNum;
        int exitBlockNum;
        int outputBlockNum;
        int countJobsBlockNum;
        int adderBlockNum;
        int previousBlock;
        int previousPin;

        yPos += 260; // y position (in pixels) of block

        // -----------------------
        // GENERATOR
        // -----------------------
        int genBlockNum = extend.placeBlock("Generator", DISCRETE_LIB, xPos, yPos,
                                            ExtendMetaBridge.PLACE_NO_NEIGH,
                                            ExtendMetaBridge.PLACE_LEFT);

        Distribution generatorDistribution = null;
        if (task instanceof AperiodicTask) {
            AperiodicTask aperiodic = (AperiodicTask) task;
            generatorDistribution = aperiodic.getInterarrivalDistribution();
            extend.setBlockLabel(genBlockNum, generatorDistribution.toString());
            xPos = xPos + 100;

            /* Aperiodic which are not SSTask use a mix of SS and periodic blocks.
             * They start with a Task block because we don't want them to be scheduled
             * by the SSMgr. They use SSSubtask because we want to queue arrivals before
             * sending them to the CPU. They end with TaskEnd because we don't compute
             * serviceTime info for them.
             */
            taskBlockNum = extend.placeBlock((aperiodic instanceof SSTask) ? "SSTask" : "Task",
                                             SEI_LIB, xPos, yPos - 18,
                                             ExtendMetaBridge.PLACE_NO_NEIGH,
                                             ExtendMetaBridge.PLACE_LEFT);

            // connect to generator (generator out pin is 0; SSTask in pin is 0)
            extend.makeConnection(genBlockNum, 0, taskBlockNum, 0);

            // constant - task offset value (*** note this is hard-coded to 0
            // ***)
            constBlockNum = extend.insertConstBlock(0, -25, 50, taskBlockNum,
                                                    ExtendMetaBridge.PLACE_TOP);

            // connect constant (Constant out pin is 0; SSTask offset in pin is
            // 2)
            extend.makeConnection(constBlockNum, 0, taskBlockNum, 2);

            // constant - task ID
            constBlockNum = extend.insertConstBlock((double) task.getTaskId(), 0, 80, taskBlockNum,
                                                    ExtendMetaBridge.PLACE_TOP);

            // connect constant (Constant out pin is 0; SSTask ID in pin is 3)
            extend.makeConnection(constBlockNum, 0, taskBlockNum, 3);

            // connect QL
            if (aperiodic instanceof SSTask) {
                SSTask ssTask = (SSTask) aperiodic;
                if (queueLengthAdderPort > 2) { // need a new adder
                    int newAdder = extend.placeBlock("Add", GENERIC_LIB, 10, 0,
                                                     queueLengthAdderBlock,
                                                     ExtendMetaBridge.PLACE_RIGHT);
                    queueLengthAdderPort = 0;
                    extend.makeConnection(queueLengthAdderBlock, ADD_OUT, newAdder,
                                          queueLengthAdderPort++);
                    queueLengthAdderBlock = newAdder;
                }
                extend.makeConnection(taskBlockNum, SSTASK_QL, queueLengthAdderBlock,
                                      queueLengthAdderPort++);

                // set SS parameters
                int replenishmentBlock = extend.findBlock("RT",
                                                          ExtendMetaBridge.FIND_BLOCK_BY_LABEL, 0,
                                                          1, 1);
                if (replenishmentBlock == -1) {
                    throw new Exception("Block RT for SS replenishment time not found in Extend");
                }
                extend.pokeDouble("conValue", replenishmentBlock, ssTask.getReplenishmentPeriod());

                int budgetBlock = extend.findBlock("Budget", ExtendMetaBridge.FIND_BLOCK_BY_LABEL,
                                                   0, 1, 1);
                if (budgetBlock == -1) {
                    throw new Exception("Block Budget for SS not found in Extend");
                }
                extend.pokeDouble("init", budgetBlock, ssTask.getBudget());

                int bkgPriorityBlock = extend.findBlock("BkgPriority",
                                                        ExtendMetaBridge.FIND_BLOCK_BY_LABEL, 0, 1,
                                                        1);
                if (bkgPriorityBlock == -1) {
                    throw new Exception("Block BkgPriority for SS not found in Extend");
                }
                extend.pokeDouble("priority", bkgPriorityBlock, -ssTask.getBackgroundPriority());
            }
        } else { // periodic
            PeriodicTask periodic = (PeriodicTask) task;
            generatorDistribution = ModelFactory.eINSTANCE.createConstant();
            ((Constant) generatorDistribution).setValue(periodic.getPeriod());
            extend.setBlockLabel(genBlockNum, "Period: " + periodic.getPeriod());

            if (periodic.getOffset() > 0) {

                // -----------------------
                // DELAY that simulates the offset
                // -----------------------
                xPos = xPos + 90;
                delayBlockNum = extend.placeBlock("Activity, Delay", DISCRETE_LIB, xPos, yPos - 5,
                                                  ExtendMetaBridge.PLACE_NO_NEIGH,
                                                  ExtendMetaBridge.PLACE_LEFT);
                extend.pokeDouble("waitDelta", delayBlockNum, periodic.getOffset());
                extend.setBlockLabel(delayBlockNum, "offset: " + periodic.getOffset());

                // connect to generator (generator out pin is 0)
                extend.makeConnection(genBlockNum, 0, delayBlockNum, 0);
                previousBlock = delayBlockNum;
                previousPin = 1;
                xPos = xPos + 90;
            } else {
                previousBlock = genBlockNum;
                previousPin = 0; // generator out pin
                xPos = xPos + 180;
            }

            taskBlockNum = extend.placeBlock("Task", SEI_LIB, xPos, yPos - 18,
                                             ExtendMetaBridge.PLACE_NO_NEIGH,
                                             ExtendMetaBridge.PLACE_LEFT);

            // connect to generator (Task in pin is 0)
            extend.makeConnection(previousBlock, previousPin, taskBlockNum, 0);

            // constant - connector "s" of a task is the number of jobs to skip
            int jobsToSkip = (int) ((hpStart - periodic.getOffset()) / periodic.getPeriod());
            constBlockNum = extend.insertConstBlock((double) jobsToSkip, -25, 50, taskBlockNum,
                                                    ExtendMetaBridge.PLACE_TOP);

            // connect constant (Constant out pin is 0; Task offset in pin is 2)
            extend.makeConnection(constBlockNum, 0, taskBlockNum, 2);
        }

        setGeneratorDistribution(genBlockNum, generatorDistribution, task);

        // label for the Task (or SSTask)
        extend.setBlockLabel(taskBlockNum, task.getName());

        // -----------------------
        // SUBTASKS
        // -----------------------
        xPos = xPos + 25;

        int subtaskIndent = xPos;

        int subtaskCount = 0; // number of subtasks added

        for (Iterator it = task.getSubtasks().iterator(); it.hasNext();) {
            Subtask subtask = (Subtask) it.next();

            // only add subtasks that have a non-zero execution time (hence
            // excluding anchors)
            Distribution execTimeDistribution = subtask.getExecTimeDistribution();
            if (!(execTimeDistribution instanceof Constant)
                    || ((Constant) execTimeDistribution).getValue() > 0) {
                subtaskCount++;
                if (subtaskCount % 250 == 0) { // avoid strings of subtasks too
                    // long on a single row
                    xPos = subtaskIndent;
                    yPos += 260;
                }

                thisSubtaskBlockNum = addSubtask(task, subtask, subtaskCount, CPU_NUM);

                if (subtaskCount == 1) {
                    // if first subtask, connect to task block (Task ItemOut pin
                    // is 1; Subtask ItemIn pin is 0)
                    extend.makeConnection(taskBlockNum, 1, thisSubtaskBlockNum, 0);
                } else {
                    // if not first subtask, connect to last subtask (Subtask
                    // ItemOut pin is 1 and ItemIn is 0)
                    extend.makeConnection(lastSubtaskBlockNum, 1, thisSubtaskBlockNum, 0);
                }

                if (subtask.getDownsamplingFactor() > 0) { // we need to insert
                    // a downsampling
                    // block
                    int downsamplingBlockNum;
                    xPos = xPos + 120;
                    if (xPos > 32000) {
                        xPos = subtaskIndent;
                    	yPos += 260;
                	}
                    downsamplingBlockNum = extend.placeBlock("Downsampling", SEI_LIB, xPos + 100,
                                                             yPos, ExtendMetaBridge.PLACE_NO_NEIGH,
                                                             ExtendMetaBridge.PLACE_LEFT);
                    extend.makeConnection(thisSubtaskBlockNum, 1, downsamplingBlockNum, 0);
                    constBlockNum = extend.insertConstBlock((double) subtask
                            .getDownsamplingFactor(), -20, 40, downsamplingBlockNum,
                                                            ExtendMetaBridge.PLACE_BOTTOM);
                    extend.makeConnection(constBlockNum, 0, downsamplingBlockNum, 2);
                    constBlockNum = extend.insertConstBlock((double) subtask.getBypass(), 10, 70,
                                                            downsamplingBlockNum,
                                                            ExtendMetaBridge.PLACE_BOTTOM);
                    extend.makeConnection(constBlockNum, 0, downsamplingBlockNum, 3);
                    thisSubtaskBlockNum = downsamplingBlockNum;
                }

                // set values for next iteration
                lastSubtaskBlockNum = thisSubtaskBlockNum;
                xPos = xPos + 120;
                if (xPos > 32000) {
                    xPos = subtaskIndent;
                	yPos += 260;
            	}
            }
        }


        // -----------------------
        // TASK END
        // -----------------------
        xPos = xPos + 80;
        taskEndBlockNum = extend.placeBlock(
				(task instanceof SSTask) ? "SSTaskEnd" : "TaskEnd", SEI_LIB,
				xPos, yPos - 1, ExtendMetaBridge.PLACE_NO_NEIGH,
				ExtendMetaBridge.PLACE_LEFT);

        // connect to last subtask (Subtask ItemOut pin is 1)
        extend.makeConnection(lastSubtaskBlockNum, 1, taskEndBlockNum, 0);

        // -----------------------
        // ADDER
        // Used to output col 2 of csv (job number)
        // starting at 1 instead of 0
        // -----------------------
        adderBlockNum = extend.placeBlock("Add", GENERIC_LIB, xPos + 40, yPos + 100,
                                          ExtendMetaBridge.PLACE_NO_NEIGH,
                                          ExtendMetaBridge.PLACE_LEFT);

        // Constant value to add: 1
        constBlockNum = extend.insertConstBlock(1.0, -48, 0, adderBlockNum,
                                                ExtendMetaBridge.PLACE_TOP);

        // connect R in TaskEnd to adder input and
        // constant 1 to adder input
        extend.makeConnection(taskEndBlockNum, 5, adderBlockNum, 0);
        extend.makeConnection(constBlockNum, 0, adderBlockNum, 1);

        // -----------------------
        // FILE OUTPUT
        // -----------------------
        String name = modelName + "_task" + task.getTaskId() + ".csv";

        // create file on disk for output (if the file doesn't exist, Extend
        // block doesn't work)
        File outputFile = new File(name);
        outputFile.createNewFile();

        // add block
        outputBlockNum = extend.placeBlock("File Output", GENERIC_LIB, 240, 40, taskEndBlockNum,
                                           ExtendMetaBridge.PLACE_BOTTOM);

        // set file name
        extend.pokeString("fName", outputBlockNum, name);

        // set that each row be the run number (simulation number)
        extend.pokeInt("simNumber", outputBlockNum, 1);

        // set max number of rows
        extend.pokeInt("writeRows", outputBlockNum, 50000);

        // write to file (flush) at the end of simulation
        extend.pokeInt("atEnd", outputBlockNum, 1);

        // delimeter is text
        extend.pokeInt("textDelim", outputBlockNum, 1);

        // set the text that is the delimitier
        extend.pokeString("delim", outputBlockNum, ",");

        // CONSTANT: The output file with measures uses the numeric task id
        // So we put the task id in a constant
        constBlockNum = extend.insertConstBlock(task.getTaskId(), -38, 52, outputBlockNum,
                                                ExtendMetaBridge.PLACE_TOP);

        // connect task end block (LatencyOut pin is 3) to file output and
        // constant to file output and
        // adder to file output and
        // count items to file output
        extend.makeConnection(taskEndBlockNum, 3, outputBlockNum, 1);
        extend.makeConnection(constBlockNum, 0, outputBlockNum, 0);
        extend.makeConnection(adderBlockNum, 3, outputBlockNum, 3);
        extend.makeConnection(taskEndBlockNum, 5, outputBlockNum, 2);

        // CONSTANT: standard deviation is 0.0
        constBlockNum = extend.insertConstBlock(0.0, -65, -20, outputBlockNum,
                                                ExtendMetaBridge.PLACE_TOP);
        extend.makeConnection(constBlockNum, 0, outputBlockNum, 4);

        // -----------------------
        // EXIT
        // -----------------------
        xPos = xPos + 90;
        exitBlockNum = extend.placeBlock("Exit (4)", DISCRETE_LIB, xPos, yPos + 10,
                                         ExtendMetaBridge.PLACE_NO_NEIGH,
                                         ExtendMetaBridge.PLACE_LEFT);

        // connect task end to exit block num (transient items)
        extend.makeConnection(taskEndBlockNum, 1, exitBlockNum, 3);
        extend.makeConnection(taskEndBlockNum, 2, exitBlockNum, 2);
    }

    // ------------------------------------------------------------------------------
    // Method: AddSubtask
    //
    // Description: Adds a subtask in the current Extend model.
    //
    // Parameters:
    // subtask subtask to add
    // subtaskNum number of the subtask being added
    // cpuNum cpu number for the subtask
    // xPos x position (in pixels) of the last block inserted
    // yPos y position (in pixels) of the last block inserted
    // subtaskBlockNum will be set to the Extend global block number
    //
    // Return:
    // true no errors occured
    // false error occured
    //
    // ------------------------------------------------------------------------------
    protected int addSubtask(Task task, Subtask subtask, int subtaskNum, int cpuNum)
            throws Exception {

        int subtaskBlockNum;
        int constBlockNum; // constant block number
        xPos = xPos + 100; // 100 pixels between this and last block
        boolean isBypass = subtask.getBypass() > 0 && subtask.getDownsamplingFactor() == 0;

        // subtask
        String subtaskBlock = null;
        if (task instanceof AperiodicTask) {
        	subtaskBlock = "SSSubtask";
        } else {
        	subtaskBlock = (isBypass) ? "SubtaskBypass" : "Subtask";
        }
        subtaskBlockNum = extend.placeBlock(subtaskBlock, SEI_LIB,
                                            xPos, yPos, ExtendMetaBridge.PLACE_NO_NEIGH,
                                            ExtendMetaBridge.PLACE_LEFT);

        // constant: execution time
        double execTime = subtask.getExecTimeDistribution().getComputedMean();
        
        /* commented out because it was annoying in the SPCA model */
//        if (!(subtask.getExecTimeDistribution() instanceof Constant)) {
//            printToConsole("Warning: Only Constant distribution is fully supported for execution times. Using mean as constant in "
//                    + subtask.getName() + ".");
//        }

        // *** This is a temprorary fix because the extend SS block requries
        // that
        // *** the extecution time is an integer value
        if (task instanceof SSTask) {
            execTime = Math.ceil(execTime);
        }

        constBlockNum = extend.insertConstBlock(execTime, -50, 10, subtaskBlockNum,
                                                ExtendMetaBridge.PLACE_BOTTOM);
        extend.makeConnection(constBlockNum, 0, subtaskBlockNum, 2);

        // constant: priority
        constBlockNum = extend.insertConstBlock((double) (-1 * (subtask.getPriority())), -20, 40,
                                                subtaskBlockNum, ExtendMetaBridge.PLACE_BOTTOM);
        extend.makeConnection(constBlockNum, 0, subtaskBlockNum, 3);

        // constant: CPU number
        constBlockNum = extend.insertConstBlock((double) cpuNum, 10, 70, subtaskBlockNum,
                                                ExtendMetaBridge.PLACE_BOTTOM);
        extend.makeConnection(constBlockNum, 0, subtaskBlockNum, 4);

        // constant: bypass number
        if (isBypass) {
            constBlockNum = extend.insertConstBlock((double) subtask.getBypass(), 40, 40,
                                                    subtaskBlockNum, ExtendMetaBridge.PLACE_BOTTOM);
            extend.makeConnection(constBlockNum, 0, subtaskBlockNum, 5);
        }

        extend.setBlockLabel(subtaskBlockNum, subtask.getName());

        return subtaskBlockNum;
    }

    
    /**
     * @param consolePrinter The consolePrinter to set.
     */
    public void setConsolePrinter(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }
    
    protected void printToConsole(String msg) {
        if (consolePrinter != null) {
            consolePrinter.printToConsole(msg);
        } else {
            System.out.println(msg);
        }
    }

}
