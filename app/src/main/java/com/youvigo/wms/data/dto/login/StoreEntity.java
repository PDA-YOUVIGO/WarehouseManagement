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

package com.youvigo.wms.data.dto.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String companyCode;

	private String companyName;

	private String factoryCode;

	private String factoryName;

	private String stockLocation;

	@SerializedName("storeLocaltionName")
	@Expose
	private String storeLocationName;

	private String storeCode;

	private String storeName;

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

	public String getStockLocation() {
		return stockLocation;
	}

	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
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

	public StoreEntity(String companyCode, String companyName, String factoryCode, String factoryName, String stockLocation, String storeLocationName, String storeCode, String storeName) {
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.stockLocation = stockLocation;
		this.storeLocationName = storeLocationName;
		this.storeCode = storeCode;
		this.storeName = storeName;
	}

	public StoreEntity() {
	}

}