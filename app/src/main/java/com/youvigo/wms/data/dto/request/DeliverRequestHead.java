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

public class DeliverRequestHead {

	/**
	 * 仓库号
	 */
	@SerializedName("LGNUM")
	private String LGNUM;

	/**
	 * 转储单号
	 */
	@SerializedName("TANUM")
	private String TANUM;

	public String getLGNUM() {
		return LGNUM;
	}

	public void setLGNUM(String LGNUM) {
		this.LGNUM = LGNUM;
	}

	public String getTANUM() {
		return TANUM;
	}

	public void setTANUM(String TANUM) {
		this.TANUM = TANUM;
	}

	public DeliverRequestHead(String LGNUM, String TANUM) {
		this.LGNUM = LGNUM;
		this.TANUM = TANUM;
	}

	public DeliverRequestHead() {
	}
}
