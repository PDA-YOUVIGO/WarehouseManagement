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

import java.io.Serializable;

/**
 * 仓库参照
 */
public class WarehouseReferenceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 工厂编码
	 */
	private String factoryCode;

	/**
	 * 工厂名称
	 */
	private String factoryName;

	/**
	 * 库存地编码
	 */
	private String stockLocationCode;

	/**
	 * 库存地名称
	 */
	private String stockLocaltionName;

	/**
	 * 仓库编码
	 */
	private String storeCode;

	/**
	 * 仓库名称
	 */
	private String storeName;

	public WarehouseReferenceEntity() {
	}

	public WarehouseReferenceEntity(String factoryCode, String factoryName, String stockLocationCode, String stockLocationName, String storeCode, String storeName) {
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.stockLocationCode = stockLocationCode;
		this.stockLocaltionName = stockLocationName;
		this.storeCode = storeCode;
		this.storeName = storeName;
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

	public String getStockLocationCode() {
		return stockLocationCode;
	}

	public void setStockLocationCode(String stockLocationCode) {
		this.stockLocationCode = stockLocationCode;
	}

	public String getStockLocaltionName() {
		return stockLocaltionName;
	}

	public void setStockLocaltionName(String stockLocaltionName) {
		this.stockLocaltionName = stockLocaltionName;
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
}
