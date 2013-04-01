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

package edu.cmu.sei.pacc.perf.icm.util;

import edu.cmu.sei.pacc.perf.icm.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage
 * @generated
 */
public class IcmAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IcmPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IcmAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = IcmPackage.eINSTANCE;
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
	protected IcmSwitch modelSwitch =
		new IcmSwitch() {
			public Object caseAssemblyInstance(AssemblyInstance object) {
				return createAssemblyInstanceAdapter();
			}
			public Object caseIcmComponent(IcmComponent object) {
				return createIcmComponentAdapter();
			}
			public Object caseConstant(Constant object) {
				return createConstantAdapter();
			}
			public Object caseDistribution(Distribution object) {
				return createDistributionAdapter();
			}
			public Object caseElementInstance(ElementInstance object) {
				return createElementInstanceAdapter();
			}
			public Object caseExponential(Exponential object) {
				return createExponentialAdapter();
			}
			public Object caseNormal(Normal object) {
				return createNormalAdapter();
			}
			public Object casePinInstance(PinInstance object) {
				return createPinInstanceAdapter();
			}
			public Object caseSSComponent(SSComponent object) {
				return createSSComponentAdapter();
			}
			public Object caseIcmService(IcmService object) {
				return createIcmServiceAdapter();
			}
			public Object caseServiceSourcePinIcm(ServiceSourcePinIcm object) {
				return createServiceSourcePinIcmAdapter();
			}
			public Object caseSinkPinInstance(SinkPinInstance object) {
				return createSinkPinInstanceAdapter();
			}
			public Object caseSourcePinInstance(SourcePinInstance object) {
				return createSourcePinInstanceAdapter();
			}
			public Object caseUniform(Uniform object) {
				return createUniformAdapter();
			}
			public Object caseUnknown(Unknown object) {
				return createUnknownAdapter();
			}
			public Object caseScenario(Scenario object) {
				return createScenarioAdapter();
			}
			public Object caseMutex(Mutex object) {
				return createMutexAdapter();
			}
			public Object defaultCase(EObject object) {
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
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance <em>Assembly Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance
	 * @generated
	 */
	public Adapter createAssemblyInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.IcmComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmComponent
	 * @generated
	 */
	public Adapter createIcmComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Constant
	 * @generated
	 */
	public Adapter createConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Distribution <em>Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Distribution
	 * @generated
	 */
	public Adapter createDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance <em>Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance
	 * @generated
	 */
	public Adapter createElementInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Exponential <em>Exponential</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Exponential
	 * @generated
	 */
	public Adapter createExponentialAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Normal <em>Normal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Normal
	 * @generated
	 */
	public Adapter createNormalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.PinInstance <em>Pin Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance
	 * @generated
	 */
	public Adapter createPinInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.SSComponent <em>SS Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.SSComponent
	 * @generated
	 */
	public Adapter createSSComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.IcmService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmService
	 * @generated
	 */
	public Adapter createIcmServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm <em>Service Source Pin Icm</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm
	 * @generated
	 */
	public Adapter createServiceSourcePinIcmAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance <em>Sink Pin Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance
	 * @generated
	 */
	public Adapter createSinkPinInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance <em>Source Pin Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinInstance
	 * @generated
	 */
	public Adapter createSourcePinInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Uniform <em>Uniform</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Uniform
	 * @generated
	 */
	public Adapter createUniformAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Unknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Unknown
	 * @generated
	 */
	public Adapter createUnknownAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Scenario
	 * @generated
	 */
	public Adapter createScenarioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.cmu.sei.pacc.perf.icm.Mutex <em>Mutex</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.cmu.sei.pacc.perf.icm.Mutex
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

} //IcmAdapterFactory
