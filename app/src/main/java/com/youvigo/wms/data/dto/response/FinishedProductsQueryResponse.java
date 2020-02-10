package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;

public class FinishedProductsQueryResponse {

	@SerializedName("MT_PDA_On_The_Shelf_Task_Query_Resp2")
	private ShelvingQueryResponse response;

	public ShelvingQueryResponse getResponse() {
		return response;
	}

	public void setResponse(ShelvingQueryResponse response) {
		this.response = response;
	}

	public FinishedProductsQueryResponse(ShelvingQueryResponse response) {
		this.response = response;
	}

	public FinishedProductsQueryResponse() {
	}
}
