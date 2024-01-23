/*******************************************************************************
 * Copyright (c) 2009, 2021 Mountainminds GmbH & Co. KG and Contributors
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Evgeny Mandrikov - initial API and implementation
 *
 *******************************************************************************/
package org.jacoco.agent.rt.internal_8cf7cdb.core.runtime;

//import org.jacoco.core.runtime.AbstractRuntime;
//import org.jacoco.core.runtime.IRuntime;
//import org.jacoco.core.runtime.RuntimeData;
//import org.objectweb.asm.ClassWriter;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.Opcodes;

/**
 * {@link IRuntime} which defines a new class using
 * {@code java.lang.invoke.MethodHandles.Lookup.defineClass} introduced in Java
 * 9. Module where class will be defined must be opened to at least module of
 * this class.
 */
public class InjectedClassRuntime extends AbstractRuntime {

	private static final String FIELD_NAME = "data";

	private static final String FIELD_TYPE = "Ljava/lang/Object;";

	public InjectedClassRuntime(Class<Object> objectClass, String $JaCoCo) {

	}
}
