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

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.NoReservedOutbound;
import com.youvigo.wms.search.MaterialsSearchActivity;

import org.jetbrains.annotations.NotNull;

public class NoReservedOutboundDetailDialogFragment extends DialogFragment {
    public static final int REQUEST_CODE = 105;

    private static final String TAG = "NoReservedOutboundDetailDialogFragment";
    private static final String KEY_NO_RESERVED_OUTBOUND = "key_no_reserved_outbound";

    // 物料编码
    private TextView materialCoding;
    // 物料名称
    private TextView materialName;
    // 规格
    private TextView specification;
    // 供应商批次
    private TextView supplierBatch;
    // 批次号（输入）
    private EditText batchNumber;

    private MaterialButton query;

    private Context context;

    private NoReservedOutbound noReservedOutbound;

    /**
     * 展示详情页面
     */
    public static void show(FragmentManager fragmentManager) {
        ReservedOutBoundDetailDialogFragment dialogFragment = new ReservedOutBoundDetailDialogFragment();
        Bundle bundle = new Bundle();
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
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.no_reserved_outbound_detail_dialog_fragment, null);

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
                .setPositiveButton("出库", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        // 点击确定时回调
                        // 出库
                    }
                })
                .setView(view)
                .create();
    }

    private void initViews(View view) {
        materialCoding = view.findViewById(R.id.tv_material_code);
        materialName = view.findViewById(R.id.tv_material_name);
        specification = view.findViewById(R.id.tv_specification);
        batchNumber = view.findViewById(R.id.et_batch_number);
        supplierBatch = view.findViewById(R.id.tv_supplier_batch);
        query = view.findViewById(R.id.mb_query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动通用物料查询页面
                Intent intent = new Intent(context, MaterialsSearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}
