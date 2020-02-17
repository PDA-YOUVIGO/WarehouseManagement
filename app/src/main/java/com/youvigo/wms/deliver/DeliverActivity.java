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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.util.Constants;

import timber.log.Timber;

/**
 * 出库下架架页面
 */
public class DeliverActivity extends BaseActivity {
    public static final String ORDER_NUMBER = "order_number";

    private EditText taskNumber;
    private TextView department;
    private TextView orderDate;

    private ProgressBar progressBar;
    private DeliverAdapter adapter;

    private DeliverViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        observeData();
    }

    private void initViews() {
        taskNumber = findViewById(R.id.et_task_number);
        department = findViewById(R.id.tv_department);
        orderDate = findViewById(R.id.tv_order_data);
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DeliverAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(DeliverViewModel.class);

        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));

        // 设置单据信息
        viewModel.getMaterials().observe(this, materialVoucher -> {
            department.setText(materialVoucher.supplierName);
            orderDate.setText(materialVoucher.date);
        });

        // 观察明细变化
        viewModel.takeOffs().observe(this, takeOffs -> {
            if (takeOffs != null && !takeOffs.isEmpty()) {
                adapter.submitList(takeOffs);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Timber.d("onActivityResult");

        if (searchResult != null) {
            viewModel.handleData(searchResult);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.take_off_activity;
    }

    @Override
    protected void onMenuSearchClicked() {
        String orderNumber = taskNumber.getText().toString();

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.CATEGORY, Constants.TYPE_DELIVER);
        intent.putExtra(ORDER_NUMBER, orderNumber);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
