package com.youvigo.wms.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.BaseApplication;
import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.common.ResultState;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.ShelvingQueryRequest;
import com.youvigo.wms.data.dto.request.ShelvingQueryRequestDetails;
import com.youvigo.wms.data.dto.response.ShelvingResult;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.Shelving;
import com.youvigo.wms.data.entities.TakeOff;

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

public class SearchViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<ResultState> queryState = new MutableLiveData<>();
	private MutableLiveData<List<MaterialVoucher>> _materials = new MutableLiveData<List<MaterialVoucher>>();

	/**
	 * 查询数据
	 *
	 * @param startDate        开始日期
	 * @param endDate          结束日期
	 * @param materialDocument 物料编号
	 */
	void query(String year,String startDate, String endDate, String materialDocument) {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance(BaseApplication.getContext());

		SapService sapService = retrofitClient.getSapService();

		ShelvingQueryRequest shelvingQueryRequest = new ShelvingQueryRequest();
		shelvingQueryRequest.setControlInfo(new ControlInfo());

		ShelvingQueryRequestDetails shelvingQueryRequestDetails = new ShelvingQueryRequestDetails();
		shelvingQueryRequestDetails.setMaterialVoucherCode(materialDocument);
		shelvingQueryRequestDetails.setStartDate(materialDocument.isEmpty() ? "" : startDate);
		shelvingQueryRequestDetails.setEndDate(materialDocument.isEmpty() ? "" : endDate);
		shelvingQueryRequestDetails.setYear(year);
		shelvingQueryRequestDetails.setStockLocationCode(retrofitClient.getStockLocationCode());
		shelvingQueryRequestDetails.setWarehouseNumber(retrofitClient.getWarehouseNumber());

		shelvingQueryRequest.setRequestDetails(shelvingQueryRequestDetails);

		Call<ShelvingResult> shelvings = sapService.getShelvings(shelvingQueryRequest);
		shelvings.enqueue(new Callback<ShelvingResult>() {
			@Override
			public void onResponse(@NotNull Call<ShelvingResult> call, @NotNull Response<ShelvingResult> response) {
				if (response.isSuccessful()) {

					ShelvingResult shelvingResult = response.body();

					if (!shelvingResult.getShelvingQueryResponse().getResult().getSuccess().equalsIgnoreCase("S")) {
						queryState.setValue(new ResultState(false, shelvingResult.getShelvingQueryResponse().getResult().getMessage()));
						_isLoading.setValue(false);
						return;
					}

					Disposable disposable = Flowable.create((FlowableOnSubscribe<List<MaterialVoucher>>) emitter -> {

						List<MaterialVoucher> mockData = new ArrayList<>();
						List<Shelving> data = shelvingResult.getShelvingQueryResponse().getData();

						// 按单据号分组数据
						Map<String, List<Shelving>> group = data.stream().collect(Collectors.groupingBy(Shelving::getMaterialVoucherCode));

						// 组织数据
						group.forEach((k,v) -> {
							MaterialVoucher materialVoucher = new MaterialVoucher();
							materialVoucher.orderNumber = v.get(0).getMaterialVoucherCode();
							materialVoucher.date = v.get(0).getVoucherDate();
							materialVoucher.creator = v.get(0).getCreator();
							materialVoucher.supplierName = v.get(0).getSupplierName();
							materialVoucher.shelvings = v;
							mockData.add(materialVoucher);
						});

						emitter.onNext(mockData);
						emitter.onComplete();
					}, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSubscriber<List<MaterialVoucher>>() {
						@Override
						public void onNext(List<MaterialVoucher> materials) {
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
			public void onFailure(@NotNull Call<ShelvingResult> call, @NotNull Throwable t) {
				queryState.setValue(new ResultState(false, t.getMessage()));
				Timber.e(t);
			}
		});


	}

	@NotNull
	private List<TakeOff> produceTakeOffs(int i) {
		List<TakeOff> takeOffs = new ArrayList<>();
		for (int j = 0; j <= i; j++) {
			TakeOff takeOff = new TakeOff();
			takeOff.itemNumber = "1010201111100000" + j;
			takeOff.materialName = "吸氧剂";
			takeOff.basicOrder = "KG";
			takeOff.specification = "药用级";
			takeOff.lotNumber = "O12340000" + j;
			takeOffs.add(takeOff);
		}
		return takeOffs;
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
