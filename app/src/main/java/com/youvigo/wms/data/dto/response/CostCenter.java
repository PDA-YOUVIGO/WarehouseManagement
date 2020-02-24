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

package com.youvigo.wms.data.dto.response;

public class CostCenter {

	// 工厂编码
	private String BUKRS;

	// 工厂名称
	private String BUTXT;

	// 部门编码
	private String OBJID;

	// 部门名称
	private String STEXT;

	// 移动类型编码
	private String BWART;

	// 移动类型名称
	private String BTEXT;

	// 评估类编码
	private String BKLAS;

	// 评估类名称
	private String BKBEZ;

	// 成本中心编码
	private String KOSTL;

	// 成本中心名称
	private String KTEXT;

	public String getBUKRS() {
		return BUKRS;
	}

	public void setBUKRS(String BUKRS) {
		this.BUKRS = BUKRS;
	}

	public String getBUTXT() {
		return BUTXT;
	}

	public void setBUTXT(String BUTXT) {
		this.BUTXT = BUTXT;
	}

	public String getOBJID() {
		return OBJID;
	}

	public void setOBJID(String OBJID) {
		this.OBJID = OBJID;
	}

	public String getSTEXT() {
		return STEXT;
	}

	public void setSTEXT(String STEXT) {
		this.STEXT = STEXT;
	}

	public String getBWART() {
		return BWART;
	}

	public void setBWART(String BWART) {
		this.BWART = BWART;
	}

	public String getBTEXT() {
		return BTEXT;
	}

	public void setBTEXT(String BTEXT) {
		this.BTEXT = BTEXT;
	}

	public String getBKLAS() {
		return BKLAS;
	}

	public void setBKLAS(String BKLAS) {
		this.BKLAS = BKLAS;
	}

	public String getBKBEZ() {
		return BKBEZ;
	}

	public void setBKBEZ(String BKBEZ) {
		this.BKBEZ = BKBEZ;
	}

	public String getKOSTL() {
		return KOSTL;
	}

	public void setKOSTL(String KOSTL) {
		this.KOSTL = KOSTL;
	}

	public String getKTEXT() {
		return KTEXT;
	}

	public void setKTEXT(String KTEXT) {
		this.KTEXT = KTEXT;
	}

	public CostCenter(String BUKRS, String BUTXT, String OBJID, String STEXT, String BWART, String BTEXT, String BKLAS
			, String BKBEZ, String KOSTL, String KTEXT) {
		this.BUKRS = BUKRS;
		this.BUTXT = BUTXT;
		this.OBJID = OBJID;
		this.STEXT = STEXT;
		this.BWART = BWART;
		this.BTEXT = BTEXT;
		this.BKLAS = BKLAS;
		this.BKBEZ = BKBEZ;
		this.KOSTL = KOSTL;
		this.KTEXT = KTEXT;
	}

	public CostCenter() {
	}
}
