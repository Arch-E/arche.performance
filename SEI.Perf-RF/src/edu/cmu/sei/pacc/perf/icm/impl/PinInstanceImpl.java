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
import edu.cmu.sei.pacc.perf.icm.Mutex;
import edu.cmu.sei.pacc.perf.icm.PinInstance;

import edu.cmu.sei.pacc.perf.icm.Scenario;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pin Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl#getNodeNum <em>Node Num</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl#getElementInstance <em>Element Instance</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl#getScenarios <em>Scenarios</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PinInstanceImpl extends EObjectImpl implements PinInstance {
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
	 * The cached value of the '{@link #getScenarios() <em>Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList scenarios;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PinInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IcmPackage.Literals.PIN_INSTANCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.PIN_INSTANCE__NODE_NUM, oldNodeNum, nodeNum));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.PIN_INSTANCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementInstance getElementInstance() {
		if (eContainerFeatureID != IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE) return null;
		return (ElementInstance)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElementInstance(ElementInstance newElementInstance, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newElementInstance, IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementInstance(ElementInstance newElementInstance) {
		if (newElementInstance != eInternalContainer() || (eContainerFeatureID != IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE && newElementInstance != null)) {
			if (EcoreUtil.isAncestor(this, newElementInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newElementInstance != null)
				msgs = ((InternalEObject)newElementInstance).eInverseAdd(this, IcmPackage.ELEMENT_INSTANCE__PINS, ElementInstance.class, msgs);
			msgs = basicSetElementInstance(newElementInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE, newElementInstance, newElementInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarios() {
		if (scenarios == null) {
			scenarios = new EObjectResolvingEList(Scenario.class, this, IcmPackage.PIN_INSTANCE__SCENARIOS);
		}
		return scenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.PIN_INSTANCE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetElementInstance((ElementInstance)otherEnd, msgs);
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
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				return basicSetElementInstance(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				return eInternalContainer().eInverseRemove(this, IcmPackage.ELEMENT_INSTANCE__PINS, ElementInstance.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IcmPackage.PIN_INSTANCE__NODE_NUM:
				return new Integer(getNodeNum());
			case IcmPackage.PIN_INSTANCE__NAME:
				return getName();
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				return getElementInstance();
			case IcmPackage.PIN_INSTANCE__SCENARIOS:
				return getScenarios();
			case IcmPackage.PIN_INSTANCE__ID:
				return new Integer(getId());
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
			case IcmPackage.PIN_INSTANCE__NODE_NUM:
				setNodeNum(((Integer)newValue).intValue());
				return;
			case IcmPackage.PIN_INSTANCE__NAME:
				setName((String)newValue);
				return;
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				setElementInstance((ElementInstance)newValue);
				return;
			case IcmPackage.PIN_INSTANCE__SCENARIOS:
				getScenarios().clear();
				getScenarios().addAll((Collection)newValue);
				return;
			case IcmPackage.PIN_INSTANCE__ID:
				setId(((Integer)newValue).intValue());
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
			case IcmPackage.PIN_INSTANCE__NODE_NUM:
				setNodeNum(NODE_NUM_EDEFAULT);
				return;
			case IcmPackage.PIN_INSTANCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				setElementInstance((ElementInstance)null);
				return;
			case IcmPackage.PIN_INSTANCE__SCENARIOS:
				getScenarios().clear();
				return;
			case IcmPackage.PIN_INSTANCE__ID:
				setId(ID_EDEFAULT);
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
			case IcmPackage.PIN_INSTANCE__NODE_NUM:
				return nodeNum != NODE_NUM_EDEFAULT;
			case IcmPackage.PIN_INSTANCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IcmPackage.PIN_INSTANCE__ELEMENT_INSTANCE:
				return getElementInstance() != null;
			case IcmPackage.PIN_INSTANCE__SCENARIOS:
				return scenarios != null && !scenarios.isEmpty();
			case IcmPackage.PIN_INSTANCE__ID:
				return id != ID_EDEFAULT;
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
		result.append(" (nodeNum: ");
		result.append(nodeNum);
		result.append(", name: ");
		result.append(name);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //PinInstanceImpl
