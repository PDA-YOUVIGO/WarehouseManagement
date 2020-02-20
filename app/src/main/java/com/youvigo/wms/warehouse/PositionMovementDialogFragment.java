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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.response.CargoLocation;
import com.youvigo.wms.data.entities.PositionMovementModelView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionMovementDialogFragment extends DialogFragment {
    private static final String TAG = "PositionMovementDialogFragment";
    private static final String KEY_position = "KEY_position";
    private static int localtion;

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
    // 下架货位
    private TextView cargoCode;
    // 可用量
    private TextView tv_position_qty;
    // 单位
    private TextView tv_unit;
    // 上架货位
    private EditText tv_put_position_number;
    //上架类型
    private TextView tv_put_position_type;
    // 上架数量
    private EditText tv_put_position_main_qty;
    private Context context;
    private PositionMovementModelView position;
    private OnPositionInforCompleted mOnPositionInforCompleted;
    private MaterialButton mb_query;


    public interface OnPositionInforCompleted {
        void inputPositionInforCompleted(int adapterPosition);
    }

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
        localtion = adapterPosition;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        mOnPositionInforCompleted = (OnPositionInforCompleted) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DetailDialogTheme);
        if (getArguments() != null) {
            position = getArguments().getParcelable(KEY_position);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.position_movement_dialog_fragment, null);
        initViews(view);
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
                        if(!verify()){return;}
                        submit();
                        mOnPositionInforCompleted.inputPositionInforCompleted(localtion);
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
        position.setVSOLM(tv_put_position_main_qty.getText().toString()); //上架数量
        position.setNLPLA(tv_put_position_number.getText().toString()); // 上架货位
        position.setNLTYP(tv_put_position_type.getText().toString()); // 上架货位类型
    }

    /**
     * 数据校验
     */
    private boolean verify() {
        if (tv_put_position_number.getText().toString().isEmpty()) {
            showMessage("请输入上架货位。");
            return false;
        } else if (tv_put_position_type.getText().toString().isEmpty()) {
            showMessage("请维护上架货位类型");
            return false;
        } else if (Double.parseDouble(tv_put_position_main_qty.getText().toString()) > position.getVERME()) {
            showMessage("上架数量不能大于可用量。");
            return false;
        } else if (tv_put_position_main_qty.getText().toString().isEmpty()) {
            showMessage("请输入上架数量");
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
        specification = view.findViewById(R.id.tv_specification_description); // 规格
        supplierBatch = view.findViewById(R.id.tv_supplier_batch_description); // 供应商批次
        cargoCode = view.findViewById(R.id.tv_cargo_code_description); // 下架货位
        tv_position_qty = view.findViewById(R.id.tv_position_qty); // 可用量
        tv_put_position_number = view.findViewById(R.id.tv_on_put_qty); //上架货位
        tv_put_position_type = view.findViewById(R.id.tv_on_put_qty_type); //上架货位类型
        tv_put_position_main_qty = view.findViewById(R.id.tv_put_position_main_qty); // 上架数量
        tv_unit = view.findViewById(R.id.sp_main_unit); // 上架单位

        materialCoding.setText(position.getMATNR());
        materialName.setText(position.getMAKTX());
        batchNumber.setText(position.getCHARG());
        specification.setText(position.getZZDRUGSPEC());
        supplierBatch.setText(position.getZZLICHA());
        cargoCode.setText(position.getVLPLA());
        tv_position_qty.setText(String.valueOf(position.getVERME()));
        tv_put_position_number.setText(position.getNLPLA());
        tv_put_position_type.setText(position.getNLTYP());
        tv_put_position_main_qty.setText(position.getVSOLM());
        tv_unit.setText(position.getMEINS());

        tv_put_position_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    getCargoLocation();
                }
            }
        });
    }

    private void getCargoLocation(){
        // 仓位类型查询
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        BackendApi backendApi = retrofitClient.getBackendApi();
        Call<ApiResponse<List<CargoLocation>>> cargoResponse = backendApi.verificationCargo(retrofitClient.getWarehouseNumber(),tv_put_position_number.getText().toString());
        cargoResponse.enqueue(new Callback<ApiResponse<List<CargoLocation>>>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse<List<CargoLocation>>> call, @NotNull Response<ApiResponse<List<CargoLocation>>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<List<CargoLocation>> onPositionResponse = response.body();
                    List<CargoLocation> responseDetails = onPositionResponse.getData();
                    if (responseDetails.size()>0) {
                        tv_put_position_type.setText(responseDetails.get(0).getLgtyp());
                    } else {
                        tv_put_position_number.setText("");
                        showMessage("未能正确获取仓位信息");
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<ApiResponse<List<CargoLocation>>> call, @NotNull Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }
}
