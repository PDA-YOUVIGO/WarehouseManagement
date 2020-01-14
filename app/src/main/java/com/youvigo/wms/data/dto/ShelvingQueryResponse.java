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
public class ShelvingQueryResponse {

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

}
