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

package com.youvigo.wms.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.FinishedProductsQueryRequest;
import com.youvigo.wms.data.dto.request.FinishedProductsQueryRequestDetails;
import com.youvigo.wms.data.dto.response.FinishedProductsQueryResponse;
import com.youvigo.wms.data.entities.Shelving;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class FinishedProductsViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> queryState = new MutableLiveData<>();
	private MutableLiveData<List<Shelving>> _shelves = new MutableLiveData<>();

	void query(String materialCode, String batchNumber) {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		// 构建查询参数
		FinishedProductsQueryRequest request = new FinishedProductsQueryRequest();
		request.setControlInfo(new ControlInfo());

		FinishedProductsQueryRequestDetails requestDetails = new FinishedProductsQueryRequestDetails();
		requestDetails.setMaterialCode(materialCode);
		requestDetails.setBatchCode(batchNumber);
		requestDetails.setYear(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
		requestDetails.setWarehouseNumber(retrofitClient.getWarehouseNumber());
		requestDetails.setStockLocationCode(retrofitClient.getStockLocationCode());
		request.setDetails(requestDetails);

		Call<FinishedProductsQueryResponse> call = sapService.queryFinishedProducts(request);

		// 执行异步查询
		call.enqueue(new Callback<FinishedProductsQueryResponse>() {
			@Override
			public void onResponse(@NotNull Call<FinishedProductsQueryResponse> call, @NotNull Response<FinishedProductsQueryResponse> response) {
				if (response.isSuccessful()) {

					FinishedProductsQueryResponse result = response.body();

					if (!result.getResponse().getResult().getSuccess().equalsIgnoreCase("S")) {
						queryState.setValue(new ResultState(false, result.getResponse().getResult().getMessage()));
						_isLoading.setValue(false);
						return;
					}

					Disposable disposable = Flowable.create((FlowableOnSubscribe<List<Shelving>>) emitter -> {
						List<Shelving> data = result.getResponse().getData();

						emitter.onNext(data);
						emitter.onComplete();
					}, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribeWith(new DisposableSubscriber<List<Shelving>>(){

								@Override
								public void onNext(List<Shelving> shelvings) {
									_shelves.setValue(shelvings);
								}

								@Override
								public void onError(Throwable t) {
									_isLoading.setValue(false);
								}

								@Override
								public void onComplete() {
									_isLoading.setValue(false);
								}
							});

					addSubscription(disposable);

				}
			}

			@Override
			public void onFailure(@NotNull Call<FinishedProductsQueryResponse> call, @NotNull Throwable t) {
				queryState.setValue(new ResultState(false, t.getMessage()));
				Timber.d(t);
			}
		});

	}

	LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	LiveData<List<Shelving>> shelves() {
		return _shelves;
	}

	LiveData<ResultState> getQueryState() {
		return queryState;
	}

}
