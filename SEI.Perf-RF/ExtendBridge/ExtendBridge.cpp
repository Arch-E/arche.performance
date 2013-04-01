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
 
 //******************************************************************************
// File: ExtendBridge.cpp
//
// This class is used to interact with Extend. It has methods to create an
// Extend model, set the value of the parameters of the model and execute
// the model.
// OLE Automation is used to interact with Extend.
// For more information, see Extend Developer's Reference (v6):
//      - page D87: global variables (this class uses Global0, GlobalInt0 and GlobalStr0)
//      - page D252: Extend as an OLE Automation server
//      - Appendix C: menu commands, which can also be called via OLE.
//
// History:
//      see VSS history
//******************************************************************************

#include "stdafx.h"
#include "ExtendBridge.h"



static WCHAR EXTEND_CLSID_STRING[] = L"{E167B362-7044-11d2-99DE-00C0230406DF}";


//******************************************************************************
// PUBLIC METHODS
//******************************************************************************

//------------------------------------------------------------------------------
// Method: ExtendBridge
//
// Description: Constructor.
//
// Return:
//		none
//
//------------------------------------------------------------------------------
ExtendBridge::ExtendBridge() {
	// initialize the OLE
	OleInitialize(NULL);

    pExtendDisp = NULL;
    extendPath = NULL;

    //
    // Standard libraries (libraries usually required by SEI models)
    // 
    libraries.insert(StringPair(SEI_LIB, "SEI-2.lix"));
    libraries.insert(StringPair(BPR_LIB, "BPR.lix"));
    libraries.insert(StringPair(UTILITIES_LIB, "Utilities.lix"));
    libraries.insert(StringPair(DISCRETE_LIB, "Discrete Event.lix"));
    libraries.insert(StringPair(GENERIC_LIB, "Generic.lix"));
    libraries.insert(StringPair(PLOTTER_LIB, "Plotter.lix"));
}


//------------------------------------------------------------------------------
// Method: ~ExtendBridge
//
// Description: Desctructor.
//
// Parameters:
//		none
//
// Return:
//		none
//
//------------------------------------------------------------------------------
ExtendBridge::~ExtendBridge() {
	if (extendPath != NULL) {
		delete[] extendPath;
	}

	// done using the OLE
    if (pExtendDisp != NULL) {
	    pExtendDisp->Release();
    }
	OleUninitialize();
}


//------------------------------------------------------------------------------
// Method: RunSimulation
//
// Description: Runs the Extend model.
//
// Parameters:
//		startTime		simulation start time (in milliseconds)
//		endTime			simulation end time (in milliseconds)
//		numSims			number of simulations
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::RunSimulation(double startTime, double endTime, double numSims) {
	bool result = true;
	printf("\nRunning Extend simulation...\n");


	// --- set the simulation parameters ---
	// sets the time units to milliseconds
	result = Execute("SetTimeUnits(2);");
    if (!result) {
		printf("            ERROR: Unable to set time units.\n\n");
		return(false);
	}
	printf("            Set time units.\n");

	// set the run time parameters
	// *** note: numSteps not really needed for discrete event simulation
	// *** it is hard-coded here
	result = SetRunParameters(endTime, startTime, numSims, 1001);
    if (!result) {
		printf("            ERROR: Unable to set run parameters.\n\n");
		return(false);
	}
	printf("            Set run parameters.\n");

#if 0
	// --- set the animation to visible ---
	result = Execute("ExecuteMenuCommand(2020);");
	if (!result) {
		printf("            Unable to set animation to visible.\n\n");
		//return(false);  // not a fatal error, do not return
	}
	printf("            Set animation to visible.\n");
#endif

	// --- run the simulation ---
    // control returns immediately after starting the model
	result = Execute("ExecuteMenuCommand(6000);");
    if (!result) {
		return(false);
    }
	printf("        Simulation complete.\n");

	return(result);
}


//------------------------------------------------------------------------------
// Method: SaveModel
//
// Description: Saves the Extend model.
//
// Parameters:
//		name			path and name to save the model to
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::SaveModel(const char *name) {

	return(SaveModelAs(name));		// save model
}


/**
 * Calls the close model menu command in Extend.
 * 
 */
bool ExtendBridge::closeModel() {
    bool result = Execute("ExecuteMenuCommand(4);");
    if (!result) {
    	printf("        ERROR: Unable to close model in Extend.\n\n");
        return false;
    }
    return true;
}



