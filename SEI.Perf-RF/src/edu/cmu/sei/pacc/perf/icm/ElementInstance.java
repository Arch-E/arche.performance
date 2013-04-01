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
 * A representation of the model object '<em><b>Element Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getNodeNum <em>Node Num</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeNodeNum <em>Type Node Num</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getPins <em>Pins</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getElementInstance()
 * @model abstract="true"
 * @generated
 */
public interface ElementInstance extends EObject {
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
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getElementInstance_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getElementInstance_NodeNum()
	 * @model
	 * @generated
	 */
	int getNodeNum();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getNodeNum <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Num</em>' attribute.
	 * @see #getNodeNum()
	 * @generated
	 */
	void setNodeNum(int value);

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getElementInstance_TypeName()
	 * @model
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Type Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Node Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Node Num</em>' attribute.
	 * @see #setTypeNodeNum(int)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getElementInstance_TypeNodeNum()
	 * @model
	 * @generated
	 */
	int getTypeNodeNum();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeNodeNum <em>Type Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Node Num</em>' attribute.
	 * @see #getTypeNodeNum()
	 * @generated
	 */
	void setTypeNodeNum(int value);

	/**
	 * Returns the value of the '<em><b>Pins</b></em>' containment reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.PinInstance}.
	 * It is bidirectional and its opposite is '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getElementInstance <em>Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pins</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pins</em>' containment reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getElementInstance_Pins()
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance#getElementInstance
	 * @model type="edu.cmu.sei.pacc.perf.icm.PinInstance" opposite="elementInstance" containment="true"
	 * @generated
	 */
	EList getPins();

} // ElementInstance
