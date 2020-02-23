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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.youvigo.wms.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MaterialSearchDialogFragment extends DialogFragment {
	public static final int REQUEST_CODE = 102;
	private static final String TAG = "materialSearchDialogFragment";
	private View view;
	private Context context;
	private OnMaterialSearchInforCompleted mOnMaterialSearchInforCompleted;
	private TextView materialCode;
	private TextView batchNumber;
	private TextView materialDescription;
	private TextView materialCommonName;
	private TextView specification;
	private TextView cargoSpace;

	/**
	 * Dialog Show
	 */
	public static void show(FragmentManager fragmentManager) {
		MaterialSearchDialogFragment dialogFragment = new MaterialSearchDialogFragment();
		Bundle bundle = new Bundle();
		dialogFragment.setArguments(bundle);
		dialogFragment.show(fragmentManager, TAG);
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
	}

	@Override
	public void onAttach(@NotNull Activity activity) {
		super.onAttach(activity);
		mOnMaterialSearchInforCompleted = (OnMaterialSearchInforCompleted) activity;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		view = LayoutInflater.from(context).inflate(R.layout.material_search_dialog_fragment, null);
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
				materialCode = view.findViewById(R.id.et_material_coding);
				batchNumber = view.findViewById(R.id.et_batch_number);
				materialDescription = view.findViewById(R.id.et_item);
				materialCommonName = view.findViewById(R.id.et_common_name);
				specification = view.findViewById(R.id.et_specification);
				cargoSpace = view.findViewById(R.id.et_position);
				mOnMaterialSearchInforCompleted.inputMaterialInforCompleted(materialCode.getText() == null ? "" :
								materialCode.getText().toString(), batchNumber.getText() == null ? "" :
								batchNumber.getText().toString(), materialDescription.getText() == null ? "" :
								materialDescription.getText().toString(), materialCommonName.getText() == null ? "" :
								materialCommonName.getText().toString(), specification.getText() == null ? "" :
								specification.getText().toString(), cargoSpace.getText() == null ? "" :
								cargoSpace.getText().toString(),
						null, null);
				dismiss();
			}
		});
		builder.setView(view);
		return builder.create();
	}

	public interface OnMaterialSearchInforCompleted {
		void inputMaterialInforCompleted(String materialCode, String batchNumber, String materialDescription,
										 String materialCommonName, String specification, String cargoCode,
										 List<String> materilaFilter, String cargoSpaceTypeFilter);
	}
}
