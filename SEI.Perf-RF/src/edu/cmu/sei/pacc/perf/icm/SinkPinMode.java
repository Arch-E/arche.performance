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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sink Pin Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see edu.cmu.sei.pacc.perf.icm.IcmPackage#getSinkPinMode()
 * @model
 * @generated
 */
public final class SinkPinMode extends AbstractEnumerator {
	/**
	 * The '<em><b>Asynch</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Asynch</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #ASYNCH_LITERAL
	 * @model name="asynch"
	 * @generated
	 * @ordered
	 */
    public static final int ASYNCH = 0;

	/**
	 * The '<em><b>Mutex</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Mutex</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #MUTEX_LITERAL
	 * @model name="mutex"
	 * @generated
	 * @ordered
	 */
    public static final int MUTEX = 1;

	/**
	 * The '<em><b>Reenter</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Reenter</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #REENTER_LITERAL
	 * @model name="reenter"
	 * @generated
	 * @ordered
	 */
    public static final int REENTER = 2;

	/**
	 * The '<em><b>Asynch</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #ASYNCH
	 * @generated
	 * @ordered
	 */
    public static final SinkPinMode ASYNCH_LITERAL = new SinkPinMode(ASYNCH, "asynch", "asynch");

	/**
	 * The '<em><b>Mutex</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #MUTEX
	 * @generated
	 * @ordered
	 */
    public static final SinkPinMode MUTEX_LITERAL = new SinkPinMode(MUTEX, "mutex", "mutex");

	/**
	 * The '<em><b>Reenter</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #REENTER
	 * @generated
	 * @ordered
	 */
    public static final SinkPinMode REENTER_LITERAL = new SinkPinMode(REENTER, "reenter", "reenter");

	/**
	 * An array of all the '<em><b>Sink Pin Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final SinkPinMode[] VALUES_ARRAY =
		new SinkPinMode[] {
			ASYNCH_LITERAL,
			MUTEX_LITERAL,
			REENTER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Sink Pin Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Sink Pin Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static SinkPinMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SinkPinMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sink Pin Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SinkPinMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SinkPinMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sink Pin Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static SinkPinMode get(int value) {
		switch (value) {
			case ASYNCH: return ASYNCH_LITERAL;
			case MUTEX: return MUTEX_LITERAL;
			case REENTER: return REENTER_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SinkPinMode(int value, String name, String literal) {
		super(value, name, literal);
	}

} //SinkPinMode
