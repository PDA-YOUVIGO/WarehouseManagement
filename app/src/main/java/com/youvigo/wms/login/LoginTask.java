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

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youvigo.wms.common.Result;
import com.youvigo.wms.dto.LoggedInUserResponseEntity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginTask extends AsyncTask<String, Void, Result> {

	private String loginUrl;
	private String account;
	private String password;
	private String factoryCode;
	private String stockLocation;

	// 回调接口
	private OnDataFinishedListener onDataFinishedListener;

	LoginTask(String url, String account, String password, String factoryCode, String stockLocation) {
		this.loginUrl = url;
		this.account = account;
		this.password = password;
		this.factoryCode = factoryCode;
		this.stockLocation = stockLocation;
	}

	void setOnDataFinishedListener(OnDataFinishedListener onDataFinishedListener) {
		this.onDataFinishedListener = onDataFinishedListener;
	}

	@Override
	protected Result doInBackground(String... strings) {
		try {
			String url = String.format("http://%s/blade-data/login?account=%s&password=%s&factoryCode=%s&stocklocation=%s", loginUrl, account, password, factoryCode, stockLocation);

			OkHttpClient okHttpClient = new OkHttpClient();
			Request request = new Request.Builder().get().url(url).build();

			final Gson gson = new GsonBuilder().create();
			try (Response response = okHttpClient.newCall(request).execute()){
				if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

				try (ResponseBody responseBody = response.body()) {
					if (responseBody != null) {
						String responseStr = responseBody.string();
						LoggedInUserResponseEntity loggedInUserResponseEntity = gson.fromJson(responseStr, LoggedInUserResponseEntity.class);

						if (!loggedInUserResponseEntity.isSuccess()) {
							return new Result.Error(new Exception(loggedInUserResponseEntity.getMsg()));
						}
						return new Result.Success<>(loggedInUserResponseEntity.getData());
					}
				}
			}

		} catch (Exception e) {
			return new Result.Error(e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Result result) {

		if (result instanceof Result.Success) {
			LoggedInUser loggedInUser = ((Result.Success<LoggedInUser>) result).getData();
			onDataFinishedListener.onDataSuccessfully(loggedInUser);
		} else {
			Exception error = ((Result.Error) result).getError();
			onDataFinishedListener.onDataFailed(error);
		}

	}
}
