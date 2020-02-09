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

package com.youvigo.wms.data.dto.base;

import com.google.gson.annotations.SerializedName;

public class MessageInfo {

	/**
	 * 	消息类型
	 *
	 * 	S 成功
	 * 	E 失败
	 */
	@SerializedName("MSGTYPE")
	private String success;


	/**
	 * 消息内容
	 */
	@SerializedName("MSGTXT")
	private String message;

	public MessageInfo() {
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageInfo(String success, String message) {
		this.success = success;
		this.message = message;
	}
}
