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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.entities.StockMaterial;
import com.youvigo.wms.data.entities.WarehouseInventoryModelView;
import com.youvigo.wms.search.MaterialsSearchActivity;
import com.youvigo.wms.util.Constants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * 明盘
 */
public class WarehouseInventoryBrightDiskActivity extends BaseActivity implements WarehouseInventoryManDialogFragment.OnPositionInforCompleted{
    public static final int REQUEST_CODE = 200;
    public static final String KEY_CARGO_CODE = "key_cargoCode";
    public static final String KEY_VOUCHER_NUM = "key_voucherNumber";
    private ProgressBar progressBar;
    private WarehouseInventoryManAdapter adapter;
    private WarehouseInventoryManViewModel viewModel;
    private TextView voucherNumber;
    private TextView cargoCode;
    private EditText materialCode;
    @Nullable
    protected List<WarehouseInventoryModelView> inventoryResult = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        observeData();
        initIntent();
    }

    private void initViews() {
        voucherNumber = findViewById(R.id.inventory_code); //凭证号
        cargoCode = findViewById(R.id.inventory_position_code); //仓位
        materialCode = findViewById(R.id.material_code); //物料编码
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WarehouseInventoryManAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(WarehouseInventoryManViewModel.class);
        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.inventorys().observe(this, inventory -> {
            if (inventory != null && !inventory.isEmpty()) {
                adapter.submitList(inventory);
            }
        });
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String position_code = intent.getStringExtra(KEY_CARGO_CODE);
            String voucher_Number = intent.getStringExtra(KEY_VOUCHER_NUM);
            cargoCode.setText(position_code);
            voucherNumber.setText(voucher_Number);
            intent = new Intent(this, MaterialsSearchActivity.class);
            intent.putExtra(MaterialsSearchActivity.KEY_CARGOCODE, position_code);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    List<StockMaterial> stockMaterial = new ArrayList<>();
                    stockMaterial.add( data.getParcelableExtra(Constants.MATERIAL_SEARCH_RESULT));
                    processorData(inventoryResult,stockMaterial);
                    Timber.d("onActivityResult material:%s", inventoryResult.toString());
                }
            }
        }
        if (inventoryResult != null) {
            viewModel.handleData(inventoryResult);
        }
    }

    private List<WarehouseInventoryModelView> processorData(List<WarehouseInventoryModelView> inventoryResult, List<StockMaterial> StockMaterials){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        for (StockMaterial m : StockMaterials) {
            WarehouseInventoryModelView inverrtoryModelView = new WarehouseInventoryModelView();
            inverrtoryModelView.setLGNUM(retrofitClient.getWarehouseNumber()); // 仓库号
            inverrtoryModelView.setWERKS(retrofitClient.getFactoryCode()); // 工厂
            inverrtoryModelView.setLGORT(retrofitClient.getStockLocationCode()); // 库存地

            inverrtoryModelView.setIVNUM(voucherNumber.getText().toString()); // 凭证号
            inverrtoryModelView.setMATNR(m.getMaterialCode()); // 物料编码
            inverrtoryModelView.setMAKTX(m.getMaterialDescription()); // 物料描述
            inverrtoryModelView.setCHARG(m.getBatchNumber()); // 批号
            inverrtoryModelView.setMEINS(m.getBaseUnit()); // 基本单位
            inverrtoryModelView.setMEINS_TXT(m.getBaseUnitTxt()); // 基本单位文本
            inverrtoryModelView.setLGPLA(m.getCargoSpace()); // 仓位
            inverrtoryModelView.setADDFLAG(""); // 盘点标识
            inverrtoryModelView.setMENGA(0);
            inverrtoryModelView.setNUMBER(m.getActualInventory()); // 现存量
            inverrtoryModelView.setZZCOMMONNAME(m.getMaterialCommonName()); // 物料通用名称
            inverrtoryModelView.setZZLICHA(m.getZZLICHA()); // 供应商批次
            inverrtoryModelView.setBESTQ(m.getBESTQ()); // 库存类别
            // 合箱
            inverrtoryModelView.setZZPACKAGING(m.getZZPACKAGING());
            inverrtoryModelView.setZZLICHA_MAIN(m.getZZLICHA_MAIN());
            inverrtoryModelView.setZZMENGE_MAIN(m.getZZMENGE_MAIN());
            inverrtoryModelView.setZZLICHA_AUXILIARY(m.getZZLICHA_AUXILIARY());
            inverrtoryModelView.setZZMENGE_AUXILIARY(m.getZZMENGE_AUXILIARY());
            //备用字段
            Additional addotonal = new Additional();
            addotonal.setAddit1("");
            addotonal.setAddit2("");
            addotonal.setAddit3("");
            addotonal.setAddit4("");
            addotonal.setAddit5("");;
            inverrtoryModelView.setADDITIONAL(addotonal);
            inventoryResult.add(inverrtoryModelView);
        }
        return inventoryResult;
    }

    @Override
    protected int getLayoutId() { return R.layout.warehouse_inventory_brightdisk_activity; }

    /**
     * 点击事件
     * @param item menu
     * @return Boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }  else if (item.getItemId() == R.id.menu_search) {
            onMenuSearchClicked();
        }
        else if (item.getItemId() == R.id.tv_submit) {
            onMenuSubmitClicked();
        }
        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
    }

    /**
     * 提交数据
     */
    protected void onMenuSubmitClicked(){
        WarehouseInventoryCommon  common = new WarehouseInventoryCommon(this,viewModel.inventorys().getValue());
        common.inventorySubmit();
    }

    /**
     * 查询
     */
    @Override
    protected void onMenuSearchClicked() {
        String position_code = cargoCode.getText().toString();
        String mCode = materialCode.getText().toString();
        Intent intent = new Intent(this, MaterialsSearchActivity.class);
        intent.putExtra(MaterialsSearchActivity.KEY_CARGOCODE, position_code);
        intent.putExtra(MaterialsSearchActivity.KEY_MATERIAL_CODE, mCode);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 消息提示
     * @param message 消息
     */
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 更新Item
     * @param adapterPosition Item位置
     */
    @Override
    public void inputPositionInforCompleted(int adapterPosition) {
        adapter.notifyItemChanged(adapterPosition);
    }
}
