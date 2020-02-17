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

public class Material implements Parcelable {

    /// 仓库号
    private String LGNUM ;

    /// 物料编号
    private  String MATNR ;

    /// 物料描述
    private  String MAKTX ;

    /// 工厂
    private  String WERKS ;

    /// 库存地点
    private  String LGORT ;

    /// 批号
    private  String CHARG ;

    /// 基本单位
    private  String MEINS ;

    /// 基本单位文本
    private  String MEINS_TXT ;

    /// 总库存量
    private double GESME ;


    /// 可用库存量
    /// </summary>
    private double VERME ;

    /// 仓位
    private  String LGPLA ;

    /// 包装规格
    private  String ZZPACKINGSPEC ;

    /// 内包装规格
    private  String ZZINNERPACK ;

    /// 内包装计量单位
    private  String ZZINNERPACKUNIT ;

    /// 外包装规格
    private String ZZOUTERPACK ;

    /// 外包装计量单位
    private String ZZOUTERPACKUNIT ;

    /// 供应商名称
    private String ZZSUPP_NAME ;

    /// 委托厂商
    private String ZZENTRUSTMFGR ;

    /// 委托厂商名称
    private String ZZENTRUSTMFGR_NAME ;

    /// 生产厂商
    private String ZZMFGR ;

    /// 生产厂商名称
    private String ZZMFGR_NAME ;

    /// 国药准字号
    /// </summary>
    private String ZZAPPR_NUM ;

    /// 注册证号
    private String ZZREG_NUM ;

    /// 包材版本
    private String ZZPACK_VER ;

    /// 供应商批次（长文本）
    private String ZZLICHA ;

    /// 折纯系数
    private String ZZPURCOE ;

    /// 折干系数
    private String ZZDRYCOE ;

    /// 检验报告日期
    private String ZZCOATERM ;

    /// COA链接地址
    private String ZZCOA_LINK ;

    /// 检验报告编号
    private String ZZCOA_NUM ;

    /// 放行日期
    private String ZZUDTERM ;

    /// 质检状态
    private String ZZVCODE ;

    /// 定点信息
    private String ZZINFORMATION ;

    /// 质量原因
    private String ZZQUALITY_REASON ;

    /// 质量部备注
    private String ZZQUALITY_REMARK ;

    /// 物流部备注
    private String ZZLOGISTICS_REMARK ;

    /// OA申请编号
    private String ZZOA_PR ;

    /// OA申请行项目号
    private String ZZOAITEM_PR ;

    /// 请购人
    private String ZZAFNAM ;

    /// 请购部门
    private String ZZBEDNR ;

    /// 入库数量
    private String ZZMENGE ;

    /// 采购订单编号
    private String ZZEBELN ;

    /// 采购订单项目
    private String ZZEBELP ;

    /// 电采订单号
    private String ZZNCPO ;

    /// 电采订单项目
    private String ZZNCPOITEM ;

    /// 项目号
    private String ZZAUFNR ;

    /// 项目阶段
    private String ZZKTEXT ;

    /// 检验类型
    private String ZZINSPECTION_TYPE ;

    /// 是否合箱
    private String ZZPACKAGING ;

    /// 主批次
    private String ZZLICHA_MAIN ;

    /// 主批此数量
    private String ZZMENGE_MAIN ;

    /// 辅批次
    private String ZZLICHA_AUXILIARY ;

    /// 辅批次数量
    private String ZZMENGE_AUXILIARY ;

    /// 客户
    private String ZZKUNNR ;

    /// 一致性评价通过
    private String ZZCONSISTENCY ;

    /// 一致性评价通过日期
    private String ZZCONSISTENCYDATE ;

    /// 货号
    private String ZZPROCODE ;

    /// 库存积压状态标识
    private String ZZOVERSTOCK ;

    /// 销售订单
    private String ZZVBELN ;

    /// 销售订单项目
    private String ZZPOSNR ;

    /// 销售合同
    private String ZZBSTKD ;

    /// 规格
    private String ZZDRUGSPEC ;

    /// 物料通用名称
    private String ZZCOMMONNAME ;

    /// 供应商批次
    private String LICHA ;

    /// 仓位类型
    private String LGTYP ;

    /// 库存类别
    private String BESTQ ;

    /// 安全库存
    private String EISBE ;

    /// 最小安全库存
    private String EISLO ;


