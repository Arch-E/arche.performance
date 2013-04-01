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

package edu.cmu.sei.pacc.perf.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see edu.cmu.sei.pacc.perf.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://edu.cmu.sei.pacc.perf.model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "edu.cmu.sei.pacc.perf.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.TaskImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 0;

	/**
	 * The feature id for the '<em><b>Task Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TASK_ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NAME = 1;

	/**
	 * The feature id for the '<em><b>Subtasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUBTASKS = 2;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DEADLINE = 3;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.PeriodicTaskImpl <em>Periodic Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.PeriodicTaskImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getPeriodicTask()
	 * @generated
	 */
	int PERIODIC_TASK = 1;

	/**
	 * The feature id for the '<em><b>Task Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK__TASK_ID = TASK__TASK_ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK__NAME = TASK__NAME;

	/**
	 * The feature id for the '<em><b>Subtasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK__SUBTASKS = TASK__SUBTASKS;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK__DEADLINE = TASK__DEADLINE;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK__PERIOD = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK__OFFSET = TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Periodic Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.AperiodicTaskImpl <em>Aperiodic Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.AperiodicTaskImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getAperiodicTask()
	 * @generated
	 */
	int APERIODIC_TASK = 2;

	/**
	 * The feature id for the '<em><b>Task Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APERIODIC_TASK__TASK_ID = TASK__TASK_ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APERIODIC_TASK__NAME = TASK__NAME;

	/**
	 * The feature id for the '<em><b>Subtasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APERIODIC_TASK__SUBTASKS = TASK__SUBTASKS;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APERIODIC_TASK__DEADLINE = TASK__DEADLINE;

	/**
	 * The feature id for the '<em><b>Interarrival Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION = TASK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Aperiodic Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APERIODIC_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl <em>Subtask</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getSubtask()
	 * @generated
	 */
	int SUBTASK = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__NAME = 0;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__PRIORITY = 1;

	/**
	 * The feature id for the '<em><b>Ret Anchor Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__RET_ANCHOR_USED = 2;

	/**
	 * The feature id for the '<em><b>Activation Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__ACTIVATION_SYNCHRONOUS = 3;

	/**
	 * The feature id for the '<em><b>Exec Time Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__EXEC_TIME_DISTRIBUTION = 4;

	/**
	 * The feature id for the '<em><b>Bypass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__BYPASS = 5;

	/**
	 * The feature id for the '<em><b>Downsampling Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__DOWNSAMPLING_FACTOR = 6;

	/**
	 * The feature id for the '<em><b>Calling Thread Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__CALLING_THREAD_PRIORITY = 7;

	/**
	 * The feature id for the '<em><b>Mutexes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__MUTEXES = 8;

	/**
	 * The feature id for the '<em><b>Pin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK__PIN_ID = 9;

	/**
	 * The number of structural features of the '<em>Subtask</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTASK_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.DistributionImpl <em>Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.DistributionImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getDistribution()
	 * @generated
	 */
	int DISTRIBUTION = 4;

	/**
	 * The number of structural features of the '<em>Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTRIBUTION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.ConstantImpl <em>Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.ConstantImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 5;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__VALUE = DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_FEATURE_COUNT = DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.ExponentialImpl <em>Exponential</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.ExponentialImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getExponential()
	 * @generated
	 */
	int EXPONENTIAL = 6;

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
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.UniformImpl <em>Uniform</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.UniformImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getUniform()
	 * @generated
	 */
	int UNIFORM = 7;

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
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.UnknownImpl <em>Unknown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.UnknownImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getUnknown()
	 * @generated
	 */
	int UNKNOWN = 8;

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
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.NormalImpl <em>Normal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.NormalImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getNormal()
	 * @generated
	 */
	int NORMAL = 9;

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
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.PerformanceModelImpl <em>Performance Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.PerformanceModelImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getPerformanceModel()
	 * @generated
	 */
	int PERFORMANCE_MODEL = 10;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_MODEL__TASKS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_MODEL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Mutexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_MODEL__MUTEXES = 2;

	/**
	 * The feature id for the '<em><b>Source File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_MODEL__SOURCE_FILE = 3;

	/**
	 * The number of structural features of the '<em>Performance Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_MODEL_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl <em>SS Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getSSTask()
	 * @generated
	 */
	int SS_TASK = 11;

	/**
	 * The feature id for the '<em><b>Task Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__TASK_ID = APERIODIC_TASK__TASK_ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__NAME = APERIODIC_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Subtasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__SUBTASKS = APERIODIC_TASK__SUBTASKS;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__DEADLINE = APERIODIC_TASK__DEADLINE;

	/**
	 * The feature id for the '<em><b>Interarrival Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__INTERARRIVAL_DISTRIBUTION = APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION;

	/**
	 * The feature id for the '<em><b>Budget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__BUDGET = APERIODIC_TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Replenishment Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__REPLENISHMENT_PERIOD = APERIODIC_TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Background Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK__BACKGROUND_PRIORITY = APERIODIC_TASK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>SS Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SS_TASK_FEATURE_COUNT = APERIODIC_TASK_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.cmu.sei.pacc.perf.model.impl.MutexImpl <em>Mutex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.cmu.sei.pacc.perf.model.impl.MutexImpl
	 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getMutex()
	 * @generated
	 */
	int MUTEX = 12;

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
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Task#getTaskId <em>Task Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task Id</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Task#getTaskId()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_TaskId();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Task#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Task#getName()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.model.Task#getSubtasks <em>Subtasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subtasks</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Task#getSubtasks()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Subtasks();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Task#getDeadline <em>Deadline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deadline</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Task#getDeadline()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Deadline();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.PeriodicTask <em>Periodic Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Periodic Task</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PeriodicTask
	 * @generated
	 */
	EClass getPeriodicTask();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.PeriodicTask#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PeriodicTask#getPeriod()
	 * @see #getPeriodicTask()
	 * @generated
	 */
	EAttribute getPeriodicTask_Period();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.PeriodicTask#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PeriodicTask#getOffset()
	 * @see #getPeriodicTask()
	 * @generated
	 */
	EAttribute getPeriodicTask_Offset();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.AperiodicTask <em>Aperiodic Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aperiodic Task</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.AperiodicTask
	 * @generated
	 */
	EClass getAperiodicTask();

	/**
	 * Returns the meta object for the containment reference '{@link edu.cmu.sei.pacc.perf.model.AperiodicTask#getInterarrivalDistribution <em>Interarrival Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Interarrival Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.AperiodicTask#getInterarrivalDistribution()
	 * @see #getAperiodicTask()
	 * @generated
	 */
	EReference getAperiodicTask_InterarrivalDistribution();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Subtask <em>Subtask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subtask</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask
	 * @generated
	 */
	EClass getSubtask();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getName()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getPriority()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_Priority();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#isRetAnchorUsed <em>Ret Anchor Used</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ret Anchor Used</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#isRetAnchorUsed()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_RetAnchorUsed();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#isActivationSynchronous <em>Activation Synchronous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activation Synchronous</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#isActivationSynchronous()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_ActivationSynchronous();

	/**
	 * Returns the meta object for the containment reference '{@link edu.cmu.sei.pacc.perf.model.Subtask#getExecTimeDistribution <em>Exec Time Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exec Time Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getExecTimeDistribution()
	 * @see #getSubtask()
	 * @generated
	 */
	EReference getSubtask_ExecTimeDistribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#getBypass <em>Bypass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bypass</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getBypass()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_Bypass();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#getDownsamplingFactor <em>Downsampling Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Downsampling Factor</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getDownsamplingFactor()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_DownsamplingFactor();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#getCallingThreadPriority <em>Calling Thread Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Calling Thread Priority</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getCallingThreadPriority()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_CallingThreadPriority();

	/**
	 * Returns the meta object for the reference list '{@link edu.cmu.sei.pacc.perf.model.Subtask#getMutexes <em>Mutexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mutexes</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getMutexes()
	 * @see #getSubtask()
	 * @generated
	 */
	EReference getSubtask_Mutexes();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Subtask#getPinId <em>Pin Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pin Id</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Subtask#getPinId()
	 * @see #getSubtask()
	 * @generated
	 */
	EAttribute getSubtask_PinId();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Distribution <em>Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Distribution</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Distribution
	 * @generated
	 */
	EClass getDistribution();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Constant
	 * @generated
	 */
	EClass getConstant();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Constant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Constant#getValue()
	 * @see #getConstant()
	 * @generated
	 */
	EAttribute getConstant_Value();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Exponential <em>Exponential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Exponential
	 * @generated
	 */
	EClass getExponential();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Exponential#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Exponential#getMean()
	 * @see #getExponential()
	 * @generated
	 */
	EAttribute getExponential_Mean();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Uniform <em>Uniform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uniform</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Uniform
	 * @generated
	 */
	EClass getUniform();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Uniform#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Uniform#getMax()
	 * @see #getUniform()
	 * @generated
	 */
	EAttribute getUniform_Max();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Uniform#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Uniform#getMin()
	 * @see #getUniform()
	 * @generated
	 */
	EAttribute getUniform_Min();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Unknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unknown</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Unknown
	 * @generated
	 */
	EClass getUnknown();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Unknown#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Unknown#getMean()
	 * @see #getUnknown()
	 * @generated
	 */
	EAttribute getUnknown_Mean();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Unknown#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Unknown#getMin()
	 * @see #getUnknown()
	 * @generated
	 */
	EAttribute getUnknown_Min();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Unknown#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Unknown#getMax()
	 * @see #getUnknown()
	 * @generated
	 */
	EAttribute getUnknown_Max();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Normal <em>Normal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normal</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Normal
	 * @generated
	 */
	EClass getNormal();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Normal#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Normal#getMean()
	 * @see #getNormal()
	 * @generated
	 */
	EAttribute getNormal_Mean();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Normal#getStdDev <em>Std Dev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Std Dev</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Normal#getStdDev()
	 * @see #getNormal()
	 * @generated
	 */
	EAttribute getNormal_StdDev();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.PerformanceModel <em>Performance Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Performance Model</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PerformanceModel
	 * @generated
	 */
	EClass getPerformanceModel();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.model.PerformanceModel#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tasks</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PerformanceModel#getTasks()
	 * @see #getPerformanceModel()
	 * @generated
	 */
	EReference getPerformanceModel_Tasks();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.PerformanceModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PerformanceModel#getName()
	 * @see #getPerformanceModel()
	 * @generated
	 */
	EAttribute getPerformanceModel_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.cmu.sei.pacc.perf.model.PerformanceModel#getMutexes <em>Mutexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mutexes</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PerformanceModel#getMutexes()
	 * @see #getPerformanceModel()
	 * @generated
	 */
	EReference getPerformanceModel_Mutexes();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.PerformanceModel#getSourceFile <em>Source File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source File</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.PerformanceModel#getSourceFile()
	 * @see #getPerformanceModel()
	 * @generated
	 */
	EAttribute getPerformanceModel_SourceFile();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.SSTask <em>SS Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SS Task</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.SSTask
	 * @generated
	 */
	EClass getSSTask();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.SSTask#getBudget <em>Budget</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Budget</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.SSTask#getBudget()
	 * @see #getSSTask()
	 * @generated
	 */
    EAttribute getSSTask_Budget();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.SSTask#getReplenishmentPeriod <em>Replenishment Period</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Replenishment Period</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.SSTask#getReplenishmentPeriod()
	 * @see #getSSTask()
	 * @generated
	 */
    EAttribute getSSTask_ReplenishmentPeriod();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.SSTask#getBackgroundPriority <em>Background Priority</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background Priority</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.SSTask#getBackgroundPriority()
	 * @see #getSSTask()
	 * @generated
	 */
    EAttribute getSSTask_BackgroundPriority();

	/**
	 * Returns the meta object for class '{@link edu.cmu.sei.pacc.perf.model.Mutex <em>Mutex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mutex</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Mutex
	 * @generated
	 */
	EClass getMutex();

	/**
	 * Returns the meta object for the attribute '{@link edu.cmu.sei.pacc.perf.model.Mutex#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.cmu.sei.pacc.perf.model.Mutex#getName()
	 * @see #getMutex()
	 * @generated
	 */
	EAttribute getMutex_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.TaskImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Task Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__TASK_ID = eINSTANCE.getTask_TaskId();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__NAME = eINSTANCE.getTask_Name();

		/**
		 * The meta object literal for the '<em><b>Subtasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__SUBTASKS = eINSTANCE.getTask_Subtasks();

		/**
		 * The meta object literal for the '<em><b>Deadline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__DEADLINE = eINSTANCE.getTask_Deadline();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.PeriodicTaskImpl <em>Periodic Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.PeriodicTaskImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getPeriodicTask()
		 * @generated
		 */
		EClass PERIODIC_TASK = eINSTANCE.getPeriodicTask();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERIODIC_TASK__PERIOD = eINSTANCE.getPeriodicTask_Period();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERIODIC_TASK__OFFSET = eINSTANCE.getPeriodicTask_Offset();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.AperiodicTaskImpl <em>Aperiodic Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.AperiodicTaskImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getAperiodicTask()
		 * @generated
		 */
		EClass APERIODIC_TASK = eINSTANCE.getAperiodicTask();

		/**
		 * The meta object literal for the '<em><b>Interarrival Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APERIODIC_TASK__INTERARRIVAL_DISTRIBUTION = eINSTANCE.getAperiodicTask_InterarrivalDistribution();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl <em>Subtask</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.SubtaskImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getSubtask()
		 * @generated
		 */
		EClass SUBTASK = eINSTANCE.getSubtask();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__NAME = eINSTANCE.getSubtask_Name();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__PRIORITY = eINSTANCE.getSubtask_Priority();

		/**
		 * The meta object literal for the '<em><b>Ret Anchor Used</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__RET_ANCHOR_USED = eINSTANCE.getSubtask_RetAnchorUsed();

		/**
		 * The meta object literal for the '<em><b>Activation Synchronous</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__ACTIVATION_SYNCHRONOUS = eINSTANCE.getSubtask_ActivationSynchronous();

		/**
		 * The meta object literal for the '<em><b>Exec Time Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBTASK__EXEC_TIME_DISTRIBUTION = eINSTANCE.getSubtask_ExecTimeDistribution();

		/**
		 * The meta object literal for the '<em><b>Bypass</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__BYPASS = eINSTANCE.getSubtask_Bypass();

		/**
		 * The meta object literal for the '<em><b>Downsampling Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__DOWNSAMPLING_FACTOR = eINSTANCE.getSubtask_DownsamplingFactor();

		/**
		 * The meta object literal for the '<em><b>Calling Thread Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__CALLING_THREAD_PRIORITY = eINSTANCE.getSubtask_CallingThreadPriority();

		/**
		 * The meta object literal for the '<em><b>Mutexes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBTASK__MUTEXES = eINSTANCE.getSubtask_Mutexes();

		/**
		 * The meta object literal for the '<em><b>Pin Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBTASK__PIN_ID = eINSTANCE.getSubtask_PinId();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.DistributionImpl <em>Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.DistributionImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getDistribution()
		 * @generated
		 */
		EClass DISTRIBUTION = eINSTANCE.getDistribution();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.ConstantImpl <em>Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.ConstantImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getConstant()
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
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.ExponentialImpl <em>Exponential</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.ExponentialImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getExponential()
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
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.UniformImpl <em>Uniform</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.UniformImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getUniform()
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
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.UnknownImpl <em>Unknown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.UnknownImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getUnknown()
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
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.NormalImpl <em>Normal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.NormalImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getNormal()
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
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.PerformanceModelImpl <em>Performance Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.PerformanceModelImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getPerformanceModel()
		 * @generated
		 */
		EClass PERFORMANCE_MODEL = eINSTANCE.getPerformanceModel();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERFORMANCE_MODEL__TASKS = eINSTANCE.getPerformanceModel_Tasks();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERFORMANCE_MODEL__NAME = eINSTANCE.getPerformanceModel_Name();

		/**
		 * The meta object literal for the '<em><b>Mutexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERFORMANCE_MODEL__MUTEXES = eINSTANCE.getPerformanceModel_Mutexes();

		/**
		 * The meta object literal for the '<em><b>Source File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERFORMANCE_MODEL__SOURCE_FILE = eINSTANCE.getPerformanceModel_SourceFile();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl <em>SS Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.SSTaskImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getSSTask()
		 * @generated
		 */
		EClass SS_TASK = eINSTANCE.getSSTask();

		/**
		 * The meta object literal for the '<em><b>Budget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SS_TASK__BUDGET = eINSTANCE.getSSTask_Budget();

		/**
		 * The meta object literal for the '<em><b>Replenishment Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SS_TASK__REPLENISHMENT_PERIOD = eINSTANCE.getSSTask_ReplenishmentPeriod();

		/**
		 * The meta object literal for the '<em><b>Background Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SS_TASK__BACKGROUND_PRIORITY = eINSTANCE.getSSTask_BackgroundPriority();

		/**
		 * The meta object literal for the '{@link edu.cmu.sei.pacc.perf.model.impl.MutexImpl <em>Mutex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.cmu.sei.pacc.perf.model.impl.MutexImpl
		 * @see edu.cmu.sei.pacc.perf.model.impl.ModelPackageImpl#getMutex()
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

	}

} //ModelPackage
