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

public class DeliverRequestItem {

	/// 转储单项目
	public String TAPOS;

	/// 物料编号
	public String MATNR;

	/// 物料描述
	public String MAKTX;

	/// 工厂
	public String WERKS;

	/// 批号
	public String CHARG;

	/// 基本单位
	public String MEINS;

	/// 基本单位数量
	public String VSOLM;

	/// 辅助单位
	public String ALTME;

	/// 辅助单位数量
	public String VSOLA;

	/// 下架仓储类型
	public String VLTYP;

	/// 下架仓位
	public String VLPLA;

	/// 操作人登陆账号
	public String ZOPERC;

	/// 操作人名字
	public String ZOPERN;

	/// PDA操作时间
	public String ZOPERT;

	/// 是否合箱
	public String ZHXFLAG;

	/// 主批次
	public String MAINBATCH;

	/// 主批次数量
	public String MENGE1;

	/// 辅批次
	public String OPTBATCH;

	/// 辅批次数量
	public String MENGE2;

	public Additional ADDITIONAL;

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

	public String getZHXFLAG() {
		return ZHXFLAG;
	}

	public void setZHXFLAG(String ZHXFLAG) {
		this.ZHXFLAG = ZHXFLAG;
	}

	public String getMAINBATCH() {
		return MAINBATCH;
	}

	public void setMAINBATCH(String MAINBATCH) {
		this.MAINBATCH = MAINBATCH;
	}

	public String getMENGE1() {
		return MENGE1;
	}

	public void setMENGE1(String MENGE1) {
		this.MENGE1 = MENGE1;
	}

	public String getOPTBATCH() {
		return OPTBATCH;
	}

	public void setOPTBATCH(String OPTBATCH) {
		this.OPTBATCH = OPTBATCH;
	}

	public String getMENGE2() {
		return MENGE2;
	}

	public void setMENGE2(String MENGE2) {
		this.MENGE2 = MENGE2;
	}

	public Additional getADDITIONAL() {
		return ADDITIONAL;
	}

	public void setADDITIONAL(Additional ADDITIONAL) {
		this.ADDITIONAL = ADDITIONAL;
	}

	public DeliverRequestItem(String TAPOS, String MATNR, String MAKTX, String WERKS, String CHARG, String MEINS, String VSOLM, String ALTME, String VSOLA, String VLTYP, String VLPLA, String ZOPERC, String ZOPERN, String ZOPERT, String ZHXFLAG, String MAINBATCH, String MENGE1, String OPTBATCH, String MENGE2, Additional ADDITIONAL) {
		this.TAPOS = TAPOS;
		this.MATNR = MATNR;
		this.MAKTX = MAKTX;
		this.WERKS = WERKS;
		this.CHARG = CHARG;
		this.MEINS = MEINS;
		this.VSOLM = VSOLM;
		this.ALTME = ALTME;
		this.VSOLA = VSOLA;
		this.VLTYP = VLTYP;
		this.VLPLA = VLPLA;
		this.ZOPERC = ZOPERC;
		this.ZOPERN = ZOPERN;
		this.ZOPERT = ZOPERT;
		this.ZHXFLAG = ZHXFLAG;
		this.MAINBATCH = MAINBATCH;
		this.MENGE1 = MENGE1;
		this.OPTBATCH = OPTBATCH;
		this.MENGE2 = MENGE2;
		this.ADDITIONAL = ADDITIONAL;
	}

	public DeliverRequestItem() {
	}
}
