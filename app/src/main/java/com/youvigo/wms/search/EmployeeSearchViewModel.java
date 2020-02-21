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

package com.youvigo.wms.search;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.entities.Employee;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EmployeeSearchViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> _resultState = new MutableLiveData<>();
	private MutableLiveData<List<Employee>> _employees = new MutableLiveData<>();


	@SuppressLint("CheckResult")
	void queryEmployee(String employeeCode) {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		BackendApi backendApi = retrofitClient.getBackendApi();

		backendApi.getEmployee(employeeCode, retrofitClient.getFactoryCode())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<List<Employee>>>() {
					@Override
					public void onSuccess(ApiResponse<List<Employee>> listApiResponse) {
						if (listApiResponse.getData() != null) {
							_isLoading.setValue(false);
							_employees.setValue(listApiResponse.getData());
						}
					}

					@Override
					public void onError(Throwable e) {
						_isLoading.setValue(false);
						_resultState.setValue(new ResultState(false, e.getMessage()));
					}

				});
	}

	public LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	public LiveData<ResultState> resultState() {
		return _resultState;
	}

	public LiveData<List<Employee>> employees() {
		return _employees;
	}
}
