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

package edu.cmu.sei.pacc.perf.icm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.cmu.sei.pacc.perf.icm.IcmFactory
 * @model kind="package"
 * @generated
 */
public interface IcmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "icm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///edu.cmu.sei.pacc.perf.icm";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "edu.cmu.sei.pacc.perf.icm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IcmPackage eINSTANCE = edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl <em>Assembly Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getAssemblyInstance()
	 * @generated
	 */
	int ASSEMBLY_INSTANCE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE__ELEMENTS = 1;

	/**
	 * The feature id for the '<em><b>Connection Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD = 2;

	/**
	 * The feature id for the '<em><b>Scenarios</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE__SCENARIOS = 3;

	/**
	 * The feature id for the '<em><b>Mutexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE__MUTEXES = 4;

	/**
	 * The feature id for the '<em><b>Source File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE__SOURCE_FILE = 5;

	/**
	 * The number of structural features of the '<em>Assembly Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_INSTANCE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl <em>Element Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getElementInstance()
	 * @generated
	 */
	int ELEMENT_INSTANCE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_INSTANCE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_INSTANCE__NODE_NUM = 1;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_INSTANCE__TYPE_NAME = 2;

	/**
	 * The feature id for the '<em><b>Type Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_INSTANCE__TYPE_NODE_NUM = 3;

	/**
	 * The feature id for the '<em><b>Pins</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_INSTANCE__PINS = 4;

	/**
	 * The number of structural features of the '<em>Element Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_INSTANCE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.IcmComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmComponentImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getIcmComponent()
	 * @generated
	 */
	int ICM_COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_COMPONENT__NAME = ELEMENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_COMPONENT__NODE_NUM = ELEMENT_INSTANCE__NODE_NUM;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_COMPONENT__TYPE_NAME = ELEMENT_INSTANCE__TYPE_NAME;

	/**
	 * The feature id for the '<em><b>Type Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_COMPONENT__TYPE_NODE_NUM = ELEMENT_INSTANCE__TYPE_NODE_NUM;

	/**
	 * The feature id for the '<em><b>Pins</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_COMPONENT__PINS = ELEMENT_INSTANCE__PINS;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_COMPONENT_FEATURE_COUNT = ELEMENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.DistributionImpl <em>Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.DistributionImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getDistribution()
	 * @generated
	 */
	int DISTRIBUTION = 3;

	/**
	 * The number of structural features of the '<em>Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTRIBUTION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ConstantImpl <em>Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.ConstantImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__VALUE = DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__OFFSET = DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_FEATURE_COUNT = DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ExponentialImpl <em>Exponential</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.ExponentialImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getExponential()
	 * @generated
	 */
	int EXPONENTIAL = 5;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL__MEAN = DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exponential</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_FEATURE_COUNT = DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.NormalImpl <em>Normal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.NormalImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getNormal()
	 * @generated
	 */
	int NORMAL = 6;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL__MEAN = DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL__STD_DEV = DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Normal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_FEATURE_COUNT = DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl <em>Pin Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getPinInstance()
	 * @generated
	 */
	int PIN_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_INSTANCE__NODE_NUM = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_INSTANCE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Element Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_INSTANCE__ELEMENT_INSTANCE = 2;

	/**
	 * The feature id for the '<em><b>Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_INSTANCE__SCENARIOS = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_INSTANCE__ID = 4;

	/**
	 * The number of structural features of the '<em>Pin Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_INSTANCE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.SSComponentImpl <em>SS Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.SSComponentImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSSComponent()
	 * @generated
	 */
	int SS_COMPONENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__NAME = ICM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__NODE_NUM = ICM_COMPONENT__NODE_NUM;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__TYPE_NAME = ICM_COMPONENT__TYPE_NAME;

	/**
	 * The feature id for the '<em><b>Type Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__TYPE_NODE_NUM = ICM_COMPONENT__TYPE_NODE_NUM;

	/**
	 * The feature id for the '<em><b>Pins</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__PINS = ICM_COMPONENT__PINS;

	/**
	 * The feature id for the '<em><b>Budget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__BUDGET = ICM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Replenishment Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__REPLENISHMENT_PERIOD = ICM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Background Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT__BACKGROUND_PRIORITY = ICM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>SS Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_COMPONENT_FEATURE_COUNT = ICM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.IcmServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmServiceImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getIcmService()
	 * @generated
	 */
	int ICM_SERVICE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE__NAME = ELEMENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE__NODE_NUM = ELEMENT_INSTANCE__NODE_NUM;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE__TYPE_NAME = ELEMENT_INSTANCE__TYPE_NAME;

	/**
	 * The feature id for the '<em><b>Type Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE__TYPE_NODE_NUM = ELEMENT_INSTANCE__TYPE_NODE_NUM;

	/**
	 * The feature id for the '<em><b>Pins</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE__PINS = ELEMENT_INSTANCE__PINS;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE__PRIORITY = ELEMENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICM_SERVICE_FEATURE_COUNT = ELEMENT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl <em>Source Pin Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSourcePinInstance()
	 * @generated
	 */
	int SOURCE_PIN_INSTANCE = 12;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__NODE_NUM = PIN_INSTANCE__NODE_NUM;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__NAME = PIN_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Element Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__ELEMENT_INSTANCE = PIN_INSTANCE__ELEMENT_INSTANCE;

	/**
	 * The feature id for the '<em><b>Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__SCENARIOS = PIN_INSTANCE__SCENARIOS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__ID = PIN_INSTANCE__ID;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__MODE = PIN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sinks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE__SINKS = PIN_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>React Sinks</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SOURCE_PIN_INSTANCE__REACT_SINKS = PIN_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Source Pin Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_PIN_INSTANCE_FEATURE_COUNT = PIN_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl <em>Service Source Pin Icm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getServiceSourcePinIcm()
	 * @generated
	 */
	int SERVICE_SOURCE_PIN_ICM = 10;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__NODE_NUM = SOURCE_PIN_INSTANCE__NODE_NUM;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__NAME = SOURCE_PIN_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Element Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__ELEMENT_INSTANCE = SOURCE_PIN_INSTANCE__ELEMENT_INSTANCE;

	/**
	 * The feature id for the '<em><b>Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__SCENARIOS = SOURCE_PIN_INSTANCE__SCENARIOS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__ID = SOURCE_PIN_INSTANCE__ID;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__MODE = SOURCE_PIN_INSTANCE__MODE;

	/**
	 * The feature id for the '<em><b>Sinks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__SINKS = SOURCE_PIN_INSTANCE__SINKS;

	/**
	 * The feature id for the '<em><b>React Sinks</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SERVICE_SOURCE_PIN_ICM__REACT_SINKS = SOURCE_PIN_INSTANCE__REACT_SINKS;

	/**
	 * The feature id for the '<em><b>Event Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION = SOURCE_PIN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exec Time Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION = SOURCE_PIN_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM__DEADLINE = SOURCE_PIN_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Service Source Pin Icm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_SOURCE_PIN_ICM_FEATURE_COUNT = SOURCE_PIN_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl <em>Sink Pin Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSinkPinInstance()
	 * @generated
	 */
	int SINK_PIN_INSTANCE = 11;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__NODE_NUM = PIN_INSTANCE__NODE_NUM;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__NAME = PIN_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Element Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__ELEMENT_INSTANCE = PIN_INSTANCE__ELEMENT_INSTANCE;

	/**
	 * The feature id for the '<em><b>Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__SCENARIOS = PIN_INSTANCE__SCENARIOS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__ID = PIN_INSTANCE__ID;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__MODE = PIN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>React Sources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__REACT_SOURCES = PIN_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Link Sources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__LINK_SOURCES = PIN_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Exec Time Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION = PIN_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__PRIORITY = PIN_INSTANCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Downsampling Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR = PIN_INSTANCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mutexes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE__MUTEXES = PIN_INSTANCE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Sink Pin Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_PIN_INSTANCE_FEATURE_COUNT = PIN_INSTANCE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.UniformImpl <em>Uniform</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.UniformImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getUniform()
	 * @generated
	 */
	int UNIFORM = 13;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM__MAX = DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM__MIN = DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Uniform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_FEATURE_COUNT = DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.UnknownImpl <em>Unknown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.UnknownImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getUnknown()
	 * @generated
	 */
	int UNKNOWN = 14;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__MEAN = DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__MIN = DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__MAX = DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Unknown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE_COUNT = DISTRIBUTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ScenarioImpl <em>Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.ScenarioImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getScenario()
	 * @generated
	 */
	int SCENARIO = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__NUMBER = 1;

	/**
	 * The number of structural features of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.impl.MutexImpl <em>Mutex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.impl.MutexImpl
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getMutex()
	 * @generated
	 */
	int MUTEX = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTEX__NAME = 0;

	/**
	 * The number of structural features of the '<em>Mutex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTEX_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.SinkPinMode <em>Sink Pin Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinMode
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSinkPinMode()
	 * @generated
	 */
	int SINK_PIN_MODE = 17;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.icm.SourcePinMode <em>Source Pin Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinMode
	 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSourcePinMode()
	 * @generated
	 */
	int SOURCE_PIN_MODE = 18;


	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance <em>Assembly Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly Instance</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance
	 * @generated
	 */
	EClass getAssemblyInstance();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getName()
	 * @see #getAssemblyInstance()
	 * @generated
	 */
	EAttribute getAssemblyInstance_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getElements()
	 * @see #getAssemblyInstance()
	 * @generated
	 */
	EReference getAssemblyInstance_Elements();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getConnectionOverhead <em>Connection Overhead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Overhead</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getConnectionOverhead()
	 * @see #getAssemblyInstance()
	 * @generated
	 */
	EAttribute getAssemblyInstance_ConnectionOverhead();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scenarios</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getScenarios()
	 * @see #getAssemblyInstance()
	 * @generated
	 */
	EReference getAssemblyInstance_Scenarios();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getMutexes <em>Mutexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mutexes</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getMutexes()
	 * @see #getAssemblyInstance()
	 * @generated
	 */
	EReference getAssemblyInstance_Mutexes();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getSourceFile <em>Source File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source File</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.AssemblyInstance#getSourceFile()
	 * @see #getAssemblyInstance()
	 * @generated
	 */
	EAttribute getAssemblyInstance_SourceFile();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.IcmComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmComponent
	 * @generated
	 */
	EClass getIcmComponent();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Constant
	 * @generated
	 */
	EClass getConstant();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Constant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Constant#getValue()
	 * @see #getConstant()
	 * @generated
	 */
	EAttribute getConstant_Value();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Constant#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Constant#getOffset()
	 * @see #getConstant()
	 * @generated
	 */
	EAttribute getConstant_Offset();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Distribution <em>Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Distribution
	 * @generated
	 */
	EClass getDistribution();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance <em>Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Instance</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance
	 * @generated
	 */
	EClass getElementInstance();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance#getName()
	 * @see #getElementInstance()
	 * @generated
	 */
	EAttribute getElementInstance_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getNodeNum <em>Node Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Num</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance#getNodeNum()
	 * @see #getElementInstance()
	 * @generated
	 */
	EAttribute getElementInstance_NodeNum();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeName()
	 * @see #getElementInstance()
	 * @generated
	 */
	EAttribute getElementInstance_TypeName();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeNodeNum <em>Type Node Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Node Num</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance#getTypeNodeNum()
	 * @see #getElementInstance()
	 * @generated
	 */
	EAttribute getElementInstance_TypeNodeNum();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.icm.ElementInstance#getPins <em>Pins</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pins</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ElementInstance#getPins()
	 * @see #getElementInstance()
	 * @generated
	 */
	EReference getElementInstance_Pins();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Exponential <em>Exponential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Exponential
	 * @generated
	 */
	EClass getExponential();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Exponential#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Exponential#getMean()
	 * @see #getExponential()
	 * @generated
	 */
	EAttribute getExponential_Mean();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Normal <em>Normal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normal</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Normal
	 * @generated
	 */
	EClass getNormal();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Normal#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Normal#getMean()
	 * @see #getNormal()
	 * @generated
	 */
	EAttribute getNormal_Mean();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Normal#getStdDev <em>Std Dev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Std Dev</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Normal#getStdDev()
	 * @see #getNormal()
	 * @generated
	 */
	EAttribute getNormal_StdDev();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.PinInstance <em>Pin Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pin Instance</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance
	 * @generated
	 */
	EClass getPinInstance();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getNodeNum <em>Node Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Num</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance#getNodeNum()
	 * @see #getPinInstance()
	 * @generated
	 */
	EAttribute getPinInstance_NodeNum();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance#getName()
	 * @see #getPinInstance()
	 * @generated
	 */
	EAttribute getPinInstance_Name();

	/**
	 * Returns the meta object for the container reference '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getElementInstance <em>Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Element Instance</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance#getElementInstance()
	 * @see #getPinInstance()
	 * @generated
	 */
	EReference getPinInstance_ElementInstance();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Scenarios</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance#getScenarios()
	 * @see #getPinInstance()
	 * @generated
	 */
	EReference getPinInstance_Scenarios();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.PinInstance#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.PinInstance#getId()
	 * @see #getPinInstance()
	 * @generated
	 */
	EAttribute getPinInstance_Id();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.SSComponent <em>SS Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SS Component</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SSComponent
	 * @generated
	 */
	EClass getSSComponent();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SSComponent#getBudget <em>Budget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Budget</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SSComponent#getBudget()
	 * @see #getSSComponent()
	 * @generated
	 */
	EAttribute getSSComponent_Budget();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SSComponent#getReplenishmentPeriod <em>Replenishment Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Replenishment Period</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SSComponent#getReplenishmentPeriod()
	 * @see #getSSComponent()
	 * @generated
	 */
	EAttribute getSSComponent_ReplenishmentPeriod();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SSComponent#getBackgroundPriority <em>Background Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background Priority</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SSComponent#getBackgroundPriority()
	 * @see #getSSComponent()
	 * @generated
	 */
	EAttribute getSSComponent_BackgroundPriority();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.IcmService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmService
	 * @generated
	 */
	EClass getIcmService();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.IcmService#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmService#getPriority()
	 * @see #getIcmService()
	 * @generated
	 */
	EAttribute getIcmService_Priority();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm <em>Service Source Pin Icm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Source Pin Icm</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm
	 * @generated
	 */
	EClass getServiceSourcePinIcm();

	/**
	 * Returns the meta object for the containment reference '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getEventDistribution <em>Event Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getEventDistribution()
	 * @see #getServiceSourcePinIcm()
	 * @generated
	 */
	EReference getServiceSourcePinIcm_EventDistribution();

	/**
	 * Returns the meta object for the containment reference '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getExecTimeDistribution <em>Exec Time Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exec Time Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getExecTimeDistribution()
	 * @see #getServiceSourcePinIcm()
	 * @generated
	 */
	EReference getServiceSourcePinIcm_ExecTimeDistribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getDeadline <em>Deadline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deadline</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getDeadline()
	 * @see #getServiceSourcePinIcm()
	 * @generated
	 */
	EAttribute getServiceSourcePinIcm_Deadline();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance <em>Sink Pin Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sink Pin Instance</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance
	 * @generated
	 */
	EClass getSinkPinInstance();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getMode()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EAttribute getSinkPinInstance_Mode();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getReactSources <em>React Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>React Sources</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getReactSources()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EReference getSinkPinInstance_ReactSources();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getLinkSources <em>Link Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Link Sources</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getLinkSources()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EReference getSinkPinInstance_LinkSources();

	/**
	 * Returns the meta object for the containment reference '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getExecTimeDistribution <em>Exec Time Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exec Time Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getExecTimeDistribution()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EReference getSinkPinInstance_ExecTimeDistribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getPriority()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EAttribute getSinkPinInstance_Priority();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getDownsamplingFactor <em>Downsampling Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Downsampling Factor</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getDownsamplingFactor()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EAttribute getSinkPinInstance_DownsamplingFactor();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getMutexes <em>Mutexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mutexes</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getMutexes()
	 * @see #getSinkPinInstance()
	 * @generated
	 */
	EReference getSinkPinInstance_Mutexes();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance <em>Source Pin Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Pin Instance</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinInstance
	 * @generated
	 */
	EClass getSourcePinInstance();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getMode()
	 * @see #getSourcePinInstance()
	 * @generated
	 */
	EAttribute getSourcePinInstance_Mode();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getSinks <em>Sinks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sinks</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getSinks()
	 * @see #getSourcePinInstance()
	 * @generated
	 */
	EReference getSourcePinInstance_Sinks();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getReactSinks <em>React Sinks</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>React Sinks</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getReactSinks()
	 * @see #getSourcePinInstance()
	 * @generated
	 */
    EReference getSourcePinInstance_ReactSinks();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Uniform <em>Uniform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uniform</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Uniform
	 * @generated
	 */
	EClass getUniform();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Uniform#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Uniform#getMax()
	 * @see #getUniform()
	 * @generated
	 */
	EAttribute getUniform_Max();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Uniform#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Uniform#getMin()
	 * @see #getUniform()
	 * @generated
	 */
	EAttribute getUniform_Min();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Unknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unknown</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Unknown
	 * @generated
	 */
	EClass getUnknown();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Unknown#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Unknown#getMean()
	 * @see #getUnknown()
	 * @generated
	 */
	EAttribute getUnknown_Mean();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Unknown#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Unknown#getMin()
	 * @see #getUnknown()
	 * @generated
	 */
	EAttribute getUnknown_Min();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Unknown#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Unknown#getMax()
	 * @see #getUnknown()
	 * @generated
	 */
	EAttribute getUnknown_Max();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Scenario
	 * @generated
	 */
	EClass getScenario();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Scenario#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Scenario#getName()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Scenario#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Scenario#getNumber()
	 * @see #getScenario()
	 * @generated
	 */
	EAttribute getScenario_Number();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.icm.Mutex <em>Mutex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mutex</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Mutex
	 * @generated
	 */
	EClass getMutex();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.icm.Mutex#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.Mutex#getName()
	 * @see #getMutex()
	 * @generated
	 */
	EAttribute getMutex_Name();

	/**
	 * Returns the meta object for enum '{@link edu.cmu.sei.pacc.perf.icm.SinkPinMode <em>Sink Pin Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sink Pin Mode</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinMode
	 * @generated
	 */
	EEnum getSinkPinMode();

	/**
	 * Returns the meta object for enum '{@link edu.cmu.sei.pacc.perf.icm.SourcePinMode <em>Source Pin Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Source Pin Mode</em>'.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinMode
	 * @generated
	 */
	EEnum getSourcePinMode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IcmFactory getIcmFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl <em>Assembly Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.AssemblyInstanceImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getAssemblyInstance()
		 * @generated
		 */
		EClass ASSEMBLY_INSTANCE = eINSTANCE.getAssemblyInstance();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSEMBLY_INSTANCE__NAME = eINSTANCE.getAssemblyInstance_Name();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_INSTANCE__ELEMENTS = eINSTANCE.getAssemblyInstance_Elements();

		/**
		 * The meta object literal for the '<em><b>Connection Overhead</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSEMBLY_INSTANCE__CONNECTION_OVERHEAD = eINSTANCE.getAssemblyInstance_ConnectionOverhead();

		/**
		 * The meta object literal for the '<em><b>Scenarios</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_INSTANCE__SCENARIOS = eINSTANCE.getAssemblyInstance_Scenarios();

		/**
		 * The meta object literal for the '<em><b>Mutexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_INSTANCE__MUTEXES = eINSTANCE.getAssemblyInstance_Mutexes();

		/**
		 * The meta object literal for the '<em><b>Source File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSEMBLY_INSTANCE__SOURCE_FILE = eINSTANCE.getAssemblyInstance_SourceFile();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.IcmComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmComponentImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getIcmComponent()
		 * @generated
		 */
		EClass ICM_COMPONENT = eINSTANCE.getIcmComponent();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ConstantImpl <em>Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.ConstantImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getConstant()
		 * @generated
		 */
		EClass CONSTANT = eINSTANCE.getConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT__VALUE = eINSTANCE.getConstant_Value();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT__OFFSET = eINSTANCE.getConstant_Offset();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.DistributionImpl <em>Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.DistributionImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getDistribution()
		 * @generated
		 */
		EClass DISTRIBUTION = eINSTANCE.getDistribution();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl <em>Element Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.ElementInstanceImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getElementInstance()
		 * @generated
		 */
		EClass ELEMENT_INSTANCE = eINSTANCE.getElementInstance();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_INSTANCE__NAME = eINSTANCE.getElementInstance_Name();

		/**
		 * The meta object literal for the '<em><b>Node Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_INSTANCE__NODE_NUM = eINSTANCE.getElementInstance_NodeNum();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_INSTANCE__TYPE_NAME = eINSTANCE.getElementInstance_TypeName();

		/**
		 * The meta object literal for the '<em><b>Type Node Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_INSTANCE__TYPE_NODE_NUM = eINSTANCE.getElementInstance_TypeNodeNum();

		/**
		 * The meta object literal for the '<em><b>Pins</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_INSTANCE__PINS = eINSTANCE.getElementInstance_Pins();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ExponentialImpl <em>Exponential</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.ExponentialImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getExponential()
		 * @generated
		 */
		EClass EXPONENTIAL = eINSTANCE.getExponential();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL__MEAN = eINSTANCE.getExponential_Mean();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.NormalImpl <em>Normal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.NormalImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getNormal()
		 * @generated
		 */
		EClass NORMAL = eINSTANCE.getNormal();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL__MEAN = eINSTANCE.getNormal_Mean();

		/**
		 * The meta object literal for the '<em><b>Std Dev</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL__STD_DEV = eINSTANCE.getNormal_StdDev();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl <em>Pin Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.PinInstanceImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getPinInstance()
		 * @generated
		 */
		EClass PIN_INSTANCE = eINSTANCE.getPinInstance();

		/**
		 * The meta object literal for the '<em><b>Node Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIN_INSTANCE__NODE_NUM = eINSTANCE.getPinInstance_NodeNum();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIN_INSTANCE__NAME = eINSTANCE.getPinInstance_Name();

		/**
		 * The meta object literal for the '<em><b>Element Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIN_INSTANCE__ELEMENT_INSTANCE = eINSTANCE.getPinInstance_ElementInstance();

		/**
		 * The meta object literal for the '<em><b>Scenarios</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIN_INSTANCE__SCENARIOS = eINSTANCE.getPinInstance_Scenarios();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIN_INSTANCE__ID = eINSTANCE.getPinInstance_Id();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.SSComponentImpl <em>SS Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.SSComponentImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSSComponent()
		 * @generated
		 */
		EClass SS_COMPONENT = eINSTANCE.getSSComponent();

		/**
		 * The meta object literal for the '<em><b>Budget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SS_COMPONENT__BUDGET = eINSTANCE.getSSComponent_Budget();

		/**
		 * The meta object literal for the '<em><b>Replenishment Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SS_COMPONENT__REPLENISHMENT_PERIOD = eINSTANCE.getSSComponent_ReplenishmentPeriod();

		/**
		 * The meta object literal for the '<em><b>Background Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SS_COMPONENT__BACKGROUND_PRIORITY = eINSTANCE.getSSComponent_BackgroundPriority();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.IcmServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmServiceImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getIcmService()
		 * @generated
		 */
		EClass ICM_SERVICE = eINSTANCE.getIcmService();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICM_SERVICE__PRIORITY = eINSTANCE.getIcmService_Priority();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl <em>Service Source Pin Icm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.ServiceSourcePinIcmImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getServiceSourcePinIcm()
		 * @generated
		 */
		EClass SERVICE_SOURCE_PIN_ICM = eINSTANCE.getServiceSourcePinIcm();

		/**
		 * The meta object literal for the '<em><b>Event Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_SOURCE_PIN_ICM__EVENT_DISTRIBUTION = eINSTANCE.getServiceSourcePinIcm_EventDistribution();

		/**
		 * The meta object literal for the '<em><b>Exec Time Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_SOURCE_PIN_ICM__EXEC_TIME_DISTRIBUTION = eINSTANCE.getServiceSourcePinIcm_ExecTimeDistribution();

		/**
		 * The meta object literal for the '<em><b>Deadline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_SOURCE_PIN_ICM__DEADLINE = eINSTANCE.getServiceSourcePinIcm_Deadline();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl <em>Sink Pin Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.SinkPinInstanceImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSinkPinInstance()
		 * @generated
		 */
		EClass SINK_PIN_INSTANCE = eINSTANCE.getSinkPinInstance();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINK_PIN_INSTANCE__MODE = eINSTANCE.getSinkPinInstance_Mode();

		/**
		 * The meta object literal for the '<em><b>React Sources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINK_PIN_INSTANCE__REACT_SOURCES = eINSTANCE.getSinkPinInstance_ReactSources();

		/**
		 * The meta object literal for the '<em><b>Link Sources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINK_PIN_INSTANCE__LINK_SOURCES = eINSTANCE.getSinkPinInstance_LinkSources();

		/**
		 * The meta object literal for the '<em><b>Exec Time Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION = eINSTANCE.getSinkPinInstance_ExecTimeDistribution();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINK_PIN_INSTANCE__PRIORITY = eINSTANCE.getSinkPinInstance_Priority();

		/**
		 * The meta object literal for the '<em><b>Downsampling Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR = eINSTANCE.getSinkPinInstance_DownsamplingFactor();

		/**
		 * The meta object literal for the '<em><b>Mutexes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINK_PIN_INSTANCE__MUTEXES = eINSTANCE.getSinkPinInstance_Mutexes();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl <em>Source Pin Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.SourcePinInstanceImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSourcePinInstance()
		 * @generated
		 */
		EClass SOURCE_PIN_INSTANCE = eINSTANCE.getSourcePinInstance();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_PIN_INSTANCE__MODE = eINSTANCE.getSourcePinInstance_Mode();

		/**
		 * The meta object literal for the '<em><b>Sinks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_PIN_INSTANCE__SINKS = eINSTANCE.getSourcePinInstance_Sinks();

		/**
		 * The meta object literal for the '<em><b>React Sinks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_PIN_INSTANCE__REACT_SINKS = eINSTANCE.getSourcePinInstance_ReactSinks();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.UniformImpl <em>Uniform</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.UniformImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getUniform()
		 * @generated
		 */
		EClass UNIFORM = eINSTANCE.getUniform();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIFORM__MAX = eINSTANCE.getUniform_Max();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIFORM__MIN = eINSTANCE.getUniform_Min();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.UnknownImpl <em>Unknown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.UnknownImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getUnknown()
		 * @generated
		 */
		EClass UNKNOWN = eINSTANCE.getUnknown();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNKNOWN__MEAN = eINSTANCE.getUnknown_Mean();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNKNOWN__MIN = eINSTANCE.getUnknown_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNKNOWN__MAX = eINSTANCE.getUnknown_Max();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.ScenarioImpl <em>Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.ScenarioImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getScenario()
		 * @generated
		 */
		EClass SCENARIO = eINSTANCE.getScenario();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__NAME = eINSTANCE.getScenario_Name();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO__NUMBER = eINSTANCE.getScenario_Number();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.impl.MutexImpl <em>Mutex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.impl.MutexImpl
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getMutex()
		 * @generated
		 */
		EClass MUTEX = eINSTANCE.getMutex();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MUTEX__NAME = eINSTANCE.getMutex_Name();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.SinkPinMode <em>Sink Pin Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.SinkPinMode
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSinkPinMode()
		 * @generated
		 */
		EEnum SINK_PIN_MODE = eINSTANCE.getSinkPinMode();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.icm.SourcePinMode <em>Source Pin Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.icm.SourcePinMode
		 * @see edu.cmu.sei.pacc.perf.icm.impl.IcmPackageImpl#getSourcePinMode()
		 * @generated
		 */
		EEnum SOURCE_PIN_MODE = eINSTANCE.getSourcePinMode();

	}

} //IcmPackage
