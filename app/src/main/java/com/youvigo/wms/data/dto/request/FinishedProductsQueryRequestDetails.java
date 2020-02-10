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

public class FinishedProductsQueryRequestDetails extends ShelvingQueryRequestDetails{

	@SerializedName("MATNR")
	private String materialCode;

	@SerializedName("CHARG")
	private String batchCode;

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public FinishedProductsQueryRequestDetails() {
	}

	public FinishedProductsQueryRequestDetails(String materialCode, String batchCode) {
		this.materialCode = materialCode;
		this.batchCode = batchCode;
	}

	public FinishedProductsQueryRequestDetails(String warehouseNumber, String stockLocationCode, String materialVoucherCode, String year, String startDate, String endDate, String materialCode, String batchCode) {
		super(warehouseNumber, stockLocationCode, materialVoucherCode, year, startDate, endDate);
		this.materialCode = materialCode;
		this.batchCode = batchCode;
	}
}
