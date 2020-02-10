package com.youvigo.wms.data.dto.request;

import com.youvigo.wms.data.dto.base.Additional;

public class OnShevingDetails {

	// 仓库号
	private String LGNUM;

	// 移动类型
	private String BWLVS;

	// 物料凭证号
	private String MBLNR;

	// 物料凭证行项目
	private String ZEILE;

	// 物料编号
	private String MATNR;

	// 物料描述
	private String MAKTX;

	// 工厂
	private String WERKS;

	// 批号
	private String CHARG;

	// 基本单位
	private String MEINS;

	// 基本单位数量
	private double OFMEA;

	// 转移要求号
	private String TBNUM;

	// 转储需求项目
	private String TBPOS;

	// 上架仓位
	private String NLPLA;

	// 操作人登陆账号
	private String ZOPERC;

	// 操作人名字
	private String ZOPERN;

	// PDA操作时间
	private String ZOPERT;

	// 附加字段
	private Additional ADDITIONAL;

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

	public String getLGNUM() {
		return LGNUM;
	}

	public void setLGNUM(String LGNUM) {
		this.LGNUM = LGNUM;
	}

	public String getBWLVS() {
		return BWLVS;
	}

	public void setBWLVS(String BWLVS) {
		this.BWLVS = BWLVS;
	}

	public String getMBLNR() {
		return MBLNR;
	}

	public void setMBLNR(String MBLNR) {
		this.MBLNR = MBLNR;
	}

	public String getZEILE() {
		return ZEILE;
	}

	public void setZEILE(String ZEILE) {
		this.ZEILE = ZEILE;
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

	public double getOFMEA() {
		return OFMEA;
	}

	public void setOFMEA(double OFMEA) {
		this.OFMEA = OFMEA;
	}

	public String getTBNUM() {
		return TBNUM;
	}

	public void setTBNUM(String TBNUM) {
		this.TBNUM = TBNUM;
	}

	public String getTBPOS() {
		return TBPOS;
	}

	public void setTBPOS(String TBPOS) {
		this.TBPOS = TBPOS;
	}

	public String getNLPLA() {
		return NLPLA;
	}

	public void setNLPLA(String NLPLA) {
		this.NLPLA = NLPLA;
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

	public Additional getADDITIONAL() {
		return ADDITIONAL;
	}

	public void setADDITIONAL(Additional ADDITIONAL) {
		this.ADDITIONAL = ADDITIONAL;
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

	public OnShevingDetails(String LGNUM, String BWLVS, String MBLNR, String ZEILE, String MATNR, String MAKTX, String WERKS, String CHARG, String MEINS, double OFMEA, String TBNUM, String TBPOS, String NLPLA, String ZOPERC, String ZOPERN, String ZOPERT, Additional ADDITIONAL, String ZZPACKAGING, String ZZLICHA_MAIN, String ZZMENGE_MAIN, String ZZLICHA_AUXILIARY, String ZZMENGE_AUXILIARY) {
		this.LGNUM = LGNUM;
		this.BWLVS = BWLVS;
		this.MBLNR = MBLNR;
		this.ZEILE = ZEILE;
		this.MATNR = MATNR;
		this.MAKTX = MAKTX;
		this.WERKS = WERKS;
		this.CHARG = CHARG;
		this.MEINS = MEINS;
		this.OFMEA = OFMEA;
		this.TBNUM = TBNUM;
		this.TBPOS = TBPOS;
		this.NLPLA = NLPLA;
		this.ZOPERC = ZOPERC;
		this.ZOPERN = ZOPERN;
		this.ZOPERT = ZOPERT;
		this.ADDITIONAL = ADDITIONAL;
		this.ZZPACKAGING = ZZPACKAGING;
		this.ZZLICHA_MAIN = ZZLICHA_MAIN;
		this.ZZMENGE_MAIN = ZZMENGE_MAIN;
		this.ZZLICHA_AUXILIARY = ZZLICHA_AUXILIARY;
		this.ZZMENGE_AUXILIARY = ZZMENGE_AUXILIARY;
	}

	public OnShevingDetails() {
	}
}
