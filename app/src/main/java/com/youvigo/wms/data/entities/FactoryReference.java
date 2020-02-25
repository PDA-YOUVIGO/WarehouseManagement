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

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 登录页面工厂下拉选择数据源对象
 */
public class FactoryReference implements Parcelable, Comparable<FactoryReference> {

	private String companyCode;
	private String companyName;
	private String factoryCode;
	private String factoryName;
	private List<StoreLocationReference> stores;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public List<StoreLocationReference> getStores() {
		return stores;
	}

	public void setStores(List<StoreLocationReference> stores) {
		this.stores = stores;
	}

	@NonNull
	@Override
	public String toString() {
		return String.format("%s - %s", getFactoryCode(), getFactoryName());
	}

	public FactoryReference(String companyCode, String companyName, String factoryCode, String factoryName,
							List<StoreLocationReference> stores) {
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.stores = stores;
	}

	public FactoryReference() {
	}


	@Override
	public int compareTo(FactoryReference factoryReference) {
		return Integer.parseInt(getFactoryCode()) - Integer.parseInt(factoryReference.getFactoryCode());
	}

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.companyCode);
		dest.writeString(this.companyName);
		dest.writeString(this.factoryCode);
		dest.writeString(this.factoryName);
		dest.writeTypedList(this.stores);
	}

	protected FactoryReference(Parcel in) {
		this.companyCode = in.readString();
		this.companyName = in.readString();
		this.factoryCode = in.readString();
		this.factoryName = in.readString();
		this.stores = in.createTypedArrayList(StoreLocationReference.CREATOR);
	}

	public static final Creator<FactoryReference> CREATOR = new Creator<FactoryReference>() {
		@Override
		public FactoryReference createFromParcel(Parcel source) {return new FactoryReference(source);}

		@Override
		public FactoryReference[] newArray(int size) {return new FactoryReference[size];}
	};
}
