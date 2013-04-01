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

import edu.cmu.sei.pacc.perf.model.Distribution;
import edu.cmu.sei.pacc.perf.model.ModelPackage;
import edu.cmu.sei.pacc.perf.model.SSTask;

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
 * An implementation of the model object '<em><b>SS Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl#getBudget <em>Budget</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl#getReplenishmentPeriod <em>Replenishment Period</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl#getBackgroundPriority <em>Background Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SSTaskImpl extends AperiodicTaskImpl implements SSTask {
	/**
	 * The default value of the '{@link #getBudget() <em>Budget</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBudget()
	 * @generated
	 * @ordered
	 */
    protected static final double BUDGET_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBudget() <em>Budget</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBudget()
	 * @generated
	 * @ordered
	 */
    protected double budget = BUDGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getReplenishmentPeriod() <em>Replenishment Period</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getReplenishmentPeriod()
	 * @generated
	 * @ordered
	 */
    protected static final int REPLENISHMENT_PERIOD_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getReplenishmentPeriod() <em>Replenishment Period</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getReplenishmentPeriod()
	 * @generated
	 * @ordered
	 */
    protected int replenishmentPeriod = REPLENISHMENT_PERIOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getBackgroundPriority() <em>Background Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBackgroundPriority()
	 * @generated
	 * @ordered
	 */
    protected static final int BACKGROUND_PRIORITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBackgroundPriority() <em>Background Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBackgroundPriority()
	 * @generated
	 * @ordered
	 */
    protected int backgroundPriority = BACKGROUND_PRIORITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SSTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SS_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getBudget() {
		return budget;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBudget(double newBudget) {
		double oldBudget = budget;
		budget = newBudget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SS_TASK__BUDGET, oldBudget, budget));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getReplenishmentPeriod() {
		return replenishmentPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setReplenishmentPeriod(int newReplenishmentPeriod) {
		int oldReplenishmentPeriod = replenishmentPeriod;
		replenishmentPeriod = newReplenishmentPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SS_TASK__REPLENISHMENT_PERIOD, oldReplenishmentPeriod, replenishmentPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getBackgroundPriority() {
		return backgroundPriority;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBackgroundPriority(int newBackgroundPriority) {
		int oldBackgroundPriority = backgroundPriority;
		backgroundPriority = newBackgroundPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SS_TASK__BACKGROUND_PRIORITY, oldBackgroundPriority, backgroundPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.SS_TASK__BUDGET:
				return new Double(getBudget());
			case ModelPackage.SS_TASK__REPLENISHMENT_PERIOD:
				return new Integer(getReplenishmentPeriod());
			case ModelPackage.SS_TASK__BACKGROUND_PRIORITY:
				return new Integer(getBackgroundPriority());
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
			case ModelPackage.SS_TASK__BUDGET:
				setBudget(((Double)newValue).doubleValue());
				return;
			case ModelPackage.SS_TASK__REPLENISHMENT_PERIOD:
				setReplenishmentPeriod(((Integer)newValue).intValue());
				return;
			case ModelPackage.SS_TASK__BACKGROUND_PRIORITY:
				setBackgroundPriority(((Integer)newValue).intValue());
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
			case ModelPackage.SS_TASK__BUDGET:
				setBudget(BUDGET_EDEFAULT);
				return;
			case ModelPackage.SS_TASK__REPLENISHMENT_PERIOD:
				setReplenishmentPeriod(REPLENISHMENT_PERIOD_EDEFAULT);
				return;
			case ModelPackage.SS_TASK__BACKGROUND_PRIORITY:
				setBackgroundPriority(BACKGROUND_PRIORITY_EDEFAULT);
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
			case ModelPackage.SS_TASK__BUDGET:
				return budget != BUDGET_EDEFAULT;
			case ModelPackage.SS_TASK__REPLENISHMENT_PERIOD:
				return replenishmentPeriod != REPLENISHMENT_PERIOD_EDEFAULT;
			case ModelPackage.SS_TASK__BACKGROUND_PRIORITY:
				return backgroundPriority != BACKGROUND_PRIORITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (budget: ");
		result.append(budget);
		result.append(", replenishmentPeriod: ");
		result.append(replenishmentPeriod);
		result.append(", backgroundPriority: ");
		result.append(backgroundPriority);
		result.append(')');
		return result.toString();
	}

} //SSTaskImpl
