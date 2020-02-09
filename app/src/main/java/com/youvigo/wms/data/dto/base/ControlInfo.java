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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * SAP请求通用头
 */
public class ControlInfo {

	/**
	 * 来源系统
	 */
	@SerializedName("SSystem")
	private String srcSystem;

	/**
	 * 目标系统
	 */
	@SerializedName("DSystem")
	private String targetSystem;

	/**
	 * 接口编号
	 */
	@SerializedName("InterfaceID")
	private String interfaceID;

	/**
	 * 发送时间
	 */
	@SerializedName("SendTime")
	private String sendTime;

	/**
	 * 序号
	 */
	@SerializedName("Serial")
	private String serial;

	public ControlInfo() {
		this.srcSystem = "AndroidPDA";
		this.targetSystem = "SAP";
		this.interfaceID = "pdaDev";
		this.sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		this.serial = UUID.randomUUID().toString();
	}

	public String getSrcSystem() {
		return srcSystem;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public String getInterfaceID() {
		return interfaceID;
	}

	public String getSendTime() {
		return sendTime;
	}

	public String getSerial() {
		return serial;
	}
}
