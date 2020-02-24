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
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.youvigo.wms.R;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.AssessmentCategoriesRequest;
import com.youvigo.wms.data.dto.request.AssessmentCategoriesRequestDetails;
import com.youvigo.wms.data.dto.response.AssessmentCategoriesResponse;
import com.youvigo.wms.data.dto.response.AssessmentCategoriesResponseDetail;
import com.youvigo.wms.data.dto.response.CostCenter;
import com.youvigo.wms.data.entities.NoReservedOutBoundDetail;
import com.youvigo.wms.data.entities.StockMaterial;
import com.youvigo.wms.search.MaterialsSearchActivity;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NoReservedOutBoundDetailActivity extends AppCompatActivity {

	public static final int REQUEST_CODE = 105;
	public static final String NO_RESERVED_OUT_BOUND_DETAIL_RESULT = "no_reserved_out_bound_detail_result";
	public static final String KEY_EMPLOYEE_CODE = "key_employee_code";
	public static final String KEY_DEPARTMENT = "key_department";
	public static final String KEY_MOVETYPE_CODE = "key_movetype_code";

	private String employee_code;
	private String department_code;
	private String movetype_code;

	private BroadcastReceiver mReceiver;

	// 物料编码
	private EditText materialCode;

	// 批次号（输入）
	private EditText batchNumber;

	private EditText cargoSpace;

	private EditText quantity;
	private EditText remark;

	// 物料名称
	private TextView materialName;
	// 规格
	private TextView specification;
	// 供应商批次
	private TextView supplierBatch;

	private TextView stockQuantity;
	private TextView stockUnit;

	private NoReservedOutBoundDetail noReservedOutBoundDetail;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_submit_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_no_reserved_out_bound_detail);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		Intent intent = getIntent();
		employee_code = intent.getStringExtra(KEY_EMPLOYEE_CODE);
		department_code = intent.getStringExtra(KEY_DEPARTMENT);
		movetype_code = intent.getStringExtra(KEY_MOVETYPE_CODE);

		initViews();
		initScan();
	}

	private void initViews() {
		materialCode = findViewById(R.id.et_material_code);
		materialName = findViewById(R.id.tv_material_name);
		batchNumber = findViewById(R.id.et_batch_number);
		specification = findViewById(R.id.tv_specification);
		supplierBatch = findViewById(R.id.tv_supplier_batch);
		stockQuantity = findViewById(R.id.tv_required_quantity_description);
		stockUnit = findViewById(R.id.tv_required_unit_description);
		cargoSpace = findViewById(R.id.tv_out_shelf_description);
		quantity = findViewById(R.id.tv_number_description);
		remark = findViewById(R.id.et_remark);
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

	private void onMenuSubmitClicked() {
		confirm();
	}

	/**
	 * 点击搜索
	 */
	private void onMenuSearchClicked() {

		if (TextUtils.isEmpty(materialCode.getText().toString())
				&& TextUtils.isEmpty(batchNumber.getText().toString())) {
			showMessage("请维护物料编码或批次查询条件");
			return;
		}

		String currentMaterialCode = materialCode.getText().toString();
		String currentBatchNumber = batchNumber.getText().toString();

		if (TextUtils.isEmpty(currentMaterialCode) && TextUtils.isEmpty(currentBatchNumber)) {
			showMessage("请维护你要出库的物料查询信息");
		}

		Intent intent = new Intent(this, MaterialsSearchActivity.class);
		intent.putExtra(MaterialsSearchActivity.KEY_MATERIAL_CODE, currentMaterialCode);
		intent.putExtra(MaterialsSearchActivity.KEY_BATCH_NUMBER, currentBatchNumber);
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

		if (requestCode == REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					List<StockMaterial> stockMaterial =
							data.getParcelableArrayListExtra(Constants.MATERIAL_SEARCH_RESULT);
					if (stockMaterial != null && stockMaterial.size() > 0) {
						StockMaterial material = stockMaterial.get(0);

						noReservedOutBoundDetail = new NoReservedOutBoundDetail();
						noReservedOutBoundDetail.setStockQuantity(material.getActualInventory());
						noReservedOutBoundDetail.setMaterialCode(material.getMaterialCode());
						noReservedOutBoundDetail.setMaterialDescription(material.getMaterialDescription());
						noReservedOutBoundDetail.setBatchNumber(material.getBatchNumber());
						noReservedOutBoundDetail.setStockCargoSpace(material.getCargoSpace());
						noReservedOutBoundDetail.setSpecification(material.getSpecification());
						noReservedOutBoundDetail.setWERKS(material.getFactoryCode());
						noReservedOutBoundDetail.setLGORT(material.getInventoryLocation());
						noReservedOutBoundDetail.setSupplieBatch(material.getZZLICHA());
						noReservedOutBoundDetail.setStockUnit(material.getBaseUnitTxt());

						batchNumber.setText(material.getBatchNumber());
						materialCode.setText(material.getMaterialCode());
						materialName.setText(material.getMaterialCommonName());
						specification.setText(material.getSpecification());
						supplierBatch.setText(material.getZZLICHA());
						stockQuantity.setText(String.valueOf(material.getActualInventory()));
						stockUnit.setText(material.getBaseUnitTxt());
						cargoSpace.setText(material.getCargoSpace());

						quantity.setFocusable(true);
						quantity.requestFocus();

						proceeAssessmentCategories(material.getMaterialCode());
					}
				}
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void initScan() {
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String scanResult = intent.getStringExtra("SCAN_BARCODE1");
				final String scanResultWithQrcode = intent.getStringExtra("SCAN_BARCODE2");
				final String scanStatus = intent.getStringExtra("SCAN_STATE");

				if ("ok".equals(scanStatus)) {
					if (scanResult.contains("-")) {
						String[] strings = scanResult.split("-");
						materialCode.setText(strings[0]);
						batchNumber.setText(strings[1]);
					} else if (materialCode.hasFocus()) {
						materialCode.setText(scanResult);
					} else if (batchNumber.hasFocus()) {
						batchNumber.setText(scanResult);
					} else if (cargoSpace.hasFocus()) {
						cargoSpace.setText(scanResult);
					} else {
						materialCode.setText(scanResult);
					}

					onMenuSearchClicked();

				} else {
					showMessage("获取扫描数据失败");
				}
			}
		};
		registerReceiver();
	}

	private boolean verification() {
		boolean result = true;

		try {
			double currentQuantity = Double.parseDouble(quantity.getText().toString());
			if (TextUtils.isEmpty(materialCode.getText().toString()) &&
					TextUtils.isEmpty(batchNumber.getText().toString())
					&& TextUtils.isEmpty(cargoSpace.getText().toString())
					&& TextUtils.isEmpty(quantity.getText().toString())) {
				showMessage("你还有未维护的数据，请补充。");
				result = false;
			} else if (currentQuantity > noReservedOutBoundDetail.getStockQuantity()) {
				showMessage("出库数量大于当前库存可用量。");
				result = false;
			} else if (TextUtils.isEmpty(noReservedOutBoundDetail.getCostCenter())) {
				showMessage("物料成本中心未获取");
				result = false;
			}
		} catch (NumberFormatException e) {
			showMessage("出库数量不正确。");
			result = false;
		}

		return result;
	}

	private void confirm() {

		if (!verification()) return;

		double currentQuantity = Double.parseDouble(quantity.getText().toString());

		noReservedOutBoundDetail.setQuantity(currentQuantity);
		noReservedOutBoundDetail.setMEMO(remark.getText().toString());
		noReservedOutBoundDetail.setCargoSpace(cargoSpace.getText().toString());
		noReservedOutBoundDetail.setMaterialCode(materialCode.getText().toString());
		noReservedOutBoundDetail.setBatchNumber(batchNumber.getText().toString());

		Intent intent = new Intent();
		intent.putExtra(NO_RESERVED_OUT_BOUND_DETAIL_RESULT, noReservedOutBoundDetail);

		setResult(RESULT_OK, intent);
		finish();
	}

	/**
	 * 获取及处理物料评估类
	 *
	 * @param materialCode 物料编码
	 */
	@SuppressLint("CheckResult")
	private void proceeAssessmentCategories(String materialCode) {
		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		SapService sapService = retrofitClient.getSapService();

		AssessmentCategoriesRequest request = new AssessmentCategoriesRequest();
		request.setControlInfo(new ControlInfo());

		AssessmentCategoriesRequestDetails requestDetails =
				new AssessmentCategoriesRequestDetails();
		requestDetails.setMaterialCode(materialCode);
		requestDetails.setFactoryCode(retrofitClient.getFactoryCode());
		requestDetails.setADDITIONAL1("");
		requestDetails.setADDITIONAL2("");

		request.setDetails(requestDetails);

		sapService.queryAssessmentCategories(request)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<AssessmentCategoriesResponse>() {
					@Override
					public void onSuccess(AssessmentCategoriesResponse assessmentCategoriesResponse) {

						AssessmentCategoriesResponseDetail detail =
								assessmentCategoriesResponse.getResponseRoot().getDetails()
										.stream()
										.findFirst()
										.orElse(null);

						if (detail != null) {
							processCostCenter(detail.getKOSTL());
						} else {
							Utils.showDialog(NoReservedOutBoundDetailActivity.this,
									"错误",
									"获取物料评估类失败，是否重新获取？",
									(dialog, which) -> proceeAssessmentCategories(materialCode));
						}
					}

					@Override
					public void onError(Throwable e) {
						Utils.showDialog(NoReservedOutBoundDetailActivity.this,
								"错误",
								"获取物料评估类失败，是否重新获取？" + "\n" + e.getMessage(),
								(dialog, which) -> proceeAssessmentCategories(materialCode));
					}
				});
	}

	/**
	 * @param kostl 评估类
	 */
	@SuppressLint("CheckResult")
	private void processCostCenter(String kostl) {

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		BackendApi backendApi = retrofitClient.getBackendApi();

		backendApi.queryCostCenter(department_code, retrofitClient.getFactoryCode(), movetype_code, kostl)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<CostCenter>>() {
					@Override
					public void onSuccess(ApiResponse<CostCenter> costCenterApiResponse) {
						CostCenter costCenter = costCenterApiResponse.getData();
						if (costCenterApiResponse.getCode() != 200) {
							Utils.showToast(NoReservedOutBoundDetailActivity.this, costCenterApiResponse.getMessage());
							return;
						}
						noReservedOutBoundDetail.setCostCenter(costCenter.getKOSTL());
						noReservedOutBoundDetail.setCostCenterDescription(costCenter.getKTEXT());
					}

					@Override
					public void onError(Throwable e) {
						Utils.showDialog(NoReservedOutBoundDetailActivity.this,
								"错误",
								"获取成本中心失败，是否重新获取？" + "\n" + e.getMessage(),
								(dialog, which) -> processCostCenter(kostl));
					}
				});
	}

	private void registerReceiver() {
		IntentFilter mFilter = new IntentFilter(Constants.BROADCAST_RESULT);
		this.registerReceiver(mReceiver, mFilter);
	}

	private void unRegisterReceiver() {
		try {
			this.unregisterReceiver(mReceiver);
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
	}

	@Override
	protected void onDestroy() {
		unRegisterReceiver();
		super.onDestroy();
	}

	private void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}


}
