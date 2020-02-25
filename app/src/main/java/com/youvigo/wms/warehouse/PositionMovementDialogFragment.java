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
import com.youvigo.wms.base.OnItemCompleted;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.response.CargoLocation;
import com.youvigo.wms.data.entities.PositionMovementModelView;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionMovementDialogFragment extends DialogFragment {
    private static final String TAG = "PositionMovementDialogFragment";
    private static final String KEY_position = "KEY_position";
    private static int location;

    private BroadcastReceiver mReceiver;
    // 上架货位
    private EditText tv_on_put_space;
    //上架类型
    private TextView tv_put_position_type;
    // 上架数量
    private EditText tv_put_position_main_qty;
    private Context context;
    private PositionMovementModelView position;
    private OnItemCompleted onItemCompleted;

    /**
     * 展示详情页面
     *
     * @param position  仓位移动详情数据
     */
    public static void show(FragmentManager fragmentManager, PositionMovementModelView position, int adapterPosition) {
        PositionMovementDialogFragment dialogFragment = new PositionMovementDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_position, position);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragmentManager, TAG);
        location = adapterPosition;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        onItemCompleted = (OnItemCompleted) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DetailDialogTheme);
        if (getArguments() != null) {
            position = getArguments().getParcelable(KEY_position);
        }
        initReceiver();
    }

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
                    if (tv_on_put_space.hasFocus()){
                        tv_on_put_space.setText(scanResult);
                        getCargoLocation();
                    }
                } else {
                    showAlertDialog("扫描结果","获取扫描数据失败","确定");
                }
            }
        };
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.position_movement_dialog_fragment, null);
        initViews(view);
        registerReceiver();
        return buildDialog(view);
    }

    @NotNull
    private AlertDialog buildDialog(View view) {
        return new AlertDialog.Builder(context)
                .setTitle("仓位移动详情")
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        if(verify())
                        {
                            submit();
                            onItemCompleted.itemCompleted(location);
                            dismiss();
                        }
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
        position.setVSOLM(tv_put_position_main_qty.getText().toString()); //上架数量
        position.setNLPLA(tv_on_put_space.getText().toString()); // 上架货位
        position.setNLTYP(tv_put_position_type.getText().toString()); // 上架货位类型
    }

    /**
     * 数据校验
     */
    private boolean verify() {
        if (tv_on_put_space.getText().toString().isEmpty()) {
            showMessage("请输入上架货位");
            return false;
        } else if (tv_put_position_type.getText().toString().isEmpty()) {
            showMessage("请维护上架货位类型");
            return false;
        } else if (Double.parseDouble(tv_put_position_main_qty.getText().toString()) > position.getVERME()) {
            showMessage("上架数量不能大于可用量");
            return false;
        } else if (tv_put_position_main_qty.getText().toString().isEmpty()) {
            showMessage("请输入上架数量");
            return false;
        }
        return true;
    }

    /**
     * 初始化界面数据
     * @param view 初始化界面
     */
    private void initViews(View view) {
        // 物料编码
        TextView materialCoding = view.findViewById(R.id.tv_material_code_description); // 物料编码
        // 物料名称
        TextView materialName = view.findViewById(R.id.tv_material_name_description); // 物料名称
        // 批次号
        TextView batchNumber = view.findViewById(R.id.tv_batch_number_description); // 批号
        // 规格
        TextView specification = view.findViewById(R.id.tv_specification_description); // 规格
        // 供应商批次
        TextView supplierBatch = view.findViewById(R.id.tv_supplier_batch_description); // 供应商批次
        // 下架货位
        TextView cargoCode = view.findViewById(R.id.tv_cargo_code_description); // 下架货位
        // 可用量
        TextView tv_position_qty = view.findViewById(R.id.tv_position_qty); // 可用量
        tv_on_put_space = view.findViewById(R.id.tv_on_put_space); //上架货位
        tv_put_position_type = view.findViewById(R.id.tv_on_put_qty_type); //上架货位类型
        tv_put_position_main_qty = view.findViewById(R.id.tv_put_position_main_qty); // 上架数量
        // 单位
        TextView tv_unit = view.findViewById(R.id.sp_main_unit); // 上架单位

        materialCoding.setText(position.getMATNR());
        materialName.setText(position.getZZCOMMONNAME());
        batchNumber.setText(position.getCHARG());
        specification.setText(position.getZZDRUGSPEC());
        supplierBatch.setText(position.getZZLICHA());
        cargoCode.setText(position.getVLPLA());
        tv_position_qty.setText(String.valueOf(position.getVERME()));
        tv_on_put_space.setText(position.getNLPLA());
        tv_put_position_type.setText(position.getNLTYP());
        tv_put_position_main_qty.setText(position.getVSOLM());
        tv_unit.setText(position.getMEINS_TXT());
    }

    private void getCargoLocation(){
        // 仓位类型查询
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        BackendApi backendApi = retrofitClient.getBackendApi();
        Call<ApiResponse<List<CargoLocation>>> cargoResponse = backendApi.verificationCargo(retrofitClient.getWarehouseNumber(),tv_on_put_space.getText().toString());
        cargoResponse.enqueue(new Callback<ApiResponse<List<CargoLocation>>>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse<List<CargoLocation>>> call, @NotNull Response<ApiResponse<List<CargoLocation>>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<List<CargoLocation>> onPositionResponse = response.body();
                    List<CargoLocation> responseDetails = onPositionResponse.getData();
                    if (responseDetails.size()>0) {
                        tv_put_position_type.setText(responseDetails.get(0).getLgtyp());
                    } else {
                        tv_on_put_space.setText("");
                        showAlertDialog("处理结果","未能正确获取仓位信息","确定");
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<ApiResponse<List<CargoLocation>>> call, @NotNull Throwable t) {
                showAlertDialog("错误信息",t.getMessage(),"确定");
            }
        });
    }

    private void showMessage(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
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
