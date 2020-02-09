/*
 * Copyright (c) 2020. komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.entities.Shelving;

import java.util.List;

public class ShelvingQueryResponse {

	@SerializedName("ControlInfo")
	private ControlInfo controlInfo;

	@SerializedName("ITEM")
	private List<Shelving> data;

	@SerializedName("RETURN")
	private SapResult result;

	public ControlInfo getControlInfo() {
		return controlInfo;
	}

	public void setControlInfo(ControlInfo controlInfo) {
		this.controlInfo = controlInfo;
	}

	public List<Shelving> getData() {
		return data;
	}

	public void setData(List<Shelving> data) {
		this.data = data;
	}

	public SapResult getResult() {
		return result;
	}

	public void setResult(SapResult result) {
		this.result = result;
	}

	public ShelvingQueryResponse(ControlInfo controlInfo, List<Shelving> data, SapResult result) {
		this.controlInfo = controlInfo;
		this.data = data;
		this.result = result;
	}

	public ShelvingQueryResponse() {
	}
}
