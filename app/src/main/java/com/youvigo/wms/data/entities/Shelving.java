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

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.Additional;

public class Shelving implements Parcelable {

    /**
     * 仓库号
     */
    @SerializedName("LGNUM")
    private String warehouseNumber;

    /**
     * 移动类型
     */
    @SerializedName("BWLVS")
    private String moveType;

    /**
     * 单据号（物料凭证号）
     */
    @SerializedName("MBLNR")
    private String materialVoucherCode;

    /**
     * 物料凭证行项目编号（行号）
     */
    @SerializedName("ZEILE")
    private String voucherLineNumber;

    /**
     * 物料编号
     */
    @SerializedName("MATNR")
    private String materialNumber;

    /**
     * 物料描述
     */
    @SerializedName("MAKTX")
    private String materialDescription;

    /**
     * 工厂
     */
    @SerializedName("WERKS")
    private String factoryCode;

    /**
     * 批号
     */
    @SerializedName("CHARG")
    private String batchNumber;

    /**
     * 基本单位
     */
    @SerializedName("MEINS")
    private String baseUnit;

    /**
     * 基本单位中文值
     */
    @SerializedName("MEINS_TXT")
    private String baseUnitTxt;

    /**
     * 基本单位数量
     */
    @SerializedName("OFMEA")
    private double basicQuantity;

    /**
     * 辅助单位
     */
    @SerializedName("ALTME")
    private String auxiliaryUnit;

    /**
     * 辅助单位中文值
     */
    @SerializedName("ALTME_TXT")
    private String auxiliaryUnitTxt;

    /**
     * 辅助单位数量
     */
    @SerializedName("VSOLA")
    private double auxiliaryQuantity;

    /**
     * 转移要求号
     */
    @SerializedName("TBNUM")
    private String tbnum;

    /**
     * 转储需求项目
     */
    @SerializedName("TBPOS")
    private String tbpos;

    /**
     * 创建人
     */
    @SerializedName("USNAM")
    private String creator;

    /**
     * 凭证日期
     */
    @SerializedName("BLDAT")
    private String voucherDate;

    /**
     * 规格
     */
    @SerializedName("ZZDRUGSPEC")
    private String specifications;

    /**
     * 通用名称
     */
    @SerializedName("ZZCOMMONNAME")
    private String commonName;

    /**
     * 供应商名称
     */
    @SerializedName("VENDORNAME")
    private String supplierName;

    /**
     * 供应商批次
     */
    @SerializedName("LICHA")
    @Deprecated
    private String supplierBatchNumber;

    /**
     * 供应商批次
     */
    @SerializedName("ZZLICHA")
    private String supplierBatchCode;

    /**
     * 是否合箱
     */
    @SerializedName("ZZPACKAGING")
    private String packaging;

    /**
     * 主批次
     */
    @SerializedName("ZZLICHA_MAIN")
    private String mainBatchNumber;

    /**
     * 主批次数量
     */
    @SerializedName("ZZMENGE_MAIN")
    private String mainBatchNumberQuantity;

    /**
     * 辅批次
     */
    @SerializedName("ZZLICHA_AUXILIARY")
    private String auxiliaryBatchNumber;

    /**
     * 辅批次数量
     */
    @SerializedName("ZZMENGE_AUXILIARY")
    private String auxiliaryBatchNumberQuantity;

    /**
     * 未上架数量
     */
    private double notOnShelvesQuantity;

    /**
     * 已上架数量
     */
    private double OnshelvesQuantity;

    /**
     * 附加字段
     */
    @SerializedName("ADDITIONAL")
    private Additional additional;

    public double getOnshelvesQuantity() {
        return OnshelvesQuantity;
    }

    public void setOnshelvesQuantity(double onshelvesQuantity) {
        OnshelvesQuantity = onshelvesQuantity;
    }

    public Shelving() {
    }

