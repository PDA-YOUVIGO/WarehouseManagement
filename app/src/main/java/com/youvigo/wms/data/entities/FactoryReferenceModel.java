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

import java.io.Serializable;
import java.util.List;

/**
 * 登录页面工厂下拉选择数据源对象
 */
public class FactoryReferenceModel implements Serializable, Comparable<FactoryReferenceModel> {

	private static final long serialVersionUID = 1L;

	private String companyCode;
	private String companyName;
	private String factoryCode;
	private String factoryName;
	private List<StoreReferenceModel> stores;

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

	public List<StoreReferenceModel> getStores() {
		return stores;
	}

	public void setStores(List<StoreReferenceModel> stores) {
		this.stores = stores;
	}

	public String getFactoryDisplayName() {
		return String.format("%s - %s", getFactoryCode(), getFactoryName());
	}

	public FactoryReferenceModel(String companyCode, String companyName, String factoryCode, String factoryName, List<StoreReferenceModel> stores) {
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.stores = stores;
	}

	public FactoryReferenceModel() {
	}


	@Override
	public int compareTo(FactoryReferenceModel factoryReferenceModel) {
		return Integer.parseInt(getFactoryCode()) - Integer.parseInt(factoryReferenceModel.getFactoryCode());
	}
}
