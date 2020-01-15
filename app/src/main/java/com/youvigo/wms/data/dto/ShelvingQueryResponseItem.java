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

/**
 * 入库上架SAP返回数据报文
 */
public class ShelvingQueryResponseItem {

	/**
	 * 仓库号
	 */
	@SerializedName("LGNUM")
	private String warehouseNumber;

	/**
	 * 移动类型
	 */
	@SerializedName("BWLVS")
	public String moveType;

	/**
	 * 单据号（物料凭证号）
	 */
	@SerializedName("MBLNR")
	private String materialVoucherCode;

	/**
	 * 物料凭证行项目编号（行号）
	 */
	@SerializedName("ZEILE")
	public String voucherLineNumber;

	/**
	 * 物料编号
	 */
	@SerializedName("MATNR")
	public String materialNumber;

	/**
	 * 物料描述
	 */
	@SerializedName("MAKTX")
	public String materialDescription;

	/**
	 * 工厂
	 */
	@SerializedName("WERKS")
	public String factoryCode;

	/**
	 * 批号
	 */
	@SerializedName("CHARG")
	public String batchNumber;

	/**
	 * 基本单位
	 */
	@SerializedName("MEINS")
	public String baseUnit;

	/**
	 * 基本单位中文值
	 */
	@SerializedName("MEINS_TXT")
	public String baseUnitTxt;

	/**
	 * 基本单位数量
	 */
	@SerializedName("OFMEA")
	public double basicQuantity;

	/**
	 * 辅助单位
	 */
	@SerializedName("ALTME")
	public String auxiliaryUnit;

	/**
	 * 辅助单位中文值
	 */
	@SerializedName("ALTME_TXT")
	public String auxiliaryUnitTxt;

	/**
	 * 辅助单位数量
	 */
	@SerializedName("VSOLA")
	public double auxiliaryQuantity;

	/**
	 * 转移要求号
	 */
	@SerializedName("TBNUM")
	public String tbnum;

	/**
	 * 转储需求项目
	 */
	@SerializedName("TBPOS")
	public String tbpos;

	/**
	 * 附加字段
	 */
	@SerializedName("ADDITIONAL")
	public Additional additional;

	/**
	 * 创建人
	 */
	@SerializedName("USNAM")
	public String creator;

	/**
	 * 凭证日期
	 */
	@SerializedName("BLDAT")
	public String voucherDate;

	/**
	 * 规格
	 */
	@SerializedName("ZZDRUGSPEC")
	public String specifications;

	/**
	 * 通用名称
	 */
	@SerializedName("ZZCOMMONNAME")
	public String commonName;

	/**
	 * 供应商名称
	 */
	@SerializedName("VENDORNAME")
	public String supplierName;

	/**
	 * 供应商批次
	 */
	@SerializedName("LICHA")
	@Deprecated
	public String supplierBatchNumber;

	/**
	 * 供应商批次
	 */
	@SerializedName("ZZLICHA")
	public String supplierBatchCode;

	/**
	 * 是否合箱
	 */
	@SerializedName("ZZPACKAGING")
	public String packaging;

	/**
	 * 主批次
	 */
	@SerializedName("ZZLICHA_MAIN")
	public String mainBatchNumber;

	/**
	 * 主批次数量
	 */
	@SerializedName("ZZMENGE_MAIN")
	public String mainBatchNumberQuantity;

	/**
	 * 辅批次
	 */
	@SerializedName("ZZLICHA_AUXILIARY")
	public String auxiliaryBatchNumber;

	/**
	 * 辅批次数量
	 */
	@SerializedName("ZZMENGE_AUXILIARY")
	public String auxiliaryBatchNumberQuantity;

	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getMaterialVoucherCode() {
		return materialVoucherCode;
	}

	public void setMaterialVoucherCode(String materialVoucherCode) {
		this.materialVoucherCode = materialVoucherCode;
	}

	public String getVoucherLineNumber() {
		return voucherLineNumber;
	}

