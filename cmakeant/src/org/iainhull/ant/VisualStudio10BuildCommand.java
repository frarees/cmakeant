/* cmakeant - copyright Iain Hull.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iainhull.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualStudio10BuildCommand extends BuildCommand {
	protected final Map<String, String> workspaceExtentions;
	protected final WorkSpaceLocator locator;
	
	public VisualStudio10BuildCommand(GeneratorRule generator, String makeCommand, String cmakeGenerator) {
		this(generator, makeCommand, cmakeGenerator, new WorkSpaceLocator(), createWorkspaceExtentions());
	}

	VisualStudio10BuildCommand(GeneratorRule generator, String makeCommand, String cmakeGenerator, WorkSpaceLocator locator) {
		this(generator, makeCommand, cmakeGenerator, locator, createWorkspaceExtentions());
	}
	
	protected VisualStudio10BuildCommand(GeneratorRule generator, String makeCommand, String cmakeGenerator, WorkSpaceLocator locator, Map<String, String> workspaceExtentions) {
		super(generator, makeCommand, cmakeGenerator);
		this.locator = locator;
		this.workspaceExtentions = workspaceExtentions;
	}

	
	@Override
	protected List<String> buildCommand() {
		List<String> ret = new ArrayList<String>();
		//ret.add(makeCommand);
		// FIXME: shouldn't be hardcoded, but as far as I've tested, cmake retrieves devenv.com, which is not encouraged
		ret.add("msbuild");
		//ret.add("/t:Build");
		ret.add("/p:Configuration=" + defaultBuildType(generator.getBuildtype()).toString());
		
		if (generator.getTarget() != null) {
			ret.add("/t:" + generator.getTarget());
		}
		
		if (generator.getUsejobs()) {
			ret.add("/m");
		}
		
		ret.add(workspace(workspaceExtentions.get(cmakeGenerator)));
		
		return ret;
	}

	@Override
	protected boolean canBuild() {
		return workspaceExtentions.containsKey(cmakeGenerator);
	}
	
	@Override
	protected boolean canSkipCmakeStep() {
		return false;
	}
	
	protected BuildType defaultBuildType(BuildType buildType) {
		if (buildType != null) {
			return buildType;
		}
		return BuildType.Release;
	}
	
	private static Map<String, String> createWorkspaceExtentions() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Visual Studio 10", "sln");
		map.put("Visual Studio 10 Win64", "sln");
		map.put("Visual Studio 11", "sln");
		map.put("Visual Studio 11 Win64", "sln");
	
		return Collections.unmodifiableMap(map);
	}
	
	protected String workspace(final String extension) {
		File binaryDir = generator.getBindir();
		return locator.findByExtension(binaryDir, extension);
	}
}
