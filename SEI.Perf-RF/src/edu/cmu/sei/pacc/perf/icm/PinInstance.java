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
 * A representation of the model object '<em><b>Pin Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getNodeNum <em>Node Num</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getElementInstance <em>Element Instance</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getScenarios <em>Scenarios</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getPinInstance()
 * @model abstract="true"
 * @generated
 */
public interface PinInstance extends EObject {
	/**
	 * Returns the value of the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Num</em>' attribute.
	 * @see #setNodeNum(int)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getPinInstance_NodeNum()
	 * @model
	 * @generated
	 */
	int getNodeNum();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getNodeNum <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Num</em>' attribute.
	 * @see #getNodeNum()
	 * @generated
	 */
	void setNodeNum(int value);

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
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getPinInstance_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Element Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getPins <em>Pins</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Instance</em>' container reference.
	 * @see #setElementInstance(ElementInstance)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getPinInstance_ElementInstance()
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance#getPins
	 * @model opposite="pins"
	 * @generated
	 */
	ElementInstance getElementInstance();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getElementInstance <em>Element Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Instance</em>' container reference.
	 * @see #getElementInstance()
	 * @generated
	 */
	void setElementInstance(ElementInstance value);

	/**
	 * Returns the value of the '<em><b>Scenarios</b></em>' reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.Scenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenarios</em>' reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getPinInstance_Scenarios()
	 * @model type="edu.cmu.sei.pacc.perf.icm.Scenario"
	 * @generated
	 */
	EList getScenarios();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getPinInstance_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // PinInstance
