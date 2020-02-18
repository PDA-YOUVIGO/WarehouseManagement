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

package com.youvigo.wms.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 库存物料
 */
public class StockMaterial implements Parcelable {

	/// 仓库号
	@SerializedName("LGNUM")
	private String warehouseNumber;

	/// 物料编号
	@SerializedName("MATNR")
	private String materialCode;

	/// 物料描述
	@SerializedName("MAKTX")
	private String materialDescription;

	/// 工厂
	@SerializedName("WERKS")
	private String factoryCode;

	/// 库存地点
	@SerializedName("LGORT")
	private String inventoryLocation;

	/// 批号
	@SerializedName("CHARG")
	private String batchNumber;

	/// 基本单位
	@SerializedName("MEINS")
	private String baseUnit;

	/// 基本单位文本
	@SerializedName("MEINS_TXT")
	private String baseUnitTxt;

	/// 总库存量
	@SerializedName("GESME")
	private double totalInventory;

	/// 可用库存量
	@SerializedName("VERME")
	private double actualInventory;

	/// 仓位
	@SerializedName("LGPLA")
	private String cargoSpace;

	/// 包装规格
	private String ZZPACKINGSPEC;

	/// 内包装规格
	private String ZZINNERPACK;

	/// 内包装计量单位
	private String ZZINNERPACKUNIT;

	/// 外包装规格
	private String ZZOUTERPACK;

	/// 外包装计量单位
	private String ZZOUTERPACKUNIT;

	/// 供应商名称
	private String ZZSUPP_NAME;

	/// 委托厂商
	private String ZZENTRUSTMFGR;

	/// 委托厂商名称
	private String ZZENTRUSTMFGR_NAME;

	/// 生产厂商
	private String ZZMFGR;

	/// 生产厂商名称
	private String ZZMFGR_NAME;

	/// 国药准字号
	private String ZZAPPR_NUM;

	/// 注册证号
	private String ZZREG_NUM;

	/// 包材版本
	private String ZZPACK_VER;

	/// 供应商批次（长文本）
	private String ZZLICHA;

	/// 折纯系数
	private String ZZPURCOE;

	/// 折干系数
	private String ZZDRYCOE;

	/// 检验报告日期
	private String ZZCOATERM;

	/// COA链接地址
	private String ZZCOA_LINK;

	/// 检验报告编号
	private String ZZCOA_NUM;

	/// 放行日期
	private String ZZUDTERM;

	/// 质检状态
	private String ZZVCODE;

	/// 定点信息
	private String ZZINFORMATION;

	/// 质量原因
	private String ZZQUALITY_REASON;

	/// 质量部备注
	private String ZZQUALITY_REMARK;

	/// 物流部备注
	private String ZZLOGISTICS_REMARK;

	/// OA申请编号
	private String ZZOA_PR;

	/// OA申请行项目号
	private String ZZOAITEM_PR;

	/// 请购人
	private String ZZAFNAM;

	/// 请购部门
	private String ZZBEDNR;

	/// 入库数量
	private String ZZMENGE;

	/// 采购订单编号
	private String ZZEBELN;

	/// 采购订单项目
	private String ZZEBELP;

	/// 电采订单号
	private String ZZNCPO;

	/// 电采订单项目
	private String ZZNCPOITEM;

	/// 项目号
	private String ZZAUFNR;

	/// 项目阶段
	private String ZZKTEXT;

	/// 检验类型
	private String ZZINSPECTION_TYPE;

	/// 是否合箱
	private String ZZPACKAGING;

	/// 主批次
	private String ZZLICHA_MAIN;

	/// 主批此数量
	private String ZZMENGE_MAIN;

	/// 辅批次
	private String ZZLICHA_AUXILIARY;

	/// 辅批次数量
	private String ZZMENGE_AUXILIARY;

	/// 客户
	private String ZZKUNNR;

	/// 一致性评价通过
	private String ZZCONSISTENCY;

	/// 一致性评价通过日期
	private String ZZCONSISTENCYDATE;

	/// 货号
	private String ZZPROCODE;

	/// 库存积压状态标识
	private String ZZOVERSTOCK;

	/// 销售订单
	private String ZZVBELN;

	/// 销售订单项目
	private String ZZPOSNR;

