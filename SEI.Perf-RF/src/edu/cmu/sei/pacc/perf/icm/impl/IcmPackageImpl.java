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
import edu.cmu.sei.pacc.perf.icm.Constant;
import edu.cmu.sei.pacc.perf.icm.Distribution;
import edu.cmu.sei.pacc.perf.icm.ElementInstance;
import edu.cmu.sei.pacc.perf.icm.Exponential;
import edu.cmu.sei.pacc.perf.icm.IcmComponent;
import edu.cmu.sei.pacc.perf.icm.IcmFactory;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.icm.IcmService;
import edu.cmu.sei.pacc.perf.icm.Mutex;
import edu.cmu.sei.pacc.perf.icm.Normal;
import edu.cmu.sei.pacc.perf.icm.PinInstance;
import edu.cmu.sei.pacc.perf.icm.SSComponent;
import edu.cmu.sei.pacc.perf.icm.Scenario;
import edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm;
import edu.cmu.sei.pacc.perf.icm.SinkPinInstance;
import edu.cmu.sei.pacc.perf.icm.SinkPinMode;
import edu.cmu.sei.pacc.perf.icm.SourcePinInstance;
import edu.cmu.sei.pacc.perf.icm.SourcePinMode;
import edu.cmu.sei.pacc.perf.icm.Uniform;
import edu.cmu.sei.pacc.perf.icm.Unknown;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IcmPackageImpl extends EPackageImpl implements IcmPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass icmComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass distributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass normalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pinInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ssComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass icmServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceSourcePinIcmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sinkPinInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourcePinInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniformEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unknownEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mutexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sinkPinModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sourcePinModeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IcmPackageImpl() {
		super(eNS_URI, IcmFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IcmPackage init() {
		if (isInited) return (IcmPackage)EPackage.Registry.INSTANCE.getEPackage(IcmPackage.eNS_URI);

		// Obtain or create and register package
		IcmPackageImpl theIcmPackage = (IcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof IcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new IcmPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theIcmPackage.createPackageContents();

		// Initialize created meta-data
		theIcmPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIcmPackage.freeze();

		return theIcmPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyInstance() {
		return assemblyInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssemblyInstance_Name() {
		return (EAttribute)assemblyInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyInstance_Elements() {
		return (EReference)assemblyInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssemblyInstance_ConnectionOverhead() {
		return (EAttribute)assemblyInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyInstance_Scenarios() {
		return (EReference)assemblyInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyInstance_Mutexes() {
		return (EReference)assemblyInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssemblyInstance_SourceFile() {
		return (EAttribute)assemblyInstanceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIcmComponent() {
		return icmComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstant() {
		return constantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstant_Value() {
		return (EAttribute)constantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstant_Offset() {
		return (EAttribute)constantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDistribution() {
		return distributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementInstance() {
		return elementInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementInstance_Name() {
		return (EAttribute)elementInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementInstance_NodeNum() {
		return (EAttribute)elementInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementInstance_TypeName() {
		return (EAttribute)elementInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementInstance_TypeNodeNum() {
		return (EAttribute)elementInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementInstance_Pins() {
		return (EReference)elementInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExponential() {
		return exponentialEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExponential_Mean() {
		return (EAttribute)exponentialEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNormal() {
		return normalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNormal_Mean() {
		return (EAttribute)normalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNormal_StdDev() {
		return (EAttribute)normalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPinInstance() {
		return pinInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPinInstance_NodeNum() {
		return (EAttribute)pinInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPinInstance_Name() {
		return (EAttribute)pinInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPinInstance_ElementInstance() {
		return (EReference)pinInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPinInstance_Scenarios() {
		return (EReference)pinInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPinInstance_Id() {
		return (EAttribute)pinInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSSComponent() {
		return ssComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSSComponent_Budget() {
		return (EAttribute)ssComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSSComponent_ReplenishmentPeriod() {
		return (EAttribute)ssComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSSComponent_BackgroundPriority() {
		return (EAttribute)ssComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIcmService() {
		return icmServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIcmService_Priority() {
		return (EAttribute)icmServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceSourcePinIcm() {
		return serviceSourcePinIcmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceSourcePinIcm_EventDistribution() {
		return (EReference)serviceSourcePinIcmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceSourcePinIcm_ExecTimeDistribution() {
		return (EReference)serviceSourcePinIcmEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceSourcePinIcm_Deadline() {
		return (EAttribute)serviceSourcePinIcmEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSinkPinInstance() {
		return sinkPinInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSinkPinInstance_Mode() {
		return (EAttribute)sinkPinInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSinkPinInstance_ReactSources() {
		return (EReference)sinkPinInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSinkPinInstance_LinkSources() {
		return (EReference)sinkPinInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSinkPinInstance_ExecTimeDistribution() {
		return (EReference)sinkPinInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSinkPinInstance_Priority() {
		return (EAttribute)sinkPinInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSinkPinInstance_DownsamplingFactor() {
		return (EAttribute)sinkPinInstanceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSinkPinInstance_Mutexes() {
		return (EReference)sinkPinInstanceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSourcePinInstance() {
		return sourcePinInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSourcePinInstance_Mode() {
		return (EAttribute)sourcePinInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSourcePinInstance_Sinks() {
		return (EReference)sourcePinInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSourcePinInstance_ReactSinks() {
		return (EReference)sourcePinInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUniform() {
		return uniformEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniform_Max() {
		return (EAttribute)uniformEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniform_Min() {
		return (EAttribute)uniformEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnknown() {
		return unknownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnknown_Mean() {
		return (EAttribute)unknownEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnknown_Min() {
		return (EAttribute)unknownEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnknown_Max() {
		return (EAttribute)unknownEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenario() {
		return scenarioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScenario_Name() {
		return (EAttribute)scenarioEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScenario_Number() {
		return (EAttribute)scenarioEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMutex() {
		return mutexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMutex_Name() {
		return (EAttribute)mutexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSinkPinMode() {
		return sinkPinModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSourcePinMode() {
		return sourcePinModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IcmFactory getIcmFactory() {
		return (IcmFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		assemblyInstanceEClass = createEClass(ASSEMBLY_INSTANCE);
		createEAttribute(assemblyInstanceEClass, ASSEMBLY_INSTANCE__NAME);
		createEReference(assemblyInstanceEClass, ASSEMBLY_INSTANCE__ELEMENTS);
		createEAttribute(assemblyInstanceEClass, ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD);
		createEReference(assemblyInstanceEClass, ASSEMBLY_INSTANCE__SCENARIOS);
		createEReference(assemblyInstanceEClass, ASSEMBLY_INSTANCE__MUTEXES);
		createEAttribute(assemblyInstanceEClass, ASSEMBLY_INSTANCE__SOURCE_FILE);

		icmComponentEClass = createEClass(ICM_COMPONENT);

		constantEClass = createEClass(CONSTANT);
		createEAttribute(constantEClass, CONSTANT__VALUE);
		createEAttribute(constantEClass, CONSTANT__OFFSET);

		distributionEClass = createEClass(DISTRIBUTION);

		elementInstanceEClass = createEClass(ELEMENT_INSTANCE);
		createEAttribute(elementInstanceEClass, ELEMENT_INSTANCE__NAME);
		createEAttribute(elementInstanceEClass, ELEMENT_INSTANCE__NODE_NUM);
		createEAttribute(elementInstanceEClass, ELEMENT_INSTANCE__TYPE_NAME);
		createEAttribute(elementInstanceEClass, ELEMENT_INSTANCE__TYPE_NODE_NUM);
		createEReference(elementInstanceEClass, ELEMENT_INSTANCE__PINS);

		exponentialEClass = createEClass(EXPONENTIAL);
		createEAttribute(exponentialEClass, EXPONENTIAL__MEAN);

		normalEClass = createEClass(NORMAL);
		createEAttribute(normalEClass, NORMAL__MEAN);
		createEAttribute(normalEClass, NORMAL__STD_DEV);

		pinInstanceEClass = createEClass(PIN_INSTANCE);
		createEAttribute(pinInstanceEClass, PIN_INSTANCE__NODE_NUM);
		createEAttribute(pinInstanceEClass, PIN_INSTANCE__NAME);
		createEReference(pinInstanceEClass, PIN_INSTANCE__ELEMENT_INSTANCE);
		createEReference(pinInstanceEClass, PIN_INSTANCE__SCENARIOS);
		createEAttribute(pinInstanceEClass, PIN_INSTANCE__ID);

		ssComponentEClass = createEClass(SS_COMPONENT);
		createEAttribute(ssComponentEClass, SS_COMPONENT__BUDGET);
		createEAttribute(ssComponentEClass, SS_COMPONENT__REPLENISHMENT_PERIOD);
		createEAttribute(ssComponentEClass, SS_COMPONENT__BACKGROUND_PRIORITY);

		icmServiceEClass = createEClass(ICM_SERVICE);
		createEAttribute(icmServiceEClass, ICM_SERVICE__PRIORITY);

		serviceSourcePinIcmEClass = createEClass(SERVICE_SOURCE_PIN_ICM);
		createEReference(serviceSourcePinIcmEClass, SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION);
		createEReference(serviceSourcePinIcmEClass, SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION);
		createEAttribute(serviceSourcePinIcmEClass, SERVICE_SOURCE_PIN_ICM__DEADLINE);

		sinkPinInstanceEClass = createEClass(SINK_PIN_INSTANCE);
		createEAttribute(sinkPinInstanceEClass, SINK_PIN_INSTANCE__MODE);
		createEReference(sinkPinInstanceEClass, SINK_PIN_INSTANCE__REACT_SOURCES);
		createEReference(sinkPinInstanceEClass, SINK_PIN_INSTANCE__LINK_SOURCES);
		createEReference(sinkPinInstanceEClass, SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION);
		createEAttribute(sinkPinInstanceEClass, SINK_PIN_INSTANCE__PRIORITY);
		createEAttribute(sinkPinInstanceEClass, SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR);
		createEReference(sinkPinInstanceEClass, SINK_PIN_INSTANCE__MUTEXES);

		sourcePinInstanceEClass = createEClass(SOURCE_PIN_INSTANCE);
		createEAttribute(sourcePinInstanceEClass, SOURCE_PIN_INSTANCE__MODE);
		createEReference(sourcePinInstanceEClass, SOURCE_PIN_INSTANCE__SINKS);
		createEReference(sourcePinInstanceEClass, SOURCE_PIN_INSTANCE__REACT_SINKS);

		uniformEClass = createEClass(UNIFORM);
		createEAttribute(uniformEClass, UNIFORM__MAX);
		createEAttribute(uniformEClass, UNIFORM__MIN);

		unknownEClass = createEClass(UNKNOWN);
		createEAttribute(unknownEClass, UNKNOWN__MEAN);
		createEAttribute(unknownEClass, UNKNOWN__MIN);
		createEAttribute(unknownEClass, UNKNOWN__MAX);

		scenarioEClass = createEClass(SCENARIO);
		createEAttribute(scenarioEClass, SCENARIO__NAME);
		createEAttribute(scenarioEClass, SCENARIO__NUMBER);

		mutexEClass = createEClass(MUTEX);
		createEAttribute(mutexEClass, MUTEX__NAME);

		// Create enums
		sinkPinModeEEnum = createEEnum(SINK_PIN_MODE);
		sourcePinModeEEnum = createEEnum(SOURCE_PIN_MODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		icmComponentEClass.getESuperTypes().add(this.getElementInstance());
		constantEClass.getESuperTypes().add(this.getDistribution());
		exponentialEClass.getESuperTypes().add(this.getDistribution());
		normalEClass.getESuperTypes().add(this.getDistribution());
		ssComponentEClass.getESuperTypes().add(this.getIcmComponent());
		icmServiceEClass.getESuperTypes().add(this.getElementInstance());
		serviceSourcePinIcmEClass.getESuperTypes().add(this.getSourcePinInstance());
		sinkPinInstanceEClass.getESuperTypes().add(this.getPinInstance());
		sourcePinInstanceEClass.getESuperTypes().add(this.getPinInstance());
		uniformEClass.getESuperTypes().add(this.getDistribution());
		unknownEClass.getESuperTypes().add(this.getDistribution());

		// Initialize classes and features; add operations and parameters
		initEClass(assemblyInstanceEClass, AssemblyInstance.class, "AssemblyInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssemblyInstance_Name(), ecorePackage.getEString(), "name", null, 0, 1, AssemblyInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssemblyInstance_Elements(), this.getElementInstance(), null, "elements", null, 0, -1, AssemblyInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssemblyInstance_ConnectionOverhead(), ecorePackage.getEDouble(), "connectionOverhead", null, 0, 1, AssemblyInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssemblyInstance_Scenarios(), this.getScenario(), null, "scenarios", null, 0, -1, AssemblyInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssemblyInstance_Mutexes(), this.getMutex(), null, "mutexes", null, 0, -1, AssemblyInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssemblyInstance_SourceFile(), ecorePackage.getEString(), "sourceFile", null, 0, 1, AssemblyInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(icmComponentEClass, IcmComponent.class, "IcmComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstant_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstant_Offset(), ecorePackage.getEDouble(), "offset", null, 0, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(distributionEClass, Distribution.class, "Distribution", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = addEOperation(distributionEClass, null, "add");
		addEParameter(op, ecorePackage.getEDouble(), "value", 0, 1);

		initEClass(elementInstanceEClass, ElementInstance.class, "ElementInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElementInstance_Name(), ecorePackage.getEString(), "name", null, 0, 1, ElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementInstance_NodeNum(), ecorePackage.getEInt(), "nodeNum", null, 0, 1, ElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementInstance_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1, ElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementInstance_TypeNodeNum(), ecorePackage.getEInt(), "typeNodeNum", null, 0, 1, ElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementInstance_Pins(), this.getPinInstance(), this.getPinInstance_ElementInstance(), "pins", null, 0, -1, ElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialEClass, Exponential.class, "Exponential", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExponential_Mean(), ecorePackage.getEDouble(), "mean", null, 0, 1, Exponential.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(normalEClass, Normal.class, "Normal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNormal_Mean(), ecorePackage.getEDouble(), "mean", null, 0, 1, Normal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNormal_StdDev(), ecorePackage.getEDouble(), "stdDev", null, 0, 1, Normal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pinInstanceEClass, PinInstance.class, "PinInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPinInstance_NodeNum(), ecorePackage.getEInt(), "nodeNum", null, 0, 1, PinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPinInstance_Name(), ecorePackage.getEString(), "name", null, 0, 1, PinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPinInstance_ElementInstance(), this.getElementInstance(), this.getElementInstance_Pins(), "elementInstance", null, 0, 1, PinInstance.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPinInstance_Scenarios(), this.getScenario(), null, "scenarios", null, 0, -1, PinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPinInstance_Id(), ecorePackage.getEInt(), "id", null, 0, 1, PinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ssComponentEClass, SSComponent.class, "SSComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSSComponent_Budget(), ecorePackage.getEDouble(), "budget", null, 0, 1, SSComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSSComponent_ReplenishmentPeriod(), ecorePackage.getEInt(), "replenishmentPeriod", null, 0, 1, SSComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSSComponent_BackgroundPriority(), ecorePackage.getEInt(), "backgroundPriority", null, 0, 1, SSComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(icmServiceEClass, IcmService.class, "IcmService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIcmService_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, IcmService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceSourcePinIcmEClass, ServiceSourcePinIcm.class, "ServiceSourcePinIcm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceSourcePinIcm_EventDistribution(), this.getDistribution(), null, "eventDistribution", null, 1, 1, ServiceSourcePinIcm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceSourcePinIcm_ExecTimeDistribution(), this.getDistribution(), null, "execTimeDistribution", null, 0, 1, ServiceSourcePinIcm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceSourcePinIcm_Deadline(), ecorePackage.getEDouble(), "deadline", null, 0, 1, ServiceSourcePinIcm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sinkPinInstanceEClass, SinkPinInstance.class, "SinkPinInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSinkPinInstance_Mode(), this.getSinkPinMode(), "mode", null, 0, 1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSinkPinInstance_ReactSources(), this.getSourcePinInstance(), this.getSourcePinInstance_ReactSinks(), "reactSources", null, 0, -1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSinkPinInstance_LinkSources(), this.getSourcePinInstance(), this.getSourcePinInstance_Sinks(), "linkSources", null, 0, -1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSinkPinInstance_ExecTimeDistribution(), this.getDistribution(), null, "execTimeDistribution", null, 1, 1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSinkPinInstance_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSinkPinInstance_DownsamplingFactor(), ecorePackage.getEInt(), "downsamplingFactor", null, 0, 1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSinkPinInstance_Mutexes(), this.getMutex(), null, "mutexes", null, 0, -1, SinkPinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sourcePinInstanceEClass, SourcePinInstance.class, "SourcePinInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSourcePinInstance_Mode(), this.getSourcePinMode(), "mode", null, 0, 1, SourcePinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSourcePinInstance_Sinks(), this.getSinkPinInstance(), this.getSinkPinInstance_LinkSources(), "sinks", null, 0, -1, SourcePinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSourcePinInstance_ReactSinks(), this.getSinkPinInstance(), this.getSinkPinInstance_ReactSources(), "reactSinks", null, 0, -1, SourcePinInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uniformEClass, Uniform.class, "Uniform", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUniform_Max(), ecorePackage.getEDouble(), "max", null, 0, 1, Uniform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUniform_Min(), ecorePackage.getEDouble(), "min", null, 0, 1, Uniform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unknownEClass, Unknown.class, "Unknown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnknown_Mean(), ecorePackage.getEDouble(), "mean", null, 0, 1, Unknown.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnknown_Min(), ecorePackage.getEDouble(), "min", null, 0, 1, Unknown.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnknown_Max(), ecorePackage.getEDouble(), "max", null, 0, 1, Unknown.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioEClass, Scenario.class, "Scenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScenario_Name(), ecorePackage.getEString(), "name", null, 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScenario_Number(), ecorePackage.getEInt(), "number", null, 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mutexEClass, Mutex.class, "Mutex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMutex_Name(), ecorePackage.getEString(), "name", null, 0, 1, Mutex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(sinkPinModeEEnum, SinkPinMode.class, "SinkPinMode");
		addEEnumLiteral(sinkPinModeEEnum, SinkPinMode.ASYNCH_LITERAL);
		addEEnumLiteral(sinkPinModeEEnum, SinkPinMode.MUTEX_LITERAL);
		addEEnumLiteral(sinkPinModeEEnum, SinkPinMode.REENTER_LITERAL);

		initEEnum(sourcePinModeEEnum, SourcePinMode.class, "SourcePinMode");
		addEEnumLiteral(sourcePinModeEEnum, SourcePinMode.UNICAST_LITERAL);
		addEEnumLiteral(sourcePinModeEEnum, SourcePinMode.MULTICAST_LITERAL);
		addEEnumLiteral(sourcePinModeEEnum, SourcePinMode.SYNCH_LITERAL);
		addEEnumLiteral(sourcePinModeEEnum, SourcePinMode.REENTER_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //IcmPackageImpl
