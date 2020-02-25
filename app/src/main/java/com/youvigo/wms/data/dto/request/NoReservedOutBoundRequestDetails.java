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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoReservedOutBoundRequestDetails {

	// 仓库编号
	private String LGNUM;

	// 行项目编号
	@SerializedName("ITEMNO")
	private int lineNumber;

	// 工厂
	private String WERKS;

	// 库存地点
	private String LGORT;

	// 仓位
	@SerializedName("LGPLA")
	private String cargoSpace;

	// 物料编号
	@SerializedName("MATNR")
	private String materialCode;

	// 物料名称
	// 接口不需要传递，在序列化时将其忽略
	@Expose(deserialize = false, serialize = false)
	@SerializedName("MAKTX")
	private String materialDescription;

	// 批次
	@SerializedName("CHARG")
	private String batchNumber;

	/// 规格
	@SerializedName("ZZDRUGSPEC")
	@Expose(deserialize = false, serialize = false)
	private String specification;

	// 数量
	@SerializedName("BDMNG")
	private double quantity;

	// 单位
	@SerializedName("MEINS")
	private String baseUnit;

	// 收货库存地点
	private String UMLGO;

	// 备注
	private String MEMO;

	// 成本中心
	@SerializedName("KOSTL")
	private String costCenter;

	public String getLGNUM() {
		return LGNUM;
	}

	public void setLGNUM(String LGNUM) {
		this.LGNUM = LGNUM;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getWERKS() {
		return WERKS;
	}

	public void setWERKS(String WERKS) {
		this.WERKS = WERKS;
	}

	public String getLGORT() {
		return LGORT;
	}

	public void setLGORT(String LGORT) {
		this.LGORT = LGORT;
	}

	public String getCargoSpace() {
		return cargoSpace;
	}

	public void setCargoSpace(String cargoSpace) {
		this.cargoSpace = cargoSpace;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getUMLGO() {
		return UMLGO;
	}

	public void setUMLGO(String UMLGO) {
		this.UMLGO = UMLGO;
	}

	public String getMEMO() {
		return MEMO;
	}

	public void setMEMO(String MEMO) {
		this.MEMO = MEMO;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public NoReservedOutBoundRequestDetails(String LGNUM, int lineNumber, String WERKS, String LGORT,
											String cargoSpace, String materialCode, String materialDescription,
											String batchNumber, String specification, double quantity, String baseUnit
			, String UMLGO, String MEMO, String costCenter) {
		this.LGNUM = LGNUM;
		this.lineNumber = lineNumber;
		this.WERKS = WERKS;
		this.LGORT = LGORT;
		this.cargoSpace = cargoSpace;
		this.materialCode = materialCode;
		this.materialDescription = materialDescription;
		this.batchNumber = batchNumber;
		this.specification = specification;
		this.quantity = quantity;
		this.baseUnit = baseUnit;
		this.UMLGO = UMLGO;
		this.MEMO = MEMO;
		this.costCenter = costCenter;
	}

	public NoReservedOutBoundRequestDetails() {
	}
}
