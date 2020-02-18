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

package com.youvigo.wms.warehouse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.WarehouseInventoryQueryRequest;
import com.youvigo.wms.data.dto.request.WarehouseInventoryQueryRequestDetails;
import com.youvigo.wms.data.dto.response.WarehouseInventoryQueryResponse;
import com.youvigo.wms.data.dto.response.WarehouseInventoryQueryResponseDetails;
import com.youvigo.wms.data.entities.WarehouseInventoryQueryModelView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

public class WarehouseInventoryViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> queryState = new MutableLiveData<>();
	private MutableLiveData<List<WarehouseInventoryQueryModelView>> _inventorys = new MutableLiveData<>();

	/**
	 * 盘点凭证查询数据
	 * @param startDate        开始时间
	 * @param endDate          结束时间
	 * @param voucherNumber         凭证号
	 */
	void query(String startDate, String endDate, String voucherNumber ) {
		_isLoading.setValue(true);
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();
		// 构建请求
		WarehouseInventoryQueryRequest inventoryQueryRequest = new WarehouseInventoryQueryRequest();
		inventoryQueryRequest.setControlInfo(new ControlInfo());
		//备用字段
		Additional addotonal = new Additional();
		addotonal.setAddit1("");
		addotonal.setAddit2("");
		addotonal.setAddit3("");
		addotonal.setAddit4("");
		addotonal.setAddit5("");
		inventoryQueryRequest.setADDITIONAL(addotonal);
		// 请求体
		WarehouseInventoryQueryRequestDetails inventoryQueryRequestDetails = new WarehouseInventoryQueryRequestDetails();
		inventoryQueryRequestDetails.setLGNUM(retrofitClient.getWarehouseNumber()); // 仓库号
		inventoryQueryRequestDetails.setIVNUM(voucherNumber); // 凭证号
		inventoryQueryRequestDetails.setSPDATU(voucherNumber.isEmpty() ? startDate : ""); // 开始时间
		inventoryQueryRequestDetails.setEPDATU(voucherNumber.isEmpty() ? endDate : ""); // 结束时间
		inventoryQueryRequest.setData(inventoryQueryRequestDetails);

		Call<WarehouseInventoryQueryResponse> inventory = sapService.queryWarehouseInventory(inventoryQueryRequest);
		inventory.enqueue(new Callback<WarehouseInventoryQueryResponse>() {
			@Override
			public void onResponse(@NotNull Call<WarehouseInventoryQueryResponse> call, @NotNull Response<WarehouseInventoryQueryResponse> response) {
				if (response.isSuccessful()) {
					WarehouseInventoryQueryResponse inventoryQueryResult = response.body();
					assert inventoryQueryResult != null;
					if (!inventoryQueryResult.getData().getRETURN().getMSGTYPE().equalsIgnoreCase("S")) {
						queryState.setValue(new ResultState(false, inventoryQueryResult.getData().getRETURN().getMSGTXT()));
						_isLoading.setValue(false);
						return;
					}
					Disposable disposable = Flowable.create((FlowableOnSubscribe<List<WarehouseInventoryQueryModelView>>) emitter -> {
						List<WarehouseInventoryQueryResponseDetails> data = inventoryQueryResult.getData().getData();
						List<WarehouseInventoryQueryModelView> modelView = new ArrayList<>();
						// 按凭证号分组数据
						Map<String, List<WarehouseInventoryQueryResponseDetails>> inventoryGroup = data.stream().collect(Collectors.groupingBy(WarehouseInventoryQueryResponseDetails::getIVNUM));
						// 构建数据
						inventoryGroup.forEach((k,v) -> {
							WarehouseInventoryQueryModelView wmv = new WarehouseInventoryQueryModelView();
							WarehouseInventoryQueryResponseDetails details = v.get(0);
							wmv.setIVNUM(details.getIVNUM());
							wmv.setLines(v);
							modelView.add(wmv);
						});
						emitter.onNext(modelView);
						emitter.onComplete();
					}, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribeWith(new DisposableSubscriber<List<WarehouseInventoryQueryModelView>>() {
						@Override
						public void onNext(List<WarehouseInventoryQueryModelView> mv) {
							_inventorys.setValue(mv);
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
			public void onFailure(@NotNull Call<WarehouseInventoryQueryResponse> call, @NotNull Throwable t) {
				queryState.setValue(new ResultState(false, t.getMessage()));
				Timber.e(t);
			}
		});
	}

	LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	LiveData<List<WarehouseInventoryQueryModelView>> inventory() { return _inventorys; }

	LiveData<ResultState> getQueryState() {
		return queryState;
	}

}
