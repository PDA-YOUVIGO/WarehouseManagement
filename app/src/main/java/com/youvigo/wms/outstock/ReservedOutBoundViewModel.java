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

package com.youvigo.wms.outstock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.request.ReservedOutBoundRequest;
import com.youvigo.wms.data.dto.response.ReservedOutBoundResponse;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.ReservedOutbound;

import org.jetbrains.annotations.NotNull;

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

public class ReservedOutBoundViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<MaterialVoucher> _order = new MutableLiveData<>();
	private MutableLiveData<List<ReservedOutbound>> _items = new MutableLiveData<>();
	private MutableLiveData<ResultState> _submitResult = new MutableLiveData<>();

	public void handleDate(MaterialVoucher materialVoucher) {

		_isLoading.setValue(true);
		_order.setValue(materialVoucher);

		Disposable disposable = Flowable.create((FlowableOnSubscribe<List<ReservedOutbound>>) emitter -> {
			emitter.onNext(materialVoucher.reservedOutbounds);
			emitter.onComplete();
		}, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSubscriber<List<ReservedOutbound>>() {
			@Override
			public void onNext(List<ReservedOutbound> result) {
				_items.setValue(result);
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

	public void submit(ReservedOutBoundRequest request) {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		Call<ReservedOutBoundResponse> call = sapService.submitReservedOutBound(request);
		call.enqueue(new Callback<ReservedOutBoundResponse>() {
			@Override
			public void onResponse(@NotNull Call<ReservedOutBoundResponse> call, @NotNull Response<ReservedOutBoundResponse> response) {
				if (response.isSuccessful()) {
					ReservedOutBoundResponse reservedOutBoundResponse = response.body();
					if (!reservedOutBoundResponse.getMessage().getSuccess().equals("S")) {
						_submitResult.setValue(new ResultState(false, reservedOutBoundResponse.getMessage().getMessage()));
					} else {
						_submitResult.setValue(new ResultState(true, reservedOutBoundResponse.getMessage().getMessage()));
					}
				}
			}

			@Override
			public void onFailure(@NotNull Call<ReservedOutBoundResponse> call, @NotNull Throwable t) {
				_submitResult.setValue(new ResultState(false, t.getMessage()));
			}
		});

	}

	public LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	public LiveData<MaterialVoucher> getMaterialVoucher() {
		return _order;
	}

	public LiveData<List<ReservedOutbound>> getMaterialVoucherItems() {
		return _items;
	}
}
