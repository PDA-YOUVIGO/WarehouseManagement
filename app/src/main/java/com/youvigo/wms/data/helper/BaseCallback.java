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

package com.youvigo.wms.data.helper;

import okhttp3.Request;
import okhttp3.Response;

public interface BaseCallback<T> {

	/**
	 * 请求失败（网络问题）
	 * @param request
	 * @param e
	 */
	void onFailure(Request request, Exception e);

	/**
	 * 请求成功
	 * @param response
	 * @param model
	 */
	void onSuccess(Response response, T model);

	/**
	 * 请求成功但没有数据返回
	 * @param response
	 * @param errMessage
	 */
	void onFalse(Response response, String errMessage);

	/**
	 * 请求成功但有错误，例如Gson解析错误
	 * @param response
	 * @param errorCode
	 * @param e
	 */
	void onError(Response response, int errorCode, Exception e);
}
