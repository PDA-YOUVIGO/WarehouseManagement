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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.NoReservedOutBoundDetail;
import com.youvigo.wms.data.entities.StockMaterial;
import com.youvigo.wms.search.MaterialsSearchActivity;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

public class NoReservedOutBoundDetailDialogFragment extends DialogFragment {
    public static final int REQUEST_CODE = 105;

    private static final String TAG = "NoReservedOutBoundDetailDialogFragment";
    private static final String KEY_NO_RESERVED_OUTBOUND = "key_no_reserved_outbound";

    private static int location;
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

    private MaterialButton query;

    private Context context;

    private NoReservedOutBoundDetail noReservedOutBoundDetail;

    /**
     * 展示详情页面
     */
    public static void show(FragmentManager fragmentManager, NoReservedOutBoundDetail noReservedOutBoundDetail, int position) {
        NoReservedOutBoundDetailDialogFragment dialogFragment = new NoReservedOutBoundDetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NO_RESERVED_OUTBOUND, noReservedOutBoundDetail);
        location = position;
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
            noReservedOutBoundDetail = getArguments().getParcelable(KEY_NO_RESERVED_OUTBOUND);
            Timber.d("the position is: %s", location);
        }

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
                    }else {
                        materialCode.setText(scanResult);
                    }

                    queryMaterial();

                } else {
                    showMessage("获取扫描数据失败");
                }
            }
        };
    }

    private void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.no_reserved_outbound_detail_dialog_fragment, null);

        initViews(view);

        registerReceiver();

        return buildDialog(view);
    }

    @NotNull
    private AlertDialog buildDialog(View view) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.no_reserved_out_bound_details)
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
        materialCode = view.findViewById(R.id.et_material_code);
        materialName = view.findViewById(R.id.tv_material_name);
        batchNumber = view.findViewById(R.id.et_batch_number);
        specification = view.findViewById(R.id.tv_specification);
        supplierBatch = view.findViewById(R.id.tv_supplier_batch);
        stockQuantity = view.findViewById(R.id.tv_required_quantity_description);
        stockUnit = view.findViewById(R.id.tv_required_unit_description);
        cargoSpace = view.findViewById(R.id.tv_out_shelf_description);
        quantity = view.findViewById(R.id.tv_number_description);
        remark = view.findViewById(R.id.et_remark);
        query = view.findViewById(R.id.mb_material_query);

        query.setOnClickListener(v -> queryMaterial());
    }

    /**
     * 打开物料查询界面
     */
    private void queryMaterial() {

        String currentMaterialCode = materialCode.getText().toString();
        String currentBatchNumber = batchNumber.getText().toString();

        if (TextUtils.isEmpty(currentMaterialCode) && TextUtils.isEmpty(currentBatchNumber)) {
            showMessage("请维护你要出库的物料查询信息");
        }

        Intent intent = new Intent(context, MaterialsSearchActivity.class);
        intent.putExtra(MaterialsSearchActivity.KEY_MATERIAL_CODE, currentMaterialCode);
        intent.putExtra(MaterialsSearchActivity.KEY_BATCH_NUMBER, currentBatchNumber);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    List<StockMaterial> stockMaterial = data.getParcelableArrayListExtra(Constants.MATERIAL_SEARCH_RESULT);
                    if (stockMaterial != null && stockMaterial.size() > 0) {
                        StockMaterial material = stockMaterial.get(0);
                        batchNumber.setText(material.getBatchNumber());
                        materialCode.setText(material.getMaterialCode());
                        materialName.setText(material.getMaterialCommonName());
                        specification.setText(material.getSpecification());
                        supplierBatch.setText(material.getZZLICHA());
                        stockQuantity.setText(String.valueOf(material.getActualInventory()));
                        stockUnit.setText(material.getBaseUnitTxt());
                        cargoSpace.setText(material.getCargoSpace());
                    }
                }
            }
        }

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
