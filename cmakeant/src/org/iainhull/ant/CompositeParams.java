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
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to implement CmakeRule inheritence, this only supports 
 * the getters.
 */
public class CompositeParams implements Params {
	private Params first;
	private Params second;
	
	public CompositeParams(Params first, Params second) {
		this.first = first;
		this.second = second;
	}

	public File getBindir() {
		File ret = second.getBindir();
		if (ret == null) {
			ret = first.getBindir();
		}
		return ret;
	}

	public BuildType getBuildtype() {
		BuildType ret = second.getBuildtype();
		if (ret == null) {
			ret = first.getBuildtype();
		}
		return ret;
	}
	
	public String getTarget() {
		String ret = second.getTarget();
		if (ret == null) {
			ret = first.getTarget();
		}
		return ret;
	}
	
	public void setCmakepathdir(String cmakepath) {
		second.setCmakepathdir(cmakepath);
	}
	
	public String getCmakepathdir() {
		String ret = second.getCmakepathdir();
		if (ret == null) {
			ret = first.getCmakepathdir();
		}
		return ret;
	}
	
	public void setUsejobs(boolean jobs) {
		second.setUsejobs(jobs);
	}
	
	public boolean getUsejobs() {
		boolean ret = second.getUsejobs();
		if (ret == false) {
			ret = first.getUsejobs();
		}
		return ret;
	}
	
	public void setBindir(File binaryDir) {
		second.setBindir(binaryDir);
	}

	public void setBuildtype(BuildType buildType) {
		second.setBuildtype(buildType);
	}

	public Variable createVariable() {
		return second.createVariable();
	}

	public void setTarget(String target) {
		second.setTarget(target);
	}
	
	public Map<String, Variable> getVariables() {
		Map<String, Variable> ret = new HashMap<String, Variable>(first.getVariables());
		ret.putAll(second.getVariables());
		return ret;
	}
}
