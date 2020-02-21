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

public class OrderType implements Parcelable {

	// 单据类型
	private String typeCode;

	// 单据类型名称
	private String typeName;

	// 工厂编码
	private String factoryCode;

	// 工厂名称
	private String factoryName;

	protected OrderType(Parcel in) {
		typeCode = in.readString();
		typeName = in.readString();
		factoryCode = in.readString();
		factoryName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(typeCode);
		dest.writeString(typeName);
		dest.writeString(factoryCode);
		dest.writeString(factoryName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<OrderType> CREATOR = new Creator<OrderType>() {
		@Override
		public OrderType createFromParcel(Parcel in) {
			return new OrderType(in);
		}

		@Override
		public OrderType[] newArray(int size) {
			return new OrderType[size];
		}
	};

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	@NotNull
	@Override
	public String toString() {
		return String.format("%s - %s", typeCode, typeName);
	}
}
