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

package com.youvigo.wms.warehouse;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.youvigo.wms.R;
import com.youvigo.wms.base.OnItemCompleted;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.response.Material;
import com.youvigo.wms.data.dto.response.MaterialUnit;
import com.youvigo.wms.data.entities.WarehouseInventoryModelView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WarehouseInventoryManDialogFragment extends DialogFragment {
    private static final String TAG = "WarehouseInventoryDialogFragment";
    private static final String KEY_INVENTORY = "KEY_inventory";
    private static int localtion;

    // 物料编码
    private TextView materialCoding;
    // 物料名称
    private TextView materialName;
    // 批次号
    private TextView batchNumber;
    // 供应商批次
    private TextView supplierBatch;
    // 仓位
    private TextView cargoCode;
    // 现存量
    private TextView tv_position_qty;
    // 单位
    private TextView tv_unit;
    // 凭证号
    private TextView voucher_code;
    // 主数量
    private EditText tv_on_put_qty;
    //辅数量
    private EditText tv_assist_qty;

    // 单位
    private Spinner auxiliaryUnit;

    //合箱
    private AppCompatCheckBox tv_checkout;

    private TextView tv_master_batch_code; //主批次
    private TextView tv_master_batch_qty; //主批次数量
    private TextView tv_auxiliary_batch_code; //辅批次
    private TextView tv_auxiliary_batch_qty; //辅批次数量

    // MaterialUnit中的单位名称
    private List<MaterialUnit> auxiliaryUnits = new ArrayList<>();
    private ArrayAdapter<MaterialUnit> auxiliaryUnitsAdapter;
    private Context context;
    private WarehouseInventoryModelView inventoryModekView;
    private OnItemCompleted onItemCompleted;

    /**
     * 展示详情页面
     *
     * @param data  盘点详情数据
     */
    public static void show(FragmentManager fragmentManager, WarehouseInventoryModelView data, int adapterPosition) {
        WarehouseInventoryManDialogFragment dialogFragment = new WarehouseInventoryManDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_INVENTORY, data);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragmentManager, TAG);
        localtion = adapterPosition;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        onItemCompleted = (OnItemCompleted)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DetailDialogTheme);
        if (getArguments() != null) {
            inventoryModekView = getArguments().getParcelable(KEY_INVENTORY);
        }
        initUnits(inventoryModekView.getMATNR());
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
                            materialUnit.setMseh3(inventoryModekView.getMEINS_TXT());
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
        View view = LayoutInflater.from(context).inflate(R.layout.warehouse_inventory_man_dialog_fragment, null);
        initViews(view);
        return buildDialog(view);
    }

    @NotNull
    private AlertDialog buildDialog(View view) {
        return new AlertDialog.Builder(context)
                .setTitle("盘点详情数据")
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        verify();
                        if(!verify()){return;}
                        submit();
                        onItemCompleted.itemCompleted(localtion);
                        dismiss();
                    }
                })
                .setView(view)
                .create();
    }

    /**
     * 修改提交数据
     */
    private  void submit()
    {
        inventoryModekView.setMENGA(Double.valueOf(tv_on_put_qty.getText().toString())); //主数量/盘点数量
        inventoryModekView.setADDFLAG(inventoryModekView.getMENGA() > inventoryModekView.getNUMBER() ? "X" : ""); // 盘点标识
    }

    /**
     * 数据校验
     */
    private boolean verify() {
        if (tv_on_put_qty.getText().toString().isEmpty()) {
            showMessage("请输入盘点主数量。");
            return false;
        }
        return true;
    }


    /**
     * 消息提示
     * @param message 消息
     */
    private void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 初始化界面数据
     * @param view 初始化界面
     */
    private void initViews(View view) {
        materialCoding = view.findViewById(R.id.tv_material_code_description); // 物料编码
        materialName = view.findViewById(R.id.tv_material_name_description); // 物料描述
        batchNumber = view.findViewById(R.id.tv_batch_number_description); // 批号
        supplierBatch = view.findViewById(R.id.tv_supplier_batch_description); // 供应商批次
        cargoCode = view.findViewById(R.id.tv_cargo_code_description); // 仓位
        voucher_code = view.findViewById(R.id.tv_voucher_code); // 凭证号
        tv_position_qty = view.findViewById(R.id.tv_position_qty); //现存量
        tv_on_put_qty = view.findViewById(R.id.tv_on_put_qty); //主数量
        tv_unit = view.findViewById(R.id.tv_unit); // 单位
        tv_assist_qty = view.findViewById(R.id.tv_assist_qty); // 辅数量
        auxiliaryUnit = view.findViewById(R.id.sp_auxiliary_unit);
        // 合箱
        tv_checkout = view.findViewById(R.id.tv_checkout); //合箱
        tv_master_batch_code = view.findViewById(R.id.tv_master_batch_code); //主批次
        tv_master_batch_qty = view.findViewById(R.id.tv_master_batch_qty); // 主批次数量
        tv_auxiliary_batch_code = view.findViewById(R.id.tv_auxiliary_batch_code); // 辅批次
        tv_auxiliary_batch_qty = view.findViewById(R.id.tv_auxiliary_batch_qty); // 辅批次数量

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        // TODO 待完善合箱显示
        String inventoryWay = retrofitClient.getInventoryMethod(); // 获取盘点方式
        boolean display_mergebox = retrofitClient.getDisplayMergeBox();// 获取是否显示合箱

        // 辨别盘点方式
        if (inventoryWay.equals("BrightDisk")){
            tv_position_qty.setText(String.valueOf(inventoryModekView.getNUMBER()));
        }else {
            tv_position_qty.setText("/");
        }
        materialCoding.setText(inventoryModekView.getMATNR());
        materialName.setText(inventoryModekView.getZZCOMMONNAME());
        batchNumber.setText(inventoryModekView.getCHARG());
        supplierBatch.setText(inventoryModekView.getZZLICHA());
        cargoCode.setText(inventoryModekView.getLGPLA());
        voucher_code.setText(inventoryModekView.getIVNUM());
        tv_unit.setText(inventoryModekView.getMEINS_TXT());

        if (inventoryModekView.getZZPACKAGING().equals("X") && display_mergebox){
            //合箱
            tv_checkout.setChecked(true);
            tv_master_batch_code.setText(inventoryModekView.getZZLICHA_MAIN());
            tv_master_batch_qty.setText(inventoryModekView.getZZMENGE_MAIN());
            tv_auxiliary_batch_code.setText(inventoryModekView.getZZLICHA_AUXILIARY());
            tv_auxiliary_batch_qty.setText(inventoryModekView.getZZMENGE_AUXILIARY());
        }else {
              view.findViewById(R.id.constraintLayout).setVisibility(View.GONE);
        }
        auxiliaryUnitsAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, auxiliaryUnits);
        auxiliaryUnit.setAdapter(auxiliaryUnitsAdapter);
        auxiliaryUnit.setPrompt("请选择辅计量单位");
        auxiliaryUnit.setSelection(0, true);
        actionListener(tv_on_put_qty, tv_assist_qty);
    }

    /**
     * 绑定回车监听事件
     * @param text1 主数量
     * @param text2 辅数量
     */
    private void actionListener(EditText text1,EditText text2){
        text1.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    computeAuxiliaryValue();
                }else if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    computeAuxiliaryValue();
                }else if(actionId == EditorInfo.IME_ACTION_DONE){
                    computeAuxiliaryValue();
                }else if(actionId == EditorInfo.IME_ACTION_NEXT){
                    computeAuxiliaryValue();
                }
                return false;
            }
        });
        text2.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    computeMainValue();
                }else if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    computeMainValue();
                }else if(actionId == EditorInfo.IME_ACTION_DONE){
                    computeMainValue();
                }
                else if(actionId == EditorInfo.IME_ACTION_NEXT){
                    computeMainValue();
                }
                return false;
            }
        });
    }

    /**
     * 计算辅数量
     */
    private void computeAuxiliaryValue()
    {
        MaterialUnit curUnit = (MaterialUnit)auxiliaryUnit.getSelectedItem();
        if (curUnit == null){return;}
        Double rate = Double.valueOf(curUnit.getUmrez());
        if (rate.equals(Double.valueOf("0")))
            return;
        Double value =Double.valueOf(tv_on_put_qty.getText().toString());
        Double resultVal = (double) Math.round(value / rate * 1000) / 1000;
        tv_assist_qty.setText(String.valueOf(resultVal));
    }

    /**
     * 计算主数量
     */
    private void computeMainValue()
    {
        MaterialUnit curUnit = (MaterialUnit)auxiliaryUnit.getSelectedItem();
        if (curUnit == null){return;}

        Double rate = Double.valueOf(curUnit.getUmrez());
        if (rate.equals(Double.valueOf("0")))
            return;
        Double value =Double.valueOf(tv_assist_qty.getText().toString());
        Double resultVal = (double) Math.round(value * rate * 1000) / 1000;
        tv_on_put_qty.setText(String.valueOf(resultVal));
    }


}
