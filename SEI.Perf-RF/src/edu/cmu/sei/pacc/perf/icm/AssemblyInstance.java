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

package edu.cmu.sei.pacc.perf.icm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getElements <em>Elements</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getConnectionOverhead <em>Connection Overhead</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getScenarios <em>Scenarios</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getMutexes <em>Mutexes</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getSourceFile <em>Source File</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance()
 * @model
 * @generated
 */
public interface AssemblyInstance extends EObject {
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
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.ElementInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance_Elements()
	 * @model type="edu.cmu.sei.pacc.perf.icm.ElementInstance" containment="true"
	 * @generated
	 */
	EList getElements();

	/**
	 * Returns the value of the '<em><b>Connection Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Overhead</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Overhead</em>' attribute.
	 * @see #setConnectionOverhead(double)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance_ConnectionOverhead()
	 * @model
	 * @generated
	 */
	double getConnectionOverhead();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getConnectionOverhead <em>Connection Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Overhead</em>' attribute.
	 * @see #getConnectionOverhead()
	 * @generated
	 */
	void setConnectionOverhead(double value);

	/**
	 * Returns the value of the '<em><b>Scenarios</b></em>' containment reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.Scenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenarios</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenarios</em>' containment reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance_Scenarios()
	 * @model type="edu.cmu.sei.pacc.perf.icm.Scenario" containment="true"
	 * @generated
	 */
	EList getScenarios();

	/**
	 * Returns the value of the '<em><b>Mutexes</b></em>' containment reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.Mutex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutexes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutexes</em>' containment reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance_Mutexes()
	 * @model type="edu.cmu.sei.pacc.perf.icm.Mutex" containment="true"
	 * @generated
	 */
	EList getMutexes();

	/**
	 * Returns the value of the '<em><b>Source File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source File</em>' attribute.
	 * @see #setSourceFile(String)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getAssemblyInstance_SourceFile()
	 * @model
	 * @generated
	 */
	String getSourceFile();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getSourceFile <em>Source File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source File</em>' attribute.
	 * @see #getSourceFile()
	 * @generated
	 */
	void setSourceFile(String value);

} // AssemblyInstance
