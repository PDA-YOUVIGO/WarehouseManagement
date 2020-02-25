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

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.youvigo.wms.R;
import com.youvigo.wms.base.OnCommonSearchCompleted;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MaterialSearchDialogFragment extends DialogFragment {
	public static final int REQUEST_CODE = 102;
	private static final String TAG = "materialSearchDialogFragment";
	private View view;
	private Context context;
	private OnCommonSearchCompleted SearchCompleted;
	private EditText materialCode;
	private EditText batchNumber;
	private EditText materialDescription;
	private EditText materialCommonName;
	private EditText specification;
	private EditText cargoSpace;
	private BroadcastReceiver mReceiver;

	/**
	 * 初始化扫描程序
	 */
	private void initReceiver(){
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final String scanResult = intent.getStringExtra("SCAN_BARCODE1");
				final String scanResultWithQrcode = intent.getStringExtra("SCAN_BARCODE2");
				final String scanStatus = intent.getStringExtra("SCAN_STATE");
				if ("ok".equals(scanStatus)) {
					if (materialCode.hasFocus()){
						materialCode.setText(scanResult);
					}else if (batchNumber.hasFocus()){
						batchNumber.setText(scanResult);
					}else if (cargoSpace.hasFocus()){
						cargoSpace.setText(scanResult);
					}
				}
			}
		};
	}

	/**
	 * Dialog Show
	 */
	public static void show(FragmentManager fragmentManager) {
		MaterialSearchDialogFragment dialogFragment = new MaterialSearchDialogFragment();
		Bundle bundle = new Bundle();
		dialogFragment.setArguments(bundle);
		dialogFragment.show(fragmentManager, TAG);
	}

	private void initView(){
		materialCode = view.findViewById(R.id.et_material_coding);
		batchNumber = view.findViewById(R.id.et_batch_number);
		materialDescription = view.findViewById(R.id.et_item);
		materialCommonName = view.findViewById(R.id.et_common_name);
		specification = view.findViewById(R.id.et_specification);
		cargoSpace = view.findViewById(R.id.et_position);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.DetailDialogTheme);
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		this.context = context;
		SearchCompleted = (OnCommonSearchCompleted) context;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		view = LayoutInflater.from(context).inflate(R.layout.material_search_dialog_fragment, null);
		initView();
		initReceiver();
		registerReceiver();
		return buildDialog(view);
	}

	@NotNull
	private AlertDialog buildDialog(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.material_detail);
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog1, int which) {
				// 点击取消时回调
				dismiss();
			}
		});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog1, int which) {
				SearchCompleted.materialParameterForCompleted(materialCode.getText() == null ? "" :
								materialCode.getText().toString(), batchNumber.getText() == null ? "" :
								batchNumber.getText().toString(), materialDescription.getText() == null ? "" :
								materialDescription.getText().toString(), materialCommonName.getText() == null ? "" :
								materialCommonName.getText().toString(), specification.getText() == null ? "" :
								specification.getText().toString(), cargoSpace.getText() == null ? "" :
								cargoSpace.getText().toString(),
						new ArrayList<>(), "");
				dismiss();
			}
		});
		builder.setView(view);
		return builder.create();
	}

	private void showAlertDialog(String title, String message,String displayName) {
		AlertDialog normalDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message)
				.setNegativeButton("关闭", (dialog, which) -> {
					dialog.dismiss();
				}).setNeutralButton(displayName, (dialog, which) -> {
				}).create();
		normalDialog.show();
	}

	private void registerReceiver()
	{
		IntentFilter mFilter= new IntentFilter(Constants.BROADCAST_RESULT);
		getContext().registerReceiver(mReceiver, mFilter);
	}

	private void unRegisterReceiver()
	{
		try {
			getContext().unregisterReceiver(mReceiver);
		} catch (Exception e) {
			showAlertDialog("错误信息",e.getMessage(),"确定");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		unRegisterReceiver();
	}

}
