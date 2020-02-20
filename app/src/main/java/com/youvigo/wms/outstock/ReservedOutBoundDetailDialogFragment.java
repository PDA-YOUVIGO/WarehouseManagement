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

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.base.OnItemCompleted;
import com.youvigo.wms.data.entities.ReservedOutBound;
import com.youvigo.wms.data.entities.StockMaterial;
import com.youvigo.wms.search.MaterialsSearchActivity;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class ReservedOutBoundDetailDialogFragment extends DialogFragment {
	public static final int REQUEST_CODE = 102;
	private static final String TAG = "ReservedOutBoundDetailDialogFragment";
	private static final String KEY_RESERVED_OUT_BOUND = "key_reserved_out_bound";

	private OnItemCompleted onItemCompleted;
	private static int location;

	private BroadcastReceiver mReceiver;
	// 物料编码
	private TextView materialCode;
	// 物料名称
	private TextView materialName;
	// 规格
	private TextView specification;
	// 供应商批次
	private TextView supplierBatch;
	// 批次号（输入）
	private EditText batchNumber;
	private EditText cargoSpace;

	// 需求数量
	private TextView requiredQuantity;
	private TextView basicUnit;
	private TextView storeCargoSpace;
	private EditText quantity;
	private EditText remark;

	private MaterialButton queryBtn;

	private Context context;

	private ReservedOutBound reservedOutbound;
	private ReservedOutBoundViewModel viewModel;

	/**
	 * 展示详情页面
	 */
	public static void show(FragmentManager fragmentManager, ReservedOutBound reservedOutbound, int position) {
		ReservedOutBoundDetailDialogFragment dialogFragment = new ReservedOutBoundDetailDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(KEY_RESERVED_OUT_BOUND, reservedOutbound);
		location = position;
		dialogFragment.setArguments(bundle);
		dialogFragment.show(fragmentManager, TAG);
	}

	@Override
	public void onAttach(@NonNull Activity activity) {
		super.onAttach(activity);
		onItemCompleted = (OnItemCompleted) activity;
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		this.context = context;
		if (context instanceof ReservedOutBoundActivity) {
			viewModel = new ViewModelProvider((ReservedOutBoundActivity) context).get(ReservedOutBoundViewModel.class);
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setStyle(DialogFragment.STYLE_NORMAL, R.style.DetailDialogTheme);

		if (getArguments() != null) {
			reservedOutbound = getArguments().getParcelable(KEY_RESERVED_OUT_BOUND);

			Timber.d("the position is: %s", location);

		}

		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String scanResult = intent.getStringExtra("SCAN_BARCODE1");
				final String scanResultWithQrcode = intent.getStringExtra("SCAN_BARCODE2");
				final String scanStatus = intent.getStringExtra("SCAN_STATE");

				if ("ok".equals(scanStatus)) {
					cargoSpace.setText(scanResult);
					quantity.setFocusable(true);
				} else {
					showMessage("获取扫描数据失败");
				}
			}
		};
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(context).inflate(R.layout.reserved_out_bound_detail_dialog_fragment, null);

		initViews(view);

		registerReceiver();

		return buildDialog(view);
	}

	@NotNull
	private AlertDialog buildDialog(View view) {
		return new AlertDialog.Builder(context).setTitle(R.string.reserved_out_bound_detail)
				.setNegativeButton(R.string.cancel, (dialog1, which) -> {
					dismiss();
				})
				.setPositiveButton("确认", (dialog1, which) -> {
					confirm();
					onItemCompleted.itemCompleted(location);
					dismiss();
				}).setView(view).create();
	}

	private void showMessage(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * 数据确认
	 */
	private void confirm() {
		String quantityValue = quantity.getText().toString();
		String batchNumberValue = batchNumber.getText().toString();

		String remarkValue = remark.getText().toString();
		String cargoSpaceValue = cargoSpace.getText().toString();

		// 逻辑判断
		if (cargoSpaceValue.isEmpty()) {
			showMessage("请扫描下架仓位");
			return;
		} else if (!reservedOutbound.getVerificationBatchNumber().equals(batchNumberValue)) {
			showMessage("批次数据不正确");
			return;
		} else if (!Utils.isNumericzidal(quantityValue)) {
			showMessage("数量格式不正确");
			return;
		} else if (reservedOutbound.getActualInventory() < Double.parseDouble(quantityValue)) {
			showMessage("出库数量不能大于库存数量");
			return;
		}

		reservedOutbound.setQuantity(Double.parseDouble(quantityValue));
		reservedOutbound.setBatchNumber(batchNumberValue);
		reservedOutbound.setMEMO(remarkValue);
		reservedOutbound.setCargoSpace(cargoSpaceValue);

	}


	private void initViews(View view) {
		materialCode = view.findViewById(R.id.tv_material_code);
		materialName = view.findViewById(R.id.tv_material_name);
		specification = view.findViewById(R.id.tv_specification);
		batchNumber = view.findViewById(R.id.et_batch_number);
		supplierBatch = view.findViewById(R.id.tv_supplier_batch);
		queryBtn = view.findViewById(R.id.mb_query);
		requiredQuantity = view.findViewById(R.id.tv_required_quantity_description);
		basicUnit = view.findViewById(R.id.tv_required_unit_description);
		storeCargoSpace = view.findViewById(R.id.tv_position_description);
		cargoSpace = view.findViewById(R.id.tv_out_shelf_description);
		quantity = view.findViewById(R.id.tv_number_description);
		remark = view.findViewById(R.id.et_remark);

		// 启动通用物料查询页面
		queryBtn.setOnClickListener(v -> {

			String currentBatchNumber = batchNumber.getText().toString();

			Intent intent = new Intent(context, MaterialsSearchActivity.class);
			intent.putExtra(MaterialsSearchActivity.KEY_MATERIAL_CODE, reservedOutbound.getMaterialCode());
			intent.putExtra(MaterialsSearchActivity.KEY_BATCH_NUMBER, currentBatchNumber);
			startActivityForResult(intent, REQUEST_CODE);
		});

		materialCode.setText(reservedOutbound.getMaterialCode());
		materialName.setText(reservedOutbound.getMaterialName());
		batchNumber.setText(reservedOutbound.getBatchNumber());
		specification.setText(reservedOutbound.getSpecification());
		cargoSpace.setText(reservedOutbound.getCargoSpace());
		supplierBatch.setText(reservedOutbound.getZZLICHA());
		requiredQuantity.setText(reservedOutbound.getBDMNG());
		basicUnit.setText(reservedOutbound.getBaseUnitTxt());
		storeCargoSpace.setText(reservedOutbound.getCargoSpace());

		if (reservedOutbound.getQuantity() > 0) {
			quantity.setText(String.valueOf(reservedOutbound.getQuantity()));
		}

		remark.setText(reservedOutbound.getMEMO());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

		if (requestCode == REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					StockMaterial stockMaterial = data.getParcelableExtra(Constants.MATERIAL_SEARCH_RESULT);
					if (stockMaterial != null) {
						storeCargoSpace.setText(stockMaterial.getCargoSpace());
						batchNumber.setText(stockMaterial.getBatchNumber());
						reservedOutbound.setVerificationCargoSpace(stockMaterial.getCargoSpace());
						reservedOutbound.setActualInventory(stockMaterial.getActualInventory());
						reservedOutbound.setVerificationBatchNumber(stockMaterial.getBatchNumber());
					}
				}
			}
		}

		//super.onActivityResult(requestCode, resultCode, data);
	}

	private void registerReceiver() {
		IntentFilter mFilter = new IntentFilter(Constants.BROADCAST_RESULT);
		getContext().registerReceiver(mReceiver, mFilter);
	}

	private void unRegisterReceiver() {
		try {
			getContext().unregisterReceiver(mReceiver);
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		unRegisterReceiver();
	}

}