	public void setVoucherLineNumber(String voucherLineNumber) {
		this.voucherLineNumber = voucherLineNumber;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getBaseUnitTxt() {
		return baseUnitTxt;
	}

	public void setBaseUnitTxt(String baseUnitTxt) {
		this.baseUnitTxt = baseUnitTxt;
	}

	public double getBasicQuantity() {
		return basicQuantity;
	}

	public void setBasicQuantity(double basicQuantity) {
		this.basicQuantity = basicQuantity;
	}

	public String getAuxiliaryUnit() {
		return auxiliaryUnit;
	}

	public void setAuxiliaryUnit(String auxiliaryUnit) {
		this.auxiliaryUnit = auxiliaryUnit;
	}

	public String getAuxiliaryUnitTxt() {
		return auxiliaryUnitTxt;
	}

	public void setAuxiliaryUnitTxt(String auxiliaryUnitTxt) {
		this.auxiliaryUnitTxt = auxiliaryUnitTxt;
	}

	public double getAuxiliaryQuantity() {
		return auxiliaryQuantity;
	}

	public void setAuxiliaryQuantity(double auxiliaryQuantity) {
		this.auxiliaryQuantity = auxiliaryQuantity;
	}

	public String getTbnum() {
		return tbnum;
	}

	public void setTbnum(String tbnum) {
		this.tbnum = tbnum;
	}

	public String getTbpos() {
		return tbpos;
	}

	public void setTbpos(String tbpos) {
		this.tbpos = tbpos;
	}

	public Additional getAdditional() {
		return additional;
	}

	public void setAdditional(Additional additional) {
		this.additional = additional;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierBatchNumber() {
		return supplierBatchNumber;
	}

	public void setSupplierBatchNumber(String supplierBatchNumber) {
		this.supplierBatchNumber = supplierBatchNumber;
	}

	public String getSupplierBatchCode() {
		return supplierBatchCode;
	}

	public void setSupplierBatchCode(String supplierBatchCode) {
		this.supplierBatchCode = supplierBatchCode;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getMainBatchNumber() {
		return mainBatchNumber;
	}

	public void setMainBatchNumber(String mainBatchNumber) {
		this.mainBatchNumber = mainBatchNumber;
	}

	public String getMainBatchNumberQuantity() {
		return mainBatchNumberQuantity;
	}

	public void setMainBatchNumberQuantity(String mainBatchNumberQuantity) {
		this.mainBatchNumberQuantity = mainBatchNumberQuantity;
	}

	public String getAuxiliaryBatchNumber() {
		return auxiliaryBatchNumber;
	}

	public void setAuxiliaryBatchNumber(String auxiliaryBatchNumber) {
		this.auxiliaryBatchNumber = auxiliaryBatchNumber;
	}

	public String getAuxiliaryBatchNumberQuantity() {
		return auxiliaryBatchNumberQuantity;
	}

	public void setAuxiliaryBatchNumberQuantity(String auxiliaryBatchNumberQuantity) {
		this.auxiliaryBatchNumberQuantity = auxiliaryBatchNumberQuantity;
	}

	public ShelvingQueryResponseItem(String warehouseNumber, String moveType, String materialVoucherCode, String voucherLineNumber, String materialNumber, String materialDescription, String factoryCode, String batchNumber, String baseUnit, String baseUnitTxt, double basicQuantity, String auxiliaryUnit, String auxiliaryUnitTxt, double auxiliaryQuantity, String tbnum, String tbpos, Additional additional, String creator, String voucherDate, String specifications, String commonName, String supplierName, String supplierBatchNumber, String supplierBatchCode, String packaging, String mainBatchNumber, String mainBatchNumberQuantity, String auxiliaryBatchNumber, String auxiliaryBatchNumberQuantity) {
		this.warehouseNumber = warehouseNumber;
		this.moveType = moveType;
		this.materialVoucherCode = materialVoucherCode;
		this.voucherLineNumber = voucherLineNumber;
		this.materialNumber = materialNumber;
		this.materialDescription = materialDescription;
		this.factoryCode = factoryCode;
		this.batchNumber = batchNumber;
		this.baseUnit = baseUnit;
		this.baseUnitTxt = baseUnitTxt;
		this.basicQuantity = basicQuantity;
		this.auxiliaryUnit = auxiliaryUnit;
		this.auxiliaryUnitTxt = auxiliaryUnitTxt;
		this.auxiliaryQuantity = auxiliaryQuantity;
		this.tbnum = tbnum;
		this.tbpos = tbpos;
		this.additional = additional;
		this.creator = creator;
		this.voucherDate = voucherDate;
		this.specifications = specifications;
		this.commonName = commonName;
		this.supplierName = supplierName;
		this.supplierBatchNumber = supplierBatchNumber;
		this.supplierBatchCode = supplierBatchCode;
		this.packaging = packaging;
		this.mainBatchNumber = mainBatchNumber;
		this.mainBatchNumberQuantity = mainBatchNumberQuantity;
		this.auxiliaryBatchNumber = auxiliaryBatchNumber;
		this.auxiliaryBatchNumberQuantity = auxiliaryBatchNumberQuantity;
	}

	public ShelvingQueryResponseItem() {
	}
}
