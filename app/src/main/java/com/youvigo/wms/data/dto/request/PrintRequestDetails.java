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

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.Additional;

public class PrintRequestDetails {

	// PDA出库单
	@SerializedName("PDAOUTORDER")
	private String pdaOrderNumber;

	// 打印机名称
	@SerializedName("PRINTERNAME")
	private String printerName;

	// 程序名称
	@SerializedName("PROGRAMM")
	private String programName;

	// 附加字段1
	@SerializedName("ADDITIONAL")
	private Additional additional;

	public String getPdaOrderNumber() {
		return pdaOrderNumber;
	}

	public void setPdaOrderNumber(String pdaOrderNumber) {
		this.pdaOrderNumber = pdaOrderNumber;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Additional getAdditional() {
		return additional;
	}

	public void setAdditional(Additional additional) {
		this.additional = additional;
	}

	public PrintRequestDetails(String pdaOrderNumber, String printerName, String programName, Additional additional) {
		this.pdaOrderNumber = pdaOrderNumber;
		this.printerName = printerName;
		this.programName = programName;
		this.additional = additional;
	}

	public PrintRequestDetails() {
	}
}
