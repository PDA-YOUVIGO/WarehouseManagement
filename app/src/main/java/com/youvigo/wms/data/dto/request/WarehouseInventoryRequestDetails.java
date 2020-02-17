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

public class WarehouseInventoryRequestDetails {

    /// 仓库号
    private String LGNUM ;

    /// 盘点凭证号
    private String IVNUM ;

    /// 盘点凭证行项目
    private String IVPOS ;

    /// 物料编码
    private String MATNR ;

    /// 物料描述
    private String MAKTX ;

    /// 工厂
    private String WERKS ;

    /// 批号
    private String CHARG ;

    /// 基本单位
    private String MEINS ;

    /// 盘点数量
    private double MENGA ;

    /// 盘点标识
    private String ADDFLAG ;

    /// 仓库地点
    private String LGORT ;

    /// 仓位
    private String LGPLA ;

    /// 库存类别
    private String BESTQ ;

    /// 附加字段
    private Additional ADDITIONAL ;

    /// 操作人登陆账号
    private String ZOPERC ;

    /// 操作人名字
    private String ZOPERN ;

    /// PDA操作时间
    private String ZOPERT ;

    public WarehouseInventoryRequestDetails() {
    }

    public WarehouseInventoryRequestDetails(String LGNUM, String IVNUM, String IVPOS, String MATNR, String MAKTX, String WERKS, String CHARG, String MEINS, double MENGA, String ADDFLAG, String LGORT, String LGPLA, String BESTQ, Additional ADDITIONAL, String ZOPERC, String ZOPERN, String ZOPERT) {
        this.LGNUM = LGNUM;
        this.IVNUM = IVNUM;
        this.IVPOS = IVPOS;
        this.MATNR = MATNR;
        this.MAKTX = MAKTX;
        this.WERKS = WERKS;
        this.CHARG = CHARG;
        this.MEINS = MEINS;
        this.MENGA = MENGA;
        this.ADDFLAG = ADDFLAG;
        this.LGORT = LGORT;
        this.LGPLA = LGPLA;
        this.BESTQ = BESTQ;
        this.ADDITIONAL = ADDITIONAL;
        this.ZOPERC = ZOPERC;
        this.ZOPERN = ZOPERN;
        this.ZOPERT = ZOPERT;
    }

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
    }

    public String getIVNUM() {
        return IVNUM;
    }

    public void setIVNUM(String IVNUM) {
        this.IVNUM = IVNUM;
    }

    public String getIVPOS() {
        return IVPOS;
    }

    public void setIVPOS(String IVPOS) {
        this.IVPOS = IVPOS;
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

    public double getMENGA() {
        return MENGA;
    }

    public void setMENGA(double MENGA) {
        this.MENGA = MENGA;
    }

    public String getADDFLAG() {
        return ADDFLAG;
    }

    public void setADDFLAG(String ADDFLAG) {
        this.ADDFLAG = ADDFLAG;
    }

    public String getLGORT() {
        return LGORT;
    }

    public void setLGORT(String LGORT) {
        this.LGORT = LGORT;
    }

    public String getLGPLA() {
        return LGPLA;
    }

    public void setLGPLA(String LGPLA) {
        this.LGPLA = LGPLA;
    }

    public String getBESTQ() {
        return BESTQ;
    }

    public void setBESTQ(String BESTQ) {
        this.BESTQ = BESTQ;
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
}
