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

package com.youvigo.wms.data.dto.request;

import com.youvigo.wms.data.dto.base.Additional;

public class InternalOrderRequestDetails {

	// 订单号
	private String AUFNR;

	// 订单类型
	private String AUART;

	// 描述
	private String KTEXT;

	// 工厂
	private String WERKS;

	private Additional ADDITIONAL;

	public String getAUFNR() {
		return AUFNR;
	}

	public void setAUFNR(String AUFNR) {
		this.AUFNR = AUFNR;
	}

	public String getAUART() {
		return AUART;
	}

	public void setAUART(String AUART) {
		this.AUART = AUART;
	}

	public String getKTEXT() {
		return KTEXT;
	}

	public void setKTEXT(String KTEXT) {
		this.KTEXT = KTEXT;
	}

	public String getWERKS() {
		return WERKS;
	}

	public void setWERKS(String WERKS) {
		this.WERKS = WERKS;
	}

	public Additional getADDITIONAL() {
		return ADDITIONAL;
	}

	public void setADDITIONAL(Additional ADDITIONAL) {
		this.ADDITIONAL = ADDITIONAL;
	}

	public InternalOrderRequestDetails(String AUFNR, String AUART, String KTEXT, String WERKS, Additional ADDITIONAL) {
		this.AUFNR = AUFNR;
		this.AUART = AUART;
		this.KTEXT = KTEXT;
		this.WERKS = WERKS;
		this.ADDITIONAL = ADDITIONAL;
	}

	public InternalOrderRequestDetails() {
	}
}
