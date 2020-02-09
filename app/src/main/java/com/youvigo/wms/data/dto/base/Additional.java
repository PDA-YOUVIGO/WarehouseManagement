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

/**
 * SAP附加字段
 */
public class Additional {
	@SerializedName("ADDIT1")
	private String addit1;

	@SerializedName("ADDIT2")
	private String addit2;

	@SerializedName("ADDIT3")
	private String addit3;

	@SerializedName("ADDIT4")
	private String addit4;

	@SerializedName("ADDIT5")
	private String addit5;

	public String getAddit1() {
		return addit1;
	}

	public void setAddit1(String addit1) {
		this.addit1 = addit1;
	}

	public String getAddit2() {
		return addit2;
	}

	public void setAddit2(String addit2) {
		this.addit2 = addit2;
	}

	public String getAddit3() {
		return addit3;
	}

	public void setAddit3(String addit3) {
		this.addit3 = addit3;
	}

	public String getAddit4() {
		return addit4;
	}

	public void setAddit4(String addit4) {
		this.addit4 = addit4;
	}

	public String getAddit5() {
		return addit5;
	}

	public void setAddit5(String addit5) {
		this.addit5 = addit5;
	}

	public Additional(String addit1, String addit2, String addit3, String addit4, String addit5) {
		this.addit1 = addit1;
		this.addit2 = addit2;
		this.addit3 = addit3;
		this.addit4 = addit4;
		this.addit5 = addit5;
	}

	public Additional() {
	}
}
