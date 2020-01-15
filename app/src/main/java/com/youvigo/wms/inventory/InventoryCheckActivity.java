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

package com.youvigo.wms.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.search.MaterialsSearchAdapter;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.util.Constants;

import timber.log.Timber;

/**
 * 库存查询页面
 */
public class InventoryCheckActivity extends BaseActivity {
    public static final String MATERIAL_CODING = "material_coding";
    public static final String BATCH_NUMBER = "batch_number";
    public static final String ITEM = "item";

    private EditText materialCoding, batchNumber, item, commonName, specification, position;

    private ProgressBar progressBar;
    private MaterialsSearchAdapter adapter;

    private InventoryCheckViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        observeData();
    }

    private void initViews() {
        materialCoding = findViewById(R.id.et_material_coding);
        batchNumber = findViewById(R.id.et_batch_number);
        item = findViewById(R.id.et_item);
        commonName = findViewById(R.id.et_common_name);
        specification = findViewById(R.id.et_specification);
        position = findViewById(R.id.et_position);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MaterialsSearchAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(InventoryCheckViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Timber.d("onActivityResult");
    }

    @Override
    protected void onMenuSearchClicked() {
        String coding = materialCoding.getText().toString();
        String number = batchNumber.getText().toString();
        String item = this.item.getText().toString();
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.CATEGORY, Constants.TYPE_INVENTORY_CHECK);
        intent.putExtra(MATERIAL_CODING, coding);
        intent.putExtra(ITEM, item);
        intent.putExtra(BATCH_NUMBER, number);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.inventory_check_activity;
    }
}