/**
 * Closes Extend by calling the File | Exit menu option.
 * NOTE: if any open models have not been saved, Extend will prompt the
 *       user to opt to save the model. Therefore, make sure you save the 
 *       model (method SaveModel) before closing Extend
 * 
 */
bool ExtendBridge::closeExtend() {

    bool result = Execute("ExecuteMenuCommand(1);");
    if (!result) {
    	printf("        ERROR: Unable to close Extend.\n\n");
        return false;
    }
    printf("        Extend is closed.\n");
    return true;
}


//------------------------------------------------------------------------------
// Method: OpenExtend
//
// Description: Finds the Extend application if it is running.  If it is not
//		running, this code will open the application and then get a reference
//		to it. 
//
// Parameters:
//		openLibraries: if true will also open libraries usually required
//                     by SEI models
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::OpenExtend(bool openLibraries) {
	LPUNKNOWN	m_pUnknown = NULL;
	CLSID 		clsid;

	CLSIDFromString (EXTEND_CLSID_STRING, &clsid);

	// try to create an instance of the Extend object
	HRESULT hResult = GetActiveObject(clsid, NULL, (IUnknown **) &m_pUnknown);

	if (hResult == S_OK) {
    	// found active object, now get a pointer to its IDispatch interface
		hResult = m_pUnknown->QueryInterface(IID_IDispatch, (void **) &pExtendDisp);
		hResult = m_pUnknown->Release();
		printf("        Extend already open.\n");
    } else {
    	// unable to find active object, open Extend and get IDisplatch pointer
		printf("        Extend not open, opening Extend....\n");

		hResult = CoCreateInstance (
			clsid, 						// class ID of object
			NULL,						// controlling IUnkown
			CLSCTX_LOCAL_SERVER,		// context
			IID_IDispatch,				// interface wanted
			(LPVOID *) &pExtendDisp) ;	// output variable

		if (hResult != NOERROR) {
    		// error - unable to open Extend
			printf("        ERROR: Unable to open Extend.\n\n");
			return(false);
        } else {
			printf("        Extend opened.\n");
            // NOTE: When we run Extend v6, it always start with a blank model 
            // named Model-1.mox. (Extend v5 didn't have this feature)
		}
	}

    if (openLibraries) {
    	// open the extend libraries
        bool result = openStdLibraries();
        if (!result) {
		    printf("        ERROR: Unable to open Extend libraries.\n\n");
        } else {
		    printf("        Extend libraries opened.\n");
        }
        return (result);
    }

	return true;
}


/**
 * Get the full pathname to the Extend application. This function is useful for 
 * determining the location of files for which only the file�s relative location
 * to the Extend application is known.
 * NOTE: due to a bug in Extend, getExtendPath returns NULL in case no model is open.
 */
char *ExtendBridge::getExtendPath() {
    if (extendPath != NULL) {
        return extendPath;
    } else {
    	char command[MAX_COMMAND_LEN];
	    // get the path to extend
	    sprintf(command, "GetAppPath();");
	    bool result = Execute_ReturnStr(command, extendPath);
        if (!result) {
		    return NULL;
        }
        return extendPath;
    }
}


/**
 * Opens a given extend library (argument is the name of the library without path info)
 * Assumes the library will be in the Extend\Libraries directory.
 *
 * If library is only a file name, it is assumed to be in Extend's library directory. If it
 * is a path, that path is used to load the library.
 */
bool ExtendBridge::openLibrary(const char *library) {
	bool result = true;
	char libPath[MAX_COMMAND_LEN];

	/*
	 * if the library is already open, don't even try
	 */
    char command[MAX_COMMAND_LEN];
	char* version = 0;
	sprintf(command, "GetLibraryVersionByName(\"%s\");", library);
	result = Execute_ReturnStr(command, version);
	if (version) delete[] version;
    if (!result) { // is not loaded, actually, GetLibraryVersionByName, loads the library anyway
		if (strchr(library, '\\')) {
			strcpy(libPath, library);
		} else {
			sprintf(libPath, "%s%s%s", getExtendPath(), "Libraries\\", library);
		}
		printf("\t\topen lib: %s\n", libPath);
		result = OpenExtendFile(libPath);
	} else {
		printf("\t\topen lib: %s (v)\n", library); // the v is to tell how it was opened
	}
	return(result);
}

