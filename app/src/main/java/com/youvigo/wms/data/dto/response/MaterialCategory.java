package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;

/**
 * 物料分类
 */
public class MaterialCategory {

	@SerializedName("CLASSCODE")
	private String classCode;

	@SerializedName("KLTXT")
	private String className;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public MaterialCategory(String classCode, String className) {
		this.classCode = classCode;
		this.className = className;
	}

	public MaterialCategory() {
	}
}