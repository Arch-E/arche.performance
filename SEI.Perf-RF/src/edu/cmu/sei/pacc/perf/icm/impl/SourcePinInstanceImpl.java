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
import edu.cmu.sei.pacc.perf.icm.SinkPinInstance;
import edu.cmu.sei.pacc.perf.icm.SourcePinInstance;
import edu.cmu.sei.pacc.perf.icm.SourcePinMode;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Pin Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl#getSinks <em>Sinks</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl#getReactSinks <em>React Sinks</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SourcePinInstanceImpl extends PinInstanceImpl implements SourcePinInstance {
	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final SourcePinMode MODE_EDEFAULT = SourcePinMode.UNICAST_LITERAL;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected SourcePinMode mode = MODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSinks() <em>Sinks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSinks()
	 * @generated
	 * @ordered
	 */
	protected EList sinks;

	/**
	 * The cached value of the '{@link #getReactSinks() <em>React Sinks</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getReactSinks()
	 * @generated
	 * @ordered
	 */
    protected EList reactSinks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourcePinInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IcmPackage.Literals.SOURCE_PIN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourcePinMode getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(SourcePinMode newMode) {
		SourcePinMode oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SOURCE_PIN_INSTANCE__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSinks() {
		if (sinks == null) {
			sinks = new EObjectWithInverseResolvingEList.ManyInverse(SinkPinInstance.class, this, IcmPackage.SOURCE_PIN_INSTANCE__SINKS, IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES);
		}
		return sinks;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getReactSinks() {
		if (reactSinks == null) {
			reactSinks = new EObjectWithInverseResolvingEList.ManyInverse(SinkPinInstance.class, this, IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS, IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES);
		}
		return reactSinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.SOURCE_PIN_INSTANCE__SINKS:
				return ((InternalEList)getSinks()).basicAdd(otherEnd, msgs);
			case IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS:
				return ((InternalEList)getReactSinks()).basicAdd(otherEnd, msgs);
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
			case IcmPackage.SOURCE_PIN_INSTANCE__SINKS:
				return ((InternalEList)getSinks()).basicRemove(otherEnd, msgs);
			case IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS:
				return ((InternalEList)getReactSinks()).basicRemove(otherEnd, msgs);
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
			case IcmPackage.SOURCE_PIN_INSTANCE__MODE:
				return getMode();
			case IcmPackage.SOURCE_PIN_INSTANCE__SINKS:
				return getSinks();
			case IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS:
				return getReactSinks();
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
			case IcmPackage.SOURCE_PIN_INSTANCE__MODE:
				setMode((SourcePinMode)newValue);
				return;
			case IcmPackage.SOURCE_PIN_INSTANCE__SINKS:
				getSinks().clear();
				getSinks().addAll((Collection)newValue);
				return;
			case IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS:
				getReactSinks().clear();
				getReactSinks().addAll((Collection)newValue);
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
			case IcmPackage.SOURCE_PIN_INSTANCE__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case IcmPackage.SOURCE_PIN_INSTANCE__SINKS:
				getSinks().clear();
				return;
			case IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS:
				getReactSinks().clear();
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
			case IcmPackage.SOURCE_PIN_INSTANCE__MODE:
				return mode != MODE_EDEFAULT;
			case IcmPackage.SOURCE_PIN_INSTANCE__SINKS:
				return sinks != null && !sinks.isEmpty();
			case IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS:
				return reactSinks != null && !reactSinks.isEmpty();
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
		result.append(" (mode: ");
		result.append(mode);
		result.append(')');
		return result.toString();
	}

} //SourcePinInstanceImpl
