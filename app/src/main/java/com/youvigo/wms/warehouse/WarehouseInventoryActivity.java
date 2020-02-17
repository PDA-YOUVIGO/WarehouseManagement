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

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;

import java.time.LocalDate;

/**
 * 仓库盘点页面
 */
public class WarehouseInventoryActivity extends BaseActivity {

    private ProgressBar progressBar;
    private TextView startDate;
    private TextView endDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        LocalDate localDate = LocalDate.now();
        startDate = findViewById(R.id.tv_start);
        startDate.setText(localDate.toString());
        startDate.setOnClickListener(v -> new DatePickerDialog(WarehouseInventoryActivity.this, (view, year, month, dayOfMonth) -> {
            startDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString());
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).show());

        endDate = findViewById(R.id.tv_end);
        endDate.setText(localDate.toString());
        endDate.setOnClickListener(v -> new DatePickerDialog(WarehouseInventoryActivity.this, (view, year, month, dayOfMonth) -> {
            endDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString());
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).show());

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new SearchAdapter();
//        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.warehouse_inventory_activity;
    }

    @Override
    protected void onMenuSearchClicked() {

    }
}
