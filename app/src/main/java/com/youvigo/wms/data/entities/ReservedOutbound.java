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

public class ReservedOutbound implements Parcelable {

    /// 预留单号
    private String RSNUM;

    /// 预留行项目
    private String RSPOS;

    /// 成本中心
    private String KOSTL;

    /// 成本中心描述
    private String LTEXT;

    /// 移动类型
    private String BWART;

    /// 物料编号
    @SerializedName("MATNR")
    private String materialCode;

    /// 物料描述
    @SerializedName("MAKTX")
    private String materialDescription;

    /// 工厂
    private String WERKS;

    /// 批次
    @SerializedName("CHARG")
    private String batchNumber;

    /// 需求量
    private String BDMNG;

    /// 单位
    @SerializedName("MEINS")
    private String baseUnit;

    /// 单位文本
    @SerializedName("MEINS_TXT")
    private String baseUnitTxt;

    /// 生产订单（内部订单）
    private String AUFNR;

    /// 内部订单描述
    private String KTEXT;

    /// 组件需求日期
    private String BDTER;

    /// 创建人
    private String USNAM;

    /// 规格
    @SerializedName("ZZDRUGSPEC")
    private String specification;

    /// 通用名称
    @SerializedName("ZZCOMMONNAME")
    private String materialName;

    /// 供应商批次
    @Deprecated
    private String LICHA;

    /// 供应商批次
    private String ZZLICHA;

    /// 领料人姓名
    private String NACHN;

    /// 领料人编码
    private String PERNR;

    /// 领料人部门
    private String ORGEH;

    /// 部门描述
    private String ORGTX;

    /// 收获库存地
    private String UMLGO;

    /// 收货库存地名称
    private String LGOBE;

    /// 可用库存量
    private double VERME;

    /// 总库存量
    private double GESME;

    /// 安全库存
    private String EISBE;

    /// 最小安全库存
    private String EISLO;

    /// 已拣
    private double Num;

    /// 货位
    private String Cargo;

    /// 验证货位
    private String VerificationCargo;

    /// 备注
    private String MEMO;


    public ReservedOutbound() {
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.RSNUM);
        dest.writeString(this.RSPOS);
        dest.writeString(this.KOSTL);
        dest.writeString(this.LTEXT);
        dest.writeString(this.BWART);
        dest.writeString(this.materialCode);
        dest.writeString(this.materialDescription);
        dest.writeString(this.WERKS);
        dest.writeString(this.batchNumber);
        dest.writeString(this.BDMNG);
        dest.writeString(this.baseUnit);
        dest.writeString(this.baseUnitTxt);
        dest.writeString(this.AUFNR);
        dest.writeString(this.KTEXT);
        dest.writeString(this.BDTER);
        dest.writeString(this.USNAM);
        dest.writeString(this.specification);
        dest.writeString(this.materialName);
        dest.writeString(this.LICHA);
        dest.writeString(this.ZZLICHA);
        dest.writeString(this.NACHN);
        dest.writeString(this.PERNR);
        dest.writeString(this.ORGEH);
        dest.writeString(this.ORGTX);
        dest.writeString(this.UMLGO);
        dest.writeString(this.LGOBE);
        dest.writeDouble(this.VERME);
        dest.writeDouble(this.GESME);
        dest.writeString(this.EISBE);
        dest.writeString(this.EISLO);
        dest.writeDouble(this.Num);
        dest.writeString(this.Cargo);
        dest.writeString(this.VerificationCargo);
        dest.writeString(this.MEMO);
    }

    protected ReservedOutbound(Parcel in) {
        this.RSNUM = in.readString();
        this.RSPOS = in.readString();
        this.KOSTL = in.readString();
        this.LTEXT = in.readString();
        this.BWART = in.readString();
        this.materialCode = in.readString();
        this.materialDescription = in.readString();
        this.WERKS = in.readString();
        this.batchNumber = in.readString();
        this.BDMNG = in.readString();
        this.baseUnit = in.readString();
        this.baseUnitTxt = in.readString();
        this.AUFNR = in.readString();
        this.KTEXT = in.readString();
        this.BDTER = in.readString();
        this.USNAM = in.readString();
        this.specification = in.readString();
        this.materialName = in.readString();
        this.LICHA = in.readString();
        this.ZZLICHA = in.readString();
        this.NACHN = in.readString();
        this.PERNR = in.readString();
        this.ORGEH = in.readString();
        this.ORGTX = in.readString();
        this.UMLGO = in.readString();
        this.LGOBE = in.readString();
        this.VERME = in.readDouble();
        this.GESME = in.readDouble();
        this.EISBE = in.readString();
        this.EISLO = in.readString();
        this.Num = in.readDouble();
        this.Cargo = in.readString();
        this.VerificationCargo = in.readString();
        this.MEMO = in.readString();
    }

    public static final Creator<ReservedOutbound> CREATOR = new Creator<ReservedOutbound>() {
        @Override
        public ReservedOutbound createFromParcel(Parcel source) {return new ReservedOutbound(source);}

        @Override
        public ReservedOutbound[] newArray(int size) {return new ReservedOutbound[size];}
    };

    public String getRSNUM() {
        return RSNUM;
    }

    public String getRSPOS() {
        return RSPOS;
    }

    public String getKOSTL() {
        return KOSTL;
    }

    public String getLTEXT() {
        return LTEXT;
    }

    public String getBWART() {
        return BWART;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public String getWERKS() {
        return WERKS;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getBDMNG() {
        return BDMNG;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public String getBaseUnitTxt() {
        return baseUnitTxt;
    }

    public String getAUFNR() {
        return AUFNR;
    }

    public String getKTEXT() {
        return KTEXT;
    }

    public String getBDTER() {
        return BDTER;
    }

    public String getUSNAM() {
        return USNAM;
    }

    public String getSpecification() {
        return specification;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getLICHA() {
        return LICHA;
    }

    public String getZZLICHA() {
        return ZZLICHA;
    }

    public String getNACHN() {
        return NACHN;
    }

    public String getPERNR() {
        return PERNR;
    }

    public String getORGEH() {
        return ORGEH;
    }

    public String getORGTX() {
        return ORGTX;
    }

    public String getUMLGO() {
        return UMLGO;
    }

    public String getLGOBE() {
        return LGOBE;
    }

    public double getVERME() {
        return VERME;
    }

    public double getGESME() {
        return GESME;
    }

    public String getEISBE() {
        return EISBE;
    }

    public String getEISLO() {
        return EISLO;
    }

    public double getNum() {
        return Num;
    }

    public String getCargo() {
        return Cargo;
    }

    public String getVerificationCargo() {
        return VerificationCargo;
    }

    public String getMEMO() {
        return MEMO;
    }
}