//------------------------------------------------------------------------------
// Opens the Extend libraries that are usually required by SEI models.
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::openStdLibraries() {
	bool result = true;

	// open the libraries
	for (LibMap::iterator lib = libraries.begin(); lib != libraries.end(); lib++) {
		result = openLibrary(lib->second.c_str());
        if (!result) {
			return(false);
        }
	}
	return(result);
}


//------------------------------------------------------------------------------
// Method: SetRunParameters
//
// Description: Sets the Extend simulation run parameters.
//
// Parameters:
//		endTime			simulation end time (in global time units)
//		startTime		simulation start time (in global time units)
//		numSims			number of simulations
//		numStep			number of steps
//						*** number of steps not necessary for discrete event
//						*** simulations, but it needs to be set to avoid errors
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::SetRunParameters(double endTime, double startTime, 
                                    double numSims, double numStep) {

	char command[MAX_COMMAND_LEN];
	bool result;
	int retInt;

	sprintf(command, "SetRunParameters(%f, %f, %f, %f);", endTime, startTime, numSims, numStep);
	result = Execute_ReturnInt(command, retInt);

	// if the command could not be executed or if the return value is non-zero
    if (!result || (retInt < 0)) {
		return(false);
    }

	return(true);
}


//------------------------------------------------------------------------------
// Method: OpenExtendFile
//
// Description: Opens the given Extend file.
//
// Parameters:
//		name			path and name of file to open
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::OpenExtendFile(const char *name) {
	char command[MAX_COMMAND_LEN];
	bool result;
	int retInt = 0;

	sprintf(command, "OpenExtendFile(\"%s\");", name);
	result = Execute_ReturnInt(command, retInt);

	// if the command could not be executed or if the OpenExtendFile function
	// returned a nonzero value, then the open attempt has failed
    if (!result || (retInt != 0)) {
    if (result) printf ("result is true\n");
    if (!result) printf ("result is false\n");
		printf ("retInt = 0x%.8x (%d)\n", retInt, retInt);
    return(false);
    }
	return(true);
}


//------------------------------------------------------------------------------
// Method: SetDirty
//
// Description: Sets/Unsets the `dirty' flag on the active worksheet.
//              A common use for this functionality would be to set the dirty
//              flag to `FALSE' before issuing the ExecutemenuCommand function
//              to close a worksheet. This has the effect of closing the
//              worksheet without querying the user if they want to save, or not. 
//
// Parameters:
//		dirty     bool dirty flag to set the dirty bit within Extend worksheet
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::SetDirty(bool dirty) {
	char command[MAX_COMMAND_LEN];
	bool result;

  sprintf(command, "SetDirty(%d);", dirty?1:0);
	result = Execute_ReturnVoid(command);

	// if the command could not be executed then the SetDirty attempt has failed
    if (!result) {
		return(false);
    }
	return(true);
}


//------------------------------------------------------------------------------
// Method: NewModel
//
// Description: Executes the Extend "New Model" menu command.
//
// Parameters:
//		none
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::NewModel() {
	// New Model is MenuCommand 2 in Extend
	return(Execute("ExecuteMenuCommand(2);"));
}


//------------------------------------------------------------------------------
// Method: SaveModelAs
//
// Description: Saves the current model to the given file name.
//
// *** ALN 08/06/03:  Currently this is not working.  I have emailed Dave Krahl
// *** from Imagine If and he is looking into this.
//
// Parameters:
//		name			path and name to save model to (".mox" is appended)
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::SaveModelAs(const char *name) {
    bool result = true;
    int retInt = 0;
    char command[MAX_COMMAND_LEN];

    // execute the ModL SaveModelAs command
    sprintf(command, "SaveModelAs(\"%s.mox\");", name);
    result = Execute_ReturnInt(command, retInt);  

    // if the command could not be executed or if the SaveModelAs function
    // returned a nonzero value, then the save attempt has failed
    if (!result || retInt != 0) {
        return(false);
    }
    return(true);
}


//------------------------------------------------------------------------------
// Method: InsertConstBlock
//
// Description: Inserts a constant block into the current Extend model.
//
// Parameters:
//		constValue      numeric value of the constant
//		xPos			x position to place block at - if there is a neighbor
//						specified, this position is relative to the neighbor
//		yPos			y position to place block at - if there is a neighbor
//						specified, this position is relative to the neighbor
//		neigh			neighbor block, -1 if no neighbor
//		side			how the block is placed relative to neighbor
//						0 - left
//						1 - top
//						2 - right
//						3 - bottom
//		blockNum		will contain the
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::InsertConstBlock(double constValue, int xPos, int yPos, 
                                    int neigh, int side, int &blockNum) {
	bool result = true;

	// add constant block
	result = PlaceBlock("Constant", GENERIC_LIB, xPos, yPos, neigh, side, blockNum);
    if (!result) {
		return(false);
    }
	// set constant value & return result
	result = PokeDouble("conValue", blockNum, constValue);
	return(result);
}


