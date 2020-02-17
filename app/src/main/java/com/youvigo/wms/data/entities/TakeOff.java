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

public class TakeOff implements Parcelable {

    // 仓库号
    private String LGNUM;

    // 转储单创建日期
    private String BDATU;

    // 移动类型
    private String BWLVS;

    // 装运类型
    private String TRART;

    // 转储单编号
    @SerializedName("TANUM")
    private String orderNumber;

    // 转储单项目
    private String TAPOS;

    // 物料编号
    @SerializedName("MATNR")
    private String materialCode;

    // 物料描述
    @SerializedName("MAKTX")
    private String materialDescription;

    // 批号
    @SerializedName("CHARG")
    private String batchNumber;

    // 工厂
    private String WERKS;

    // 基本单位
    @SerializedName("MEINS")
    private String baseUnit;

    // 基本单位文本
    @SerializedName("MEINS_TXT")
    private String baseUnitDescription;

    // 基本单位数量(应拣)
    @SerializedName("VSOLM")
    private double quantity;

    // 基本单位数量(已拣)
    private double Num;

    // 辅助单位
    @SerializedName("ALTME")
    private String uxiliaryUnit;

    // 辅助单位文本
    @SerializedName("ALTME_TXT")
    private String uxiliaryUnitDescription;

    // 辅助单位数量
    private double VSOLA;

    // 上架仓储类型
    private String NLTYP;

    // 上架仓位
    private String NLPLA;

    // 确认标志
    private String PQUIT;

    // 创建人
    private String USNAM;

    // 凭证日期
    private String BLDAT;

    // 备用字段
    private Additional ADDITIONAL;

    // 下架仓位
    private String VLPLA;

    // 下架仓储类型
    private String VLTYP;

    // 规格
    @SerializedName("ZZDRUGSPEC")
    private String specification;

    // 通用名称
    @SerializedName("ZZCOMMONNAME")
    private String materialName;

    // 供应商批次废除
    @Deprecated
    private String LICHA;

    // 供应商批次
    private String ZZLICHA;

    // 员工号
    private String PERNR;

    // 员工姓名
    private String SNAME;

    // 组织单位
    private String ORGEH;

    // 组织单位短文本
    private String ORGTX;

    // 是否合箱
    private String ZZPACKAGING;

    // 主批次
    private String ZZLICHA_MAIN;

    // 主批次数量
    private String ZZMENGE_MAIN;

    // 辅批次
    private String ZZLICHA_AUXILIARY;

    // 辅批次数量
    private String ZZMENGE_AUXILIARY;

    // 备注
    private String Remarks;

    /**  用户设置项 */

    // 货位
    private String CargoLocation;

    // 已下架
    private double takeOffQuantity;

    // 未下架
    private double NotTakeOffQuantity;

    // 扫描批次号
    private String scanBatchCode;

    // 扫描货位
    private String Cargo;


    public TakeOff() {
    }

