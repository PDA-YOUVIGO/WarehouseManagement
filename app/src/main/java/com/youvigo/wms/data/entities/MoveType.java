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

import org.jetbrains.annotations.NotNull;

/**
 * 移动类型
 */
public class MoveType implements Parcelable {

	/// 移动类型名称
	private String moveName;

	/// 移动类型编码
	private String moveCode;

	/// GMCODE
	private String gmcode;

	/// 工厂编码
	private String factoryCode;

	/// 工厂名称
	private String factoryName;

	/// 内部订单必填
	private boolean innerOrderNo;

	/// 成本中心必填
	private boolean costCenter;

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public String getMoveCode() {
		return moveCode;
	}

	public void setMoveCode(String moveCode) {
		this.moveCode = moveCode;
	}

	public String getGmcode() {
		return gmcode;
	}

	public void setGmcode(String gmcode) {
		this.gmcode = gmcode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public boolean isInnerOrderNo() {
		return innerOrderNo;
	}

	public void setInnerOrderNo(boolean innerOrderNo) {
		this.innerOrderNo = innerOrderNo;
	}

	public boolean isCostCenter() {
		return costCenter;
	}

	public void setCostCenter(boolean costCenter) {
		this.costCenter = costCenter;
	}

	public MoveType(String moveName, String moveCode, String gmcode, String factoryCode, String factoryName, boolean innerOrderNo, boolean costCenter) {
		this.moveName = moveName;
		this.moveCode = moveCode;
		this.gmcode = gmcode;
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.innerOrderNo = innerOrderNo;
		this.costCenter = costCenter;
	}

	public MoveType() {
	}

	@NotNull
	@Override
	public String toString() {
		return String.format("%s - %s", moveCode, moveName);
	}

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.moveName);
		dest.writeString(this.moveCode);
		dest.writeString(this.gmcode);
		dest.writeString(this.factoryCode);
		dest.writeString(this.factoryName);
		dest.writeByte(this.innerOrderNo ? (byte) 1 : (byte) 0);
		dest.writeByte(this.costCenter ? (byte) 1 : (byte) 0);
	}

	protected MoveType(Parcel in) {
		this.moveName = in.readString();
		this.moveCode = in.readString();
		this.gmcode = in.readString();
		this.factoryCode = in.readString();
		this.factoryName = in.readString();
		this.innerOrderNo = in.readByte() != 0;
		this.costCenter = in.readByte() != 0;
	}

	public static final Creator<MoveType> CREATOR = new Creator<MoveType>() {
		@Override
		public MoveType createFromParcel(Parcel source) {return new MoveType(source);}

		@Override
		public MoveType[] newArray(int size) {return new MoveType[size];}
	};
}
