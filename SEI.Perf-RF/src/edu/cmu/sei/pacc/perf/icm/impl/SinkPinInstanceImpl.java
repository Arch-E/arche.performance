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
import edu.cmu.sei.pacc.perf.icm.Mutex;
import edu.cmu.sei.pacc.perf.icm.SinkPinInstance;
import edu.cmu.sei.pacc.perf.icm.SinkPinMode;
import edu.cmu.sei.pacc.perf.icm.SourcePinInstance;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sink Pin Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getReactSources <em>React Sources</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getLinkSources <em>Link Sources</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getExecTimeDistribution <em>Exec Time Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getDownsamplingFactor <em>Downsampling Factor</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl#getMutexes <em>Mutexes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SinkPinInstanceImpl extends PinInstanceImpl implements SinkPinInstance {
	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final SinkPinMode MODE_EDEFAULT = SinkPinMode.ASYNCH_LITERAL;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected SinkPinMode mode = MODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReactSources() <em>React Sources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReactSources()
	 * @generated
	 * @ordered
	 */
	protected EList reactSources;

	/**
	 * The cached value of the '{@link #getLinkSources() <em>Link Sources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkSources()
	 * @generated
	 * @ordered
	 */
	protected EList linkSources;

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
	 * The cached value of the '{@link #getMutexes() <em>Mutexes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMutexes()
	 * @generated
	 * @ordered
	 */
	protected EList mutexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SinkPinInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IcmPackage.Literals.SINK_PIN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SinkPinMode getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(SinkPinMode newMode) {
		SinkPinMode oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SINK_PIN_INSTANCE__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReactSources() {
		if (reactSources == null) {
			reactSources = new EObjectWithInverseResolvingEList.ManyInverse(SourcePinInstance.class, this, IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES, IcmPackage.SOURCE_PIN_INSTANCE__REACT_SINKS);
		}
		return reactSources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLinkSources() {
		if (linkSources == null) {
			linkSources = new EObjectWithInverseResolvingEList.ManyInverse(SourcePinInstance.class, this, IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES, IcmPackage.SOURCE_PIN_INSTANCE__SINKS);
		}
		return linkSources;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION, oldExecTimeDistribution, newExecTimeDistribution);
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
				msgs = ((InternalEObject)execTimeDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION, null, msgs);
			if (newExecTimeDistribution != null)
				msgs = ((InternalEObject)newExecTimeDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION, null, msgs);
			msgs = basicSetExecTimeDistribution(newExecTimeDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION, newExecTimeDistribution, newExecTimeDistribution));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SINK_PIN_INSTANCE__PRIORITY, oldPriority, priority));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IcmPackage.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR, oldDownsamplingFactor, downsamplingFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMutexes() {
		if (mutexes == null) {
			mutexes = new EObjectResolvingEList(Mutex.class, this, IcmPackage.SINK_PIN_INSTANCE__MUTEXES);
		}
		return mutexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES:
				return ((InternalEList)getReactSources()).basicAdd(otherEnd, msgs);
			case IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES:
				return ((InternalEList)getLinkSources()).basicAdd(otherEnd, msgs);
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
			case IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES:
				return ((InternalEList)getReactSources()).basicRemove(otherEnd, msgs);
			case IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES:
				return ((InternalEList)getLinkSources()).basicRemove(otherEnd, msgs);
			case IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION:
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
			case IcmPackage.SINK_PIN_INSTANCE__MODE:
				return getMode();
			case IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES:
				return getReactSources();
			case IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES:
				return getLinkSources();
			case IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION:
				return getExecTimeDistribution();
			case IcmPackage.SINK_PIN_INSTANCE__PRIORITY:
				return new Integer(getPriority());
			case IcmPackage.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR:
				return new Integer(getDownsamplingFactor());
			case IcmPackage.SINK_PIN_INSTANCE__MUTEXES:
				return getMutexes();
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
			case IcmPackage.SINK_PIN_INSTANCE__MODE:
				setMode((SinkPinMode)newValue);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES:
				getReactSources().clear();
				getReactSources().addAll((Collection)newValue);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES:
				getLinkSources().clear();
				getLinkSources().addAll((Collection)newValue);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION:
				setExecTimeDistribution((Distribution)newValue);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__PRIORITY:
				setPriority(((Integer)newValue).intValue());
				return;
			case IcmPackage.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR:
				setDownsamplingFactor(((Integer)newValue).intValue());
				return;
			case IcmPackage.SINK_PIN_INSTANCE__MUTEXES:
				getMutexes().clear();
				getMutexes().addAll((Collection)newValue);
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
			case IcmPackage.SINK_PIN_INSTANCE__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES:
				getReactSources().clear();
				return;
			case IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES:
				getLinkSources().clear();
				return;
			case IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION:
				setExecTimeDistribution((Distribution)null);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR:
				setDownsamplingFactor(DOWNSAMPLING_FACTOR_EDEFAULT);
				return;
			case IcmPackage.SINK_PIN_INSTANCE__MUTEXES:
				getMutexes().clear();
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
			case IcmPackage.SINK_PIN_INSTANCE__MODE:
				return mode != MODE_EDEFAULT;
			case IcmPackage.SINK_PIN_INSTANCE__REACT_SOURCES:
				return reactSources != null && !reactSources.isEmpty();
			case IcmPackage.SINK_PIN_INSTANCE__LINK_SOURCES:
				return linkSources != null && !linkSources.isEmpty();
			case IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION:
				return execTimeDistribution != null;
			case IcmPackage.SINK_PIN_INSTANCE__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case IcmPackage.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR:
				return downsamplingFactor != DOWNSAMPLING_FACTOR_EDEFAULT;
			case IcmPackage.SINK_PIN_INSTANCE__MUTEXES:
				return mutexes != null && !mutexes.isEmpty();
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
		result.append(", priority: ");
		result.append(priority);
		result.append(", downsamplingFactor: ");
		result.append(downsamplingFactor);
		result.append(')');
		return result.toString();
	}

} //SinkPinInstanceImpl
