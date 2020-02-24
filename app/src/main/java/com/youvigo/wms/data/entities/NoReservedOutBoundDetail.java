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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoReservedOutBoundDetail implements Parcelable {

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

    @Expose(serialize = false, deserialize = false)
    private double stockQuantity;

    @Expose(serialize = false, deserialize = false)
    private String stockUnit;

    @Expose(serialize = false, deserialize = false)
    private String stockCargoSpace;

    @Expose(serialize = false, deserialize = false)
    private String supplieBatch;

    // 单位
    @SerializedName("MEINS")
    private String baseUnit;

    // 收货库存地点
    private String UMLGO;

    // 备注
    private String MEMO;

    // 成本中心
    @SerializedName("KOSTL")
    private String costCenterDescription;

    @Expose(serialize = false, deserialize = false)
    private String costCenter;

    public NoReservedOutBoundDetail() {}

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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockCargoSpace() {
        return stockCargoSpace;
    }

    public void setStockCargoSpace(String stockCargoSpace) {
        this.stockCargoSpace = stockCargoSpace;
    }

    public String getCostCenterDescription() {
        return costCenterDescription;
    }

    public void setCostCenterDescription(String costCenterDescription) {
        this.costCenterDescription = costCenterDescription;
    }

    public String getSupplieBatch() {
        return supplieBatch;
    }

    public void setSupplieBatch(String supplieBatch) {
        this.supplieBatch = supplieBatch;
    }

    public String getStockUnit() {
        return stockUnit;
    }

    public void setStockUnit(String stockUnit) {
        this.stockUnit = stockUnit;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.LGNUM);
        dest.writeInt(this.lineNumber);
        dest.writeString(this.WERKS);
        dest.writeString(this.LGORT);
        dest.writeString(this.cargoSpace);
        dest.writeString(this.materialCode);
        dest.writeString(this.materialDescription);
        dest.writeString(this.batchNumber);
        dest.writeString(this.specification);
        dest.writeDouble(this.quantity);
        dest.writeDouble(this.stockQuantity);
        dest.writeString(this.stockUnit);
        dest.writeString(this.stockCargoSpace);
        dest.writeString(this.supplieBatch);
        dest.writeString(this.baseUnit);
        dest.writeString(this.UMLGO);
        dest.writeString(this.MEMO);
        dest.writeString(this.costCenterDescription);
        dest.writeString(this.costCenter);
    }

    protected NoReservedOutBoundDetail(Parcel in) {
        this.LGNUM = in.readString();
        this.lineNumber = in.readInt();
        this.WERKS = in.readString();
        this.LGORT = in.readString();
        this.cargoSpace = in.readString();
        this.materialCode = in.readString();
        this.materialDescription = in.readString();
        this.batchNumber = in.readString();
        this.specification = in.readString();
        this.quantity = in.readDouble();
        this.stockQuantity = in.readDouble();
        this.stockUnit = in.readString();
        this.stockCargoSpace = in.readString();
        this.supplieBatch = in.readString();
        this.baseUnit = in.readString();
        this.UMLGO = in.readString();
        this.MEMO = in.readString();
        this.costCenterDescription = in.readString();
        this.costCenter = in.readString();
    }

    public static final Creator<NoReservedOutBoundDetail> CREATOR = new Creator<NoReservedOutBoundDetail>() {
        @Override
        public NoReservedOutBoundDetail createFromParcel(Parcel source) {return new NoReservedOutBoundDetail(source);}

        @Override
        public NoReservedOutBoundDetail[] newArray(int size) {return new NoReservedOutBoundDetail[size];}
    };
}
