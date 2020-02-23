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
import com.youvigo.wms.data.entities.StockMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MaterialSearchViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> queryState = new MutableLiveData<>();
	private MutableLiveData<List<StockMaterial>> _materials = new MutableLiveData<>();

	/**
	 * 查询数据
	 *
	 * @param materialCode        物料编码
	 * @param batchNumber         批次号
	 * @param materialDescription 物料描述
	 * @param materialCommonName  通用名称
	 * @param specification       规格
	 * @param cargoCode           仓位
	 */
	void query(String materialCode, String batchNumber, String materialDescription, String materialCommonName,
			   String specification, String cargoCode, List<String> materilaFilter,
			   String cargoSpaceTypeFilter) {

		_isLoading.setValue(true);
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		// 构建请求
		MaterialQueryRequest materialQueryRequest = new MaterialQueryRequest();
		materialQueryRequest.setControlInfo(new ControlInfo());

		// 请求体
		MaterialQueryRequestDetails materialQueryRequestDetails = new MaterialQueryRequestDetails();
		materialQueryRequestDetails.setMATNR(materialCode == null ? "" : materialCode); // 物料编码
		materialQueryRequestDetails.setCHARG(batchNumber == null ? "" : batchNumber); // 批次
		materialQueryRequestDetails.setMAKTX(materialDescription == null ? "" : materialDescription); // 物料描述
		materialQueryRequestDetails.setZZCOMMONNAME(materialCommonName == null ? "" : materialCommonName); // 通用名称
		materialQueryRequestDetails.setZZDRUGSPEC(specification == null ? "" : specification); //规格
		materialQueryRequestDetails.setLGPLA(cargoCode == null ? "" : cargoCode); // 仓位
		materialQueryRequestDetails.setWERKS(retrofitClient.getFactoryCode());// 工厂
		materialQueryRequestDetails.setLGORT(retrofitClient.getStockLocationCode()); // 库存的
		materialQueryRequestDetails.setLGNUM(retrofitClient.getWarehouseNumber()); // 仓库
		materialQueryRequestDetails.setADDITIONAL(new Additional());
		materialQueryRequest.setMaterialQueryRequestDetails(materialQueryRequestDetails);


		Disposable disposable =
				sapService.materialQuery(materialQueryRequest).map(materialQueryResult -> {

					if (!materialQueryResult.getMaterialQueryResponse().getMessage().getSuccess().equals("S") &&
							materialQueryResult.getMaterialQueryResponse().getData() == null) {
						queryState.setValue(new ResultState(false,
								materialQueryResult.getMaterialQueryResponse().getMessage().getMessage()));
						_isLoading.setValue(false);
						return new ArrayList<StockMaterial>();
					}

					// 过滤数据
					return materialQueryResult.getMaterialQueryResponse().getData()
							.stream().filter(material -> !materilaFilter.contains(material.getBESTQ()) || Pattern.matches(cargoSpaceTypeFilter, material.getLGTYP()))
							.collect(Collectors.toList());

				})
						.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeWith(new DisposableSubscriber<List<StockMaterial>>() {
							@Override
							public void onNext(List<StockMaterial> stockMaterials) {
								if (stockMaterials != null && stockMaterials.size() > 0) {
									_materials.setValue(stockMaterials);
								}
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

	LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	LiveData<List<StockMaterial>> materials() {
		return _materials;
	}

	LiveData<ResultState> getQueryState() {
		return queryState;
	}

}
