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

package com.youvigo.wms.data.dto.base;

import com.google.gson.annotations.SerializedName;

/**
 * Pda后台返回的数据结构
 * @param <T>
 */
public class ApiResponse<T> {

	@SerializedName("code")
	private int code;

	@SerializedName("success")
	private boolean success;

	@SerializedName("data")
	private T data;

	@SerializedName("msg")
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponse() {
	}

	public ApiResponse(int code, boolean success, T data, String message) {
		this.code = code;
		this.success = success;
		this.data = data;
		this.message = message;
	}
}
