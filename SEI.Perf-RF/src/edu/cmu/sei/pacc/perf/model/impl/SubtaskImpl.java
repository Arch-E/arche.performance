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
import edu.cmu.sei.pacc.perf.model.Mutex;
import edu.cmu.sei.pacc.perf.model.Subtask;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subtask</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#isRetAnchorUsed <em>Ret Anchor Used</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#isActivationSynchronous <em>Activation Synchronous</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getExecTimeDistribution <em>Exec Time Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getBypass <em>Bypass</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getDownsamplingFactor <em>Downsampling Factor</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getCallingThreadPriority <em>Calling Thread Priority</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getMutexes <em>Mutexes</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl#getPinId <em>Pin Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubtaskImpl extends EObjectImpl implements Subtask {
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
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int PRIORITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected int priority = PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isRetAnchorUsed() <em>Ret Anchor Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRetAnchorUsed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RET_ANCHOR_USED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRetAnchorUsed() <em>Ret Anchor Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRetAnchorUsed()
	 * @generated
	 * @ordered
	 */
	protected boolean retAnchorUsed = RET_ANCHOR_USED_EDEFAULT;

	/**
	 * The default value of the '{@link #isActivationSynchronous() <em>Activation Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivationSynchronous()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVATION_SYNCHRONOUS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActivationSynchronous() <em>Activation Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivationSynchronous()
	 * @generated
	 * @ordered
	 */
	protected boolean activationSynchronous = ACTIVATION_SYNCHRONOUS_EDEFAULT;

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
	 * The default value of the '{@link #getBypass() <em>Bypass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBypass()
	 * @generated
	 * @ordered
	 */
	protected static final int BYPASS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBypass() <em>Bypass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBypass()
	 * @generated
	 * @ordered
	 */
	protected int bypass = BYPASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDownsamplingFactor() <em>Downsampling Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownsamplingFactor()
	 * @generated
	 * @ordered
	 */
	protected static final int DOWNSAMPLING_FACTOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDownsamplingFactor() <em>Downsampling Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownsamplingFactor()
	 * @generated
	 * @ordered
	 */
	protected int downsamplingFactor = DOWNSAMPLING_FACTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallingThreadPriority() <em>Calling Thread Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallingThreadPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int CALLING_THREAD_PRIORITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCallingThreadPriority() <em>Calling Thread Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallingThreadPriority()
	 * @generated
	 * @ordered
	 */
	protected int callingThreadPriority = CALLING_THREAD_PRIORITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMutexes() <em>Mutexes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMutexes()
	 * @generated
	 * @ordered
	 */
	protected EList<Mutex> mutexes;

	/**
	 * The default value of the '{@link #getPinId() <em>Pin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPinId()
	 * @generated
	 * @ordered
	 */
	protected static final int PIN_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPinId() <em>Pin Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPinId()
	 * @generated
	 * @ordered
	 */
	protected int pinId = PIN_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubtaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SUBTASK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(int newPriority) {
		int oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRetAnchorUsed() {
		return retAnchorUsed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRetAnchorUsed(boolean newRetAnchorUsed) {
		boolean oldRetAnchorUsed = retAnchorUsed;
		retAnchorUsed = newRetAnchorUsed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__RET_ANCHOR_USED, oldRetAnchorUsed, retAnchorUsed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActivationSynchronous() {
		return activationSynchronous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivationSynchronous(boolean newActivationSynchronous) {
		boolean oldActivationSynchronous = activationSynchronous;
		activationSynchronous = newActivationSynchronous;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__ACTIVATION_SYNCHRONOUS, oldActivationSynchronous, activationSynchronous));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION, oldExecTimeDistribution, newExecTimeDistribution);
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
				msgs = ((InternalEObject)execTimeDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION, null, msgs);
			if (newExecTimeDistribution != null)
				msgs = ((InternalEObject)newExecTimeDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION, null, msgs);
			msgs = basicSetExecTimeDistribution(newExecTimeDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION, newExecTimeDistribution, newExecTimeDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getBypass() {
		return bypass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBypass(int newBypass) {
		int oldBypass = bypass;
		bypass = newBypass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__BYPASS, oldBypass, bypass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDownsamplingFactor() {
		return downsamplingFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDownsamplingFactor(int newDownsamplingFactor) {
		int oldDownsamplingFactor = downsamplingFactor;
		downsamplingFactor = newDownsamplingFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__DOWNSAMPLING_FACTOR, oldDownsamplingFactor, downsamplingFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCallingThreadPriority() {
		return callingThreadPriority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallingThreadPriority(int newCallingThreadPriority) {
		int oldCallingThreadPriority = callingThreadPriority;
		callingThreadPriority = newCallingThreadPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__CALLING_THREAD_PRIORITY, oldCallingThreadPriority, callingThreadPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Mutex> getMutexes() {
		if (mutexes == null) {
			mutexes = new EObjectResolvingEList<Mutex>(Mutex.class, this, ModelPackage.SUBTASK__MUTEXES);
		}
		return mutexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPinId() {
		return pinId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPinId(int newPinId) {
		int oldPinId = pinId;
		pinId = newPinId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBTASK__PIN_ID, oldPinId, pinId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION:
				return basicSetExecTimeDistribution(null, msgs);
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
			case ModelPackage.SUBTASK__NAME:
				return getName();
			case ModelPackage.SUBTASK__PRIORITY:
				return new Integer(getPriority());
			case ModelPackage.SUBTASK__RET_ANCHOR_USED:
				return isRetAnchorUsed() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.SUBTASK__ACTIVATION_SYNCHRONOUS:
				return isActivationSynchronous() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION:
				return getExecTimeDistribution();
			case ModelPackage.SUBTASK__BYPASS:
				return new Integer(getBypass());
			case ModelPackage.SUBTASK__DOWNSAMPLING_FACTOR:
				return new Integer(getDownsamplingFactor());
			case ModelPackage.SUBTASK__CALLING_THREAD_PRIORITY:
				return new Integer(getCallingThreadPriority());
			case ModelPackage.SUBTASK__MUTEXES:
				return getMutexes();
			case ModelPackage.SUBTASK__PIN_ID:
				return new Integer(getPinId());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.SUBTASK__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.SUBTASK__PRIORITY:
				setPriority(((Integer)newValue).intValue());
				return;
			case ModelPackage.SUBTASK__RET_ANCHOR_USED:
				setRetAnchorUsed(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.SUBTASK__ACTIVATION_SYNCHRONOUS:
				setActivationSynchronous(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION:
				setExecTimeDistribution((Distribution)newValue);
				return;
			case ModelPackage.SUBTASK__BYPASS:
				setBypass(((Integer)newValue).intValue());
				return;
			case ModelPackage.SUBTASK__DOWNSAMPLING_FACTOR:
				setDownsamplingFactor(((Integer)newValue).intValue());
				return;
			case ModelPackage.SUBTASK__CALLING_THREAD_PRIORITY:
				setCallingThreadPriority(((Integer)newValue).intValue());
				return;
			case ModelPackage.SUBTASK__MUTEXES:
				getMutexes().clear();
				getMutexes().addAll((Collection<? extends Mutex>)newValue);
				return;
			case ModelPackage.SUBTASK__PIN_ID:
				setPinId(((Integer)newValue).intValue());
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
			case ModelPackage.SUBTASK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__RET_ANCHOR_USED:
				setRetAnchorUsed(RET_ANCHOR_USED_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__ACTIVATION_SYNCHRONOUS:
				setActivationSynchronous(ACTIVATION_SYNCHRONOUS_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION:
				setExecTimeDistribution((Distribution)null);
				return;
			case ModelPackage.SUBTASK__BYPASS:
				setBypass(BYPASS_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__DOWNSAMPLING_FACTOR:
				setDownsamplingFactor(DOWNSAMPLING_FACTOR_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__CALLING_THREAD_PRIORITY:
				setCallingThreadPriority(CALLING_THREAD_PRIORITY_EDEFAULT);
				return;
			case ModelPackage.SUBTASK__MUTEXES:
				getMutexes().clear();
				return;
			case ModelPackage.SUBTASK__PIN_ID:
				setPinId(PIN_ID_EDEFAULT);
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
			case ModelPackage.SUBTASK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.SUBTASK__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case ModelPackage.SUBTASK__RET_ANCHOR_USED:
				return retAnchorUsed != RET_ANCHOR_USED_EDEFAULT;
			case ModelPackage.SUBTASK__ACTIVATION_SYNCHRONOUS:
				return activationSynchronous != ACTIVATION_SYNCHRONOUS_EDEFAULT;
			case ModelPackage.SUBTASK__EXEC_TIME_DISTRIBUTION:
				return execTimeDistribution != null;
			case ModelPackage.SUBTASK__BYPASS:
				return bypass != BYPASS_EDEFAULT;
			case ModelPackage.SUBTASK__DOWNSAMPLING_FACTOR:
				return downsamplingFactor != DOWNSAMPLING_FACTOR_EDEFAULT;
			case ModelPackage.SUBTASK__CALLING_THREAD_PRIORITY:
				return callingThreadPriority != CALLING_THREAD_PRIORITY_EDEFAULT;
			case ModelPackage.SUBTASK__MUTEXES:
				return mutexes != null && !mutexes.isEmpty();
			case ModelPackage.SUBTASK__PIN_ID:
				return pinId != PIN_ID_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", priority: ");
		result.append(priority);
		result.append(", retAnchorUsed: ");
		result.append(retAnchorUsed);
		result.append(", activationSynchronous: ");
		result.append(activationSynchronous);
		result.append(", bypass: ");
		result.append(bypass);
		result.append(", downsamplingFactor: ");
		result.append(downsamplingFactor);
		result.append(", callingThreadPriority: ");
		result.append(callingThreadPriority);
		result.append(", pinId: ");
		result.append(pinId);
		result.append(')');
		return result.toString();
	}

} //SubtaskImpl
