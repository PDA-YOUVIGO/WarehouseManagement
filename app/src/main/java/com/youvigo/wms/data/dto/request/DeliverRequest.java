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

public class DeliverRequest {

	@SerializedName("ControlInfo")
	private ControlInfo controlInfo;

	@SerializedName("HEAD")
	private DeliverRequestHead requestHead;

	@SerializedName("ITEM")
	private DeliverRequestItem requestItem;

	public ControlInfo getControlInfo() {
		return controlInfo;
	}

	public void setControlInfo(ControlInfo controlInfo) {
		this.controlInfo = controlInfo;
	}

	public DeliverRequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(DeliverRequestHead requestHead) {
		this.requestHead = requestHead;
	}

	public DeliverRequestItem getRequestItem() {
		return requestItem;
	}

	public void setRequestItem(DeliverRequestItem requestItem) {
		this.requestItem = requestItem;
	}

	public DeliverRequest(ControlInfo controlInfo, DeliverRequestHead requestHead, DeliverRequestItem requestItem) {
		this.controlInfo = controlInfo;
		this.requestHead = requestHead;
		this.requestItem = requestItem;
	}

	public DeliverRequest() {
	}
}
