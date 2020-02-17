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

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.MaterialQueryRequest;
import com.youvigo.wms.data.dto.request.MaterialQueryRequestDetails;
import com.youvigo.wms.data.dto.response.MaterialQueryResult;
import com.youvigo.wms.data.entities.Material;

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
import timber.log.Timber;

public class MaterialSearchViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> queryState = new MutableLiveData<>();
	private MutableLiveData<List<Material>> _materials = new MutableLiveData<>();

	/**
	 * 查询数据
	 *
	 * @param material_coding        物料编码
	 * @param batch_number          批次号
	 * @param material_description  物料描述
	 * @param common_name           规格
	 * @param specification         仓位
	 * @param position              通用名称
	 */
	void query(String material_coding, String batch_number, String material_description, String common_name, String specification, String position) {
		_isLoading.setValue(true);
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		// 构建请求
		MaterialQueryRequest materialQueryRequest = new MaterialQueryRequest();
		materialQueryRequest.setControlInfo(new ControlInfo());
		// 请求体
		MaterialQueryRequestDetails materQueryRequestDetails = new MaterialQueryRequestDetails();
		materQueryRequestDetails.setMATNR(material_coding); // 物料编码
		materQueryRequestDetails.setCHARG(batch_number); // 批次
		materQueryRequestDetails.setMAKTX(material_description); // 物料描述
		materQueryRequestDetails.setZZCOMMONNAME(common_name); // 通用名称
		materQueryRequestDetails.setZZDRUGSPEC(specification); //规格
		materQueryRequestDetails.setLGPLA(position); // 仓位
		materQueryRequestDetails.setWERKS(retrofitClient.getFactoryCode());// 工厂
		materQueryRequestDetails.setLGORT(retrofitClient.getStockLocationCode()); // 库存的
		materQueryRequestDetails.setLGNUM(retrofitClient.getWarehouseNumber()); // 仓库
		materQueryRequestDetails.setADDITIONAL(new Additional());
		materialQueryRequest.setMaterialQueryRequestDetails(materQueryRequestDetails);

		Call<MaterialQueryResult> materials = sapService.materialQuery(materialQueryRequest);
		materials.enqueue(new Callback<MaterialQueryResult>() {
			@Override
			public void onResponse(@NotNull Call<MaterialQueryResult> call, @NotNull Response<MaterialQueryResult> response) {
				if (response.isSuccessful()) {
					MaterialQueryResult materialQueryResult = response.body();
					if (!materialQueryResult.getMaterialQueryResponse().getMessage().getSuccess().equalsIgnoreCase("S")) {
						queryState.setValue(new ResultState(false, materialQueryResult.getMaterialQueryResponse().getMessage().getMessage()));
						_isLoading.setValue(false);
						return;
					}
//					if (materialQueryResult.getMaterialQueryResponse().getData()== null)
//					{
//						Timber.e(materialQueryResult.getMaterialQueryResponse().getResult().getMessage());
//						return;
//					}
					Disposable disposable = Flowable.create((FlowableOnSubscribe<List<Material>>) emitter -> {
						List<Material> data = materialQueryResult.getMaterialQueryResponse().getData();
						emitter.onNext(data);
						emitter.onComplete();
					}, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribeWith(new DisposableSubscriber<List<Material>>() {
						@Override
						public void onNext(List<Material> materials) {
							_materials.setValue(materials);
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
			public void onFailure(@NotNull Call<MaterialQueryResult> call, @NotNull Throwable t) {
				queryState.setValue(new ResultState(false, t.getMessage()));
				Timber.e(t);
			}
		});
	}

	LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	LiveData<List<Material>> materials() {
		return _materials;
	}

	LiveData<ResultState> getQueryState() {
		return queryState;
	}

}
