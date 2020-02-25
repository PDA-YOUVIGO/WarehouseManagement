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

public class NoReservedOutBoundRequest {

	@SerializedName("ControlInfo")
	private ControlInfo controlInfo;

	@SerializedName("HEAD")
	private NoReservedOutBoundRequestHead head;

	@SerializedName("ITEM")
	private List<NoReservedOutBoundRequestDetails> details;

	public ControlInfo getControlInfo() {
		return controlInfo;
	}

	public void setControlInfo(ControlInfo controlInfo) {
		this.controlInfo = controlInfo;
	}

	public NoReservedOutBoundRequestHead getHead() {
		return head;
	}

	public void setHead(NoReservedOutBoundRequestHead head) {
		this.head = head;
	}

	public List<NoReservedOutBoundRequestDetails> getDetails() {
		return details;
	}

	public void setDetails(List<NoReservedOutBoundRequestDetails> details) {
		this.details = details;
	}

	public NoReservedOutBoundRequest(ControlInfo controlInfo, NoReservedOutBoundRequestHead head,
									 List<NoReservedOutBoundRequestDetails> details) {
		this.controlInfo = controlInfo;
		this.head = head;
		this.details = details;
	}

	public NoReservedOutBoundRequest() {
	}
}
