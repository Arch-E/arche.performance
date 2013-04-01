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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Pin Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getMode <em>Mode</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getSinks <em>Sinks</em>}</li>
 *   <li>{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getReactSinks <em>React Sinks</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getSourcePinInstance()
 * @model
 * @generated
 */
public interface SourcePinInstance extends PinInstance {
	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.cmu.sei.pacc.perf.icm.SourcePinMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinMode
	 * @see #setMode(SourcePinMode)
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getSourcePinInstance_Mode()
	 * @model
	 * @generated
	 */
	SourcePinMode getMode();

	/**
	 * Sets the value of the '{@link edu.cmu.sei.pacc.perf.icm.SourcePinInstance#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see edu.cmu.sei.pacc.perf.icm.SourcePinMode
	 * @see #getMode()
	 * @generated
	 */
	void setMode(SourcePinMode value);

	/**
	 * Returns the value of the '<em><b>Sinks</b></em>' reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance}.
	 * It is bidirectional and its opposite is '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getLinkSources <em>Link Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sinks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sinks</em>' reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getSourcePinInstance_Sinks()
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getLinkSources
	 * @model type="edu.cmu.sei.pacc.perf.icm.SinkPinInstance" opposite="linkSources"
	 * @generated
	 */
	EList getSinks();

	/**
	 * Returns the value of the '<em><b>React Sinks</b></em>' reference list.
	 * The list contents are of type {@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance}.
	 * It is bidirectional and its opposite is '{@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getReactSources <em>React Sources</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>React Sinks</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>React Sinks</em>' reference list.
	 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getSourcePinInstance_ReactSinks()
	 * @see edu.cmu.sei.pacc.perf.icm.SinkPinInstance#getReactSources
	 * @model type="edu.cmu.sei.pacc.perf.icm.SinkPinInstance" opposite="reactSources"
	 * @generated
	 */
    EList getReactSinks();

} // SourcePinInstance
