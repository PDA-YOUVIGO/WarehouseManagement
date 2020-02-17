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

import com.youvigo.wms.data.dto.base.Additional;

public class PositionMovementModelView implements Parcelable {

    /// 仓库号
    public  String LGNUM  ;

    /// 行项目号
    public  String TAPOS  ;

    /// 物料编号
    public  String MATNR  ;

    /// 物料描述
    public  String MAKTX  ;

    /// 工厂
    public  String WERKS  ;

    /// 库存地点
    public  String LGORT  ;

    /// 批号
    public  String CHARG  ;

    /// 基本单位
    public  String MEINS  ;

    /// 基本单位文本
    public  String MEINS_TXT  ;

    /// 基本单位数量
    public  String VSOLM  ;

    /// 辅助单位
    public  String ALTME  ;

    /// 辅助单位文本
    public  String ALTME_TXT  ;

    /// 辅助单位数量
    public  String VSOLA  ;

    /// 下架仓储类型
    public  String VLTYP  ;

    /// 下架仓位
    public  String VLPLA  ;

    /// 库存类别
    public  String BESTQ  ;

    /// 上架仓储类型
    public  String NLTYP  ;

    /// 上架仓位
    public  String NLPLA  ;

    /// 附加字段
    public Additional ADDITIONAL  ;

    /// 可用库存量
    public double VERME  ;

    /// 规格
    public  String ZZDRUGSPEC  ;

    /// 行状态
//    public RowStatus Status  ;

    /// 是否合箱
    public  String ZZPACKAGING  ;

    /// 主批次
    public  String ZZLICHA_MAIN  ;

    /// 主批次数量
    public  String ZZMENGE_MAIN  ;

    /// 辅批次
    public  String ZZLICHA_AUXILIARY  ;

    /// 辅批次数量
    public  String ZZMENGE_AUXILIARY  ;

    /// 物料通用名称
    public  String ZZCOMMONNAME  ;

    /// 供应商批次
    public  String ZZLICHA  ;

    public PositionMovementModelView() {
    }

    public PositionMovementModelView(String LGNUM, String TAPOS, String MATNR, String MAKTX, String WERKS, String LGORT, String CHARG, String MEINS, String MEINS_TXT, String VSOLM, String ALTME, String ALTME_TXT, String VSOLA, String VLTYP, String VLPLA, String BESTQ, String NLTYP, String NLPLA, Additional ADDITIONAL, double VERME, String ZZDRUGSPEC, String ZZPACKAGING, String ZZLICHA_MAIN, String ZZMENGE_MAIN, String ZZLICHA_AUXILIARY, String ZZMENGE_AUXILIARY, String ZZCOMMONNAME, String ZZLICHA) {
        this.LGNUM = LGNUM;
        this.TAPOS = TAPOS;
        this.MATNR = MATNR;
        this.MAKTX = MAKTX;
        this.WERKS = WERKS;
        this.LGORT = LGORT;
        this.CHARG = CHARG;
        this.MEINS = MEINS;
        this.MEINS_TXT = MEINS_TXT;
        this.VSOLM = VSOLM;
        this.ALTME = ALTME;
        this.ALTME_TXT = ALTME_TXT;
        this.VSOLA = VSOLA;
        this.VLTYP = VLTYP;
        this.VLPLA = VLPLA;
        this.BESTQ = BESTQ;
        this.NLTYP = NLTYP;
        this.NLPLA = NLPLA;
        this.ADDITIONAL = ADDITIONAL;
        this.VERME = VERME;
        this.ZZDRUGSPEC = ZZDRUGSPEC;
        this.ZZPACKAGING = ZZPACKAGING;
        this.ZZLICHA_MAIN = ZZLICHA_MAIN;
        this.ZZMENGE_MAIN = ZZMENGE_MAIN;
        this.ZZLICHA_AUXILIARY = ZZLICHA_AUXILIARY;
        this.ZZMENGE_AUXILIARY = ZZMENGE_AUXILIARY;
        this.ZZCOMMONNAME = ZZCOMMONNAME;
        this.ZZLICHA = ZZLICHA;
    }

    protected PositionMovementModelView(Parcel in) {
        LGNUM = in.readString();
        TAPOS = in.readString();
        MATNR = in.readString();
        MAKTX = in.readString();
        WERKS = in.readString();
        LGORT = in.readString();
        CHARG = in.readString();
        MEINS = in.readString();
        MEINS_TXT = in.readString();
        VSOLM = in.readString();
        ALTME = in.readString();
        ALTME_TXT = in.readString();
        VSOLA = in.readString();
        VLTYP = in.readString();
        VLPLA = in.readString();
        BESTQ = in.readString();
        NLTYP = in.readString();
        NLPLA = in.readString();
        VERME = in.readDouble();
        ZZDRUGSPEC = in.readString();
        ZZPACKAGING = in.readString();
        ZZLICHA_MAIN = in.readString();
        ZZMENGE_MAIN = in.readString();
        ZZLICHA_AUXILIARY = in.readString();
        ZZMENGE_AUXILIARY = in.readString();
        ZZCOMMONNAME = in.readString();
        ZZLICHA = in.readString();
    }

