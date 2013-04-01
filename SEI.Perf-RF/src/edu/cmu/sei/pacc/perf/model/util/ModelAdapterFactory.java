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

package edu.cmu.sei.pacc.perf.model.util;

import edu.cmu.sei.pacc.perf.model.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see edu.cmu.sei.pacc.perf.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
			@Override
			public Adapter caseTask(Task object) {
				return createTaskAdapter();
			}
			@Override
			public Adapter casePeriodicTask(PeriodicTask object) {
				return createPeriodicTaskAdapter();
			}
			@Override
			public Adapter caseAperiodicTask(AperiodicTask object) {
				return createAperiodicTaskAdapter();
			}
			@Override
			public Adapter caseSubtask(Subtask object) {
				return createSubtaskAdapter();
			}
			@Override
			public Adapter caseDistribution(Distribution object) {
				return createDistributionAdapter();
			}
			@Override
			public Adapter caseConstant(Constant object) {
				return createConstantAdapter();
			}
			@Override
			public Adapter caseExponential(Exponential object) {
				return createExponentialAdapter();
			}
			@Override
			public Adapter caseUniform(Uniform object) {
				return createUniformAdapter();
			}
			@Override
			public Adapter caseUnknown(Unknown object) {
				return createUnknownAdapter();
			}
			@Override
			public Adapter caseNormal(Normal object) {
				return createNormalAdapter();
			}
			@Override
			public Adapter casePerformanceModel(PerformanceModel object) {
				return createPerformanceModelAdapter();
			}
			@Override
			public Adapter caseSSTask(SSTask object) {
				return createSSTaskAdapter();
			}
			@Override
			public Adapter caseMutex(Mutex object) {
				return createMutexAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.PeriodicTask <em>Periodic Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.PeriodicTask
	 * @generated
	 */
	public Adapter createPeriodicTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.AperiodicTask <em>Aperiodic Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.AperiodicTask
	 * @generated
	 */
	public Adapter createAperiodicTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Subtask <em>Subtask</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask
	 * @generated
	 */
	public Adapter createSubtaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Distribution <em>Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Distribution
	 * @generated
	 */
	public Adapter createDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Constant
	 * @generated
	 */
	public Adapter createConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Exponential <em>Exponential</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Exponential
	 * @generated
	 */
	public Adapter createExponentialAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Uniform <em>Uniform</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Uniform
	 * @generated
	 */
	public Adapter createUniformAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Unknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Unknown
	 * @generated
	 */
	public Adapter createUnknownAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Normal <em>Normal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Normal
	 * @generated
	 */
	public Adapter createNormalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.PerformanceModel <em>Performance Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.PerformanceModel
	 * @generated
	 */
	public Adapter createPerformanceModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.SSTask <em>SS Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.SSTask
	 * @generated
	 */
	public Adapter createSSTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.model.Mutex <em>Mutex</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.model.Mutex
	 * @generated
	 */
	public Adapter createMutexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModelAdapterFactory
