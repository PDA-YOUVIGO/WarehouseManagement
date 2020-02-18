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

public class ReservedOutBoundRequestDetails {

	// 组件需求日期
	private String BDTER;

	// 预留单号
	private String RSNUM;

	// 预留行项目
	private String RSPOS;

	// 工厂
	private String WERKS;

	// 库存地点
	private String LGORT;

	// 仓库编号
	private String LGNUM;

	// 仓位
	private String LGPLA;

	// 物料编号
	private String MATNR;

	// 批次
	private String CHARG;

	// 需求量
	private String BDMNG;

	// 单位
	private String MEINS;

	private String UMLGO;

	// 备注
	private String MEMO;

	public String getBDTER() {
		return BDTER;
	}

	public void setBDTER(String BDTER) {
		this.BDTER = BDTER;
	}

	public String getRSNUM() {
		return RSNUM;
	}

	public void setRSNUM(String RSNUM) {
		this.RSNUM = RSNUM;
	}

	public String getRSPOS() {
		return RSPOS;
	}

	public void setRSPOS(String RSPOS) {
		this.RSPOS = RSPOS;
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

	public String getLGNUM() {
		return LGNUM;
	}

	public void setLGNUM(String LGNUM) {
		this.LGNUM = LGNUM;
	}

	public String getLGPLA() {
		return LGPLA;
	}

	public void setLGPLA(String LGPLA) {
		this.LGPLA = LGPLA;
	}

	public String getMATNR() {
		return MATNR;
	}

	public void setMATNR(String MATNR) {
		this.MATNR = MATNR;
	}

	public String getCHARG() {
		return CHARG;
	}

	public void setCHARG(String CHARG) {
		this.CHARG = CHARG;
	}

	public String getBDMNG() {
		return BDMNG;
	}

	public void setBDMNG(String BDMNG) {
		this.BDMNG = BDMNG;
	}

	public String getMEINS() {
		return MEINS;
	}

	public void setMEINS(String MEINS) {
		this.MEINS = MEINS;
	}

	public String getUMLGO() {
		return UMLGO;
	}

	public void setUMLGO(String UMLGO) {
		this.UMLGO = UMLGO;
	}

	public String getMEMO() {
		return MEMO;
	}

	public void setMEMO(String MEMO) {
		this.MEMO = MEMO;
	}

	public ReservedOutBoundRequestDetails(String BDTER, String RSNUM, String RSPOS, String WERKS, String LGORT, String LGNUM, String LGPLA, String MATNR, String CHARG, String BDMNG, String MEINS, String UMLGO, String MEMO) {
		this.BDTER = BDTER;
		this.RSNUM = RSNUM;
		this.RSPOS = RSPOS;
		this.WERKS = WERKS;
		this.LGORT = LGORT;
		this.LGNUM = LGNUM;
		this.LGPLA = LGPLA;
		this.MATNR = MATNR;
		this.CHARG = CHARG;
		this.BDMNG = BDMNG;
		this.MEINS = MEINS;
		this.UMLGO = UMLGO;
		this.MEMO = MEMO;
	}

	public ReservedOutBoundRequestDetails() {
	}
}
