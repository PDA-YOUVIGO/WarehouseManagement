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

public class ReservedOutBoundQueryRequestDetails {

	/// 需求日期-起始
	public String SBDTER;
	/// 需求日期-结束
	public String EBDTER;
	/// 预留单号
	public String RSNUM;
	/// 工厂
	public String WERKS;
	/// 库存地点
	public String LGORT;
	/// 附加字段1
	public String ADDITIONAL1;
	/// 附加字段2
	public String ADDITIONAL2;

	public String getSBDTER() {
		return SBDTER;
	}

	public void setSBDTER(String SBDTER) {
		this.SBDTER = SBDTER;
	}

	public String getEBDTER() {
		return EBDTER;
	}

	public void setEBDTER(String EBDTER) {
		this.EBDTER = EBDTER;
	}

	public String getRSNUM() {
		return RSNUM;
	}

	public void setRSNUM(String RSNUM) {
		this.RSNUM = RSNUM;
	}

	public String getWERKS() {
		return WERKS;
	}

	public void setWERKS(String WERKS) {
		this.WERKS = WERKS;
	}

	public String getLGORT() {
		return LGORT;
	}

	public void setLGORT(String LGORT) {
		this.LGORT = LGORT;
	}

	public String getADDITIONAL1() {
		return ADDITIONAL1;
	}

	public void setADDITIONAL1(String ADDITIONAL1) {
		this.ADDITIONAL1 = ADDITIONAL1;
	}

	public String getADDITIONAL2() {
		return ADDITIONAL2;
	}

	public void setADDITIONAL2(String ADDITIONAL2) {
		this.ADDITIONAL2 = ADDITIONAL2;
	}

	public ReservedOutBoundQueryRequestDetails() {
	}

	public ReservedOutBoundQueryRequestDetails(String SBDTER, String EBDTER, String RSNUM, String WERKS, String LGORT, String ADDITIONAL1, String ADDITIONAL2) {
		this.SBDTER = SBDTER;
		this.EBDTER = EBDTER;
		this.RSNUM = RSNUM;
		this.WERKS = WERKS;
		this.LGORT = LGORT;
		this.ADDITIONAL1 = ADDITIONAL1;
		this.ADDITIONAL2 = ADDITIONAL2;
	}
}