//------------------------------------------------------------------------------
// Method: GACreate
//
// Description: Creates a globl array.
//
// Parameters:
//		name			name of array
//		type			array type
//						1 - GAReal
//						2 - GAInteger
//						3 - GAString
//						4 - GAString15
//						5 - GAString31
//		rows			number of rows in array
//		cols			number of columns in array
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::GACreate(const char* name, int type, int rows, int cols) {

	char command[MAX_COMMAND_LEN];
	bool result;
	int retInt;

	// create the array
	sprintf(command, "GACreate(\"%s\", %d, %d);", name, type, cols);
	result = Execute_ReturnInt(command, retInt);

	// if the command could not be executed or if the function
	// returned a negataive value, then the attempt has failed
    if (!result || (retInt < 0)) {
		return(false);
    }
	// set the number of rows
	sprintf(command, "GAResize(\"%s\", %d);", name, rows);
	result = Execute_ReturnInt(command, retInt);

	// if the command could not be executed or if the function
	// returned a negataive value, then the attempt has failed
    if (!result || (retInt < 0)) {
		return(false);
    }
	return(true);
}


//------------------------------------------------------------------------------
// Method: PlaceBlock
//
// Description: Places the given block on the current Extend model.
//
// Parameters:
//		blockName		block name to place in worksheet
//		libName			library that block is in
//		xPos			x position to place block at - if there is a neighbor
//						specified, this position is relative to the neighbor
//		yPos			y position to place block at - if there is a neighbor
//						specified, this position is relative to the neighbor
//		neigh			neighbor block, -1 if no neighbor
//		side			how the block is placed relative to neighbor
//						0 - left
//						1 - top
//						2 - right
//						3 - bottom
//		blockNum		will contain the Extend global block number
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PlaceBlock(const char *blockName, const char *libName, int xPos, int yPos, 
                           int neigh, int side, int &blockNum) {

	// create command string
	char command[MAX_COMMAND_LEN];
	sprintf(command, "PlaceBlock(\"%s\", \"%s\", %d, %d, %d, %d);", blockName,
		libName, xPos, yPos, neigh, side);

	// execute command
	bool result = Execute_ReturnInt(command, blockNum);

	// if the command could not be executed or if the block number is negative
    if (!result || (blockNum < 0)) {
		return(false);
    }
	return(true);
}


//------------------------------------------------------------------------------
// Method: MakeConnection
//
// Description: Connects two blocks in the current Extend model.
//
// NOTE: To determine the connector number for a given connector name, 
//       look in the block�s connector pane, counting from the top of the
//       list (which is connector 0). (From Developer's Reference v6 page D144)
//
// Parameters:
//		blockFrom		Extend global block ID of the from block
//		conFrom			connector ID for the given from block
//		blockTo			Extend global block ID of the to block
//		conTo			connector ID for the given to block
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::MakeConnection(int blockFrom, int conFrom, int blockTo, int conTo) {
	char command[MAX_COMMAND_LEN];
	bool result;
	int retInt;

	// execute the command
	sprintf(command, "MakeConnection(%d, %d, %d, %d);", blockFrom, conFrom, blockTo, conTo);
	result = Execute_ReturnInt(command, retInt);

	// if the command could not be executed or if a negative value is returned
    if (!result || (retInt < 0)) {
		return(false);
    }
	return(true);
}


//******************************************************************************
// COMMUNICATION WRAPPERS
//
// These functions wrap the extend communication functions so that they are
// easier to call from the above functions.
//******************************************************************************

//------------------------------------------------------------------------------
// Method: PokeInt
//
// Description: Sets the given integer parameter value for the given block.
//
// Parameters:
//		name			name of the paramter to set
//		blockNum		Extend global block ID of the block
//		value			value to set parameter to
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PokeInt(string name, int blockNum, int value) {
    return PokeInt(name.c_str(), blockNum, value);
}

