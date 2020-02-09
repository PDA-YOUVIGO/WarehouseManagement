package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;

public class MaterialUnit {

	/**
	 * 单位数量
	 */
	@SerializedName("UMREN")
	public String umren;

	/** 单位编码 */
	@SerializedName("MEINH")
	public String meinh;

	/**
	 * 单位中文名称
	 */
	@SerializedName("MSEH3")
	public String mseh3;

	/**
	 * 基本单位换算数量
	 */
	@SerializedName("UMREZ")
	public String umrez;

	public String getUmren() {
		return umren;
	}

	public void setUmren(String umren) {
		this.umren = umren;
	}

	public String getMeinh() {
		return meinh;
	}

	public void setMeinh(String meinh) {
		this.meinh = meinh;
	}

	public String getMseh3() {
		return mseh3;
	}

	public void setMseh3(String mseh3) {
		this.mseh3 = mseh3;
	}

	public String getUmrez() {
		return umrez;
	}

	public void setUmrez(String umrez) {
		this.umrez = umrez;
	}

	public MaterialUnit() {
	}

	public MaterialUnit(String umren, String meinh, String mseh3, String umrez) {
		this.umren = umren;
		this.meinh = meinh;
		this.mseh3 = mseh3;
		this.umrez = umrez;
	}
}
