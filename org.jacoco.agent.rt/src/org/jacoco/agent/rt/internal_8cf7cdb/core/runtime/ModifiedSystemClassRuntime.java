/*******************************************************************************
 * Copyright (c) 2009, 2021 Mountainminds GmbH & Co. KG and Contributors
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *
 *******************************************************************************/
package org.jacoco.agent.rt.internal_8cf7cdb.core.runtime;

import static java.lang.String.format;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.security.ProtectionDomain;


/**
 * This {@link org.jacoco.core.runtime.IRuntime} implementation works with a modified system class. A
 * new static field is added to a bootstrap class that will be used by
 * instrumented classes. As the system class itself needs to be instrumented
 * this runtime requires a Java agent.
 */
public class ModifiedSystemClassRuntime extends AbstractRuntime {

	private static final String ACCESS_FIELD_TYPE = "Ljava/lang/Object;";

	private final Class<?> systemClass;

	private final String systemClassName;

	private final String accessFieldName;

	/**
	 * Creates a new runtime based on the given class and members.
	 *
	 * @param systemClass
	 *            system class that contains the execution data
	 * @param accessFieldName
	 *            name of the public static runtime access field
	 *
	 */
	public ModifiedSystemClassRuntime(final Class<?> systemClass,
                                      final String accessFieldName) {
		super();
		this.systemClass = systemClass;
		this.systemClassName = systemClass.getName().replace('.', '/');
		this.accessFieldName = accessFieldName;
	}


	public static IRuntime createFor(Instrumentation inst, String s) {
		return null;
	}
}
