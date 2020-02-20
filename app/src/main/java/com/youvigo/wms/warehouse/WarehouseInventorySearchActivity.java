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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 仓库盘点页面
 */
public class WarehouseInventorySearchActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView startDate;
    private TextView endDate;
    private WarehouseInventorySearchAdapter adapter;
    private EditText voucherNumber;
    private WarehouseInventorySearchViewModel viewModel;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warehouse_inventory_search_activity);
        init();
        initViews();
        observeData();
        initIntent();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        voucherNumber = findViewById(R.id.edit_text);
        LocalDate localDate = LocalDate.now();
        startDate = findViewById(R.id.tv_start);
        startDate.setText(localDate.toString());
        startDate.setOnClickListener(v -> new DatePickerDialog(WarehouseInventorySearchActivity.this, (view, year, month, dayOfMonth) -> {
            startDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString());
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).show());

        endDate = findViewById(R.id.tv_end);
        endDate.setText(localDate.toString());
        endDate.setOnClickListener(v -> new DatePickerDialog(WarehouseInventorySearchActivity.this, (view, year, month, dayOfMonth) -> {
            endDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString());
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).show());

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WarehouseInventorySearchAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(WarehouseInventorySearchViewModel.class);
        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.inventory().observe(this, inventory -> {
            adapter.submitList(inventory);
            adapter.notifyDataSetChanged();
        });
        // 显示查询结果信息
        viewModel.getQueryState().observe(this, queryState -> {
            if (queryState == null) return;
            if (!queryState.isSuccess()) {
                Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initIntent() {
        voucherNumber.setText(getIntent().getStringExtra(WarehouseInventoryActivity.VOUCHER_NUMBER));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
        return true;
    }

    /**
     * 查询
     */
    protected void onMenuSearchClicked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
        LocalDate startDate = LocalDate.parse(this.startDate.getText().toString());
        LocalDate endDate = LocalDate.parse(this.endDate.getText().toString());
        viewModel.query(startDate.format(dateTimeFormatter), endDate.format(dateTimeFormatter), voucherNumber.getText() == null ? "" : voucherNumber.getText().toString());
    }

}