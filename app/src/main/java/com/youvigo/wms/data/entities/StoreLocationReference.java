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

public class StoreLocationReference implements Parcelable {

	private String stockLocationCode;
	private String storeLocationName;
	private String storeCode;
	private String storeName;

	protected StoreLocationReference(Parcel in) {
		stockLocationCode = in.readString();
		storeLocationName = in.readString();
		storeCode = in.readString();
		storeName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(stockLocationCode);
		dest.writeString(storeLocationName);
		dest.writeString(storeCode);
		dest.writeString(storeName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<StoreLocationReference> CREATOR = new Creator<StoreLocationReference>() {
		@Override
		public StoreLocationReference createFromParcel(Parcel in) {
			return new StoreLocationReference(in);
		}

		@Override
		public StoreLocationReference[] newArray(int size) {
			return new StoreLocationReference[size];
		}
	};

	public String getStockLocationCode() {
		return stockLocationCode;
	}

	public void setStockLocationCode(String stockLocationCode) {
		this.stockLocationCode = stockLocationCode;
	}

	public String getStoreLocationName() {
		return storeLocationName;
	}

	public void setStoreLocationName(String storeLocationName) {
		this.storeLocationName = storeLocationName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@NonNull
	@Override
	public String toString() {
		return String.format("%s - %s", getStockLocationCode(), getStoreLocationName());
	}

	public StoreLocationReference(String stockLocationCode, String storeLocationName, String storeCode, String storeName) {
		this.stockLocationCode = stockLocationCode;
		this.storeLocationName = storeLocationName;
		this.storeCode = storeCode;
		this.storeName = storeName;
	}

	public StoreLocationReference() {
	}
}
