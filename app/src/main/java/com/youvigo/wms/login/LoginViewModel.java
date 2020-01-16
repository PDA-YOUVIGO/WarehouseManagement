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

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.youvigo.wms.R;
import com.youvigo.wms.common.SharedPreferenceUtils;
import com.youvigo.wms.common.WarehouseManagementApplication;

class LoginViewModel extends ViewModel {

	private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
	private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

	LoginViewModel() {}

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
	void loginIn(String account, String password, String factoryCode, String stockLocation) {
		Context context = WarehouseManagementApplication.getInstance();
		String pda_interface_address = SharedPreferenceUtils.getString("pda_interface_address", "", context);
		LoginTask loginTask = new LoginTask(pda_interface_address, account, password, factoryCode, stockLocation);

		loginTask.setOnDataFinishedListener(new OnDataFinishedListener() {
			@Override
			public void onDataSuccessfully(LoggedInUser data) {
				loginResult.setValue(new LoginResult(data));
			}

			@Override
			public void onDataFailed(Exception ex) {
				loginResult.setValue(new LoginResult(ex));
			}
		});

		loginTask.execute();

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
