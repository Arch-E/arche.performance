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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Source Pin Icm</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getEventDistribution <em>Event Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getExecTimeDistribution <em>Exec Time Distribution</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getDeadline <em>Deadline</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getServiceSourcePinIcm()
 * @model
 * @generated
 */
public interface ServiceSourcePinIcm extends SourcePinInstance {
	/**
	 * Returns the value of the '<em><b>Event Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Distribution</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Distribution</em>' containment reference.
	 * @see #setEventDistribution(Distribution)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getServiceSourcePinIcm_EventDistribution()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Distribution getEventDistribution();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getEventDistribution <em>Event Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Distribution</em>' containment reference.
	 * @see #getEventDistribution()
	 * @generated
	 */
	void setEventDistribution(Distribution value);

	/**
	 * Returns the value of the '<em><b>Exec Time Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Time Distribution</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Time Distribution</em>' containment reference.
	 * @see #setExecTimeDistribution(Distribution)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getServiceSourcePinIcm_ExecTimeDistribution()
	 * @model containment="true"
	 * @generated
	 */
	Distribution getExecTimeDistribution();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getExecTimeDistribution <em>Exec Time Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exec Time Distribution</em>' containment reference.
	 * @see #getExecTimeDistribution()
	 * @generated
	 */
	void setExecTimeDistribution(Distribution value);

	/**
	 * Returns the value of the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deadline</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deadline</em>' attribute.
	 * @see #setDeadline(double)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getServiceSourcePinIcm_Deadline()
	 * @model
	 * @generated
	 */
	double getDeadline();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.ServiceSourcePinIcm#getDeadline <em>Deadline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deadline</em>' attribute.
	 * @see #getDeadline()
	 * @generated
	 */
	void setDeadline(double value);

} // ServiceSourcePinIcm
