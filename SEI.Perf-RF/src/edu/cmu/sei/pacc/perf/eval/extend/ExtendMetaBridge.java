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

public class ExtendMetaBridge {

    private long pointer; // create() sets this. DO NOT modify

    private native boolean createN();

    private native boolean executeN(String command);

    private native boolean executeReturnIntN(String command, int[] result);

    private native boolean insertConstBlockN(double constValue, int xPos, int yPos, int neigh,
            int side, int[] blockNum);

    private native boolean makeConnectionN(int blockFrom, int conFrom, int blockTo, int conTo);

    private native boolean newModelN();

    private native boolean placeBlockN(String blockName, String libName, int xPos, int yPos,
            int neigh, int side, int[] blockNum);

    private native boolean pokeDoubleN(String name, int blockNum, double value);

    private native boolean pokeIntN(String name, int blockNum, int value);

    private native boolean pokeStringN(String name, int blockNum, String value);

    private native boolean runSimulationN(double startTime, double endTime, double numSims);

    private native boolean saveModelN(String name);

    private native boolean closeExtendN();

    private native boolean openLibraryN(String library);

    private native void releaseExtendN();
    
    public static native boolean isExtendRegistered();

    public static final int PLACE_LEFT          = 0;

    public static final int PLACE_TOP           = 1;

    public static final int PLACE_RIGHT         = 2;

    public static final int PLACE_BOTTOM        = 3;

    public static final int PLACE_NO_NEIGH      = -1;

    public static final int DIST_CONSTANT       = 3;

    public static final int DIST_EXPONENTIAL    = 6;

    public static final int DIST_UNIFORM        = 16;

    public static final int DIST_NORMAL         = 12;

    public static final int FIND_BLOCK_BY_LABEL = 1;

    /**
     * Construct a bridge to control Extend
     * It assumes the ExtendBridge.dll has already been loaded
     * 
     * @throws Exception if it could not instantiate Extend
     */
    public ExtendMetaBridge() throws Exception {
        if (!createN()) {
            pointer = 0;
            throw new Exception("Failed to launch Extend");
        }
    }

    public void execute(String command) throws Exception {
        if (!executeN(command)) {
            throw new Exception(this.getClass().getName() + ".execute(" + command + ") failed");
        }
    }

    public int insertConstBlock(double constValue, int xPos, int yPos, int neigh, int side)
            throws Exception {
        int[] blockNum = new int[1];
        if (!insertConstBlockN(constValue, xPos, yPos, neigh, side, blockNum)) {
            throw new Exception(this.getClass().getName() + ".insertConstBlock(" + constValue + ","
                    + xPos + "," + yPos + "," + neigh + "," + side + ") failed");
        }
        return blockNum[0];
    }

    public void makeConnection(int blockFrom, int conFrom, int blockTo, int conTo) throws Exception {
        if (!makeConnectionN(blockFrom, conFrom, blockTo, conTo)) {
            throw new Exception(this.getClass().getName() + ".makeConnection(" + blockFrom + ","
                    + conFrom + "," + blockTo + "," + conTo + ") failed");
        }
    }

    public void newModel() throws Exception {
        if (!newModelN()) {
            throw new Exception(this.getClass().getName() + ".newModel() failed");
        }
    }

    public int placeBlock(String blockName, String libName, int xPos, int yPos, int neigh, int side)
            throws Exception {
        int[] blockNum = new int[1];
        if (!placeBlockN(blockName, libName, xPos, yPos, neigh, side, blockNum)) {
            throw new Exception(this.getClass().getName() + ".placeBlock(" + blockName + ","
                    + libName + "," + xPos + "," + yPos + "," + neigh + "," + side + ") failed");
        }
        return blockNum[0];
    }

    public void pokeDouble(String name, int blockNum, double value) throws Exception {
        if (!pokeDoubleN(name, blockNum, value)) {
            throw new Exception(this.getClass().getName() + ".pokeDouble(" + name + "," + blockNum
                    + "," + value + ") failed");
        }
    }

    public void pokeInt(String name, int blockNum, int value) throws Exception {
        if (!pokeIntN(name, blockNum, value)) {
            throw new Exception(this.getClass().getName() + ".pokeInt(" + name + "," + blockNum
                    + "," + value + ") failed");
        }
    }

    public void pokeString(String name, int blockNum, String value) throws Exception {
        if (!pokeStringN(name, blockNum, value)) {
            throw new Exception(this.getClass().getName() + ".pokeString(" + name + "," + blockNum
                    + "," + value + ") failed");
        }
    }

    public void runSimulation(double startTime, double endTime, double numSims) throws Exception {
        if (!runSimulationN(startTime, endTime, numSims)) {
            throw new Exception(this.getClass().getName() + ".runSimulation(" + startTime + ","
                    + endTime + "," + numSims + ") failed");
        }
    }

    public void saveModel(String name) throws Exception {
        if (!saveModelN(name)) {
            throw new Exception(this.getClass().getName() + ".saveModel(" + name + ") failed");
        }
    }

    public void closeExtend() throws Exception {
        if (!closeExtendN()) {
            throw new Exception(this.getClass().getName() + ".closeExtend() failed");
        }
    }

    public void openLibrary(String library) throws Exception {
        if (!openLibraryN(library)) {
            throw new Exception(this.getClass().getName() + ".openLibrary(" + library + ") failed");
        }
    }

    public void releaseExtend() {
        if (pointer == 0)
            return;
        releaseExtendN();
    }

    public void setBlockLabel(int blockNumber, String label) throws Exception {
        execute("SetBlockLabel(" + blockNumber + ",\"" + label + "\");");
    }

    public int executeReturnInt(String command) throws Exception {
        int[] result = new int[1];
        if (!executeReturnIntN(command, result)) {
            throw new Exception(this.getClass().getName() + ".executeReturnInt(" + command
                    + ") failed");
        }
        return result[0];
    }

    public int findBlock(String searchString, int which, int openDialogs, int wholeWords,
            int justBlockNumber) throws Exception {
        return executeReturnInt("FindBlock(\"" + searchString + "\"," + which + "," + openDialogs
                + "," + wholeWords + "," + justBlockNumber + ");");
    }
    
    public void closeModel() {
        try {
            execute("ExecuteMenuCommand(4);"); 
        } catch (Exception e) {}; // ignore the error
    }

    public void closeModel(String name) {
        try {
            Thread.sleep(1000); // this sleeps are needed to avoid buggy Extend behavior
            executeReturnInt("ActivateWorksheet(\"" + name + "\");");
            Thread.sleep(1000);
            closeModel();
        } catch (Exception e) {}; // ignore the error
    }
}