    public static final Creator<PositionMovementModelView> CREATOR = new Creator<PositionMovementModelView>() {
        @Override
        public PositionMovementModelView createFromParcel(Parcel in) {
            return new PositionMovementModelView(in);
        }

        @Override
        public PositionMovementModelView[] newArray(int size) {
            return new PositionMovementModelView[size];
        }
    };

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
    }

    public String getTAPOS() {
        return TAPOS;
    }

    public void setTAPOS(String TAPOS) {
        this.TAPOS = TAPOS;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getMAKTX() {
        return MAKTX;
    }

    public void setMAKTX(String MAKTX) {
        this.MAKTX = MAKTX;
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

    public String getCHARG() {
        return CHARG;
    }

    public void setCHARG(String CHARG) {
        this.CHARG = CHARG;
    }

    public String getMEINS() {
        return MEINS;
    }

    public void setMEINS(String MEINS) {
        this.MEINS = MEINS;
    }

    public String getMEINS_TXT() {
        return MEINS_TXT;
    }

    public void setMEINS_TXT(String MEINS_TXT) {
        this.MEINS_TXT = MEINS_TXT;
    }

    public String getVSOLM() {
        return VSOLM;
    }

    public void setVSOLM(String VSOLM) {
        this.VSOLM = VSOLM;
    }

    public String getALTME() {
        return ALTME;
    }

    public void setALTME(String ALTME) {
        this.ALTME = ALTME;
    }

    public String getALTME_TXT() {
        return ALTME_TXT;
    }

    public void setALTME_TXT(String ALTME_TXT) {
        this.ALTME_TXT = ALTME_TXT;
    }

    public String getVSOLA() {
        return VSOLA;
    }

    public void setVSOLA(String VSOLA) {
        this.VSOLA = VSOLA;
    }

    public String getVLTYP() {
        return VLTYP;
    }

    public void setVLTYP(String VLTYP) {
        this.VLTYP = VLTYP;
    }

    public String getVLPLA() {
        return VLPLA;
    }

    public void setVLPLA(String VLPLA) {
        this.VLPLA = VLPLA;
    }

    public String getBESTQ() {
        return BESTQ;
    }

    public void setBESTQ(String BESTQ) {
        this.BESTQ = BESTQ;
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

    public Additional getADDITIONAL() {
        return ADDITIONAL;
    }

    public void setADDITIONAL(Additional ADDITIONAL) {
        this.ADDITIONAL = ADDITIONAL;
    }

    public double getVERME() {
        return VERME;
    }

    public void setVERME(double VERME) {
        this.VERME = VERME;
    }

    public String getZZDRUGSPEC() {
        return ZZDRUGSPEC;
    }

    public void setZZDRUGSPEC(String ZZDRUGSPEC) {
        this.ZZDRUGSPEC = ZZDRUGSPEC;
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

    public String getZZCOMMONNAME() {
        return ZZCOMMONNAME;
    }

    public void setZZCOMMONNAME(String ZZCOMMONNAME) {
        this.ZZCOMMONNAME = ZZCOMMONNAME;
    }

    public String getZZLICHA() {
        return ZZLICHA;
    }

    public void setZZLICHA(String ZZLICHA) {
        this.ZZLICHA = ZZLICHA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(LGNUM);
        parcel.writeString(TAPOS);
        parcel.writeString(MATNR);
        parcel.writeString(MAKTX);
        parcel.writeString(WERKS);
        parcel.writeString(LGORT);
        parcel.writeString(CHARG);
        parcel.writeString(MEINS);
        parcel.writeString(MEINS_TXT);
        parcel.writeString(VSOLM);
        parcel.writeString(ALTME);
        parcel.writeString(ALTME_TXT);
        parcel.writeString(VSOLA);
        parcel.writeString(VLTYP);
        parcel.writeString(VLPLA);
        parcel.writeString(BESTQ);
        parcel.writeString(NLTYP);
        parcel.writeString(NLPLA);
        parcel.writeDouble(VERME);
        parcel.writeString(ZZDRUGSPEC);
        parcel.writeString(ZZPACKAGING);
        parcel.writeString(ZZLICHA_MAIN);
        parcel.writeString(ZZMENGE_MAIN);
        parcel.writeString(ZZLICHA_AUXILIARY);
        parcel.writeString(ZZMENGE_AUXILIARY);
        parcel.writeString(ZZCOMMONNAME);
        parcel.writeString(ZZLICHA);
    }
}
