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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.base.OnItemCompleted;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.ReservedOutBoundRequest;
import com.youvigo.wms.data.dto.request.ReservedOutBoundRequestDetails;
import com.youvigo.wms.data.dto.request.ReservedOutBoundRequestHead;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.MoveType;
import com.youvigo.wms.data.entities.ReservedOutBound;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 有预留出库页面
 */
public class ReservedOutBoundActivity extends BaseActivity implements OnItemCompleted {
	public static final String ORDER_NUMBER = "order_number";
	public static final String FILTER_MOVETYPES = "filter_movetypes";

	private EditText orderNumber;
	private EditText remark;

	private ProgressBar progressBar;

	private Spinner moveType;
	private TextView internalOrder;
	private TextView employer;
	private TextView department;

	private ReservedOutBoundAdapter adapter;

	private List<MoveType> moveTypes = new ArrayList<>();
	private ArrayAdapter<MoveType> moveTypeAdapter;

	private ReservedOutBoundViewModel viewModel;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_submit_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViews();

		loadMoveType();

		observeData();
	}

	private void initViews() {
		orderNumber = findViewById(R.id.tv_document_number_description);
		moveType = findViewById(R.id.sp_move_type_description);
		internalOrder = findViewById(R.id.tv_internal_order_description);
		employer = findViewById(R.id.tv_employer_description);
		department = findViewById(R.id.tv_receiving_department_description);
		remark = findViewById(R.id.et_remark);

		progressBar = findViewById(R.id.progress_bar);
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new ReservedOutBoundAdapter();
		recyclerView.setAdapter(adapter);

		moveType.setEnabled(false);

		moveTypeAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, moveTypes);
		moveType.setAdapter(moveTypeAdapter);
	}

	/**
	 * 观察数据变化
	 */
	private void observeData() {
		viewModel = new ViewModelProvider.NewInstanceFactory().create(ReservedOutBoundViewModel.class);

		viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE :
				View.GONE));

		viewModel.getMaterialVoucher().observe(this, materialVoucher -> {
			orderNumber.setText(materialVoucher.orderNumber);
			internalOrder.setText(materialVoucher.innerOrderDescription);

			MoveType moveType =
					moveTypes.stream().filter(v -> v.getMoveCode().equals(materialVoucher.moveType)).findFirst().orElse(null);

			this.moveType.setSelection(moveTypes.indexOf(moveType));

			employer.setText(materialVoucher.employerName);
			department.setText(materialVoucher.departmentName);
			remark.setText(materialVoucher.memo);
		});

		viewModel.getMaterialVoucherItems().observe(this, reservedOutbounds -> {
			if (reservedOutbounds != null && !reservedOutbounds.isEmpty()) {
				adapter.submitList(reservedOutbounds);
			}
		});

		// TODO 打印逻辑
		viewModel.getSapResult().observe(this, sapResponseMessage -> {
			if (sapResponseMessage.getSuccess().equals("S")) {
				Utils.showDialog(ReservedOutBoundActivity.this,
						"返回结果",
						"确认",
						sapResponseMessage.getMessage(), (dialog, which) -> dialog.dismiss());
			} else {
				Toast.makeText(this, sapResponseMessage.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
	}

	private void submit() {
		RetrofitClient retrofitClient = RetrofitClient.getInstance();

		MaterialVoucher materialVoucher = viewModel.getMaterialVoucher().getValue();
		MoveType selectMoveType = (MoveType) moveType.getSelectedItem();
		String currentRemark = remark.getText().toString();

		String pdaOrderNumber = String.format("%s-%s", retrofitClient.getAccount(),
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

		ReservedOutBoundRequest request = new ReservedOutBoundRequest();
		request.setControlInfo(new ControlInfo());

		ReservedOutBoundRequestHead requestHead = new ReservedOutBoundRequestHead();
		requestHead.setZZLLR(materialVoucher.employerCode);
		requestHead.setBWART(materialVoucher.moveType);
		requestHead.setPDAOUTORDER(pdaOrderNumber);
		requestHead.setGMCODE(selectMoveType.getGmcode());
		requestHead.setNACHN(materialVoucher.employerName);
		requestHead.setORGEH(materialVoucher.departmentCode);
		requestHead.setORGTX(materialVoucher.departmentName);
		requestHead.setDESCS(currentRemark);
		requestHead.setZOPERC(retrofitClient.getAccount());
		requestHead.setZOPERN(retrofitClient.getUserName());
		requestHead.setZOPERT(LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)));

		List<ReservedOutBoundRequestDetails> requestDetails = new ArrayList<>();
		for (ReservedOutBound reservedOutbound : materialVoucher.reservedOutBounds) {
			ReservedOutBoundRequestDetails details = new ReservedOutBoundRequestDetails();
			details.setBDTER(reservedOutbound.getBDTER());
			details.setRSNUM(reservedOutbound.getRSNUM());
			details.setRSPOS(reservedOutbound.getRSPOS());
			details.setWERKS(retrofitClient.getFactoryCode());
			details.setLGORT(retrofitClient.getStockLocationCode());
			details.setLGNUM(retrofitClient.getWarehouseNumber());
			details.setLGPLA(reservedOutbound.getCargoSpace());
			details.setMATNR(reservedOutbound.getMaterialCode());
			details.setCHARG(reservedOutbound.getBatchNumber());
			details.setBDMNG(String.valueOf(reservedOutbound.getQuantity()));
			details.setMEINS(reservedOutbound.getBaseUnit());
			details.setUMLGO(reservedOutbound.getUMLGO());
			details.setMEMO(reservedOutbound.getMEMO());
			requestDetails.add(details);
		}

		request.setRequestHead(requestHead);
		request.setRequestDetails(requestDetails);

		viewModel.submit(request);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (searchResult != null) {
			viewModel.handleDate(searchResult);
		}

		Timber.d("onActivityResult");
	}

	@Override
	protected int getLayoutId() {
		return R.layout.reserved_out_bound_activity;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		} else if (item.getItemId() == R.id.menu_search) {
			onMenuSearchClicked();
		} else if (item.getItemId() == R.id.menu_submit) {
			onMenuSubmitClicked();
		}

		return true;
	}

	protected void onMenuSubmitClicked() {
		submit();
	}

	@Override
	protected void onMenuSearchClicked() {

		String number = orderNumber.getText().toString();
		List<String> filter_movetypes = moveTypes.stream().map(MoveType::getMoveCode).collect(Collectors.toList());

		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra(Constants.CATEGORY, Constants.TYPE_RESERVED_OUT_BOUND);
		intent.putExtra(ORDER_NUMBER, number);
		intent.putExtra(FILTER_MOVETYPES, new ArrayList<>(filter_movetypes));
		startActivityForResult(intent, REQUEST_CODE);
	}

	/**
	 * 加载库存地
	 */
	@SuppressLint("CheckResult")
	private void loadMoveType() {
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		BackendApi backendApi = retrofitClient.getBackendApi();

		backendApi.getMoveTypes(retrofitClient.getFactoryCode())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<List<MoveType>>>() {
					@Override
					public void onSuccess(ApiResponse<List<MoveType>> listApiResponse) {
						if (listApiResponse != null) {
							moveTypes.clear();
							moveTypes.addAll(listApiResponse.getData());
							moveTypeAdapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onError(Throwable e) {
						showMessage(String.format("移动类型数据加载失败，%s", e.getMessage()));
					}
				});

	}

	private void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void itemCompleted(int adapterPosition) {
		adapter.notifyItemChanged(adapterPosition);
	}
}
