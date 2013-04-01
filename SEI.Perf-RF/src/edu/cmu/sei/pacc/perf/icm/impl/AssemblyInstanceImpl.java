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

import edu.cmu.sei.pacc.perf.icm.AssemblyInstance;
import edu.cmu.sei.pacc.perf.icm.ElementInstance;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;

import edu.cmu.sei.pacc.perf.icm.Mutex;
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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembly Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl#getConnectionOverhead <em>Connection Overhead</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl#getScenarios <em>Scenarios</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl#getMutexes <em>Mutexes</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl#getSourceFile <em>Source File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssemblyInstanceImpl extends EObjectImpl implements AssemblyInstance {
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
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList elements;

	/**
	 * The default value of the '{@link #getConnectionOverhead() <em>Connection Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionOverhead()
	 * @generated
	 * @ordered
	 */
	protected static final double CONNECTION_OVERHEAD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getConnectionOverhead() <em>Connection Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionOverhead()
	 * @generated
	 * @ordered
	 */
	protected double connectionOverhead = CONNECTION_OVERHEAD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScenarios() <em>Scenarios</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList scenarios;

	/**
	 * The cached value of the '{@link #getMutexes() <em>Mutexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMutexes()
	 * @generated
	 * @ordered
	 */
	protected EList mutexes;

	/**
	 * The default value of the '{@link #getSourceFile() <em>Source File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceFile()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceFile() <em>Source File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceFile()
	 * @generated
	 * @ordered
	 */
	protected String sourceFile = SOURCE_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblyInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IcmPackage.Literals.ASSEMBLY_INSTANCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ASSEMBLY_INSTANCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList(ElementInstance.class, this, IcmPackage.ASSEMBLY_INSTANCE__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getConnectionOverhead() {
		return connectionOverhead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionOverhead(double newConnectionOverhead) {
		double oldConnectionOverhead = connectionOverhead;
		connectionOverhead = newConnectionOverhead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD, oldConnectionOverhead, connectionOverhead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarios() {
		if (scenarios == null) {
			scenarios = new EObjectContainmentEList(Scenario.class, this, IcmPackage.ASSEMBLY_INSTANCE__SCENARIOS);
		}
		return scenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMutexes() {
		if (mutexes == null) {
			mutexes = new EObjectContainmentEList(Mutex.class, this, IcmPackage.ASSEMBLY_INSTANCE__MUTEXES);
		}
		return mutexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceFile() {
		return sourceFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceFile(String newSourceFile) {
		String oldSourceFile = sourceFile;
		sourceFile = newSourceFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.ASSEMBLY_INSTANCE__SOURCE_FILE, oldSourceFile, sourceFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.ASSEMBLY_INSTANCE__ELEMENTS:
				return ((InternalEList)getElements()).basicRemove(otherEnd, msgs);
			case IcmPackage.ASSEMBLY_INSTANCE__SCENARIOS:
				return ((InternalEList)getScenarios()).basicRemove(otherEnd, msgs);
			case IcmPackage.ASSEMBLY_INSTANCE__MUTEXES:
				return ((InternalEList)getMutexes()).basicRemove(otherEnd, msgs);
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
			case IcmPackage.ASSEMBLY_INSTANCE__NAME:
				return getName();
			case IcmPackage.ASSEMBLY_INSTANCE__ELEMENTS:
				return getElements();
			case IcmPackage.ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD:
				return new Double(getConnectionOverhead());
			case IcmPackage.ASSEMBLY_INSTANCE__SCENARIOS:
				return getScenarios();
			case IcmPackage.ASSEMBLY_INSTANCE__MUTEXES:
				return getMutexes();
			case IcmPackage.ASSEMBLY_INSTANCE__SOURCE_FILE:
				return getSourceFile();
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
			case IcmPackage.ASSEMBLY_INSTANCE__NAME:
				setName((String)newValue);
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection)newValue);
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD:
				setConnectionOverhead(((Double)newValue).doubleValue());
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__SCENARIOS:
				getScenarios().clear();
				getScenarios().addAll((Collection)newValue);
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__MUTEXES:
				getMutexes().clear();
				getMutexes().addAll((Collection)newValue);
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__SOURCE_FILE:
				setSourceFile((String)newValue);
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
			case IcmPackage.ASSEMBLY_INSTANCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__ELEMENTS:
				getElements().clear();
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD:
				setConnectionOverhead(CONNECTION_OVERHEAD_EDEFAULT);
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__SCENARIOS:
				getScenarios().clear();
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__MUTEXES:
				getMutexes().clear();
				return;
			case IcmPackage.ASSEMBLY_INSTANCE__SOURCE_FILE:
				setSourceFile(SOURCE_FILE_EDEFAULT);
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
			case IcmPackage.ASSEMBLY_INSTANCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IcmPackage.ASSEMBLY_INSTANCE__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case IcmPackage.ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD:
				return connectionOverhead != CONNECTION_OVERHEAD_EDEFAULT;
			case IcmPackage.ASSEMBLY_INSTANCE__SCENARIOS:
				return scenarios != null && !scenarios.isEmpty();
			case IcmPackage.ASSEMBLY_INSTANCE__MUTEXES:
				return mutexes != null && !mutexes.isEmpty();
			case IcmPackage.ASSEMBLY_INSTANCE__SOURCE_FILE:
				return SOURCE_FILE_EDEFAULT == null ? sourceFile != null : !SOURCE_FILE_EDEFAULT.equals(sourceFile);
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
		result.append(", connectionOverhead: ");
		result.append(connectionOverhead);
		result.append(", sourceFile: ");
		result.append(sourceFile);
		result.append(')');
		return result.toString();
	}

} //AssemblyInstanceImpl
