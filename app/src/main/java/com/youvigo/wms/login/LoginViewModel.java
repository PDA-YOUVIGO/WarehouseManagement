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

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.youvigo.wms.R;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

	private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
	private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

	LiveData<LoginFormState> getLoginFormState() {
		return loginFormState;
	}

	MutableLiveData<LoginResult> getLoginResult() {
		return loginResult;
	}

	void loginDataChanged(String username, String password) {
		if (!isUserNameValid(username)) {
			loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
		} else if (!isPasswordValid(password)) {
			loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
		} else {
			loginFormState.setValue(new LoginFormState(true));
		}
	}

	/**
	 * 获取用户登陆信息
	 *
	 * @param account       用户
	 * @param password      密码
	 * @param factoryCode   工厂编码
	 * @param stockLocation 库存地编码
	 */
	@SuppressLint("CheckResult")
	void loginIn(String account, String password, String factoryCode, String stockLocation) {
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		BackendApi backendApi = retrofitClient.getBackendApi();

		backendApi.login(account,
				password, factoryCode, stockLocation)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<LoggedInUser>>() {
					@Override
					public void onSuccess(ApiResponse<LoggedInUser> loggedInUserApiResponse) {
						if (loggedInUserApiResponse.isSuccess()) {
							LoggedInUser data = loggedInUserApiResponse.getData();
							loginResult.setValue(new LoginResult(data));
						} else {
							loginResult.setValue(new LoginResult(new Exception(loggedInUserApiResponse.getMessage())));
						}
					}

					@Override
					public void onError(Throwable e) {
						loginResult.setValue(new LoginResult(new Exception(e.getMessage())));
					}
				});
	}

	// A placeholder username validation check
	private boolean isUserNameValid(String username) {
		if (username == null) {
			return false;
		}

		return !username.trim().isEmpty();
	}

	// A placeholder password validation check
	private boolean isPasswordValid(String password) {
		return password != null && password.trim().length() > 0;
	}
}
