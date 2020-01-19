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

import java.io.Serializable;

/**
 * 用户常用备注信息
 */
public class RemarkReferenceModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String factoryCode;
	private String remark;

	public RemarkReferenceModel() {
	}

	public RemarkReferenceModel(String factoryCode, String remark) {
		this.factoryCode = factoryCode;
		this.remark = remark;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