	/// 销售合同
	private String ZZBSTKD;

	/// 规格
	@SerializedName("ZZDRUGSPEC")
	private String specification;

	/// 物料通用名称
	@SerializedName("ZZCOMMONNAME")
	private String materialCommonName;

	/// 供应商批次
	private String LICHA;

	/// 仓位类型
	private String LGTYP;

	/// 库存类别
	private String BESTQ;

	/// 安全库存
	private String EISBE;

	/// 最小安全库存
	private String EISLO;

	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
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

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getInventoryLocation() {
		return inventoryLocation;
	}

	public void setInventoryLocation(String inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
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

	public double getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(double totalInventory) {
		this.totalInventory = totalInventory;
	}

	public double getActualInventory() {
		return actualInventory;
	}

	public void setActualInventory(double actualInventory) {
		this.actualInventory = actualInventory;
	}

	public String getCargoSpace() {
		return cargoSpace;
	}

	public void setCargoSpace(String cargoSpace) {
		this.cargoSpace = cargoSpace;
	}

	public String getZZPACKINGSPEC() {
		return ZZPACKINGSPEC;
	}

	public void setZZPACKINGSPEC(String ZZPACKINGSPEC) {
		this.ZZPACKINGSPEC = ZZPACKINGSPEC;
	}

	public String getZZINNERPACK() {
		return ZZINNERPACK;
	}

	public void setZZINNERPACK(String ZZINNERPACK) {
		this.ZZINNERPACK = ZZINNERPACK;
	}

	public String getZZINNERPACKUNIT() {
		return ZZINNERPACKUNIT;
	}

	public void setZZINNERPACKUNIT(String ZZINNERPACKUNIT) {
		this.ZZINNERPACKUNIT = ZZINNERPACKUNIT;
	}

	public String getZZOUTERPACK() {
		return ZZOUTERPACK;
	}

	public void setZZOUTERPACK(String ZZOUTERPACK) {
		this.ZZOUTERPACK = ZZOUTERPACK;
	}

	public String getZZOUTERPACKUNIT() {
		return ZZOUTERPACKUNIT;
	}

	public void setZZOUTERPACKUNIT(String ZZOUTERPACKUNIT) {
		this.ZZOUTERPACKUNIT = ZZOUTERPACKUNIT;
	}

	public String getZZSUPP_NAME() {
		return ZZSUPP_NAME;
	}

	public void setZZSUPP_NAME(String ZZSUPP_NAME) {
		this.ZZSUPP_NAME = ZZSUPP_NAME;
	}

	public String getZZENTRUSTMFGR() {
		return ZZENTRUSTMFGR;
	}

	public void setZZENTRUSTMFGR(String ZZENTRUSTMFGR) {
		this.ZZENTRUSTMFGR = ZZENTRUSTMFGR;
	}

	public String getZZENTRUSTMFGR_NAME() {
		return ZZENTRUSTMFGR_NAME;
	}

	public void setZZENTRUSTMFGR_NAME(String ZZENTRUSTMFGR_NAME) {
		this.ZZENTRUSTMFGR_NAME = ZZENTRUSTMFGR_NAME;
	}

	public String getZZMFGR() {
		return ZZMFGR;
	}

	public void setZZMFGR(String ZZMFGR) {
		this.ZZMFGR = ZZMFGR;
	}

	public String getZZMFGR_NAME() {
		return ZZMFGR_NAME;
	}

	public void setZZMFGR_NAME(String ZZMFGR_NAME) {
		this.ZZMFGR_NAME = ZZMFGR_NAME;
	}

	public String getZZAPPR_NUM() {
		return ZZAPPR_NUM;
	}

	public void setZZAPPR_NUM(String ZZAPPR_NUM) {
		this.ZZAPPR_NUM = ZZAPPR_NUM;
	}

	public String getZZREG_NUM() {
		return ZZREG_NUM;
	}

	public void setZZREG_NUM(String ZZREG_NUM) {
		this.ZZREG_NUM = ZZREG_NUM;
	}

	public String getZZPACK_VER() {
		return ZZPACK_VER;
	}

	public void setZZPACK_VER(String ZZPACK_VER) {
		this.ZZPACK_VER = ZZPACK_VER;
	}

	public String getZZLICHA() {
		return ZZLICHA;
	}

	public void setZZLICHA(String ZZLICHA) {
		this.ZZLICHA = ZZLICHA;
	}

	public String getZZPURCOE() {
		return ZZPURCOE;
	}

	public void setZZPURCOE(String ZZPURCOE) {
		this.ZZPURCOE = ZZPURCOE;
	}

	public String getZZDRYCOE() {
		return ZZDRYCOE;
	}

	public void setZZDRYCOE(String ZZDRYCOE) {
		this.ZZDRYCOE = ZZDRYCOE;
	}

	public String getZZCOATERM() {
		return ZZCOATERM;
	}

	public void setZZCOATERM(String ZZCOATERM) {
		this.ZZCOATERM = ZZCOATERM;
	}

	public String getZZCOA_LINK() {
		return ZZCOA_LINK;
	}

	public void setZZCOA_LINK(String ZZCOA_LINK) {
		this.ZZCOA_LINK = ZZCOA_LINK;
	}

	public String getZZCOA_NUM() {
		return ZZCOA_NUM;
	}

	public void setZZCOA_NUM(String ZZCOA_NUM) {
		this.ZZCOA_NUM = ZZCOA_NUM;
	}

	public String getZZUDTERM() {
		return ZZUDTERM;
	}

	public void setZZUDTERM(String ZZUDTERM) {
		this.ZZUDTERM = ZZUDTERM;
	}

	public String getZZVCODE() {
		return ZZVCODE;
	}

	public void setZZVCODE(String ZZVCODE) {
		this.ZZVCODE = ZZVCODE;
	}

	public String getZZINFORMATION() {
		return ZZINFORMATION;
	}

	public void setZZINFORMATION(String ZZINFORMATION) {
		this.ZZINFORMATION = ZZINFORMATION;
	}

	public String getZZQUALITY_REASON() {
		return ZZQUALITY_REASON;
	}

	public void setZZQUALITY_REASON(String ZZQUALITY_REASON) {
		this.ZZQUALITY_REASON = ZZQUALITY_REASON;
	}

	public String getZZQUALITY_REMARK() {
		return ZZQUALITY_REMARK;
	}

	public void setZZQUALITY_REMARK(String ZZQUALITY_REMARK) {
		this.ZZQUALITY_REMARK = ZZQUALITY_REMARK;
	}

	public String getZZLOGISTICS_REMARK() {
		return ZZLOGISTICS_REMARK;
	}

	public void setZZLOGISTICS_REMARK(String ZZLOGISTICS_REMARK) {
		this.ZZLOGISTICS_REMARK = ZZLOGISTICS_REMARK;
	}

	public String getZZOA_PR() {
		return ZZOA_PR;
	}

	public void setZZOA_PR(String ZZOA_PR) {
		this.ZZOA_PR = ZZOA_PR;
	}

	public String getZZOAITEM_PR() {
		return ZZOAITEM_PR;
	}

	public void setZZOAITEM_PR(String ZZOAITEM_PR) {
		this.ZZOAITEM_PR = ZZOAITEM_PR;
	}

	public String getZZAFNAM() {
		return ZZAFNAM;
	}

	public void setZZAFNAM(String ZZAFNAM) {
		this.ZZAFNAM = ZZAFNAM;
	}

	public String getZZBEDNR() {
		return ZZBEDNR;
	}

	public void setZZBEDNR(String ZZBEDNR) {
		this.ZZBEDNR = ZZBEDNR;
	}

	public String getZZMENGE() {
		return ZZMENGE;
	}

	public void setZZMENGE(String ZZMENGE) {
		this.ZZMENGE = ZZMENGE;
	}

	public String getZZEBELN() {
		return ZZEBELN;
	}

	public void setZZEBELN(String ZZEBELN) {
		this.ZZEBELN = ZZEBELN;
	}

	public String getZZEBELP() {
		return ZZEBELP;
	}

	public void setZZEBELP(String ZZEBELP) {
		this.ZZEBELP = ZZEBELP;
	}

	public String getZZNCPO() {
		return ZZNCPO;
	}

	public void setZZNCPO(String ZZNCPO) {
		this.ZZNCPO = ZZNCPO;
	}

	public String getZZNCPOITEM() {
		return ZZNCPOITEM;
	}

	public void setZZNCPOITEM(String ZZNCPOITEM) {
		this.ZZNCPOITEM = ZZNCPOITEM;
	}

	public String getZZAUFNR() {
		return ZZAUFNR;
	}

	public void setZZAUFNR(String ZZAUFNR) {
		this.ZZAUFNR = ZZAUFNR;
	}

	public String getZZKTEXT() {
		return ZZKTEXT;
	}

	public void setZZKTEXT(String ZZKTEXT) {
		this.ZZKTEXT = ZZKTEXT;
	}

	public String getZZINSPECTION_TYPE() {
		return ZZINSPECTION_TYPE;
	}

	public void setZZINSPECTION_TYPE(String ZZINSPECTION_TYPE) {
		this.ZZINSPECTION_TYPE = ZZINSPECTION_TYPE;
	}

	public String getZZPACKAGING() {
		return ZZPACKAGING;
	}

	public void setZZPACKAGING(String ZZPACKAGING) {
		this.ZZPACKAGING = ZZPACKAGING;
	}

	public String getZZLICHA_MAIN() {
		return ZZLICHA_MAIN;
	}

	public void setZZLICHA_MAIN(String ZZLICHA_MAIN) {
		this.ZZLICHA_MAIN = ZZLICHA_MAIN;
	}

	public String getZZMENGE_MAIN() {
		return ZZMENGE_MAIN;
	}

	public void setZZMENGE_MAIN(String ZZMENGE_MAIN) {
		this.ZZMENGE_MAIN = ZZMENGE_MAIN;
	}

	public String getZZLICHA_AUXILIARY() {
		return ZZLICHA_AUXILIARY;
	}

	public void setZZLICHA_AUXILIARY(String ZZLICHA_AUXILIARY) {
		this.ZZLICHA_AUXILIARY = ZZLICHA_AUXILIARY;
	}

	public String getZZMENGE_AUXILIARY() {
		return ZZMENGE_AUXILIARY;
	}

	public void setZZMENGE_AUXILIARY(String ZZMENGE_AUXILIARY) {
		this.ZZMENGE_AUXILIARY = ZZMENGE_AUXILIARY;
	}

	public String getZZKUNNR() {
		return ZZKUNNR;
	}

	public void setZZKUNNR(String ZZKUNNR) {
		this.ZZKUNNR = ZZKUNNR;
	}

	public String getZZCONSISTENCY() {
		return ZZCONSISTENCY;
	}

	public void setZZCONSISTENCY(String ZZCONSISTENCY) {
		this.ZZCONSISTENCY = ZZCONSISTENCY;
	}

	public String getZZCONSISTENCYDATE() {
		return ZZCONSISTENCYDATE;
	}

	public void setZZCONSISTENCYDATE(String ZZCONSISTENCYDATE) {
		this.ZZCONSISTENCYDATE = ZZCONSISTENCYDATE;
	}

	public String getZZPROCODE() {
		return ZZPROCODE;
	}

	public void setZZPROCODE(String ZZPROCODE) {
		this.ZZPROCODE = ZZPROCODE;
	}

	public String getZZOVERSTOCK() {
		return ZZOVERSTOCK;
	}

	public void setZZOVERSTOCK(String ZZOVERSTOCK) {
		this.ZZOVERSTOCK = ZZOVERSTOCK;
	}

	public String getZZVBELN() {
		return ZZVBELN;
	}

	public void setZZVBELN(String ZZVBELN) {
		this.ZZVBELN = ZZVBELN;
	}

	public String getZZPOSNR() {
		return ZZPOSNR;
	}

	public void setZZPOSNR(String ZZPOSNR) {
		this.ZZPOSNR = ZZPOSNR;
	}

	public String getZZBSTKD() {
		return ZZBSTKD;
	}

	public void setZZBSTKD(String ZZBSTKD) {
		this.ZZBSTKD = ZZBSTKD;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getMaterialCommonName() {
		return materialCommonName;
	}

	public void setMaterialCommonName(String materialCommonName) {
		this.materialCommonName = materialCommonName;
	}

	public String getLICHA() {
		return LICHA;
	}

	public void setLICHA(String LICHA) {
		this.LICHA = LICHA;
	}

	public String getLGTYP() {
		return LGTYP;
	}

	public void setLGTYP(String LGTYP) {
		this.LGTYP = LGTYP;
	}

	public String getBESTQ() {
		return BESTQ;
	}

	public void setBESTQ(String BESTQ) {
		this.BESTQ = BESTQ;
	}

	public String getEISBE() {
		return EISBE;
	}

	public void setEISBE(String EISBE) {
		this.EISBE = EISBE;
	}

	public String getEISLO() {
		return EISLO;
	}

	public void setEISLO(String EISLO) {
		this.EISLO = EISLO;
	}

	public StockMaterial() {}

	@Override
	public String toString() {
		return "StockMaterial{" + "warehouseNumber='" + warehouseNumber + '\'' + ", materialCode='" + materialCode + '\'' + ", materialDescription='" + materialDescription + '\'' + ", factoryCode='" + factoryCode + '\'' + ", inventoryLocation='" + inventoryLocation + '\'' + ", batchNumber='" + batchNumber + '\'' + ", baseUnit='" + baseUnit + '\'' + ", baseUnitTxt='" + baseUnitTxt + '\'' + ", totalInventory=" + totalInventory + ", actualInventory=" + actualInventory + ", cargoSpace='" + cargoSpace + '\'' + ", ZZPACKINGSPEC='" + ZZPACKINGSPEC + '\'' + ", ZZINNERPACK='" + ZZINNERPACK + '\'' + ", ZZINNERPACKUNIT='" + ZZINNERPACKUNIT + '\'' + ", ZZOUTERPACK='" + ZZOUTERPACK + '\'' + ", ZZOUTERPACKUNIT='" + ZZOUTERPACKUNIT + '\'' + ", ZZSUPP_NAME='" + ZZSUPP_NAME + '\'' + ", ZZENTRUSTMFGR='" + ZZENTRUSTMFGR + '\'' + ", ZZENTRUSTMFGR_NAME='" + ZZENTRUSTMFGR_NAME + '\'' + ", ZZMFGR='" + ZZMFGR + '\'' + ", ZZMFGR_NAME='" + ZZMFGR_NAME + '\'' + ", ZZAPPR_NUM='" + ZZAPPR_NUM + '\'' + ", ZZREG_NUM='" + ZZREG_NUM + '\'' + ", ZZPACK_VER='" + ZZPACK_VER + '\'' + ", ZZLICHA='" + ZZLICHA + '\'' + ", ZZPURCOE='" + ZZPURCOE + '\'' + ", ZZDRYCOE='" + ZZDRYCOE + '\'' + ", ZZCOATERM='" + ZZCOATERM + '\'' + ", ZZCOA_LINK='" + ZZCOA_LINK + '\'' + ", ZZCOA_NUM='" + ZZCOA_NUM + '\'' + ", ZZUDTERM='" + ZZUDTERM + '\'' + ", ZZVCODE='" + ZZVCODE + '\'' + ", ZZINFORMATION='" + ZZINFORMATION + '\'' + ", ZZQUALITY_REASON='" + ZZQUALITY_REASON + '\'' + ", ZZQUALITY_REMARK='" + ZZQUALITY_REMARK + '\'' + ", ZZLOGISTICS_REMARK='" + ZZLOGISTICS_REMARK + '\'' + ", ZZOA_PR='" + ZZOA_PR + '\'' + ", ZZOAITEM_PR='" + ZZOAITEM_PR + '\'' + ", ZZAFNAM='" + ZZAFNAM + '\'' + ", ZZBEDNR='" + ZZBEDNR + '\'' + ", ZZMENGE='" + ZZMENGE + '\'' + ", ZZEBELN='" + ZZEBELN + '\'' + ", ZZEBELP='" + ZZEBELP + '\'' + ", ZZNCPO='" + ZZNCPO + '\'' + ", ZZNCPOITEM='" + ZZNCPOITEM + '\'' + ", ZZAUFNR='" + ZZAUFNR + '\'' + ", ZZKTEXT='" + ZZKTEXT + '\'' + ", ZZINSPECTION_TYPE='" + ZZINSPECTION_TYPE + '\'' + ", ZZPACKAGING='" + ZZPACKAGING + '\'' + ", ZZLICHA_MAIN='" + ZZLICHA_MAIN + '\'' + ", ZZMENGE_MAIN='" + ZZMENGE_MAIN + '\'' + ", ZZLICHA_AUXILIARY='" + ZZLICHA_AUXILIARY + '\'' + ", ZZMENGE_AUXILIARY='" + ZZMENGE_AUXILIARY + '\'' + ", ZZKUNNR='" + ZZKUNNR + '\'' + ", ZZCONSISTENCY='" + ZZCONSISTENCY + '\'' + ", ZZCONSISTENCYDATE='" + ZZCONSISTENCYDATE + '\'' + ", ZZPROCODE='" + ZZPROCODE + '\'' + ", ZZOVERSTOCK='" + ZZOVERSTOCK + '\'' + ", ZZVBELN='" + ZZVBELN + '\'' + ", ZZPOSNR='" + ZZPOSNR + '\'' + ", ZZBSTKD='" + ZZBSTKD + '\'' + ", specification='" + specification + '\'' + ", materialCommonName='" + materialCommonName + '\'' + ", LICHA='" + LICHA + '\'' + ", LGTYP='" + LGTYP + '\'' + ", BESTQ='" + BESTQ + '\'' + ", EISBE='" + EISBE + '\'' + ", EISLO='" + EISLO + '\'' + '}';
	}

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.warehouseNumber);
		dest.writeString(this.materialCode);
		dest.writeString(this.materialDescription);
		dest.writeString(this.factoryCode);
		dest.writeString(this.inventoryLocation);
		dest.writeString(this.batchNumber);
		dest.writeString(this.baseUnit);
		dest.writeString(this.baseUnitTxt);
		dest.writeDouble(this.totalInventory);
		dest.writeDouble(this.actualInventory);
		dest.writeString(this.cargoSpace);
		dest.writeString(this.ZZPACKINGSPEC);
		dest.writeString(this.ZZINNERPACK);
		dest.writeString(this.ZZINNERPACKUNIT);
		dest.writeString(this.ZZOUTERPACK);
		dest.writeString(this.ZZOUTERPACKUNIT);
		dest.writeString(this.ZZSUPP_NAME);
		dest.writeString(this.ZZENTRUSTMFGR);
		dest.writeString(this.ZZENTRUSTMFGR_NAME);
		dest.writeString(this.ZZMFGR);
		dest.writeString(this.ZZMFGR_NAME);
		dest.writeString(this.ZZAPPR_NUM);
		dest.writeString(this.ZZREG_NUM);
		dest.writeString(this.ZZPACK_VER);
		dest.writeString(this.ZZLICHA);
		dest.writeString(this.ZZPURCOE);
		dest.writeString(this.ZZDRYCOE);
		dest.writeString(this.ZZCOATERM);
		dest.writeString(this.ZZCOA_LINK);
		dest.writeString(this.ZZCOA_NUM);
		dest.writeString(this.ZZUDTERM);
		dest.writeString(this.ZZVCODE);
		dest.writeString(this.ZZINFORMATION);
		dest.writeString(this.ZZQUALITY_REASON);
		dest.writeString(this.ZZQUALITY_REMARK);
		dest.writeString(this.ZZLOGISTICS_REMARK);
		dest.writeString(this.ZZOA_PR);
		dest.writeString(this.ZZOAITEM_PR);
		dest.writeString(this.ZZAFNAM);
		dest.writeString(this.ZZBEDNR);
		dest.writeString(this.ZZMENGE);
		dest.writeString(this.ZZEBELN);
		dest.writeString(this.ZZEBELP);
		dest.writeString(this.ZZNCPO);
		dest.writeString(this.ZZNCPOITEM);
		dest.writeString(this.ZZAUFNR);
		dest.writeString(this.ZZKTEXT);
		dest.writeString(this.ZZINSPECTION_TYPE);
		dest.writeString(this.ZZPACKAGING);
		dest.writeString(this.ZZLICHA_MAIN);
		dest.writeString(this.ZZMENGE_MAIN);
		dest.writeString(this.ZZLICHA_AUXILIARY);
		dest.writeString(this.ZZMENGE_AUXILIARY);
		dest.writeString(this.ZZKUNNR);
		dest.writeString(this.ZZCONSISTENCY);
		dest.writeString(this.ZZCONSISTENCYDATE);
		dest.writeString(this.ZZPROCODE);
		dest.writeString(this.ZZOVERSTOCK);
		dest.writeString(this.ZZVBELN);
		dest.writeString(this.ZZPOSNR);
		dest.writeString(this.ZZBSTKD);
		dest.writeString(this.specification);
		dest.writeString(this.materialCommonName);
		dest.writeString(this.LICHA);
		dest.writeString(this.LGTYP);
		dest.writeString(this.BESTQ);
		dest.writeString(this.EISBE);
		dest.writeString(this.EISLO);
	}