bool ExtendBridge::PokeInt(const char *name, int blockNum, int value) {
	char item[MAX_COMMAND_LEN];
	char val[MAX_COMMAND_LEN];
	bool result;

	// create the command
    //
    // the item being poked is specified as:
    //
    //      VariableName:#BlockNumber:RowStart:ColStart:RowEnd:ColEnd
    //
    // The value of RowStart, ColStart, RowEnd and ColEnd can be set to
    // zero if the item is not a data table object or an array.
    //
	sprintf(item, "%s:#%d:0:0", name, blockNum);
	sprintf(val, "%d", value);

	// poke the value
	BSTR itemStr = _com_util::ConvertStringToBSTR(item);
	BSTR topicStr = SysAllocString((WCHAR *) L"system");
	BSTR valueStr = _com_util::ConvertStringToBSTR(val);

	result = Extend_Poke(valueStr, itemStr, topicStr);

	SysFreeString(itemStr);
	SysFreeString(topicStr);
	SysFreeString(valueStr);
	return(result);
}

//------------------------------------------------------------------------------
// Method: PeekInt
//
// Description: Returns the given integer parameter value for the given block.
//
// Parameters:
//		name			name of the paramter to read/peek
//		blockNum		Extend global block ID of the block
//		value			value peeked from the block ID
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PeekInt(const char *name, int blockNum, int* value) {
	char item[MAX_COMMAND_LEN];
	bool result;
  VARIANT var;

	// create the command
	sprintf(item, "%s:#%d:0:0", name, blockNum);

	// poke the value
	BSTR itemStr = _com_util::ConvertStringToBSTR(item);
	BSTR topicStr = SysAllocString((WCHAR *) L"system");

	result = Extend_Request(itemStr, topicStr, &var);
    if (!result) {
		SysFreeString(itemStr);
		SysFreeString(topicStr);
		return(false);
	}

	// get the value to return
  *value = atoi(_com_util::ConvertBSTRToString(var.bstrVal));

	SysFreeString(itemStr);
	SysFreeString(topicStr);

	return(result);
}

//------------------------------------------------------------------------------
// Method: PokeDouble
//
// Description: Sets the given double parameter value for the given block.
//
// Parameters:
//		name			name of the paramter to set
//		blockNum		Extend global block ID of the block
//		value			value to set parameter to
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PokeDouble(string name, int blockNum, double value) {
    return PokeDouble(name.c_str(), blockNum, value);
}

bool ExtendBridge::PokeDouble(const char *name, int blockNum, double value) {
	char item[MAX_COMMAND_LEN];
	char val[MAX_COMMAND_LEN];
	bool result;

	// create the command
	sprintf(item, "%s:#%d:0:0", name, blockNum);
	sprintf(val, "%f", value);

	// poke the value
	BSTR itemStr = _com_util::ConvertStringToBSTR(item);
	BSTR topicStr = SysAllocString((WCHAR *) L"system");
	BSTR valueStr = _com_util::ConvertStringToBSTR(val);

	result = Extend_Poke(valueStr, itemStr, topicStr);

	SysFreeString(itemStr);
	SysFreeString(topicStr);
	SysFreeString(valueStr);
	return(result);
}

//------------------------------------------------------------------------------
// Method: PeekDouble
//
// Description: Returns the given double/float parameter value for the given block.
//
// Parameters:
//		name			name of the paramter to read/peek
//		blockNum		Extend global block ID of the block
//		value			value peeked from the block ID
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PeekDouble(const char *name, int blockNum, double* value) {
	char item[MAX_COMMAND_LEN];
	bool result;
  VARIANT var;

	// create the command
	sprintf(item, "%s:#%d:0:0", name, blockNum);

	// poke the value
	BSTR itemStr = _com_util::ConvertStringToBSTR(item);
	BSTR topicStr = SysAllocString((WCHAR *) L"system");

	result = Extend_Request(itemStr, topicStr, &var);
    if (!result) {
		SysFreeString(itemStr);
		SysFreeString(topicStr);
		return(false);
	}

	// get the value to return
  *value = atof(_com_util::ConvertBSTRToString(var.bstrVal));

	SysFreeString(itemStr);
	SysFreeString(topicStr);

	return(result);
}

//------------------------------------------------------------------------------
// Method: PokeString
//
// Description: Sets the given string parameter value for the given block.
//
// Parameters:
//		name			name of the paramter to set
//		blockNum		Extend global block ID of the block
//		value			value to set parameter to
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PokeString(string name, int blockNum, string value) {
    return PokeString(name.c_str(), blockNum, value);
}

