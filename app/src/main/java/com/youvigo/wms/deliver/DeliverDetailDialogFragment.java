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

package com.youvigo.wms.deliver;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.youvigo.wms.R;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.DeliverRequest;
import com.youvigo.wms.data.dto.request.DeliverRequestHead;
import com.youvigo.wms.data.dto.request.DeliverRequestItem;
import com.youvigo.wms.data.dto.response.DeliverResponse;
import com.youvigo.wms.data.entities.TakeOff;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliverDetailDialogFragment extends DialogFragment {
    private static final String TAG = "DeliverDetailDialogFragment";
    private static final String KEY_TAKE_OFF = "key_take_off";

    private BroadcastReceiver mReceiver;

    // 物料编码
    private TextView materialCode;
    // 物料名称
    private TextView materialName;
    // 规格
    private TextView specification;
    // 批次号
    private TextView batchNumber;
    // 供应商批次
    private TextView supplierBatch;
    // 下架货位
    private TextView offShelfCargo;
    private TextView unit;
    // 下架数量
    private TextView outBoundQuanntity;

    // 物料编码（输入）
    private EditText materialCodeInput;
    // 货位码
    private EditText cargoCodeInput;

    private Context context;

    private TakeOff takeOff;

    /**
     * 展示详情页面
     *
     * @param takeOff 详情数据
     */
    public static void show(FragmentManager fragmentManager, TakeOff takeOff) {
        DeliverDetailDialogFragment dialogFragment = new DeliverDetailDialogFragment();
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

        // 扫描获取数据
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String scanResult = intent.getStringExtra("SCAN_BARCODE1");
                final String scanResultWithQrcode = intent.getStringExtra("SCAN_BARCODE2");
                final String scanStatus = intent.getStringExtra("SCAN_STATE");

                if ("ok".equals(scanStatus)) {
                    if (cargoCodeInput.hasFocus()) {
                        cargoCodeInput.setText(scanResult);
                        materialCodeInput.setFocusable(true);
                    } else if (materialCodeInput.hasFocus()) {
                        materialCodeInput.setText(scanResult);
                        cargoCodeInput.setFocusable(true);
                    }
                } else {
                    showMessage("获取扫描数据失败");
                }
            }
        };

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.take_off_detail_dialog_fragment, null);

        initViews(view);

        registerReceiver();

        return buildDialog(view);
    }

    @NotNull
    private AlertDialog buildDialog(View view) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.on_deliver_detail)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        // 点击取消时回调
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

        String currentCargoInput = cargoCodeInput.getText().toString();
        String currentCargo = offShelfCargo.getText().toString();
        String currentMaterialCode = materialCode.getText().toString();
        String currentMaterialCodeInput = materialCodeInput.getText().toString();


        if (currentCargoInput.isEmpty()) {
            showMessage("请扫描出库货位");
            return false;
        } else if (!currentCargo.equals(currentCargoInput)) {
            showMessage("下架仓位错误");
            return false;
        } else if (currentMaterialCodeInput.isEmpty()) {
            showMessage("请扫描出库物料编码");
            return false;
        } else if (!currentMaterialCode.equals(currentMaterialCodeInput)) {
            showMessage("出库物料与应出物料不一致，请检查。");
            return false;
        }

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

        DeliverRequest request = new DeliverRequest();
        request.setControlInfo(new ControlInfo());

        DeliverRequestHead requestHead = new DeliverRequestHead();
        requestHead.setLGNUM(takeOff.getLGNUM());
        requestHead.setTANUM(takeOff.getOrderNumber());
        request.setRequestHead(requestHead);

        DeliverRequestItem requestItem = new DeliverRequestItem();
        requestItem.setTAPOS(takeOff.getTAPOS());
        requestItem.setMATNR(takeOff.getMaterialCode());
        requestItem.setMAKTX(takeOff.getMaterialDescription());
        requestItem.setWERKS(takeOff.getWERKS());
        requestItem.setCHARG(takeOff.getBatchNumber());

        requestItem.setMEINS(takeOff.getBaseUnit());
        requestItem.setVSOLM(String.valueOf(takeOff.getQuantity()));
        requestItem.setALTME(takeOff.getUxiliaryUnit());
        requestItem.setVSOLA(String.valueOf(takeOff.getVSOLA()));
        requestItem.setVLTYP(takeOff.getVLTYP());
        requestItem.setVLPLA(takeOff.getVLPLA());

        requestItem.setZOPERC(retrofitClient.getAccount());
        requestItem.setZOPERN(retrofitClient.getUserName());
        requestItem.setZOPERT(LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)));

        requestItem.setZHXFLAG("");
        requestItem.setMAINBATCH("");
        requestItem.setMENGE1("");
        requestItem.setOPTBATCH("");
        requestItem.setMENGE2("");

        Additional additional = new Additional();
        additional.setAddit1("");
        additional.setAddit2("");
        additional.setAddit3("");
        additional.setAddit4("");
        additional.setAddit5("");

        requestItem.setADDITIONAL(additional);

        request.setRequestItem(requestItem);

        Call<DeliverResponse> call = sapService.submitDeliver(request);

        call.enqueue(new Callback<DeliverResponse>() {
            @Override
            public void onResponse(@NotNull Call<DeliverResponse> call, @NotNull Response<DeliverResponse> response) {
                if (response.isSuccessful()) {

                    DeliverResponse deliverResponse = response.body();
                    if (deliverResponse.getResponseMessage().getSuccess().equalsIgnoreCase("S")) {
                        showMessage(deliverResponse.getResponseMessage().getMessage());
                    } else if (deliverResponse.getResponseMessage().getSuccess().equalsIgnoreCase("E")) {
                        showMessage(deliverResponse.getResponseMessage().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<DeliverResponse> call, @NotNull Throwable t) {
                showMessage(t.getMessage());
            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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
            showMessage(e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterReceiver();
    }

    private void initViews(View view) {
        materialCode = view.findViewById(R.id.tv_material_code);
        materialName = view.findViewById(R.id.tv_material_name);
        specification = view.findViewById(R.id.tv_specification);
        batchNumber = view.findViewById(R.id.tv_batch_number);
        supplierBatch = view.findViewById(R.id.tv_supplier_batch);
        offShelfCargo = view.findViewById(R.id.tv_off_shelf_cargo);
        outBoundQuanntity = view.findViewById(R.id.tv_outbound_quantity);
        unit = view.findViewById(R.id.tv_unit);
        materialCodeInput = view.findViewById(R.id.tv_material_coding_input);
        cargoCodeInput = view.findViewById(R.id.tv_cargo_code_input);

        materialCode.setText(takeOff.getMaterialCode());
        materialName.setText(takeOff.getMaterialName());
        specification.setText(takeOff.getSpecification());
        batchNumber.setText(takeOff.getBatchNumber());
        supplierBatch.setText(takeOff.getZZLICHA());
        offShelfCargo.setText(takeOff.getVLPLA());
        outBoundQuanntity.setText(String.valueOf(takeOff.getQuantity()));
        unit.setText(takeOff.getBaseUnitDescription());

    }
}
