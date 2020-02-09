package com.youvigo.wms.data.dto.base;

import com.google.gson.annotations.SerializedName;

public class SapResponse<T> {
	@SerializedName("ControlInfo")
	public ControlInfo controlInfo;

	@SerializedName("RETURN")
	public MessageInfo messageInfo;

	@SerializedName("ITEM")
	public T data;
}