bool ExtendBridge::PokeString(const char *name, int blockNum, string value) {
	char item[MAX_COMMAND_LEN];
	bool result;

	// create the command
	sprintf(item, "%s:#%d:0:0", name, blockNum);

	// poke the value
	BSTR itemStr = _com_util::ConvertStringToBSTR(item);
	BSTR topicStr = SysAllocString((WCHAR *) L"system");
	BSTR valueStr = _com_util::ConvertStringToBSTR(value.c_str());

	result = Extend_Poke(valueStr, itemStr, topicStr);

	SysFreeString(itemStr);
	SysFreeString(topicStr);
	SysFreeString(valueStr);
	return(result);
}

//------------------------------------------------------------------------------
// Method: PeekString
//
// Description: Returns the given string parameter value for the given block.
//
// Parameters:
//		name			name of the paramter to read/peek
//		blockNum		Extend global block ID of the block
//		value			value peeked from the block ID
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::PeekString(const char *name, int blockNum, char* value) {
	char item[MAX_COMMAND_LEN];
	bool result;
  VARIANT var;

	// create the command
	sprintf(item, "%s:#%d:0:0", name, blockNum);

	// poke the value
	BSTR itemStr = _com_util::ConvertStringToBSTR(item);
	BSTR topicStr = SysAllocString((WCHAR *) L"system");

	result = Extend_Request(itemStr, topicStr, &var);
    if (!result) {
		SysFreeString(itemStr);
		SysFreeString(topicStr);
		return(false);
	}

	// get the value to return
  strcpy (value, _com_util::ConvertBSTRToString(var.bstrVal));

	SysFreeString(itemStr);
	SysFreeString(topicStr);

	return(result);
}


//------------------------------------------------------------------------------
// Method: Execute
//
// Description: Executes an Extend MODL command.
//
// Parameters:
//		command			command string to execute
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Execute(const char* command) {
	// create the BSTR from the input argument & call to execute
	BSTR commandStr = _com_util::ConvertStringToBSTR(command);
	bool result = Extend_Execute(commandStr);

	SysFreeString(commandStr);
  if (!result) printf ("Execute returning false, bailing out\n");
	return(result);
}


//------------------------------------------------------------------------------
// Method: Execute_ReturnVoid
//
// Description: Executes an Extend MODL command having no
//		return value.
//
// Parameters:
//		command			command string to execute
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Execute_ReturnVoid(const char* command) {
	// add the return value to the command

	// execute the command
	bool result = Execute(command);
    if (!result) {
		return(false);
    }

  return true;
}


//------------------------------------------------------------------------------
// Method: Execute_ReturnInt
//
// Description: Executes an Extend MODL command and retrieves the integer
//		return value.
//
// Parameters:
//		command			command string to execute
//		retVal			will be filled with the return value
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Execute_ReturnInt(const char* command, int &retVal) {
	// add the return value to the command
	char command2[MAX_COMMAND_LEN];
	sprintf(command2, "GlobalInt0 = %s", command);

	// execute the command
	bool result = Execute(command2);
    if (!result) {
    printf ("Execute_ReturnInt returned false bailing out\n");
		return(false);
    }

	// get the string from the Extend workspace
	BSTR itemStr  = SysAllocString((WCHAR *) L"GlobalInt0+:0:0:0");
 	BSTR topicStr = SysAllocString((WCHAR *) L"system");
	VARIANT var;
	result = Extend_Request(itemStr, topicStr, &var);
    if (!result) {
    printf ("Execute_ReturnInt returned false on Extend Request bailing out\n");
		SysFreeString(itemStr);
		SysFreeString(topicStr);
		return(false);
	}

	// get the value to return
	char* string = _com_util::ConvertBSTRToString(var.bstrVal);
	retVal = atoi(string);

	SysFreeString(itemStr);
	SysFreeString(topicStr);
	return true;
}


