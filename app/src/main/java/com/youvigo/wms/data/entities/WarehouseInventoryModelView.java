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

import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.response.MaterialUnit;

import java.util.List;

public class WarehouseInventoryModelView {

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

    /// 物料通用名称
    private String ZZCOMMONNAME ;

    /// 工厂
    private String WERKS ;

    /// 批号
    private String CHARG ;

    /// 供应商批次
    private String ZZLICHA ;

    /// 基本单位
    private String MEINS ;

    /// 基本单位文本
    private String MEINS_TXT ;

    /// 盘点数量
    private double MENGA ;

    /// 盘点标识
    private String ADDFLAG ;

    /// 仓库地点
    private String LGORT ;
    /// 仓位
    private String LGPLA ;

    /// 附加字段
    private Additional ADDITIONAL ;

    /// 现存量
    private double NUMBER ;

    /// 库存类别
    private String BESTQ ;

    /// 是否合箱
    private String ZZPACKAGING ;

    /// 主批次
    private String ZZLICHA_MAIN ;

    /// 主批次数量
    private String ZZMENGE_MAIN ;

    /// 辅批次
    private String ZZLICHA_AUXILIARY ;

    /// 辅批次数量
    private String ZZMENGE_AUXILIARY ;

    /// 换算率
    private List<MaterialUnit> units ;

    public WarehouseInventoryModelView() {
    }

    public WarehouseInventoryModelView(String LGNUM, String IVNUM, String IVPOS, String MATNR, String MAKTX, String ZZCOMMONNAME, String WERKS, String CHARG, String ZZLICHA, String MEINS, String MEINS_TXT, double MENGA, String ADDFLAG, String LGORT, String LGPLA, Additional ADDITIONAL, double NUMBER, String BESTQ, String ZZPACKAGING, String ZZLICHA_MAIN, String ZZMENGE_MAIN, String ZZLICHA_AUXILIARY, String ZZMENGE_AUXILIARY, List<MaterialUnit> units) {
        this.LGNUM = LGNUM;
        this.IVNUM = IVNUM;
        this.IVPOS = IVPOS;
        this.MATNR = MATNR;
        this.MAKTX = MAKTX;
        this.ZZCOMMONNAME = ZZCOMMONNAME;
        this.WERKS = WERKS;
        this.CHARG = CHARG;
        this.ZZLICHA = ZZLICHA;
        this.MEINS = MEINS;
        this.MEINS_TXT = MEINS_TXT;
        this.MENGA = MENGA;
        this.ADDFLAG = ADDFLAG;
        this.LGORT = LGORT;
        this.LGPLA = LGPLA;
        this.ADDITIONAL = ADDITIONAL;
        this.NUMBER = NUMBER;
        this.BESTQ = BESTQ;
        this.ZZPACKAGING = ZZPACKAGING;
        this.ZZLICHA_MAIN = ZZLICHA_MAIN;
        this.ZZMENGE_MAIN = ZZMENGE_MAIN;
        this.ZZLICHA_AUXILIARY = ZZLICHA_AUXILIARY;
        this.ZZMENGE_AUXILIARY = ZZMENGE_AUXILIARY;
        this.units = units;
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

    public String getZZCOMMONNAME() {
        return ZZCOMMONNAME;
    }

    public void setZZCOMMONNAME(String ZZCOMMONNAME) {
        this.ZZCOMMONNAME = ZZCOMMONNAME;
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

    public String getZZLICHA() {
        return ZZLICHA;
    }

    public void setZZLICHA(String ZZLICHA) {
        this.ZZLICHA = ZZLICHA;
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

    public Additional getADDITIONAL() {
        return ADDITIONAL;
    }

    public void setADDITIONAL(Additional ADDITIONAL) {
        this.ADDITIONAL = ADDITIONAL;
    }

    public double getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(double NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getBESTQ() {
        return BESTQ;
    }

    public void setBESTQ(String BESTQ) {
        this.BESTQ = BESTQ;
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

    public List<MaterialUnit> getUnits() {
        return units;
    }

    public void setUnits(List<MaterialUnit> units) {
        this.units = units;
    }
}
