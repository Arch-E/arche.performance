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
 
 #include "ExtendMetaBridge.h"
#include "ExtendBridge.h"

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    createN
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_createN
			(JNIEnv *env, jobject obj) {
	ExtendBridge* pExtend = 0;

	pExtend = new ExtendBridge();
	jfieldID pointerId = env->GetFieldID(env->GetObjectClass(obj), "pointer", "J");
	env->SetIntField(obj, pointerId, (jint) pExtend);


	return pExtend->OpenExtend(false);
}


static ExtendBridge* getExtendPointer(JNIEnv* env, jobject obj) {
	jfieldID pointerId = env->GetFieldID(env->GetObjectClass(obj), "pointer", "J");
	return (ExtendBridge*) env->GetIntField(obj, pointerId);
}

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    newModelN
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_newModelN
		(JNIEnv *env, jobject obj) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->NewModel();
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    releaseExtendN
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_releaseExtendN
		(JNIEnv *env, jobject obj) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	delete pExtend;
	jfieldID pointerId = env->GetFieldID(env->GetObjectClass(obj), "pointer", "J");
	env->SetIntField(obj, pointerId, 0);
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    executeN
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_executeN
		(JNIEnv *env, jobject obj, jstring command) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->Execute(env->GetStringUTFChars(command, 0));
}

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    insertConstBlockN
 * Signature: (DIIII[I)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_insertConstBlockN
		(JNIEnv *env, jobject obj, jdouble value, jint xpos, jint ypos, jint neigh, jint side, jintArray blockNum) {
	int blockNumValue;
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	bool rval = pExtend->InsertConstBlock(value, xpos, ypos, neigh, side, blockNumValue);
	if (rval) {
		jint jBlockNum = blockNumValue;
		env->SetIntArrayRegion(blockNum, 0, 1, &jBlockNum);
	}
	return rval;
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    makeConnectionN
 * Signature: (IIII)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_makeConnectionN
		(JNIEnv *env, jobject obj, jint blockFrom, jint conFrom, jint blockTo, jint conTo) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->MakeConnection(blockFrom, conFrom, blockTo, conTo);
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    placeBlockN
 * Signature: (Ljava/lang/String;Ljava/lang/String;IIII[I)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_placeBlockN
		(JNIEnv *env, jobject obj, jstring blockName, jstring libName, jint xpos, jint ypos, jint neigh, jint side, jintArray blockNum) {
	int blockNumValue;
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	bool rval = pExtend->PlaceBlock(env->GetStringUTFChars(blockName, 0),
									env->GetStringUTFChars(libName, 0),
									xpos, ypos, neigh, side, blockNumValue);
	if (rval) {
		jint jBlockNum = blockNumValue;
		env->SetIntArrayRegion(blockNum, 0, 1, &jBlockNum);
	}
	return rval;
}

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    executeReturnIntN
 * Signature: (Ljava/lang/String;[I)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_executeReturnIntN
		(JNIEnv *env, jobject obj, jstring command, jintArray theResult) {
	int result;
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	bool rval = pExtend->Execute_ReturnInt(env->GetStringUTFChars(command, 0), result);
	if (rval) {
		jint jResut = result;
		env->SetIntArrayRegion(theResult, 0, 1, &jResut);
	}
	return rval;
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    pokeDoubleN
 * Signature: (Ljava/lang/String;ID)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_pokeDoubleN
		(JNIEnv *env, jobject obj, jstring name, jint blockNum, jdouble value) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->PokeDouble(env->GetStringUTFChars(name, 0), blockNum, value);
}

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    pokeIntN
 * Signature: (Ljava/lang/String;II)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_pokeIntN
		(JNIEnv *env, jobject obj, jstring name, jint blockNum, jint value) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->PokeInt(env->GetStringUTFChars(name, 0), blockNum, value);
}

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    pokeStringN
 * Signature: (Ljava/lang/String;ILjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_pokeStringN
		(JNIEnv *env, jobject obj, jstring name, jint blockNum, jstring value) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->PokeString(env->GetStringUTFChars(name, 0), blockNum, env->GetStringUTFChars(value, 0));
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    runSimulationN
 * Signature: (DDD)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_runSimulationN
		(JNIEnv *env, jobject obj, jdouble startTime, jdouble endTime, jdouble numSims) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->RunSimulation(startTime, endTime, numSims);
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    saveModelN
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_saveModelN
	(JNIEnv *env, jobject obj, jstring name) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->SaveModel(env->GetStringUTFChars(name, 0));
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    closeExtendN
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_closeExtendN
		(JNIEnv *env, jobject obj) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->closeExtend();
}

/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    openLibraryN
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_openLibraryN
		(JNIEnv *env, jobject obj, jstring name) {
	ExtendBridge* pExtend = getExtendPointer(env, obj);
	return pExtend->openLibrary(env->GetStringUTFChars(name, 0));
}


/*
 * Class:     edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge
 * Method:    isExtendRegistered
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_edu_cmu_sei_pacc_perf_eval_extend_ExtendMetaBridge_isExtendRegistered
		(JNIEnv *env, jclass cls) {

	return ExtendBridge::isExtendRegistered();
}
