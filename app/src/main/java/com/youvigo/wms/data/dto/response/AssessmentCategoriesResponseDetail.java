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

public class AssessmentCategoriesResponseDetail {

	// 物料编码
	private String MATNR;

	// 估价范围（即工厂）
	private String BWKEY;

	// 评估类
	private String KOSTL;

	// 评估类描述
	private String LVORM;

	// 成功类型（S 成功， E 失败）
	private String MSGTYPE;

	// 消息
	private String MSGTXT;

	public String getMATNR() {
		return MATNR;
	}

	public void setMATNR(String MATNR) {
		this.MATNR = MATNR;
	}

	public String getBWKEY() {
		return BWKEY;
	}

	public void setBWKEY(String BWKEY) {
		this.BWKEY = BWKEY;
	}

	public String getKOSTL() {
		return KOSTL;
	}

	public void setKOSTL(String KOSTL) {
		this.KOSTL = KOSTL;
	}

	public String getLVORM() {
		return LVORM;
	}

	public void setLVORM(String LVORM) {
		this.LVORM = LVORM;
	}

	public String getMSGTYPE() {
		return MSGTYPE;
	}

	public void setMSGTYPE(String MSGTYPE) {
		this.MSGTYPE = MSGTYPE;
	}

	public String getMSGTXT() {
		return MSGTXT;
	}

	public void setMSGTXT(String MSGTXT) {
		this.MSGTXT = MSGTXT;
	}

	public AssessmentCategoriesResponseDetail() {
	}

	public AssessmentCategoriesResponseDetail(String MATNR, String BWKEY, String KOSTL, String LVORM, String MSGTYPE,
											  String MSGTXT) {
		this.MATNR = MATNR;
		this.BWKEY = BWKEY;
		this.KOSTL = KOSTL;
		this.LVORM = LVORM;
		this.MSGTYPE = MSGTYPE;
		this.MSGTXT = MSGTXT;
	}
}
