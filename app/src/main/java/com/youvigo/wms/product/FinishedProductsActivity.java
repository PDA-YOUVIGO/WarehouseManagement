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

package com.youvigo.wms.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;

import timber.log.Timber;

/**
 * 成品上架页面
 */
public class FinishedProductsActivity extends BaseActivity {
    public static final String MATERIAL_CODING = "material_coding";
    public static final String BATCH_NUMBER = "batch_number";

    private EditText materialCoding, batchNumber;

    private ProgressBar progressBar;

    private FinishedProductsAdapter adapter;
    private FinishedProductsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        observeData();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void initViews() {
        materialCoding = findViewById(R.id.et_material_coding);
        batchNumber = findViewById(R.id.et_batch_number);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FinishedProductsAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * 观察数据变化
     */
    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(FinishedProductsViewModel.class);

        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.shelves().observe(this, materials-> adapter.submitList(materials));

        viewModel.getQueryState().observe(this, queryState ->{
            if (queryState == null) return;
            if (!queryState.isSuccess()) {
                Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
            }
        } );
    }

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
    //
    //    Timber.d("onActivityResult");
    //
    //    if (searchResult != null) {
    //        viewModel.handleData(searchResult);
    //    }
    //}

    @Override
    protected int getLayoutId() {
        return R.layout.finished_products_activity;
    }

    /**
     * 点击查询
     */
    @Override
    protected void onMenuSearchClicked() {
        String code = materialCoding.getText().toString();
        String number = batchNumber.getText().toString();

        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(number)) {
            showMessage("请维护查询参数");
            return;
        }

        viewModel.query(code, number);
        //String code = materialCoding.getText().toString();
        //String number = batchNumber.getText().toString();
        //if (TextUtils.isEmpty(code) || TextUtils.isEmpty(number)) {
        //    return;
        //}
        //Intent intent = new Intent(this, SearchActivity.class);
        //intent.putExtra(Constants.CATEGORY, Constants.TYPE_PRODUCT);
        //intent.putExtra(MATERIAL_CODING, code);
        //intent.putExtra(BATCH_NUMBER, number);
        //startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Timber.d("FinishedProducts onActivityResult");
    }
}
