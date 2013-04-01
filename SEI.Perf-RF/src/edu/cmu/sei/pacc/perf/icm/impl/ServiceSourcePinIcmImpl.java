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

import edu.cmu.sei.pacc.perf.icm.Distribution;
import edu.cmu.sei.pacc.perf.icm.ElementInstance;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm;
import edu.cmu.sei.pacc.perf.icm.SourcePinMode;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Source Pin Icm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl#getEventDistribution <em>Event Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl#getExecTimeDistribution <em>Exec Time Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl#getDeadline <em>Deadline</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceSourcePinIcmImpl extends SourcePinInstanceImpl implements ServiceSourcePinIcm {
	/**
	 * The cached value of the '{@link #getEventDistribution() <em>Event Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventDistribution()
	 * @generated
	 * @ordered
	 */
	protected Distribution eventDistribution;

	/**
	 * The cached value of the '{@link #getExecTimeDistribution() <em>Exec Time Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecTimeDistribution()
	 * @generated
	 * @ordered
	 */
	protected Distribution execTimeDistribution;

	/**
	 * The default value of the '{@link #getDeadline() <em>Deadline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadline()
	 * @generated
	 * @ordered
	 */
	protected static final double DEADLINE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDeadline() <em>Deadline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadline()
	 * @generated
	 * @ordered
	 */
	protected double deadline = DEADLINE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceSourcePinIcmImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IcmPackage.Literals.SERVICE_SOURCE_PIN_ICM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Distribution getEventDistribution() {
		return eventDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventDistribution(Distribution newEventDistribution, NotificationChain msgs) {
		Distribution oldEventDistribution = eventDistribution;
		eventDistribution = newEventDistribution;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION, oldEventDistribution, newEventDistribution);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventDistribution(Distribution newEventDistribution) {
		if (newEventDistribution != eventDistribution) {
			NotificationChain msgs = null;
			if (eventDistribution != null)
				msgs = ((InternalEObject)eventDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION, null, msgs);
			if (newEventDistribution != null)
				msgs = ((InternalEObject)newEventDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION, null, msgs);
			msgs = basicSetEventDistribution(newEventDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION, newEventDistribution, newEventDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Distribution getExecTimeDistribution() {
		return execTimeDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecTimeDistribution(Distribution newExecTimeDistribution, NotificationChain msgs) {
		Distribution oldExecTimeDistribution = execTimeDistribution;
		execTimeDistribution = newExecTimeDistribution;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION, oldExecTimeDistribution, newExecTimeDistribution);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecTimeDistribution(Distribution newExecTimeDistribution) {
		if (newExecTimeDistribution != execTimeDistribution) {
			NotificationChain msgs = null;
			if (execTimeDistribution != null)
				msgs = ((InternalEObject)execTimeDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION, null, msgs);
			if (newExecTimeDistribution != null)
				msgs = ((InternalEObject)newExecTimeDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION, null, msgs);
			msgs = basicSetExecTimeDistribution(newExecTimeDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION, newExecTimeDistribution, newExecTimeDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDeadline() {
		return deadline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeadline(double newDeadline) {
		double oldDeadline = deadline;
		deadline = newDeadline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SERVICE_SOURCE_PIN_ICM__DEADLINE, oldDeadline, deadline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION:
				return basicSetEventDistribution(null, msgs);
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION:
				return basicSetExecTimeDistribution(null, msgs);
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
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION:
				return getEventDistribution();
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION:
				return getExecTimeDistribution();
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__DEADLINE:
				return new Double(getDeadline());
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
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION:
				setEventDistribution((Distribution)newValue);
				return;
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION:
				setExecTimeDistribution((Distribution)newValue);
				return;
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__DEADLINE:
				setDeadline(((Double)newValue).doubleValue());
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
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION:
				setEventDistribution((Distribution)null);
				return;
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION:
				setExecTimeDistribution((Distribution)null);
				return;
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__DEADLINE:
				setDeadline(DEADLINE_EDEFAULT);
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
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION:
				return eventDistribution != null;
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION:
				return execTimeDistribution != null;
			case IcmPackage.SERVICE_SOURCE_PIN_ICM__DEADLINE:
				return deadline != DEADLINE_EDEFAULT;
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
		result.append(" (deadline: ");
		result.append(deadline);
		result.append(')');
		return result.toString();
	}

} //ServiceSourcePinIcmImpl