    protected TakeOff(Parcel in) {
        materialCode = in.readString();
        materialName = in.readString();
        materialDescription = in.readString();
        specification = in.readString();
        orderNumber = in.readString();
        batchNumber = in.readString();
        LGNUM = in.readString();
        BDATU = in.readString();
        BWLVS = in.readString();
        TRART = in.readString();
        TAPOS = in.readString();
        WERKS = in.readString();
        baseUnit = in.readString();
        baseUnitDescription = in.readString();
        quantity = in.readDouble();
        Num = in.readDouble();
        uxiliaryUnit = in.readString();
        uxiliaryUnitDescription = in.readString();
        VSOLA = in.readDouble();
        NLTYP = in.readString();
        NLPLA = in.readString();
        PQUIT = in.readString();
        USNAM = in.readString();
        BLDAT = in.readString();
        VLPLA = in.readString();
        VLTYP = in.readString();
        LICHA = in.readString();
        ZZLICHA = in.readString();
        PERNR = in.readString();
        SNAME = in.readString();
        ORGEH = in.readString();
        ORGTX = in.readString();
        ZZPACKAGING = in.readString();
        ZZLICHA_MAIN = in.readString();
        ZZMENGE_MAIN = in.readString();
        ZZLICHA_AUXILIARY = in.readString();
        ZZMENGE_AUXILIARY = in.readString();
        Remarks = in.readString();
        CargoLocation = in.readString();
        takeOffQuantity = in.readDouble();
        NotTakeOffQuantity = in.readDouble();
        scanBatchCode = in.readString();
        Cargo = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(materialCode);
        dest.writeString(materialName);
        dest.writeString(materialDescription);
        dest.writeString(specification);
        dest.writeString(orderNumber);
        dest.writeString(batchNumber);
        dest.writeString(LGNUM);
        dest.writeString(BDATU);
        dest.writeString(BWLVS);
        dest.writeString(TRART);
        dest.writeString(TAPOS);
        dest.writeString(WERKS);
        dest.writeString(baseUnit);
        dest.writeString(baseUnitDescription);
        dest.writeDouble(quantity);
        dest.writeDouble(Num);
        dest.writeString(uxiliaryUnit);
        dest.writeString(uxiliaryUnitDescription);
        dest.writeDouble(VSOLA);
        dest.writeString(NLTYP);
        dest.writeString(NLPLA);
        dest.writeString(PQUIT);
        dest.writeString(USNAM);
        dest.writeString(BLDAT);
        dest.writeString(VLPLA);
        dest.writeString(VLTYP);
        dest.writeString(LICHA);
        dest.writeString(ZZLICHA);
        dest.writeString(PERNR);
        dest.writeString(SNAME);
        dest.writeString(ORGEH);
        dest.writeString(ORGTX);
        dest.writeString(ZZPACKAGING);
        dest.writeString(ZZLICHA_MAIN);
        dest.writeString(ZZMENGE_MAIN);
        dest.writeString(ZZLICHA_AUXILIARY);
        dest.writeString(ZZMENGE_AUXILIARY);
        dest.writeString(Remarks);
        dest.writeString(CargoLocation);
        dest.writeDouble(takeOffQuantity);
        dest.writeDouble(NotTakeOffQuantity);
        dest.writeString(scanBatchCode);
        dest.writeString(Cargo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TakeOff> CREATOR = new Creator<TakeOff>() {
        @Override
        public TakeOff createFromParcel(Parcel in) {
            return new TakeOff(in);
        }

        @Override
        public TakeOff[] newArray(int size) {
            return new TakeOff[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "TakeOff-materialCode," + materialCode + ",materialName:" + materialName + ",specification:" + specification;
    }

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
    }

    public String getBDATU() {
        return BDATU;
    }

    public void setBDATU(String BDATU) {
        this.BDATU = BDATU;
    }

    public String getBWLVS() {
        return BWLVS;
    }

    public void setBWLVS(String BWLVS) {
        this.BWLVS = BWLVS;
    }

    public String getTRART() {
        return TRART;
    }

    public void setTRART(String TRART) {
        this.TRART = TRART;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTAPOS() {
        return TAPOS;
    }

    public void setTAPOS(String TAPOS) {
        this.TAPOS = TAPOS;
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

    public String getWERKS() {
        return WERKS;
    }

    public void setWERKS(String WERKS) {
        this.WERKS = WERKS;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getBaseUnitDescription() {
        return baseUnitDescription;
    }

    public void setBaseUnitDescription(String baseUnitDescription) {
        this.baseUnitDescription = baseUnitDescription;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getNum() {
        return Num;
    }

    public void setNum(double num) {
        Num = num;
    }

    public String getUxiliaryUnit() {
        return uxiliaryUnit;
    }

    public void setUxiliaryUnit(String uxiliaryUnit) {
        this.uxiliaryUnit = uxiliaryUnit;
    }

    public String getUxiliaryUnitDescription() {
        return uxiliaryUnitDescription;
    }

    public void setUxiliaryUnitDescription(String uxiliaryUnitDescription) {
        this.uxiliaryUnitDescription = uxiliaryUnitDescription;
    }

    public double getVSOLA() {
        return VSOLA;
    }

    public void setVSOLA(double VSOLA) {
        this.VSOLA = VSOLA;
    }

    public String getNLTYP() {
        return NLTYP;
    }

    public void setNLTYP(String NLTYP) {
        this.NLTYP = NLTYP;
    }

    public String getNLPLA() {
        return NLPLA;
    }

    public void setNLPLA(String NLPLA) {
        this.NLPLA = NLPLA;
    }

    public String getPQUIT() {
        return PQUIT;
    }

    public void setPQUIT(String PQUIT) {
        this.PQUIT = PQUIT;
    }

    public String getUSNAM() {
        return USNAM;
    }

    public void setUSNAM(String USNAM) {
        this.USNAM = USNAM;
    }

    public String getBLDAT() {
        return BLDAT;
    }

    public void setBLDAT(String BLDAT) {
        this.BLDAT = BLDAT;
    }

    public Additional getADDITIONAL() {
        return ADDITIONAL;
    }

    public void setADDITIONAL(Additional ADDITIONAL) {
        this.ADDITIONAL = ADDITIONAL;
    }

    public String getVLPLA() {
        return VLPLA;
    }

    public void setVLPLA(String VLPLA) {
        this.VLPLA = VLPLA;
    }

    public String getVLTYP() {
        return VLTYP;
    }

    public void setVLTYP(String VLTYP) {
        this.VLTYP = VLTYP;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getLICHA() {
        return LICHA;
    }

    public void setLICHA(String LICHA) {
        this.LICHA = LICHA;
    }

    public String getZZLICHA() {
        return ZZLICHA;
    }

    public void setZZLICHA(String ZZLICHA) {
        this.ZZLICHA = ZZLICHA;
    }

    public String getPERNR() {
        return PERNR;
    }

    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getORGEH() {
        return ORGEH;
    }

    public void setORGEH(String ORGEH) {
        this.ORGEH = ORGEH;
    }

    public String getORGTX() {
        return ORGTX;
    }

    public void setORGTX(String ORGTX) {
        this.ORGTX = ORGTX;
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

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getCargoLocation() {
        return CargoLocation;
    }

    public void setCargoLocation(String cargoLocation) {
        CargoLocation = cargoLocation;
    }

    public double getTakeOffQuantity() {
        return takeOffQuantity;
    }

    public void setTakeOffQuantity(double takeOffQuantity) {
        this.takeOffQuantity = takeOffQuantity;
    }

    public double getNotTakeOffQuantity() {
        return NotTakeOffQuantity;
    }

    public void setNotTakeOffQuantity(double notTakeOffQuantity) {
        NotTakeOffQuantity = notTakeOffQuantity;
    }

    public String getScanBatchCode() {
        return scanBatchCode;
    }

    public void setScanBatchCode(String scanBatchCode) {
        this.scanBatchCode = scanBatchCode;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }
}