	protected StockMaterial(Parcel in) {
		this.warehouseNumber = in.readString();
		this.materialCode = in.readString();
		this.materialDescription = in.readString();
		this.factoryCode = in.readString();
		this.inventoryLocation = in.readString();
		this.batchNumber = in.readString();
		this.baseUnit = in.readString();
		this.baseUnitTxt = in.readString();
		this.totalInventory = in.readDouble();
		this.actualInventory = in.readDouble();
		this.cargoSpace = in.readString();
		this.ZZPACKINGSPEC = in.readString();
		this.ZZINNERPACK = in.readString();
		this.ZZINNERPACKUNIT = in.readString();
		this.ZZOUTERPACK = in.readString();
		this.ZZOUTERPACKUNIT = in.readString();
		this.ZZSUPP_NAME = in.readString();
		this.ZZENTRUSTMFGR = in.readString();
		this.ZZENTRUSTMFGR_NAME = in.readString();
		this.ZZMFGR = in.readString();
		this.ZZMFGR_NAME = in.readString();
		this.ZZAPPR_NUM = in.readString();
		this.ZZREG_NUM = in.readString();
		this.ZZPACK_VER = in.readString();
		this.ZZLICHA = in.readString();
		this.ZZPURCOE = in.readString();
		this.ZZDRYCOE = in.readString();
		this.ZZCOATERM = in.readString();
		this.ZZCOA_LINK = in.readString();
		this.ZZCOA_NUM = in.readString();
		this.ZZUDTERM = in.readString();
		this.ZZVCODE = in.readString();
		this.ZZINFORMATION = in.readString();
		this.ZZQUALITY_REASON = in.readString();
		this.ZZQUALITY_REMARK = in.readString();
		this.ZZLOGISTICS_REMARK = in.readString();
		this.ZZOA_PR = in.readString();
		this.ZZOAITEM_PR = in.readString();
		this.ZZAFNAM = in.readString();
		this.ZZBEDNR = in.readString();
		this.ZZMENGE = in.readString();
		this.ZZEBELN = in.readString();
		this.ZZEBELP = in.readString();
		this.ZZNCPO = in.readString();
		this.ZZNCPOITEM = in.readString();
		this.ZZAUFNR = in.readString();
		this.ZZKTEXT = in.readString();
		this.ZZINSPECTION_TYPE = in.readString();
		this.ZZPACKAGING = in.readString();
		this.ZZLICHA_MAIN = in.readString();
		this.ZZMENGE_MAIN = in.readString();
		this.ZZLICHA_AUXILIARY = in.readString();
		this.ZZMENGE_AUXILIARY = in.readString();
		this.ZZKUNNR = in.readString();
		this.ZZCONSISTENCY = in.readString();
		this.ZZCONSISTENCYDATE = in.readString();
		this.ZZPROCODE = in.readString();
		this.ZZOVERSTOCK = in.readString();
		this.ZZVBELN = in.readString();
		this.ZZPOSNR = in.readString();
		this.ZZBSTKD = in.readString();
		this.specification = in.readString();
		this.materialCommonName = in.readString();
		this.LICHA = in.readString();
		this.LGTYP = in.readString();
		this.BESTQ = in.readString();
		this.EISBE = in.readString();
		this.EISLO = in.readString();
	}

	public static final Creator<StockMaterial> CREATOR = new Creator<StockMaterial>() {
		@Override
		public StockMaterial createFromParcel(Parcel source) {return new StockMaterial(source);}

		@Override
		public StockMaterial[] newArray(int size) {return new StockMaterial[size];}
	};
}