    protected Shelving(Parcel in) {
        warehouseNumber = in.readString();
        moveType = in.readString();
        materialVoucherCode = in.readString();
        voucherLineNumber = in.readString();
        materialNumber = in.readString();
        materialDescription = in.readString();
        factoryCode = in.readString();
        batchNumber = in.readString();
        baseUnit = in.readString();
        baseUnitTxt = in.readString();
        basicQuantity = in.readDouble();
        auxiliaryUnit = in.readString();
        auxiliaryUnitTxt = in.readString();
        auxiliaryQuantity = in.readDouble();
        tbnum = in.readString();
        tbpos = in.readString();
        creator = in.readString();
        voucherDate = in.readString();
        specifications = in.readString();
        commonName = in.readString();
        supplierName = in.readString();
        supplierBatchNumber = in.readString();
        supplierBatchCode = in.readString();
        packaging = in.readString();
        mainBatchNumber = in.readString();
        mainBatchNumberQuantity = in.readString();
        auxiliaryBatchNumber = in.readString();
        auxiliaryBatchNumberQuantity = in.readString();
        OnshelvesQuantity = in.readDouble();
        notOnShelvesQuantity = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(warehouseNumber);
        dest.writeString(moveType);
        dest.writeString(materialVoucherCode);
        dest.writeString(voucherLineNumber);
        dest.writeString(materialNumber);
        dest.writeString(materialDescription);
        dest.writeString(factoryCode);
        dest.writeString(batchNumber);
        dest.writeString(baseUnit);
        dest.writeString(baseUnitTxt);
        dest.writeDouble(basicQuantity);
        dest.writeString(auxiliaryUnit);
        dest.writeString(auxiliaryUnitTxt);
        dest.writeDouble(auxiliaryQuantity);
        dest.writeString(tbnum);
        dest.writeString(tbpos);
        dest.writeString(creator);
        dest.writeString(voucherDate);
        dest.writeString(specifications);
        dest.writeString(commonName);
        dest.writeString(supplierName);
        dest.writeString(supplierBatchNumber);
        dest.writeString(supplierBatchCode);
        dest.writeString(packaging);
        dest.writeString(mainBatchNumber);
        dest.writeString(mainBatchNumberQuantity);
        dest.writeString(auxiliaryBatchNumber);
        dest.writeString(auxiliaryBatchNumberQuantity);
        dest.writeDouble(OnshelvesQuantity);
        dest.writeDouble(notOnShelvesQuantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shelving> CREATOR = new Creator<Shelving>() {
        @Override
        public Shelving createFromParcel(Parcel in) {
            return new Shelving(in);
        }

        @Override
        public Shelving[] newArray(int size) {
            return new Shelving[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Shelving{" + "warehouseNumber='" + warehouseNumber + '\'' + ", moveType='" + moveType + '\'' + ", materialVoucherCode='" + materialVoucherCode + '\'' + ", voucherLineNumber='" + voucherLineNumber + '\'' + ", materialNumber='" + materialNumber + '\'' + ", materialDescription='" + materialDescription + '\'' + ", factoryCode='" + factoryCode + '\'' + ", batchNumber='" + batchNumber + '\'' + ", baseUnit='" + baseUnit + '\'' + ", baseUnitTxt='" + baseUnitTxt + '\'' + ", basicQuantity=" + basicQuantity + ", auxiliaryUnit='" + auxiliaryUnit + '\'' + ", auxiliaryUnitTxt='" + auxiliaryUnitTxt + '\'' + ", auxiliaryQuantity=" + auxiliaryQuantity + ", tbnum='" + tbnum + '\'' + ", tbpos='" + tbpos + '\'' + ", additional=" + additional + ", creator='" + creator + '\'' + ", voucherDate='" + voucherDate + '\'' + ", specifications='" + specifications + '\'' + ", commonName='" + commonName + '\'' + ", supplierName='" + supplierName + '\'' + ", supplierBatchNumber='" + supplierBatchNumber + '\'' + ", supplierBatchCode='" + supplierBatchCode + '\'' + ", packaging='" + packaging + '\'' + ", mainBatchNumber='" + mainBatchNumber + '\'' + ", mainBatchNumberQuantity='" + mainBatchNumberQuantity + '\'' + ", auxiliaryBatchNumber='" + auxiliaryBatchNumber + '\'' + ", auxiliaryBatchNumberQuantity='" + auxiliaryBatchNumberQuantity + '\'' + '}';
    }

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

    public double getNotOnShelvesQuantity() {
        return notOnShelvesQuantity;
    }

    public void setNotOnShelvesQuantity(double notOnShelvesQuantity) {
        this.notOnShelvesQuantity = notOnShelvesQuantity;
    }
}
