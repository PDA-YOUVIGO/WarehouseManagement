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

package com.youvigo.wms.outstock;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.util.Constants;

import timber.log.Timber;

/**
 * 有预留出库页面
 */
public class OutOfStockActivity extends BaseActivity {
    public static final String DOCUMENT_NUMBER = "document_number";

    private EditText documentNumber;

    private ProgressBar progressBar;

    private TextView movementType, costCenter, internalOrder, employer, receivingDepartment, harvest, remark;

    private OutOfStockAdapter adapter;

    private OutOfStockViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        observeData();
    }

    private void initViews() {
        documentNumber = findViewById(R.id.tv_document_number_description);
        movementType = findViewById(R.id.tv_movement_type_description);
        costCenter = findViewById(R.id.tv_cost_center_description);
        internalOrder = findViewById(R.id.tv_internal_order_description);
        employer = findViewById(R.id.tv_employer_description);
        receivingDepartment = findViewById(R.id.tv_receiving_department_description);
        harvest = findViewById(R.id.tv_harvest_description);
        remark = findViewById(R.id.tv_remark_description);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OutOfStockAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(OutOfStockViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Timber.d("onActivityResult");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.out_of_stock_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_in) {
            // 拣货
        } else if (item.getItemId() == R.id.menu_out) {
            // 出库
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.out_of_stock_activity;
    }

    @Override
    protected void onMenuSearchClicked() {
        String number = documentNumber.getText().toString();
        if (TextUtils.isEmpty(number)) {
            return;
        }
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.CATEGORY, Constants.TYPE_OUT_OF_STOCK);
        intent.putExtra(DOCUMENT_NUMBER, number);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
