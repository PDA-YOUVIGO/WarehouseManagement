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

public class NoReservedOutBoundResponse {

	@SerializedName("MT_Common_SAP_2_PDA_Response")
	private SapResponseMessage responseMessage;

	public SapResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(SapResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public NoReservedOutBoundResponse(SapResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public NoReservedOutBoundResponse() {
	}
}
