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

import java.util.List;

public class NoReservedOutBound implements Parcelable {

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

	public NoReservedOutBound() {}

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

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.BWART);
		dest.writeString(this.AUFNR);
		dest.writeString(this.ZZLLR);
		dest.writeString(this.NACHN);
		dest.writeString(this.ORGEH);
		dest.writeString(this.ORGTX);
		dest.writeString(this.GMCODE);
		dest.writeString(this.DESCS);
		dest.writeString(this.PDAOUTORDER);
		dest.writeString(this.ZOPERC);
		dest.writeString(this.ZOPERN);
		dest.writeString(this.ZOPERT);
	}

	protected NoReservedOutBound(Parcel in) {
		this.BWART = in.readString();
		this.AUFNR = in.readString();
		this.ZZLLR = in.readString();
		this.NACHN = in.readString();
		this.ORGEH = in.readString();
		this.ORGTX = in.readString();
		this.GMCODE = in.readString();
		this.DESCS = in.readString();
		this.PDAOUTORDER = in.readString();
		this.ZOPERC = in.readString();
		this.ZOPERN = in.readString();
		this.ZOPERT = in.readString();
	}

	public static final Creator<NoReservedOutBound> CREATOR = new Creator<NoReservedOutBound>() {
		@Override
		public NoReservedOutBound createFromParcel(Parcel source) {return new NoReservedOutBound(source);}

		@Override
		public NoReservedOutBound[] newArray(int size) {return new NoReservedOutBound[size];}
	};
}
