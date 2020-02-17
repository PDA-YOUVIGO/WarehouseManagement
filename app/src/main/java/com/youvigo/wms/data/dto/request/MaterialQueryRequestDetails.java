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

public class MaterialQueryRequestDetails {

    /// 仓库号
    private String LGNUM ;

    /// 仓位
    private String LGPLA ;

    /// 物料编码
    private String MATNR ;

    /// 物料描述
    private String MAKTX ;

    /// 工厂
    private String WERKS ;

    /// 库存地点
    private String LGORT ;

    /// 通用名称
    private String ZZCOMMONNAME ;

    /// 规格
    private String ZZDRUGSPEC ;

    /// 批次
    private String CHARG ;

    /// 附加字段
    private Additional ADDITIONAL ;

    public MaterialQueryRequestDetails() {
    }

    public String getLGPLA() {
        return LGPLA;
    }

    public String getMATNR() {
        return MATNR;
    }

    public String getMAKTX() {
        return MAKTX;
    }

    public String getWERKS() {
        return WERKS;
    }

    public String getLGORT() {
        return LGORT;
    }

    public String getZZCOMMONNAME() {
        return ZZCOMMONNAME;
    }

    public String getZZDRUGSPEC() {
        return ZZDRUGSPEC;
    }

    public MaterialQueryRequestDetails(String LGNUM, String LGPLA, String MATNR, String MAKTX, String WERKS, String LGORT, String ZZCOMMONNAME, String ZZDRUGSPEC, String CHARG, Additional ADDITIONAL) {
        this.LGNUM = LGNUM;
        this.LGPLA = LGPLA;
        this.MATNR = MATNR;
        this.MAKTX = MAKTX;
        this.WERKS = WERKS;
        this.LGORT = LGORT;
        this.ZZCOMMONNAME = ZZCOMMONNAME;
        this.ZZDRUGSPEC = ZZDRUGSPEC;
        this.CHARG = CHARG;
        this.ADDITIONAL = ADDITIONAL;
    }

    public Additional getADDITIONAL() {
        return ADDITIONAL;
    }

    public void setADDITIONAL(Additional ADDITIONAL) {
        this.ADDITIONAL = ADDITIONAL;
    }

    public String getCHARG() {
        return CHARG;
    }


    public void setLGPLA(String LGPLA) {
        this.LGPLA = LGPLA;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public void setMAKTX(String MAKTX) {
        this.MAKTX = MAKTX;
    }

    public void setWERKS(String WERKS) {
        this.WERKS = WERKS;
    }

    public void setLGORT(String LGORT) {
        this.LGORT = LGORT;
    }

    public void setZZCOMMONNAME(String ZZCOMMONNAME) {
        this.ZZCOMMONNAME = ZZCOMMONNAME;
    }

    public void setZZDRUGSPEC(String ZZDRUGSPEC) {
        this.ZZDRUGSPEC = ZZDRUGSPEC;
    }

    public void setCHARG(String CHARG) {
        this.CHARG = CHARG;
    }

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
    }


}