    protected Material(Parcel in) {
        LGNUM = in.readString();
        MATNR = in.readString();
        MAKTX = in.readString();
        WERKS = in.readString();
        LGORT = in.readString();
        CHARG = in.readString();
        MEINS = in.readString();
        MEINS_TXT = in.readString();
        GESME = in.readDouble();
        VERME = in.readDouble();
        LGPLA = in.readString();
        ZZPACKINGSPEC = in.readString();
        ZZINNERPACK = in.readString();
        ZZINNERPACKUNIT = in.readString();
        ZZOUTERPACK = in.readString();
        ZZOUTERPACKUNIT = in.readString();
        ZZSUPP_NAME = in.readString();
        ZZENTRUSTMFGR = in.readString();
        ZZENTRUSTMFGR_NAME = in.readString();
        ZZMFGR = in.readString();
        ZZMFGR_NAME = in.readString();
        ZZAPPR_NUM = in.readString();
        ZZREG_NUM = in.readString();
        ZZPACK_VER = in.readString();
        ZZLICHA = in.readString();
        ZZPURCOE = in.readString();
        ZZDRYCOE = in.readString();
        ZZCOATERM = in.readString();
        ZZCOA_LINK = in.readString();
        ZZCOA_NUM = in.readString();
        ZZUDTERM = in.readString();
        ZZVCODE = in.readString();
        ZZINFORMATION = in.readString();
        ZZQUALITY_REASON = in.readString();
        ZZQUALITY_REMARK = in.readString();
        ZZLOGISTICS_REMARK = in.readString();
        ZZOA_PR = in.readString();
        ZZOAITEM_PR = in.readString();
        ZZAFNAM = in.readString();
        ZZBEDNR = in.readString();
        ZZMENGE = in.readString();
        ZZEBELN = in.readString();
        ZZEBELP = in.readString();
        ZZNCPO = in.readString();
        ZZNCPOITEM = in.readString();
        ZZAUFNR = in.readString();
        ZZKTEXT = in.readString();
        ZZINSPECTION_TYPE = in.readString();
        ZZPACKAGING = in.readString();
        ZZLICHA_MAIN = in.readString();
        ZZMENGE_MAIN = in.readString();
        ZZLICHA_AUXILIARY = in.readString();
        ZZMENGE_AUXILIARY = in.readString();
        ZZKUNNR = in.readString();
        ZZCONSISTENCY = in.readString();
        ZZCONSISTENCYDATE = in.readString();
        ZZPROCODE = in.readString();
        ZZOVERSTOCK = in.readString();
        ZZVBELN = in.readString();
        ZZPOSNR = in.readString();
        ZZBSTKD = in.readString();
        ZZDRUGSPEC = in.readString();
        ZZCOMMONNAME = in.readString();
        LICHA = in.readString();
        LGTYP = in.readString();
        BESTQ = in.readString();
        EISBE = in.readString();
        EISLO = in.readString();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
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

    public double getGESME() {
        return GESME;
    }

    public void setGESME(double GESME) {
        this.GESME = GESME;
    }

    public double getVERME() {
        return VERME;
    }

    public void setVERME(double VERME) {
        this.VERME = VERME;
    }

    public String getLGPLA() {
        return LGPLA;
    }

    public void setLGPLA(String LGPLA) {
        this.LGPLA = LGPLA;
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

    public String getZZDRUGSPEC() {
        return ZZDRUGSPEC;
    }

    public void setZZDRUGSPEC(String ZZDRUGSPEC) {
        this.ZZDRUGSPEC = ZZDRUGSPEC;
    }

    public String getZZCOMMONNAME() {
        return ZZCOMMONNAME;
    }

    public void setZZCOMMONNAME(String ZZCOMMONNAME) {
        this.ZZCOMMONNAME = ZZCOMMONNAME;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(LGNUM);
        parcel.writeString(MATNR);
        parcel.writeString(MAKTX);
        parcel.writeString(WERKS);
        parcel.writeString(LGORT);
        parcel.writeString(CHARG);
        parcel.writeString(MEINS);
        parcel.writeString(MEINS_TXT);
        parcel.writeDouble(GESME);
        parcel.writeDouble(VERME);
        parcel.writeString(LGPLA);
        parcel.writeString(ZZPACKINGSPEC);
        parcel.writeString(ZZINNERPACK);
        parcel.writeString(ZZINNERPACKUNIT);
        parcel.writeString(ZZOUTERPACK);
        parcel.writeString(ZZOUTERPACKUNIT);
        parcel.writeString(ZZSUPP_NAME);
        parcel.writeString(ZZENTRUSTMFGR);
        parcel.writeString(ZZENTRUSTMFGR_NAME);
        parcel.writeString(ZZMFGR);
        parcel.writeString(ZZMFGR_NAME);
        parcel.writeString(ZZAPPR_NUM);
        parcel.writeString(ZZREG_NUM);
        parcel.writeString(ZZPACK_VER);
        parcel.writeString(ZZLICHA);
        parcel.writeString(ZZPURCOE);
        parcel.writeString(ZZDRYCOE);
        parcel.writeString(ZZCOATERM);
        parcel.writeString(ZZCOA_LINK);
        parcel.writeString(ZZCOA_NUM);
        parcel.writeString(ZZUDTERM);
        parcel.writeString(ZZVCODE);
        parcel.writeString(ZZINFORMATION);
        parcel.writeString(ZZQUALITY_REASON);
        parcel.writeString(ZZQUALITY_REMARK);
        parcel.writeString(ZZLOGISTICS_REMARK);
        parcel.writeString(ZZOA_PR);
        parcel.writeString(ZZOAITEM_PR);
        parcel.writeString(ZZAFNAM);
        parcel.writeString(ZZBEDNR);
        parcel.writeString(ZZMENGE);
        parcel.writeString(ZZEBELN);
        parcel.writeString(ZZEBELP);
        parcel.writeString(ZZNCPO);
        parcel.writeString(ZZNCPOITEM);
        parcel.writeString(ZZAUFNR);
        parcel.writeString(ZZKTEXT);
        parcel.writeString(ZZINSPECTION_TYPE);
        parcel.writeString(ZZPACKAGING);
        parcel.writeString(ZZLICHA_MAIN);
        parcel.writeString(ZZMENGE_MAIN);
        parcel.writeString(ZZLICHA_AUXILIARY);
        parcel.writeString(ZZMENGE_AUXILIARY);
        parcel.writeString(ZZKUNNR);
        parcel.writeString(ZZCONSISTENCY);
        parcel.writeString(ZZCONSISTENCYDATE);
        parcel.writeString(ZZPROCODE);
        parcel.writeString(ZZOVERSTOCK);
        parcel.writeString(ZZVBELN);
        parcel.writeString(ZZPOSNR);
        parcel.writeString(ZZBSTKD);
        parcel.writeString(ZZDRUGSPEC);
        parcel.writeString(ZZCOMMONNAME);
        parcel.writeString(LICHA);
        parcel.writeString(LGTYP);
        parcel.writeString(BESTQ);
        parcel.writeString(EISBE);
        parcel.writeString(EISLO);
    }

    public Material() {}
}
