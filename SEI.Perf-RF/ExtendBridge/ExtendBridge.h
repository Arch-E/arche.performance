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
 
 //********************************************************************
// File: ExtendBridge.h
//
// Description:
//      
//
// History:
//		see VSS history
//
//********************************************************************

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include <windows.h> 
#include <ole2.h>
#include <comutil.h>

// supress debug info warning for STL
#pragma warning(disable:4786)
#include <map>
#include <string>

using namespace std;

#pragma comment(lib, "comsupp.lib")
#pragma comment(lib, "kernel32.lib")


#define EXECUTE_ID         1       // Extend IDispatch interface IDs
#define REQUEST_ID         2
#define POKE_ID            3

#define MAX_COMMAND_LEN    1000    // maximum string length for a command

#define PLACE_LEFT         0       // for Extend PlaceBlock MODL function
#define PLACE_TOP          1
#define PLACE_RIGHT        2
#define PLACE_BOTTOM       3
#define PLACE_NO_NEIGH     -1

#define CPU_NUM            1       // CPU number to place in subtask blocks

#define SEI_LIB            "SEI-2.lix"
#define BPR_LIB            "BPR.lix"
#define UTILITIES_LIB      "Utilities.lix"
#define DISCRETE_LIB       "Discrete Event.lix"
#define GENERIC_LIB        "Generic.lix"
#define PLOTTER_LIB        "Plotter.lix"



//*** This may not be the best way to do this.
//*** Currently the distribution parameter in 
//*** Task.h is a string, so the possible string
//*** values are defined here.
#define DIST_CONSTANT_STR		"C"			// clock distribution type string
#define	DIST_EXPONENTIAL_STR	"M"
#define DIST_UNIFORM_STR		"U"
#define DIST_NORMAL_STR			"N"
#define DIST_CONSTANT			3				// Extend Generator number corresponding to dist type
#define	DIST_EXPONENTIAL		6	
#define DIST_UNIFORM			16
#define DIST_NORMAL				12

//typedef vector<Task> TaskVector;				// copied from AsyncGen.cpp

typedef map<string, string> LibMap;				// <library id, library name>
typedef pair<string, string> StringPair;


/**
 * Class used to communicate with Extend
 */ 
class ExtendBridge {

    public:
        ExtendBridge();
        virtual ~ExtendBridge();		
        bool OpenExtend(bool openLibraries = false);
        bool openLibrary(const char *library);
        bool openStdLibraries();
        bool RunSimulation(double startTime, double endTime, double numSims);
        bool SaveModel(const char *name);//changed from char * to const char *
        bool closeModel();
        bool closeExtend();
        bool InsertConstBlock(double constValue, int xPos, int yPos, int neigh, int side, int &blockNum);
        bool GACreate(const char* name, int type, int rows, int colss);
        bool NewModel();
        bool SaveModelAs(const char* name);
        bool OpenExtendFile(const char* name);
        bool PlaceBlock(const char* blockName, const char* libName, int xPos, int yPos, int neigh, int side, int &blockNum);
        bool MakeConnection(int blockFrom, int conFrom, int blockTo, int conTo);
        bool SetRunParameters(double endTime, double startTime, double numSims, double numStep);
        bool SetDirty(bool dirty);

        // Extend communication wrappers
        bool PokeInt(string name, int blockNum, int value);
        bool PokeInt(const char* name,  int blockNum, int value);
        bool PeekInt(const char* name,  int blockNum, int* value);
        bool PokeDouble(string name, int blockNum, double value);
        bool PokeDouble(const char* name,  int blockNum, double value);
        bool PeekDouble(const char* name,  int blockNum, double* value);
        bool PokeString(string name, int blockNum, string value);
        bool PokeString(const char* name,  int blockNum, string value);
        bool PeekString(const char* name,  int blockNum, char* value);
        bool Execute(const char* command);
        bool Execute_ReturnVoid(const char*  command);
        bool Execute_ReturnInt(const char*  command, int &retVal);
        bool Execute_ReturnReal(const char*  command, double &retVal);
        bool Execute_ReturnStr(const char*  command, char* &retVal);

        // Extend communication methods
        bool Extend_Execute(BSTR command);
        bool Extend_Request(BSTR itemStr, BSTR topicStr, VARIANT *var);
        bool Extend_Poke(BSTR valueStr, BSTR itemStr, BSTR topicStr);

        char *getExtendPath();

		static bool isExtendRegistered();

    private:
        LPDISPATCH pExtendDisp;     // reference to Extend
        LibMap libraries;           // list of libraries used by PACC models
        char *extendPath;           // pathname to Extend installation
};


