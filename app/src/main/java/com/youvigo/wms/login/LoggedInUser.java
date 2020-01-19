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

package com.youvigo.wms.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

	private String authority;

	@SerializedName("userName")
	@Expose
	private String displayName;

	private String account;
	private String companyCode;
	private String factoryCode;
	private String stockLocation;
	private String stockCode;

	@SerializedName("dateTime")
	@Expose
	private String loginInDateTime;

	private List<RemarkReferenceModel> remarkReference;
	private String license;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getStockLocation() {
		return stockLocation;
	}

	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getLoginInDateTime() {
		return loginInDateTime;
	}

	public void setLoginInDateTime(String loginInDateTime) {
		this.loginInDateTime = loginInDateTime;
	}

	public List<RemarkReferenceModel> getRemarkReference() {
		return remarkReference;
	}

	public void setRemarkReference(List<RemarkReferenceModel> remarkReference) {
		this.remarkReference = remarkReference;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public LoggedInUser(String authority, String displayName, String account, String companyCode, String factoryCode, String stockLocation, String stockCode, String loginInDateTime, List<RemarkReferenceModel> remarkReference, String license) {
		this.authority = authority;
		this.displayName = displayName;
		this.account = account;
		this.companyCode = companyCode;
		this.factoryCode = factoryCode;
		this.stockLocation = stockLocation;
		this.stockCode = stockCode;
		this.loginInDateTime = loginInDateTime;
		this.remarkReference = remarkReference;
		this.license = license;
	}

	public LoggedInUser() {
	}
}
