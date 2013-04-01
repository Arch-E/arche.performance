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

package edu.cmu.sei.pacc.perf.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subtask</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getPriority <em>Priority</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#isRetAnchorUsed <em>Ret Anchor Used</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#isActivationSynchronous <em>Activation Synchronous</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getExecTimeDistribution <em>Exec Time Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getBypass <em>Bypass</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getDownsamplingFactor <em>Downsampling Factor</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getCallingThreadPriority <em>Calling Thread Priority</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getMutexes <em>Mutexes</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.Subtask#getPinId <em>Pin Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask()
 * @model
 * @generated
 */
public interface Subtask extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_Priority()
	 * @model
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);

	/**
	 * Returns the value of the '<em><b>Ret Anchor Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ret Anchor Used</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ret Anchor Used</em>' attribute.
	 * @see #setRetAnchorUsed(boolean)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_RetAnchorUsed()
	 * @model
	 * @generated
	 */
	boolean isRetAnchorUsed();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#isRetAnchorUsed <em>Ret Anchor Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ret Anchor Used</em>' attribute.
	 * @see #isRetAnchorUsed()
	 * @generated
	 */
	void setRetAnchorUsed(boolean value);

	/**
	 * Returns the value of the '<em><b>Activation Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Synchronous</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activation Synchronous</em>' attribute.
	 * @see #setActivationSynchronous(boolean)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_ActivationSynchronous()
	 * @model
	 * @generated
	 */
	boolean isActivationSynchronous();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#isActivationSynchronous <em>Activation Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Synchronous</em>' attribute.
	 * @see #isActivationSynchronous()
	 * @generated
	 */
	void setActivationSynchronous(boolean value);

	/**
	 * Returns the value of the '<em><b>Exec Time Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Time Distribution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Time Distribution</em>' containment reference.
	 * @see #setExecTimeDistribution(Distribution)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_ExecTimeDistribution()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Distribution getExecTimeDistribution();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getExecTimeDistribution <em>Exec Time Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exec Time Distribution</em>' containment reference.
	 * @see #getExecTimeDistribution()
	 * @generated
	 */
	void setExecTimeDistribution(Distribution value);

	/**
	 * Returns the value of the '<em><b>Bypass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bypass</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bypass</em>' attribute.
	 * @see #setBypass(int)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_Bypass()
	 * @model
	 * @generated
	 */
	int getBypass();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getBypass <em>Bypass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bypass</em>' attribute.
	 * @see #getBypass()
	 * @generated
	 */
	void setBypass(int value);

	/**
	 * Returns the value of the '<em><b>Downsampling Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Downsampling Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Downsampling Factor</em>' attribute.
	 * @see #setDownsamplingFactor(int)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_DownsamplingFactor()
	 * @model
	 * @generated
	 */
	int getDownsamplingFactor();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getDownsamplingFactor <em>Downsampling Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Downsampling Factor</em>' attribute.
	 * @see #getDownsamplingFactor()
	 * @generated
	 */
	void setDownsamplingFactor(int value);

	/**
	 * Returns the value of the '<em><b>Calling Thread Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calling Thread Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calling Thread Priority</em>' attribute.
	 * @see #setCallingThreadPriority(int)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_CallingThreadPriority()
	 * @model
	 * @generated
	 */
	int getCallingThreadPriority();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getCallingThreadPriority <em>Calling Thread Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calling Thread Priority</em>' attribute.
	 * @see #getCallingThreadPriority()
	 * @generated
	 */
	void setCallingThreadPriority(int value);

	/**
	 * Returns the value of the '<em><b>Mutexes</b></em>' reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.model.Mutex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutexes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutexes</em>' reference list.
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_Mutexes()
	 * @model
	 * @generated
	 */
	EList<Mutex> getMutexes();

	/**
	 * Returns the value of the '<em><b>Pin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pin Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pin Id</em>' attribute.
	 * @see #setPinId(int)
	 * @see edu.cmu.sei.pacc.perf.model.ModelPackage#getSubtask_PinId()
	 * @model
	 * @generated
	 */
	int getPinId();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.model.Subtask#getPinId <em>Pin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pin Id</em>' attribute.
	 * @see #getPinId()
	 * @generated
	 */
	void setPinId(int value);

} // Subtask
