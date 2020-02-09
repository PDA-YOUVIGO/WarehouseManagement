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

package com.youvigo.wms.off;

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
import com.youvigo.wms.data.entities.TakeOff;

import org.jetbrains.annotations.NotNull;

public class TakeOffDetailDialogFragment extends DialogFragment {
    private static final String TAG = "TakeOffDetailDialogFragment";
    private static final String KEY_TAKE_OFF = "key_take_off";

    // 物料编码
    private TextView materialCoding;
    // 物料名称
    private TextView materialName;
    // 规格
    private TextView specification;
    // 批次号
    private TextView batchNumber;
    // 供应商批次
    private TextView supplierBatch;
    // 下架货位
    private TextView offShelf;
    // 下架数量
    private TextView numberOfRemoved;
    // 物料编码（输入）
    private TextView materialCodingInput;
    // 货位码
    private TextView cargoCode;

    private Context context;

    private TakeOff takeOff;

    /**
     * 展示详情页面
     *
     * @param takeOff 详情数据
     */
    public static void show(FragmentManager fragmentManager, TakeOff takeOff) {
        TakeOffDetailDialogFragment dialogFragment = new TakeOffDetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_TAKE_OFF, takeOff);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragmentManager, TAG);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, R.style.DetailDialogTheme);

        if (getArguments() != null) {
            takeOff = getArguments().getParcelable(KEY_TAKE_OFF);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.take_off_detail_dialog_fragment, null);

        initViews(view);

        return buildDialog(view);
    }

    @NotNull
    private AlertDialog buildDialog(View view) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.on_shelving_detail)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        // 点击取消时回调
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        // 点击确定时回调
                    }
                })
                .setView(view)
                .create();
    }

    private void initViews(View view) {
        materialCoding = view.findViewById(R.id.tv_material_coding_description);
        materialName = view.findViewById(R.id.tv_material_name_description);
        specification = view.findViewById(R.id.tv_specification_description);
        batchNumber = view.findViewById(R.id.tv_batch_number_description);
        supplierBatch = view.findViewById(R.id.tv_supplier_batch_description);
        offShelf = view.findViewById(R.id.tv_off_shelf_description);
        numberOfRemoved = view.findViewById(R.id.tv_number_of_removed_description);
        materialCodingInput = view.findViewById(R.id.tv_material_coding_input_description);
        cargoCode = view.findViewById(R.id.tv_cargo_code_description);
    }
}
