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

package com.youvigo.wms.data.dto.request;

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.ControlInfo;

import java.util.List;

public class ReservedOutBoundRequest {

	@SerializedName("controlInfo")
	private ControlInfo controlInfo;

	@SerializedName("HEAD")
	private ReservedOutBoundRequestHead requestHead;

	@SerializedName("ITEM")
	private List<ReservedOutBoundRequestDetails> requestDetails;

	public ControlInfo getControlInfo() {
		return controlInfo;
	}

	public void setControlInfo(ControlInfo controlInfo) {
		this.controlInfo = controlInfo;
	}

	public ReservedOutBoundRequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(ReservedOutBoundRequestHead requestHead) {
		this.requestHead = requestHead;
	}

	public List<ReservedOutBoundRequestDetails> getRequestDetails() {
		return requestDetails;
	}

	public void setRequestDetails(List<ReservedOutBoundRequestDetails> requestDetails) {
		this.requestDetails = requestDetails;
	}

	public ReservedOutBoundRequest(ControlInfo controlInfo, ReservedOutBoundRequestHead requestHead, List<ReservedOutBoundRequestDetails> requestDetails) {
		this.controlInfo = controlInfo;
		this.requestHead = requestHead;
		this.requestDetails = requestDetails;
	}

	public ReservedOutBoundRequest() {
	}
}
