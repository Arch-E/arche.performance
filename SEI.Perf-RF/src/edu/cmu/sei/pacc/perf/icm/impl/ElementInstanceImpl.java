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

package edu.cmu.sei.pacc.perf.icm.impl;

import edu.cmu.sei.pacc.perf.icm.ElementInstance;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.icm.PinInstance;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl#getNodeNum <em>Node Num</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl#getTypeNodeNum <em>Type Node Num</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl#getPins <em>Pins</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ElementInstanceImpl extends EObjectImpl implements ElementInstance {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNodeNum() <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeNum()
	 * @generated
	 * @ordered
	 */
	protected static final int NODE_NUM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNodeNum() <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeNum()
	 * @generated
	 * @ordered
	 */
	protected int nodeNum = NODE_NUM_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected String typeName = TYPE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeNodeNum() <em>Type Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeNodeNum()
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_NODE_NUM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTypeNodeNum() <em>Type Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeNodeNum()
	 * @generated
	 * @ordered
	 */
	protected int typeNodeNum = TYPE_NODE_NUM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPins() <em>Pins</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPins()
	 * @generated
	 * @ordered
	 */
	protected EList pins;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IcmPackage.Literals.ELEMENT_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ELEMENT_INSTANCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNodeNum() {
		return nodeNum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeNum(int newNodeNum) {
		int oldNodeNum = nodeNum;
		nodeNum = newNodeNum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ELEMENT_INSTANCE__NODE_NUM, oldNodeNum, nodeNum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeName(String newTypeName) {
		String oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ELEMENT_INSTANCE__TYPE_NAME, oldTypeName, typeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTypeNodeNum() {
		return typeNodeNum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeNodeNum(int newTypeNodeNum) {
		int oldTypeNodeNum = typeNodeNum;
		typeNodeNum = newTypeNodeNum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ELEMENT_INSTANCE__TYPE_NODE_NUM, oldTypeNodeNum, typeNodeNum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPins() {
		if (pins == null) {
			pins = new EObjectContainmentWithInverseEList(PinInstance.class, this, IcmPackage.ELEMENT_INSTANCE__PINS, IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE);
		}
		return pins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.ELEMENT_INSTANCE__PINS:
				return ((InternalEList)getPins()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.ELEMENT_INSTANCE__PINS:
				return ((InternalEList)getPins()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IcmPackage.ELEMENT_INSTANCE__NAME:
				return getName();
			case IcmPackage.ELEMENT_INSTANCE__NODE_NUM:
				return new Integer(getNodeNum());
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NAME:
				return getTypeName();
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NODE_NUM:
				return new Integer(getTypeNodeNum());
			case IcmPackage.ELEMENT_INSTANCE__PINS:
				return getPins();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IcmPackage.ELEMENT_INSTANCE__NAME:
				setName((String)newValue);
				return;
			case IcmPackage.ELEMENT_INSTANCE__NODE_NUM:
				setNodeNum(((Integer)newValue).intValue());
				return;
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NAME:
				setTypeName((String)newValue);
				return;
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NODE_NUM:
				setTypeNodeNum(((Integer)newValue).intValue());
				return;
			case IcmPackage.ELEMENT_INSTANCE__PINS:
				getPins().clear();
				getPins().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case IcmPackage.ELEMENT_INSTANCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IcmPackage.ELEMENT_INSTANCE__NODE_NUM:
				setNodeNum(NODE_NUM_EDEFAULT);
				return;
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NAME:
				setTypeName(TYPE_NAME_EDEFAULT);
				return;
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NODE_NUM:
				setTypeNodeNum(TYPE_NODE_NUM_EDEFAULT);
				return;
			case IcmPackage.ELEMENT_INSTANCE__PINS:
				getPins().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IcmPackage.ELEMENT_INSTANCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IcmPackage.ELEMENT_INSTANCE__NODE_NUM:
				return nodeNum != NODE_NUM_EDEFAULT;
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NAME:
				return TYPE_NAME_EDEFAULT == null ? typeName != null : !TYPE_NAME_EDEFAULT.equals(typeName);
			case IcmPackage.ELEMENT_INSTANCE__TYPE_NODE_NUM:
				return typeNodeNum != TYPE_NODE_NUM_EDEFAULT;
			case IcmPackage.ELEMENT_INSTANCE__PINS:
				return pins != null && !pins.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", nodeNum: ");
		result.append(nodeNum);
		result.append(", typeName: ");
		result.append(typeName);
		result.append(", typeNodeNum: ");
		result.append(typeNodeNum);
		result.append(')');
		return result.toString();
	}

} //ElementInstanceImpl
