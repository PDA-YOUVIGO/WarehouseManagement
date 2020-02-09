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

/**
 * SAP业务返回报文执行结果信息
 */
public class SapResult {

	/**
	 * 结果字段
	 * S 成功
	 * X 失败
	 */
	@SerializedName("MSGTYPE")
	private String success;

	@SerializedName("MSGTXT")
	private String message;

	/**
	 * 仓库号
	 */
	@SerializedName("LGNUM")
	private String warehouseNumber;

	/**
	 * 转储单编号
	 */
	@SerializedName("TANUM")
	private String transferOrderNumber;

	/**
	 * 物料凭证编号
	 */
	@SerializedName("MBLNR")
	private String materialVoucherCode;

	/**
	 * 年度
	 */
	@SerializedName("MJAHR")
	private String year;

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

	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}

	public String getTransferOrderNumber() {
		return transferOrderNumber;
	}

	public void setTransferOrderNumber(String transferOrderNumber) {
		this.transferOrderNumber = transferOrderNumber;
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

	public SapResult(String success, String message, String warehouseNumber, String transferOrderNumber, String materialVoucherCode, String year) {
		this.success = success;
		this.message = message;
		this.warehouseNumber = warehouseNumber;
		this.transferOrderNumber = transferOrderNumber;
		this.materialVoucherCode = materialVoucherCode;
		this.year = year;
	}

	public SapResult() {
	}
}
