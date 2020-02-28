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

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.DeliverQueryRequest;
import com.youvigo.wms.data.dto.request.DeliverQueryRequestDetails;
import com.youvigo.wms.data.dto.request.ReservedOutBoundQueryRequest;
import com.youvigo.wms.data.dto.request.ReservedOutBoundQueryRequestDetails;
import com.youvigo.wms.data.dto.request.ShelvingQueryRequest;
import com.youvigo.wms.data.dto.request.ShelvingQueryRequestDetails;
import com.youvigo.wms.data.dto.response.DeliverQueryResponse;
import com.youvigo.wms.data.dto.response.ReservedOutBoundQueryResponse;
import com.youvigo.wms.data.dto.response.ShelvingResult;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.ReservedOutBound;
import com.youvigo.wms.data.entities.Shelving;
import com.youvigo.wms.data.entities.TakeOff;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class SearchViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> queryState = new MutableLiveData<>();
	private MutableLiveData<List<MaterialVoucher>> _materials = new MutableLiveData<>();
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);

	/**
	 * 入库上架查询数据
	 * @param startDate        开始日期
	 * @param endDate          结束日期
	 * @param orderNumber 物料编号
	 */
	void shelvingQuery(String year, String startDate, String endDate, String orderNumber, Context context) {
		_isLoading.setValue(true);
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		ShelvingQueryRequest shelvingQueryRequest = new ShelvingQueryRequest();
		shelvingQueryRequest.setControlInfo(new ControlInfo());

		ShelvingQueryRequestDetails shelvingQueryRequestDetails = new ShelvingQueryRequestDetails();
		shelvingQueryRequestDetails.setMaterialVoucherCode(orderNumber);
		shelvingQueryRequestDetails.setStartDate(orderNumber.isEmpty() ? startDate : "");
		shelvingQueryRequestDetails.setEndDate(orderNumber.isEmpty() ? endDate : "");
		shelvingQueryRequestDetails.setYear(year);
		shelvingQueryRequestDetails.setStockLocationCode(retrofitClient.getStockLocationCode());
		shelvingQueryRequestDetails.setWarehouseNumber(retrofitClient.getWarehouseNumber());
		shelvingQueryRequest.setRequestDetails(shelvingQueryRequestDetails);

		Disposable disposable = sapService.queryOnShelving(shelvingQueryRequest).map(response -> {
					if (response.getShelvingQueryResponse().getResult().getSuccess().equalsIgnoreCase("E") ||
							(response.getShelvingQueryResponse().getResult().getSuccess().equalsIgnoreCase("S") && response.getShelvingQueryResponse().getData() == null)) {
						Utils.showToast(
								context,
								response.getShelvingQueryResponse().getResult().getMessage());
						queryState.setValue(new ResultState(false, response.getShelvingQueryResponse().getResult().getMessage()));
						_isLoading.setValue(false);
						return new ArrayList<MaterialVoucher>();
					}
					List<MaterialVoucher> mockData = new ArrayList<>();
					List<Shelving> data = response.getShelvingQueryResponse().getData();
					// 按单据号分组数据
					Map<String, List<Shelving>> group = data.stream().collect(Collectors.groupingBy(Shelving::getMaterialVoucherCode));
					// 组织数据
					group.forEach((k,v) -> {
						MaterialVoucher materialVoucher = new MaterialVoucher();
						materialVoucher.orderNumber = v.get(0).getMaterialVoucherCode();
						materialVoucher.date = LocalDate.parse(v.get(0).getVoucherDate(), dateTimeFormatter).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						materialVoucher.creator = v.get(0).getCreator();
						materialVoucher.supplierName = v.get(0).getSupplierName();
						materialVoucher.shelvings = v;
						mockData.add(materialVoucher);
					});
					return mockData;
				}).subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeWith(new DisposableSubscriber<List<MaterialVoucher>>() {
							@Override
							public void onNext(List<MaterialVoucher> ModelView) {
								if (ModelView != null && ModelView.size() > 0) {
									_materials.setValue(ModelView);
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

	/**
	 * 出库下架查询
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @param orderNumber 单据号
	 */
	void tackOffQuery(String startDate, String endDate, String orderNumber, Context context) {
		_isLoading.setValue(true);
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		DeliverQueryRequest request = new DeliverQueryRequest();
		request.setControlInfo(new ControlInfo());

		DeliverQueryRequestDetails requestDetails = new DeliverQueryRequestDetails();
		requestDetails.setWarehouseNumber(retrofitClient.getWarehouseNumber());
		requestDetails.setStockLocationCode(retrofitClient.getStockLocationCode());
		requestDetails.setOrderNumber(orderNumber);
		requestDetails.setStartDate(orderNumber.isEmpty() ? startDate : "");
		requestDetails.setEndDate(orderNumber.isEmpty() ? endDate : "");
		request.setDetails(requestDetails);

		Disposable disposable = sapService.queryDeliver(request).map(response -> {
					if (response.getResponseDetails().getMessage().getSuccess().equalsIgnoreCase("E") ||
							(response.getResponseDetails().getMessage().getSuccess().equalsIgnoreCase("S") && response.getResponseDetails().getDetails() == null)) {
						Utils.showToast(context,response.getResponseDetails().getMessage().getMessage());
						queryState.setValue(new ResultState(false, response.getResponseDetails().getMessage().getMessage()));
						_isLoading.setValue(false);
						return new ArrayList<MaterialVoucher>();
					}
					List<MaterialVoucher> mockData = new ArrayList<>();
					List<TakeOff> takeOffs = response.getResponseDetails().getDetails();
					// 按单据号分组数据
					Map<String, List<TakeOff>> group = takeOffs.stream().collect(Collectors.groupingBy(TakeOff::getOrderNumber));
					// 组织数据
					group.forEach((k, v) -> {
						MaterialVoucher materialVoucher = new MaterialVoucher();
						materialVoucher.orderNumber = v.get(0).getOrderNumber();
						materialVoucher.date = LocalDate.parse(v.get(0).getBDATU(), dateTimeFormatter).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						materialVoucher.creator = v.get(0).getUSNAM();
						materialVoucher.takeOffs = v;
						materialVoucher.supplierName = v.get(0).getORGTX();
						mockData.add(materialVoucher);
					});
					return mockData;
				}).subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeWith(new DisposableSubscriber<List<MaterialVoucher>>() {
							@Override
							public void onNext(List<MaterialVoucher> ModelView) {
								if (ModelView != null && ModelView.size() > 0) {
									_materials.setValue(ModelView);
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


	/**
	 * 查询预留单
	 * @param orderNumber 预留单据号
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param moveTypes 允许的移动类型
	 */
	void reservedOutBoundQuery(String orderNumber, String startDate, String endDate, ArrayList moveTypes, Context context) {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		ReservedOutBoundQueryRequest request = new ReservedOutBoundQueryRequest();
		request.setControlInfo(new ControlInfo());

		ReservedOutBoundQueryRequestDetails requestDetails = new ReservedOutBoundQueryRequestDetails();
		requestDetails.setWERKS(retrofitClient.getFactoryCode());
		requestDetails.setSBDTER(orderNumber.isEmpty() ? startDate : "");
		requestDetails.setEBDTER(orderNumber.isEmpty() ? endDate : "");
		requestDetails.setLGORT("");
		requestDetails.setADDITIONAL1("");
		requestDetails.setADDITIONAL2("");
		requestDetails.setRSNUM(orderNumber);
		request.setDetails(requestDetails);

		Disposable disposable = sapService.queryReservedOutBound(request).map(response -> {
			if (response.getDetails().getMessage().getSuccess().equalsIgnoreCase("S") ||
					(response.getDetails().getMessage().getSuccess().equalsIgnoreCase("S") && response.getDetails().getData() == null)) {
				Utils.showToast(context,response.getDetails().getMessage().getMessage());
				queryState.setValue(new ResultState(false, response.getDetails().getMessage().getMessage()));
				_isLoading.setValue(false);
				return new ArrayList<MaterialVoucher>();
			}
			//移除非可用移动类型的数据
			if (moveTypes != null && !moveTypes.isEmpty()) {
				response.getDetails().getData().removeIf(s -> !moveTypes.contains(s.getBWART()));
			}
			List<MaterialVoucher> mockData = new ArrayList<>();
			List<ReservedOutBound> reservedOutBounds = response.getDetails().getData();

			Map<String, List<ReservedOutBound>> group = reservedOutBounds.stream().collect(Collectors.groupingBy(ReservedOutBound::getRSNUM));
			group.forEach((k, v) -> {
				MaterialVoucher materialVoucher = new MaterialVoucher();
				materialVoucher.orderNumber = v.get(0).getRSNUM();
				materialVoucher.date = LocalDate.parse(v.get(0).getBDTER(), dateTimeFormatter).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				materialVoucher.creator = v.get(0).getUSNAM();
				materialVoucher.employerCode = v.get(0).getPERNR();
				materialVoucher.employerName = v.get(0).getNACHN();
				materialVoucher.departmentCode = v.get(0).getORGEH();
				materialVoucher.departmentName = v.get(0).getORGTX();
				materialVoucher.innerOrder = v.get(0).getAUFNR();
				materialVoucher.innerOrderDescription = v.get(0).getKTEXT();
				materialVoucher.moveType = v.get(0).getBWART();
				materialVoucher.reservedOutBounds = v;
				mockData.add(materialVoucher);
			});
			return mockData;
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSubscriber<List<MaterialVoucher>>() {
					@Override
					public void onNext(List<MaterialVoucher> modelView) {
						if (modelView != null && modelView.size() > 0) {
							_materials.setValue(modelView);
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

	LiveData<List<MaterialVoucher>> materials() {
		return _materials;
	}

	LiveData<ResultState> getQueryState() {
		return queryState;
	}
}
