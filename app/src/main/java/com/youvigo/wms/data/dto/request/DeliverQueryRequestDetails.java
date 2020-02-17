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

public class DeliverQueryRequestDetails {

	// 仓库号
	@SerializedName("LGNUM")
	private String warehouseNumber;

	// 库存地
	@SerializedName("LGORT")
	private String stockLocationCode;

	// TO单号
	@SerializedName("TANUM")
	private String orderNumber;

	// 转储单创建日期
	@SerializedName("BDATU")
	private String orderDate;

	// 原仓库类型
	private String VLTYP;

	// 目的地仓库类型
	private String NLTYP;

	// 创建日期-起始
	@SerializedName("SBDATU")
	private String startDate;

	// 创建时间-结束
	@SerializedName("EBDATU")
	private String endDate;

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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getVLTYP() {
		return VLTYP;
	}

	public void setVLTYP(String VLTYP) {
		this.VLTYP = VLTYP;
	}

	public String getNLTYP() {
		return NLTYP;
	}

	public void setNLTYP(String NLTYP) {
		this.NLTYP = NLTYP;
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

	public DeliverQueryRequestDetails(String warehouseNumber, String stockLocationCode, String orderNumber, String orderDate, String VLTYP, String NLTYP, String startDate, String endDate) {
		this.warehouseNumber = warehouseNumber;
		this.stockLocationCode = stockLocationCode;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.VLTYP = VLTYP;
		this.NLTYP = NLTYP;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public DeliverQueryRequestDetails() {
	}
}
