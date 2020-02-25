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

/**
 * 无预留出库目的库存地Spinner参照
 */
public class StockLocal implements Parcelable {
	private String localCode;
	private String localName;
	private String factoryCode;

	@Override
	public String toString() {
		return String.format("%s - %s", localCode, localName);
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public StockLocal() {
	}

	public StockLocal(String localCode, String localName, String factoryCode) {
		this.localCode = localCode;
		this.localName = localName;
		this.factoryCode = factoryCode;
	}

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.localCode);
		dest.writeString(this.localName);
		dest.writeString(this.factoryCode);
	}

	protected StockLocal(Parcel in) {
		this.localCode = in.readString();
		this.localName = in.readString();
		this.factoryCode = in.readString();
	}

	public static final Creator<StockLocal> CREATOR = new Creator<StockLocal>() {
		@Override
		public StockLocal createFromParcel(Parcel source) {return new StockLocal(source);}

		@Override
		public StockLocal[] newArray(int size) {return new StockLocal[size];}
	};
}
