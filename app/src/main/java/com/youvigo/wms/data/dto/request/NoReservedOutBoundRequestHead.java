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

public class NoReservedOutBoundRequestHead {
	// 移动类型
	private String BWART;

	// 内部订单订单号
	private String AUFNR;

	// 领料人
	private String ZZLLR;

	// 领料人姓名
	private String NACHN;

	// 部门
	private String ORGEH;

	// 部门描述
	private String ORGTX;

	// 移动类型出入口编码
	private String GMCODE;

	// 凭证抬头文本
	private String DESCS;

	// PDA出库单号
	private String PDAOUTORDER;

	// 操作人登陆账号
	private String ZOPERC;

	// 操作人名字
	private String ZOPERN;

	// PDA操作时间
	private String ZOPERT;

	public String getBWART() {
		return BWART;
	}

	public void setBWART(String BWART) {
		this.BWART = BWART;
	}

	public String getAUFNR() {
		return AUFNR;
	}

	public void setAUFNR(String AUFNR) {
		this.AUFNR = AUFNR;
	}

	public String getZZLLR() {
		return ZZLLR;
	}

	public void setZZLLR(String ZZLLR) {
		this.ZZLLR = ZZLLR;
	}

	public String getNACHN() {
		return NACHN;
	}

	public void setNACHN(String NACHN) {
		this.NACHN = NACHN;
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

	public String getGMCODE() {
		return GMCODE;
	}

	public void setGMCODE(String GMCODE) {
		this.GMCODE = GMCODE;
	}

	public String getDESCS() {
		return DESCS;
	}

	public void setDESCS(String DESCS) {
		this.DESCS = DESCS;
	}

	public String getPDAOUTORDER() {
		return PDAOUTORDER;
	}

	public void setPDAOUTORDER(String PDAOUTORDER) {
		this.PDAOUTORDER = PDAOUTORDER;
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

	public NoReservedOutBoundRequestHead(String BWART, String AUFNR, String ZZLLR, String NACHN, String ORGEH,
										 String ORGTX, String GMCODE, String DESCS, String PDAOUTORDER, String ZOPERC,
										 String ZOPERN, String ZOPERT) {
		this.BWART = BWART;
		this.AUFNR = AUFNR;
		this.ZZLLR = ZZLLR;
		this.NACHN = NACHN;
		this.ORGEH = ORGEH;
		this.ORGTX = ORGTX;
		this.GMCODE = GMCODE;
		this.DESCS = DESCS;
		this.PDAOUTORDER = PDAOUTORDER;
		this.ZOPERC = ZOPERC;
		this.ZOPERN = ZOPERN;
		this.ZOPERT = ZOPERT;
	}

	public NoReservedOutBoundRequestHead() {
	}
}
