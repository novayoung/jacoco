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

import org.jacoco.agent.rt.IAgent;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.RuntimeData;

import java.io.IOException;

/**
 * The agent manages the life cycle of JaCoCo runtime.
 */
public class Agent implements IAgent {


	public static Agent getInstance(AgentOptions agentOptions) {
		return null;
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public String getSessionId() {
		return null;
	}

	@Override
	public void setSessionId(String id) {

	}

	@Override
	public void reset() {

	}

	@Override
	public byte[] getExecutionData(boolean reset) {
		return new byte[0];
	}

	@Override
	public void dump(boolean reset) throws IOException {

	}

	public RuntimeData getData() {
		return null;
	}
}
