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

package com.youvigo.wms.shelving;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.youvigo.wms.R;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.OnShevingRequest;
import com.youvigo.wms.data.dto.request.OnShevingRequestDetails;
import com.youvigo.wms.data.dto.response.Material;
import com.youvigo.wms.data.dto.response.MaterialUnit;
import com.youvigo.wms.data.dto.response.OnShevlingResponse;
import com.youvigo.wms.data.dto.response.OnShevlingResponseDetails;
import com.youvigo.wms.data.entities.Shelving;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShelvingDetailDialogFragment extends DialogFragment {
    private static final String TAG = "ShelvingDetailDialogFragment";
    private static final String KEY_SHELVING = "key_shelving";

    private BroadcastReceiver mReceiver;

    // 物料编码
    private TextView materialCoding;
    // 物料名称
    private TextView materialName;
    // 批次号
    private TextView batchNumber;
    // 规格
    private TextView specification;
    // 供应商批次
    private TextView supplierBatch;
    // 货位码
    private TextView cargoCode;
    // 未上架
    private TextView notOnShelvesQuantity, notOnShelvesUnit;
    // 已上架
    private TextView onShelveQuantity, OnShelvesUnit;
    // 当前上架
    private EditText onShelvesMainQuantity, onShelvesAuxiliayQuantity;

    // 单位
    private Spinner auxiliaryUnit;
    private TextView mainUnit;

    private Context context;

    private Shelving shelving;

    // MaterialUnit中的单位名称
    private List<MaterialUnit> auxiliaryUnits = new ArrayList<>();
    private ArrayAdapter<MaterialUnit> auxiliaryUnitsAdapter;

    /**
     * 展示详情页面
     *
     * @param shelving 详情数据
     */
    public static void show(FragmentManager fragmentManager, Shelving shelving) {
        ShelvingDetailDialogFragment dialogFragment = new ShelvingDetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SHELVING, shelving);
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
            shelving = getArguments().getParcelable(KEY_SHELVING);
        }

        initUnits(shelving.getMaterialNumber());

        // 扫描获取数据
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                this.abortBroadcast();
                final String scanResult = intent.getStringExtra("SCAN_BARCODE1");
                final String scanResultWithQrcode = intent.getStringExtra("SCAN_BARCODE2");
                final String scanStatus = intent.getStringExtra("SCAN_STATE");

                if ("ok".equals(scanStatus)) {
                    cargoCode.setText(scanResult);
                } else {
                    showMessage("获取扫描数据失败");
                }
            }
        };
    }

    /**
     * 获取物料档案
     *
     * @param materialCode 物料编码
     */
    private void initUnits(String materialCode) {

        BackendApi backendApi = RetrofitClient.getInstance().getBackendApi();
        Call<ApiResponse<List<Material>>> call = backendApi.getMaterial(materialCode);

        call.enqueue(new Callback<ApiResponse<List<Material>>>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse<List<Material>>> call, @NotNull Response<ApiResponse<List<Material>>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<List<Material>> result = response.body();
                    List<Material> materials = result.getData();

                    if (materials != null && materials.size() > 0) {
                        Material material = materials.get(0);
                        List<MaterialUnit> units = material.getUnits();
                        if (units.isEmpty()) {
                            MaterialUnit materialUnit = new MaterialUnit();
                            materialUnit.setMseh3(shelving.getAuxiliaryUnitTxt());
                            auxiliaryUnits.add(materialUnit);
                        } else {
                            auxiliaryUnits.addAll(units);
                        }
                    }
                    auxiliaryUnitsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponse<List<Material>>> call, @NotNull Throwable t) {

            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.sheling_detail_dialog_fragment, null);

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

                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        submit();
                    }
                })
                .setView(view)
                .create();
    }

    /**
     * 数据校验
     */
    private boolean verify() {

        if (onShelvesMainQuantity.getText().toString().isEmpty()) {
            showMessage("请输入上架数量。");
            return false;
        } else if (cargoCode.getText().toString().isEmpty()) {
            showMessage("请维护上架货位");
            return false;
        } else if (Double.parseDouble(onShelvesMainQuantity.getText().toString()) > shelving.getBasicQuantity()) {
            showMessage("上架数量不能大于未上架数量。");
            return false;
        } else if (shelving.getPackaging().equalsIgnoreCase("X") && Double.parseDouble(onShelvesMainQuantity.getText().toString()) != shelving.getBasicQuantity()) {
            showMessage("合箱不允许拆分上架");
            return false;
        }

        // TODO 混批检查

        return true;
    }

    /**
     * 提交到SAP
     */
    private void submit() {

        if (!verify()) {
            return;
        }

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        SapService sapService = retrofitClient.getSapService();

        OnShevingRequest request = new OnShevingRequest();
        request.setControlInfo(new ControlInfo());
        OnShevingRequestDetails requestItem = new OnShevingRequestDetails();

        requestItem.setLGNUM(retrofitClient.getWarehouseNumber());
        requestItem.setWERKS(retrofitClient.getFactoryCode());
        requestItem.setZOPERC(retrofitClient.getAccount());
        requestItem.setZOPERN(retrofitClient.getUserName());
        requestItem.setZOPERT(LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)));

        requestItem.setBWLVS(shelving.getMoveType());
        requestItem.setMBLNR(shelving.getMaterialVoucherCode());
        requestItem.setZEILE(shelving.getVoucherLineNumber());
        requestItem.setMATNR(shelving.getMaterialNumber());
        requestItem.setMAKTX(shelving.getMaterialDescription());
        requestItem.setCHARG(shelving.getBatchNumber());
        requestItem.setMEINS(shelving.getBaseUnit());
        // 基本单位数量
        requestItem.setOFMEA(shelving.getBasicQuantity());
        requestItem.setTBNUM(shelving.getTbnum());
        requestItem.setTBPOS(shelving.getTbpos());
        // 货位
        requestItem.setNLPLA(cargoCode.getText().toString());
        requestItem.setZZLICHA_MAIN(shelving.getMainBatchNumber());
        requestItem.setZZMENGE_MAIN(shelving.getMainBatchNumberQuantity());
        requestItem.setZZLICHA_AUXILIARY(shelving.getAuxiliaryBatchNumber());
        requestItem.setZZMENGE_AUXILIARY(shelving.getAuxiliaryBatchNumberQuantity());
        requestItem.setZZPACKAGING(shelving.getPackaging());
        request.setDetails(requestItem);

        Call<OnShevlingResponse> onShevlingResponseCall = sapService.submitOnSheving(request);
        onShevlingResponseCall.enqueue(new Callback<OnShevlingResponse>() {
            @Override
            public void onResponse(@NotNull Call<OnShevlingResponse> call, @NotNull Response<OnShevlingResponse> response) {
                if (response.isSuccessful()) {
                    OnShevlingResponse onShevlingResponse = response.body();
                    OnShevlingResponseDetails responseDetails = onShevlingResponse.getResponseDetails();

                    double basicQuantity = shelving.getBasicQuantity();
                    Double currentOnShelveQuantity = Double.valueOf(onShelvesMainQuantity.getText().toString());

                    if (responseDetails.getMSGTYPE().equalsIgnoreCase("E")) {
                        showMessage(responseDetails.getMSGTXT());
                    } else if (responseDetails.getMSGTYPE().equalsIgnoreCase("S")) {
                        double currentNotOnshlveQuantity = basicQuantity - currentOnShelveQuantity;
                        shelving.setNotOnShelvesQuantity(currentNotOnshlveQuantity);
                        notOnShelvesQuantity.setText(String.valueOf(currentNotOnshlveQuantity));
                        onShelveQuantity.setText(onShelveQuantity.getText());

                        showMessage(responseDetails.getMSGTXT());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<OnShevlingResponse> call, @NotNull Throwable t) {
                showMessage(t.getMessage());
            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private void initViews(View view) {
        materialCoding = view.findViewById(R.id.tv_material_coding_description);
        materialName = view.findViewById(R.id.tv_material_name_description);
        batchNumber = view.findViewById(R.id.tv_batch_number_description);
        specification = view.findViewById(R.id.tv_specification_description);
        supplierBatch = view.findViewById(R.id.tv_supplier_batch_description);
        cargoCode = view.findViewById(R.id.tv_cargo_code_description);
        notOnShelvesQuantity = view.findViewById(R.id.tv_not_on_shelves_quantity);
        notOnShelvesUnit = view.findViewById(R.id.tv_not_on_shelves_unit);
        onShelveQuantity = view.findViewById(R.id.tv_on_shelve_quantity);
        OnShelvesUnit = view.findViewById(R.id.tv_on_shelves_unit);
        onShelvesMainQuantity = view.findViewById(R.id.tv_on_shelves_main_quantity);
        mainUnit = view.findViewById(R.id.sp_main_unit);
        onShelvesAuxiliayQuantity = view.findViewById(R.id.tv_on_shelves_auxiliay_quantity);
        auxiliaryUnit = view.findViewById(R.id.sp_auxiliary_unit);

        materialCoding.setText(shelving.getMaterialNumber());
        materialName.setText(shelving.getMaterialDescription());
        batchNumber.setText(shelving.getBatchNumber());
        specification.setText(shelving.getSpecifications());
        supplierBatch.setText(shelving.getSupplierBatchNumber());
        notOnShelvesUnit.setText(shelving.getBaseUnitTxt());
        OnShelvesUnit.setText(shelving.getBaseUnitTxt());
        mainUnit.setText(shelving.getBaseUnitTxt());
        notOnShelvesQuantity.setText(String.valueOf(shelving.getBasicQuantity()));

        //mainUnitsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, mainUnits);
        //mainUnitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //mainUnit.setAdapter(mainUnitsAdapter);

        auxiliaryUnitsAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, auxiliaryUnits);
        auxiliaryUnit.setAdapter(auxiliaryUnitsAdapter);
        auxiliaryUnit.setPrompt("请选择辅计量单位");
        auxiliaryUnit.setSelection(0, true);
    }

    private void registerReceiver()
    {
        IntentFilter mFilter= new IntentFilter(Constants.BROADCAST_RESULT);
        context.registerReceiver(mReceiver, mFilter);
    }

    private void unRegisterReceiver()
    {
        try {
            context.unregisterReceiver(mReceiver);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }


}
