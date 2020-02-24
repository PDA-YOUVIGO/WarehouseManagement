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

public class AssessmentCategoriesRequestDetails {

	// 物料编码
	@SerializedName("MATNR")
	private String materialCode;

	// 工厂编码
	@SerializedName("BWKEY")
	private String factoryCode;

	// 备用字段1
	private String ADDITIONAL1;

	// 备用字段2
	private String ADDITIONAL2;

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getADDITIONAL1() {
		return ADDITIONAL1;
	}

	public void setADDITIONAL1(String ADDITIONAL1) {
		this.ADDITIONAL1 = ADDITIONAL1;
	}

	public String getADDITIONAL2() {
		return ADDITIONAL2;
	}

	public void setADDITIONAL2(String ADDITIONAL2) {
		this.ADDITIONAL2 = ADDITIONAL2;
	}

	public AssessmentCategoriesRequestDetails() {
	}

	public AssessmentCategoriesRequestDetails(String materialCode, String factoryCode, String ADDITIONAL1,
											  String ADDITIONAL2) {
		this.materialCode = materialCode;
		this.factoryCode = factoryCode;
		this.ADDITIONAL1 = ADDITIONAL1;
		this.ADDITIONAL2 = ADDITIONAL2;
	}
}