//------------------------------------------------------------------------------
// Method: Execute_ReturnReal
//
// Description: Executes an Extend MODL command and retrieves the real
//		return value.
//
// Parameters:
//		command			command string to execute
//		retVal			will be filled with the return value
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Execute_ReturnReal(const char* command, double &retVal)
{
	// add the return value to the command
	char command2[MAX_COMMAND_LEN];
  //
  // TBD: Shouldn't this be 'Global0 = %s' ??
  // TBD: see Global variables under System variables
  // TBD: under Chapter 3: The MODL Language of the 
  // TBD: Extend Developer's Reference
  //
	sprintf(command2, "GlobalInt0 = %s", command);

	// execute the command
	bool result = Execute(command2);
    if (!result) {
		return(false);
    }

	// get the string from the Extend workspace
	BSTR itemStr  = SysAllocString((WCHAR *) L"Global0+:0:0:0");
 	BSTR topicStr = SysAllocString((WCHAR *) L"system");
	VARIANT var;
	result = Extend_Request(itemStr, topicStr, &var);
    if (!result) {
		SysFreeString(itemStr);
		SysFreeString(topicStr);
		return(false);
	}

	// get the value to return
	char* string = _com_util::ConvertBSTRToString(var.bstrVal);
	retVal = atof(string);

	SysFreeString(itemStr);
	SysFreeString(topicStr);
	return true;
}



//------------------------------------------------------------------------------
// Method: Execute_ReturnStr
//
// Description: Executes an Extend MODL command and retrieves the string
//		return value.
//
// Parameters:
//		command			command string to execute
//		retVal			will be filled with the return value
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Execute_ReturnStr(const char* command, char* &retVal) {
	// add the return value to the command
	char command2[MAX_COMMAND_LEN];
	sprintf(command2, "GlobalStr0 = %s", command);

	// execute the command
	bool result = Execute(command2);
    if (!result) {
		return(false);
    }

	// get the string from the Extend workspace
	BSTR itemStr  = SysAllocString((WCHAR *) L"GlobalStr0+:0:0:0");
 	BSTR topicStr = SysAllocString((WCHAR *) L"system");
	VARIANT var;
	result = Extend_Request(itemStr, topicStr, &var);
    if (!result) {
		SysFreeString(itemStr);
		SysFreeString(topicStr);
		return(false);
	}

	// get the value to return
	retVal = _com_util::ConvertBSTRToString(var.bstrVal);

	SysFreeString(itemStr);
	SysFreeString(topicStr);
	return true;
}



//******************************************************************************
// COMMUNICATION WITH EXTEND
//
// These are the IDispatch interface functions for Extend.
//******************************************************************************

//------------------------------------------------------------------------------
// Method: Extend_Execute
//
// Description: Executes an Extend command.
//
// Parameters:
//		command			command to execute
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Extend_Execute(BSTR command) {
	// create the variant for the IDisplatch call
	VARIANTARG vString;
	VariantInit(&vString);
	vString.vt = VT_BSTR;
	vString.bstrVal = command;

	// set the DISPPARAMS structure that holds the variant
	DISPPARAMS dp;
   	dp.rgvarg 				= &vString;
	dp.cArgs 				= 1;
	dp.rgdispidNamedArgs 	= NULL;
	dp.cNamedArgs 			= 0;

	// call IDispatch::Invoke()
	EXCEPINFO ei;
	UINT uiErr;
	HRESULT hResult = pExtendDisp->Invoke(
			EXECUTE_ID,				// dispatch ID
			IID_NULL,				// must be IID_NULL
			LOCALE_SYSTEM_DEFAULT,	// how to interpret args
			DISPATCH_METHOD,		// context of Invoke
			&dp,					// pointer to arguments
			NULL,					// pointer to where result should be stored
			&ei,					// exception information
			&uiErr);				// first argument w/ an error


	// check for error
    if (hResult == S_OK) {
//    printf("Extend_Execute result     S_OK, it is 0x%.8x (%d)\n", hResult, hResult);
		return(true);
    } else {
    printf("Extend_Execute result not S_OK, it is 0x%.8x (%d)\n", hResult, hResult);
		return(false);
    }
}


