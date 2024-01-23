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
package org.jacoco.agent.rt.internal_8cf7cdb;

//import org.jacoco.agent.rt.internal.ClassFileDumper;
//import org.jacoco.agent.rt.internal.IExceptionLogger;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.IRuntime;
//import org.jacoco.core.instr.Instrumenter;
//import org.jacoco.core.runtime.AgentOptions;
//import org.jacoco.core.runtime.IRuntime;
//import org.jacoco.core.runtime.WildcardMatcher;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Class file transformer to instrument classes for code coverage analysis.
 */
public class CoverageTransformer implements ClassFileTransformer {


	public CoverageTransformer(IRuntime runtime, AgentOptions agentOptions, IExceptionLogger systemErr) {

	}

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		return new byte[0];
	}
}
