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
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.InternalOrderRequest;
import com.youvigo.wms.data.dto.request.InternalOrderRequestDetails;
import com.youvigo.wms.data.dto.response.InternalOrderResponse;
import com.youvigo.wms.data.entities.InternalOrder;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class InternalOrderViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> _resultState = new MutableLiveData<>();
	private MutableLiveData<List<InternalOrder>> _internalOrder = new MutableLiveData<>();

	@SuppressLint("CheckResult")
	void queryInternalOrder(String orderNumber, String orderType) {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		InternalOrderRequest request = new InternalOrderRequest();
		request.setControlInfo(new ControlInfo());
		InternalOrderRequestDetails requestDetails = new InternalOrderRequestDetails();
		requestDetails.setWERKS(retrofitClient.getFactoryCode());
		requestDetails.setAUFNR(orderNumber);
		requestDetails.setKTEXT("");
		requestDetails.setAUART(orderType);
		requestDetails.setADDITIONAL(new Additional());
		request.setRequestDetails(requestDetails);

		sapService.queryInternalOrder(request)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<InternalOrderResponse>() {
					@Override
					public void onSuccess(InternalOrderResponse internalOrderResponse) {
						if (internalOrderResponse.getInternalOrderResponseDetails().getResponseMessage().getSuccess().equals("S")
								&& internalOrderResponse.getInternalOrderResponseDetails().getInternalOrders() != null
								&& !internalOrderResponse.getInternalOrderResponseDetails().getInternalOrders().isEmpty()) {
							_isLoading.setValue(false);
							_internalOrder.setValue(internalOrderResponse.getInternalOrderResponseDetails().getInternalOrders());
						} else {
							_resultState.setValue(new ResultState(false,
									internalOrderResponse.getInternalOrderResponseDetails().getResponseMessage().getMessage()));
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

	public LiveData<List<InternalOrder>> internalOrders() {
		return _internalOrder;
	}
}
