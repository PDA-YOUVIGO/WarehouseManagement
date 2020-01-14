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

package com.youvigo.wms.data.dto;

import com.google.gson.annotations.SerializedName;

public class ShelvingQueryRequestDetails {

	/**
	 * 仓库号
	 */
	@SerializedName("LGNUM")
	private String warehouseNumber;

	/**
	 * 库存地点
	 */
	@SerializedName("LGORT")
	private String stockLocationCode;

	/**
	 * 单据号（物料凭证编码）
	 */
	@SerializedName("MBLNR")
	private String materialVoucherCode;

	/**
	 * 年度
	 */
	@SerializedName("MJAHR")
	private String year;

	/**
	 * 创建日期-起始
	 */
	@SerializedName("SBDATU")
	private String startDate;

	/**
	 * 创建时间-结束
	 */
	@SerializedName("EBDATU")
	private String endDate;

	public ShelvingQueryRequestDetails() {
	}

	public ShelvingQueryRequestDetails(String warehouseNumber, String stockLocationCode, String materialVoucherCode, String year, String startDate, String endDate) {
		this.warehouseNumber = warehouseNumber;
		this.stockLocationCode = stockLocationCode;
		this.materialVoucherCode = materialVoucherCode;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}

	public String getStockLocationCode() {
		return stockLocationCode;
	}

	public void setStockLocationCode(String stockLocationCode) {
		this.stockLocationCode = stockLocationCode;
	}

	public String getMaterialVoucherCode() {
		return materialVoucherCode;
	}

	public void setMaterialVoucherCode(String materialVoucherCode) {
		this.materialVoucherCode = materialVoucherCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
