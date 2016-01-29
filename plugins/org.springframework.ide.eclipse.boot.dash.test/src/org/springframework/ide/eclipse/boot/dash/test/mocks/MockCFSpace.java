/*******************************************************************************
 * Copyright (c) 2016 Pivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.boot.dash.test.mocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.cloudfoundry.client.lib.domain.CloudApplication.AppState;
import org.springframework.ide.eclipse.boot.dash.cloudfoundry.client.CFApplication;
import org.springframework.ide.eclipse.boot.dash.cloudfoundry.client.CFOrganization;
import org.springframework.ide.eclipse.boot.dash.cloudfoundry.client.CFService;

import com.google.common.collect.ImmutableList;

public class MockCFSpace extends CFSpaceData {

	private Map<String, CFService> servicesByName = new HashMap<>();
	private Map<String, CFApplication> appsByName = new HashMap<>();

	public MockCFSpace(String name, UUID guid, CFOrganization org) {
		super(name, guid, org);
	}

	public List<CFService> getServices() {
		return ImmutableList.copyOf(servicesByName.values());
	}

	public ImmutableList<CFApplication> getApplicationsWithBasicInfo() {
		return ImmutableList.copyOf(appsByName.values());
	}

	public CFApplication defApp(String name) {
		CFApplication existing = appsByName.get(name);
		if (existing==null) {
			appsByName.put(name, existing = new MockCFApplication(
					name,
					UUID.randomUUID(),
					1,
					0,
					AppState.STOPPED
			));
		}
		return existing;
	}

}
