package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;

public class ShelvingResult {

	@SerializedName("MT_PDA_On_The_Shelf_Task_Query_Resp")
	private ShelvingQueryResponse shelvingQueryResponse;

	public ShelvingQueryResponse getShelvingQueryResponse() {
		return shelvingQueryResponse;
	}

	public void setShelvingQueryResponse(ShelvingQueryResponse shelvingQueryResponse) {
		this.shelvingQueryResponse = shelvingQueryResponse;
	}

	public ShelvingResult() {
	}

	public ShelvingResult(ShelvingQueryResponse shelvingQueryResponse) {
		this.shelvingQueryResponse = shelvingQueryResponse;
	}
}
