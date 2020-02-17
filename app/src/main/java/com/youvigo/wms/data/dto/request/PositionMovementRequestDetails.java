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

import com.youvigo.wms.data.dto.base.Additional;

public class PositionMovementRequestDetails {

    /// 仓库号
    public String LGNUM ;

    /// 行项目号
    public String TAPOS ;

    /// 物料编号
    public String MATNR ;

    /// 物料描述
    public String MAKTX ;

    /// 工厂
    public String WERKS ;

    /// 库存地点
    public String LGORT ;

    /// 批号
    public String CHARG ;

    /// 基本单位
    public String MEINS ;

    /// 基本单位数量
    public String VSOLM ;

    /// 辅助单位
    public String ALTME ;

    /// 辅助单位数量
    public String VSOLA ;

    /// 下架仓储类型
    public String VLTYP ;

    /// 下架仓位
    public String VLPLA ;

    /// 库存类别
    public String BESTQ ;

    /// 上架仓储类型
    public String NLTYP ;

    /// 上架仓位
    public String NLPLA ;

    /// 附加字段
    public Additional ADDITIONAL ;

    /**
     * 用户信息
     */
    /// 操作人登陆账号
    public String ZOPERC ;

    /// 操作人名字
    public String ZOPERN ;

    /// PDA操作时间
    public String ZOPERT ;

    /**
     * 合箱
     */
    /// 是否合箱
    public String ZZPACKAGING ;

    /// 主批次
    public String ZZLICHA_MAIN ;

    /// 主批次数量
    public String ZZMENGE_MAIN ;

    /// 辅批次
    public String ZZLICHA_AUXILIARY ;

    /// 辅批次数量
    public String ZZMENGE_AUXILIARY ;

    public PositionMovementRequestDetails() {
    }

    public PositionMovementRequestDetails(String LGNUM, String TAPOS, String MATNR, String MAKTX, String WERKS, String LGORT, String CHARG, String MEINS, String VSOLM, String ALTME, String VSOLA, String VLTYP, String VLPLA, String BESTQ, String NLTYP, String NLPLA, Additional ADDITIONAL, String ZOPERC, String ZOPERN, String ZOPERT, String ZZPACKAGING, String ZZLICHA_MAIN, String ZZMENGE_MAIN, String ZZLICHA_AUXILIARY, String ZZMENGE_AUXILIARY) {
        this.LGNUM = LGNUM;
        this.TAPOS = TAPOS;
        this.MATNR = MATNR;
        this.MAKTX = MAKTX;
        this.WERKS = WERKS;
        this.LGORT = LGORT;
        this.CHARG = CHARG;
        this.MEINS = MEINS;
        this.VSOLM = VSOLM;
        this.ALTME = ALTME;
        this.VSOLA = VSOLA;
        this.VLTYP = VLTYP;
        this.VLPLA = VLPLA;
        this.BESTQ = BESTQ;
        this.NLTYP = NLTYP;
        this.NLPLA = NLPLA;
        this.ADDITIONAL = ADDITIONAL;
        this.ZOPERC = ZOPERC;
        this.ZOPERN = ZOPERN;
        this.ZOPERT = ZOPERT;
        this.ZZPACKAGING = ZZPACKAGING;
        this.ZZLICHA_MAIN = ZZLICHA_MAIN;
        this.ZZMENGE_MAIN = ZZMENGE_MAIN;
        this.ZZLICHA_AUXILIARY = ZZLICHA_AUXILIARY;
        this.ZZMENGE_AUXILIARY = ZZMENGE_AUXILIARY;
    }

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

    public String getZOPERC() {
        return ZOPERC;
    }

    public void setZOPERC(String ZOPERC) {
        this.ZOPERC = ZOPERC;
    }

    public String getZOPERN() {
        return ZOPERN;
    }

    public void setZOPERN(String ZOPERN) {
        this.ZOPERN = ZOPERN;
    }

    public String getZOPERT() {
        return ZOPERT;
    }

    public void setZOPERT(String ZOPERT) {
        this.ZOPERT = ZOPERT;
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
}
