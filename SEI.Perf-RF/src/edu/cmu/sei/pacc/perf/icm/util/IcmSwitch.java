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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage
 * @generated
 */
public class IcmSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IcmPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IcmSwitch() {
		if (modelPackage == null) {
			modelPackage = IcmPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case IcmPackage.ASSEMBLY_INSTANCE: {
				AssemblyInstance assemblyInstance = (AssemblyInstance)theEObject;
				Object result = caseAssemblyInstance(assemblyInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.ICM_COMPONENT: {
				IcmComponent icmComponent = (IcmComponent)theEObject;
				Object result = caseIcmComponent(icmComponent);
				if (result == null) result = caseElementInstance(icmComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.CONSTANT: {
				Constant constant = (Constant)theEObject;
				Object result = caseConstant(constant);
				if (result == null) result = caseDistribution(constant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.DISTRIBUTION: {
				Distribution distribution = (Distribution)theEObject;
				Object result = caseDistribution(distribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.ELEMENT_INSTANCE: {
				ElementInstance elementInstance = (ElementInstance)theEObject;
				Object result = caseElementInstance(elementInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.EXPONENTIAL: {
				Exponential exponential = (Exponential)theEObject;
				Object result = caseExponential(exponential);
				if (result == null) result = caseDistribution(exponential);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.NORMAL: {
				Normal normal = (Normal)theEObject;
				Object result = caseNormal(normal);
				if (result == null) result = caseDistribution(normal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.PIN_INSTANCE: {
				PinInstance pinInstance = (PinInstance)theEObject;
				Object result = casePinInstance(pinInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.SS_COMPONENT: {
				SSComponent ssComponent = (SSComponent)theEObject;
				Object result = caseSSComponent(ssComponent);
				if (result == null) result = caseIcmComponent(ssComponent);
				if (result == null) result = caseElementInstance(ssComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.ICM_SERVICE: {
				IcmService icmService = (IcmService)theEObject;
				Object result = caseIcmService(icmService);
				if (result == null) result = caseElementInstance(icmService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.SERVICE_SOURCE_PIN_ICM: {
				ServiceSourcePinIcm serviceSourcePinIcm = (ServiceSourcePinIcm)theEObject;
				Object result = caseServiceSourcePinIcm(serviceSourcePinIcm);
				if (result == null) result = caseSourcePinInstance(serviceSourcePinIcm);
				if (result == null) result = casePinInstance(serviceSourcePinIcm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.SINK_PIN_INSTANCE: {
				SinkPinInstance sinkPinInstance = (SinkPinInstance)theEObject;
				Object result = caseSinkPinInstance(sinkPinInstance);
				if (result == null) result = casePinInstance(sinkPinInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.SOURCE_PIN_INSTANCE: {
				SourcePinInstance sourcePinInstance = (SourcePinInstance)theEObject;
				Object result = caseSourcePinInstance(sourcePinInstance);
				if (result == null) result = casePinInstance(sourcePinInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.UNIFORM: {
				Uniform uniform = (Uniform)theEObject;
				Object result = caseUniform(uniform);
				if (result == null) result = caseDistribution(uniform);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.UNKNOWN: {
				Unknown unknown = (Unknown)theEObject;
				Object result = caseUnknown(unknown);
				if (result == null) result = caseDistribution(unknown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.SCENARIO: {
				Scenario scenario = (Scenario)theEObject;
				Object result = caseScenario(scenario);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IcmPackage.MUTEX: {
				Mutex mutex = (Mutex)theEObject;
				Object result = caseMutex(mutex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAssemblyInstance(AssemblyInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIcmComponent(IcmComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstant(Constant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDistribution(Distribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseElementInstance(ElementInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExponential(Exponential object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Normal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Normal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNormal(Normal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pin Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pin Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePinInstance(PinInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SS Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SS Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSSComponent(SSComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIcmService(IcmService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Source Pin Icm</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Source Pin Icm</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServiceSourcePinIcm(ServiceSourcePinIcm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sink Pin Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sink Pin Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSinkPinInstance(SinkPinInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source Pin Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source Pin Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSourcePinInstance(SourcePinInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uniform</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uniform</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUniform(Uniform object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unknown</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unknown</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUnknown(Unknown object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scenario</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseScenario(Scenario object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mutex</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mutex</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMutex(Mutex object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //IcmSwitch
