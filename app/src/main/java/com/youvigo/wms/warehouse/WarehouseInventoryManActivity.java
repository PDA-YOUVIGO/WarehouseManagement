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
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.dto.response.WarehouseInventoryQueryResponseDetails;
import com.youvigo.wms.util.Constants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class WarehouseInventoryManActivity extends BaseActivity {
    private ProgressBar progressBar;
    private WarehouseInventoryAdapter adapter;
    private WarehouseInventoryViewModel viewModel;
    private EditText voucherNumber;
    @Nullable
    protected List<WarehouseInventoryQueryResponseDetails> inventoryDetailsResult = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        observeData();
    }

    private void initViews() {
        voucherNumber = findViewById(R.id.edit_text); //凭证号
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WarehouseInventoryAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(WarehouseInventoryViewModel.class);
        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.inventoryDetails().observe(this, inventoryDetails -> {
            if (inventoryDetails != null && !inventoryDetails.isEmpty()) {
                adapter.submitList(inventoryDetails);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    inventoryDetailsResult = data.getParcelableArrayListExtra(Constants.WAREHOUSE_INVENTORY_DETAILS_RESULT);
                    voucherNumber.setText(data.getStringExtra(Constants.WAREHOUSE_INVENTORY_RESULT));
                    Timber.d("onActivityResult material:%s", inventoryDetailsResult.toString());
                }
            }
        }
        if (inventoryDetailsResult != null) {
            viewModel.handleData(inventoryDetailsResult);
        }

    }

    @Override
    protected int getLayoutId() { return R.layout.warehouse_inventory_activity; }

    @Override
    protected void onMenuSearchClicked() {
//        String voucherNumber = this.voucherNumber.getText().toString();
//        Intent intent = new Intent(this, WarehouseInventorySearchActivity.class);
//        intent.putExtra(Constants.CATEGORY, Constants.TYPE_WAREHOUSE_INVENTORY);
//        intent.putExtra(VOUCHER_NUMBER, voucherNumber);
//        startActivityForResult(intent, REQUEST_CODE);
    }
}