//------------------------------------------------------------------------------
// Method: Extend_Request
//
// Description: Requests a value from the Extend workspace.
//
// Parameters:
//		itemStr			item to retrieve from Extend
//						this should be in the following format:
//						<varName>:#<BlockNum>:<RowStart>:<ColStart>:<RowEnd>:<ColEnd>
//		topicStr		where the variable should be retrieved from
//						example: "system"
//		var				will contain the VARIANT with the value
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Extend_Request(BSTR itemStr, BSTR topicStr, VARIANT *var) {
	// Initialize the variant that will hold the BSTR.  Set the
	// variant's type flag to indicate the fact that this variant
	// holds a BSTR. Place the BSTR into the variant structure.
	VARIANTARG 	*requestVariant;
	requestVariant = (VARIANTARG*) malloc(sizeof(VARIANTARG) *2);

	// itemStr
	VariantInit(&requestVariant[0]);
	requestVariant[0].vt 		= VT_BSTR;
	requestVariant[0].bstrVal 	= itemStr;

	// topicStr
	VariantInit(&requestVariant[1]);
	requestVariant[1].vt 		= VT_BSTR;
    requestVariant[1].bstrVal   = topicStr;

	//	set the DISPPARAMS structure that holds the variant.
	DISPPARAMS 	dp;
	dp.rgvarg 					= requestVariant;
	dp.cArgs 					= 2;
	dp.rgdispidNamedArgs 		= NULL;
	dp.cNamedArgs 				= 0;

	//	call IDispatch::Invoke()
	EXCEPINFO ei;
	UINT uiErr;
	var->vt = VT_EMPTY;
	HRESULT hResult = pExtendDisp->Invoke(
		REQUEST_ID,				// dispatch ID
		IID_NULL,				// must be IID_NULL
		LOCALE_SYSTEM_DEFAULT,	// how to interpret args
		DISPATCH_METHOD,		// context of Invoke
		&dp,					// pointer to arguments
		var,					// pointer to where result should be stored
		&ei,					// exception information
		&uiErr);				// first argument w/ an error

	// check for error
    if (hResult == S_OK) {
//    printf("Extend_Request result     S_OK, it is 0x%.8x (%d)\n", hResult, hResult);
		return(true);
    } else {
    printf("Extend_Request result not S_OK, it is 0x%.8x (%d)\n", hResult, hResult);
    printf("\t\t%s, %s, %s\n", ei.bstrSource, ei.bstrDescription, ei.bstrHelpFile);
    
		return(false);
    }
}


//------------------------------------------------------------------------------
// Method: Extend_Poke
//
// Description: Sets a value in the Extend workspace.
//
// Parameters:
//		valueStr		value to put in Extend
//		itemStr			variable to set to the given value in Extend
//		topicStr		where the variable is located (example: "system")
//		var				will contain the VARIANT with the value
//
// Return:
//		true			no errors occured
//		false			error occured
//
//------------------------------------------------------------------------------
bool ExtendBridge::Extend_Poke(BSTR valueStr, BSTR itemStr, BSTR topicStr) {
	// Initialize the variant that will hold the BSTR.  Set the
	// variant's type flag to indicate the fact that this variant
	// holds a BSTR. Place the BSTR into the variant structure.
	VARIANTARG 	*pokeVariant;
	pokeVariant = (VARIANTARG*) malloc(sizeof(VARIANTARG) *3);

	// valueStr
	VariantInit(&pokeVariant[0]);
	pokeVariant[0].vt 		= VT_BSTR;
	pokeVariant[0].bstrVal 	= valueStr;

	// itemStr
	VariantInit(&pokeVariant[1]);
	pokeVariant[1].vt 		= VT_BSTR;
	pokeVariant[1].bstrVal 	= itemStr;

	// topicStr
	VariantInit(&pokeVariant[2]);
	pokeVariant[2].vt 		= VT_BSTR;
	pokeVariant[2].bstrVal 	= topicStr;

	// set the DISPPARAMS structure that holds the variant.
	DISPPARAMS 	dp;
	dp.rgvarg 					= pokeVariant;
	dp.cArgs 					= 3;
	dp.rgdispidNamedArgs 		= NULL;
	dp.cNamedArgs 				= 0;

	//	call IDispatch::Invoke()
	EXCEPINFO ei;
	UINT uiErr;
	HRESULT hResult = pExtendDisp->Invoke(
		POKE_ID,				// displatch ID
		IID_NULL,				// must be IID_NULL
		LOCALE_SYSTEM_DEFAULT,	// how to interpret args
		DISPATCH_METHOD,		// context of Invoke
		&dp,					// pointer to arguments
		NULL,					// pointer to where result should be stored
		&ei,					// exception information
		&uiErr);				// first argument w/ an error


	// check for error
    if (hResult == S_OK) {
		return(true);
    } else {
		return(false);
    }
}

/**
 * Checks to see if Extend is registered for OLE automation
 */
bool ExtendBridge::isExtendRegistered() {
	bool registered = false;

	OleInitialize(NULL);
	CLSID clsid;
	if (CLSIDFromProgID(L"Extend.Application", &clsid) == S_OK) {
		registered = true;
	}

	return registered;
}


