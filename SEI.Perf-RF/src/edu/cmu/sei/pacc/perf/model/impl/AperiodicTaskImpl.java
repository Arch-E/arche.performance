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

package edu.cmu.sei.pacc.perf.model.impl;

import edu.cmu.sei.pacc.perf.model.AperiodicTask;
import edu.cmu.sei.pacc.perf.model.Distribution;
import edu.cmu.sei.pacc.perf.model.ModelPackage;

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
 * An implementation of the model object '<em><b>Aperiodic Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.AperiodicTaskImpl#getInterarrivalDistribution <em>Interarrival Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AperiodicTaskImpl extends TaskImpl implements AperiodicTask {
	/**
	 * The cached value of the '{@link #getInterarrivalDistribution() <em>Interarrival Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterarrivalDistribution()
	 * @generated
	 * @ordered
	 */
	protected Distribution interarrivalDistribution;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AperiodicTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.APERIODIC_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Distribution getInterarrivalDistribution() {
		return interarrivalDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterarrivalDistribution(Distribution newInterarrivalDistribution, NotificationChain msgs) {
		Distribution oldInterarrivalDistribution = interarrivalDistribution;
		interarrivalDistribution = newInterarrivalDistribution;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION, oldInterarrivalDistribution, newInterarrivalDistribution);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterarrivalDistribution(Distribution newInterarrivalDistribution) {
		if (newInterarrivalDistribution != interarrivalDistribution) {
			NotificationChain msgs = null;
			if (interarrivalDistribution != null)
				msgs = ((InternalEObject)interarrivalDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION, null, msgs);
			if (newInterarrivalDistribution != null)
				msgs = ((InternalEObject)newInterarrivalDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION, null, msgs);
			msgs = basicSetInterarrivalDistribution(newInterarrivalDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION, newInterarrivalDistribution, newInterarrivalDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION:
				return basicSetInterarrivalDistribution(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION:
				return getInterarrivalDistribution();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION:
				setInterarrivalDistribution((Distribution)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION:
				setInterarrivalDistribution((Distribution)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION:
				return interarrivalDistribution != null;
		}
		return super.eIsSet(featureID);
	}

} //AperiodicTaskImpl
