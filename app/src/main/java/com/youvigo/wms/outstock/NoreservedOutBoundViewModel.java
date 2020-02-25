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

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.NoReservedOutBoundRequest;
import com.youvigo.wms.data.dto.request.NoReservedOutBoundRequestDetails;
import com.youvigo.wms.data.dto.request.NoReservedOutBoundRequestHead;
import com.youvigo.wms.data.dto.response.NoReservedOutBoundResponse;
import com.youvigo.wms.data.dto.response.SapResponseMessage;
import com.youvigo.wms.data.entities.MoveType;
import com.youvigo.wms.data.entities.NoReservedOutBound;
import com.youvigo.wms.data.entities.NoReservedOutBoundDetail;
import com.youvigo.wms.data.entities.StockLocal;
import com.youvigo.wms.util.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NoreservedOutBoundViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<NoReservedOutBound> _header = new MutableLiveData<>();
	private MutableLiveData<List<NoReservedOutBoundDetail>> _details = new MutableLiveData<>();
	private MutableLiveData<List<SapResponseMessage>> _sapResult = new MutableLiveData<>();

	protected void init() {
		NoReservedOutBound noReservedOutBound = new NoReservedOutBound();
		List<NoReservedOutBoundDetail> noReservedOutBoundDetails = new ArrayList<>();

		_sapResult.setValue(new ArrayList<>());
		_header.setValue(noReservedOutBound);
		_details.setValue(noReservedOutBoundDetails);
	}

	/**
	 * 提交到SAP
	 */
	@SuppressLint("CheckResult")
	public void submit() {
		_isLoading.setValue(true);

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();



		// 处理出库物料，按成本中心分组单据
		// 只处理未提交成功到行记录
		Map<String, List<NoReservedOutBoundDetail>> group =
				getItems().stream().filter(x -> !x.isSuccess())
						.collect(Collectors.groupingBy(NoReservedOutBoundDetail::getCostCenter));

		group.forEach((k, v) -> {

			NoReservedOutBoundRequest request = new NoReservedOutBoundRequest();
			request.setControlInfo(new ControlInfo());

			String pdaOrderNumber = String.format("%s%s%s", retrofitClient.getAccount(),
					LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN_SHORT)),
					v.stream().findFirst().orElse(null).getCostCenter());

			// 构建请求头
			NoReservedOutBoundRequestHead requestHead = new NoReservedOutBoundRequestHead();
			requestHead.setAUFNR(TextUtils.isEmpty(getHeader().getAUFNR()) ? "" : getHeader().getAUFNR());
			requestHead.setBWART(getHeader().getBWART());
			requestHead.setGMCODE(getHeader().getGMCODE());
			requestHead.setZZLLR(getHeader().getZZLLR());
			requestHead.setNACHN(getHeader().getNACHN());
			requestHead.setORGEH(getHeader().getORGEH());
			requestHead.setORGTX(getHeader().getORGTX());
			requestHead.setDESCS(getHeader().getDESCS());
			requestHead.setPDAOUTORDER(pdaOrderNumber);

			// 处理表头操作人员信息
			requestHead.setZOPERC(retrofitClient.getAccount());
			requestHead.setZOPERN(retrofitClient.getUserName());
			requestHead.setZOPERT(LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)));

			int rowNum = 1;

			// 构建提交到接口的行信息
			List<NoReservedOutBoundRequestDetails> requestDetails = new ArrayList<>();
			for (NoReservedOutBoundDetail item : v) {

				NoReservedOutBoundRequestDetails requestDetail = new NoReservedOutBoundRequestDetails();

				requestDetail.setLineNumber(rowNum);
				requestDetail.setLGNUM(retrofitClient.getWarehouseNumber());
				requestDetail.setWERKS(retrofitClient.getFactoryCode());
				requestDetail.setLGORT(retrofitClient.getStockLocationCode());
				requestDetail.setCargoSpace(item.getCargoSpace());
				requestDetail.setMaterialDescription(item.getMaterialDescription());
				requestDetail.setMaterialCode(item.getMaterialCode());
				requestDetail.setBatchNumber(item.getBatchNumber());
				requestDetail.setQuantity(item.getQuantity());
				requestDetail.setBaseUnit(item.getBaseUnit());
				requestDetail.setMEMO(item.getMEMO());
				requestDetail.setCostCenter(item.getCostCenter());

				// 311传输过账需要设置目的地库存地
				if (getHeader().getBWART().equals("311")) {
					item.setUMLGO(getHeader().getStockLocal().getLocalCode());
				}

				requestDetails.add(requestDetail);

				rowNum += 1;
			}


			request.setHead(requestHead);
			request.setDetails(requestDetails);

			sapService.submitNoreservedOutBound(request)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeWith(new DisposableSingleObserver<NoReservedOutBoundResponse>() {
						@Override
						public void onSuccess(NoReservedOutBoundResponse noReservedOutBoundResponse) {
							noReservedOutBoundResponse.getResponseMessage().setPdaOrderNumber(pdaOrderNumber);

							getResponses().add(noReservedOutBoundResponse.getResponseMessage());
							ArrayList<SapResponseMessage> sapResponseMessages = new ArrayList<>(getResponses());
							_sapResult.setValue(sapResponseMessages);

							// 设置数据为提交成功状态
							getDetails().getValue().stream().filter(item -> item.getCostCenter().equals(k)).forEach(i -> i.setSuccess(true));
							List<NoReservedOutBoundDetail> updateDetails = new ArrayList<>(getDetails().getValue());
							_details.setValue(updateDetails);

							_isLoading.setValue(false);
						}

						@Override
						public void onError(Throwable e) {
							_isLoading.setValue(false);
							Timber.e(e);
						}
					});
		});


	}

	/**
	 * 设置移动类型
	 *
	 * @param movetype 移动类型
	 */
	public void setMovetype(MoveType movetype) {
		getHeader().setBWART(movetype.getMoveCode());
		getHeader().setGMCODE(movetype.getGmcode());
	}

	/**
	 * 设置备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		getHeader().setDESCS(remark);
	}

	/**
	 * 设置领用人信息
	 *
	 * @param employeeCode   员工编号
	 * @param EmployeeName   员工名字
	 * @param departmentCode 部门编号
	 * @param departmentName 部门名称
	 */
	public void setUseInfo(String employeeCode, String EmployeeName,
						   String departmentCode, String departmentName) {
		getHeader().setZZLLR(employeeCode);
		getHeader().setNACHN(EmployeeName);
		getHeader().setORGEH(departmentCode);
		getHeader().setORGTX(departmentName);
	}

	/**
	 * 设置内部订单号
	 *
	 * @param orderNumber 内部订单号
	 */
	public void setInternalOrder(String orderNumber) {
		getHeader().setAUFNR(orderNumber);
	}

	/**
	 * 设置311移动类型目的库存地
	 *
	 * @param stockLocal 目的库存地对象
	 */
	public void setStockLocal(StockLocal stockLocal) {
		getHeader().setStockLocal(stockLocal);
	}

	public void insert(NoReservedOutBoundDetail noReservedOutBoundDetail) {
		_details.getValue().add(noReservedOutBoundDetail);
	}

	public void delete(NoReservedOutBoundDetail noReservedOutBoundDetail) {
		_details.getValue().remove(noReservedOutBoundDetail);
	}

	public LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	public LiveData<NoReservedOutBound> getMaterialVoucher() {
		return _header;
	}

	public LiveData<List<NoReservedOutBoundDetail>> getDetails() {
		return _details;
	}

	public LiveData<List<SapResponseMessage>> getSapResult() {
		return _sapResult;
	}

	private NoReservedOutBound getHeader() {
		return _header.getValue();
	}

	private List<NoReservedOutBoundDetail> getItems() {
		return _details.getValue();
	}

	private List<SapResponseMessage> getResponses() {
		return _sapResult.getValue();
	}

}
