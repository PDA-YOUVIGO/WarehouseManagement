package com.youvigo.wms.common;


import androidx.annotation.Nullable;

public class ResultState {

	private boolean isSuccess;

	@Nullable
	private String message;

	public ResultState(boolean isSuccess, @Nullable String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public ResultState(boolean isSuccess) {
		this.isSuccess = isSuccess;
		this.message = null;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	@Nullable
	public String getMessage() {
		return message;
	}
}
