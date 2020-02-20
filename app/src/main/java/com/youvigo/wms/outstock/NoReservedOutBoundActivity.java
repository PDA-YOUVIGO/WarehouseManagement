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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.entities.MoveType;
import com.youvigo.wms.search.EmployeeSearchActivity;
import com.youvigo.wms.search.InternalOrderSearchActivity;

import java.util.List;

import retrofit2.Call;
import timber.log.Timber;

/**
 * 无预留出库页面
 */
public class NoReservedOutBoundActivity extends BaseActivity {
    public static final int REQUEST_CODE = 103;

    private ProgressBar progressBar;

    private Spinner moveType;
    private Spinner placeOfReceipt;

    private TextView internalOrder;
    private TextView employer;
    private TextView department;

    private EditText remark;

    private MaterialButton innerOrderQuery;
    private MaterialButton employeeQuery;

    private NoReservedOutBoundAdapter adapter;
    private NoreservedOutBoundViewModel viewModel;

    private ArrayAdapter<MoveType> moveTypeAdapter;
    private ArrayAdapter<?> placeOfReceiptAdapter;

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
    }

    private void initViews() {
        moveType = findViewById(R.id.sp_move_type);
        placeOfReceipt = findViewById(R.id.sp_place_of_receipt);
        internalOrder = findViewById(R.id.tv_internal_order);

        innerOrderQuery = findViewById(R.id.mb_inner_order_query);
        innerOrderQuery.setOnClickListener(v -> launchInternalOrderSearchActivity());

        FloatingActionButton newDelivery = findViewById(R.id.fab_add);
        newDelivery.setOnClickListener(v -> {
            // 新增发货
        });

        employer = findViewById(R.id.tv_employer);
        department = findViewById(R.id.tv_department);

        employeeQuery = findViewById(R.id.mb_employee_query);
        employeeQuery.setOnClickListener(v -> {
            // 查询领用人
            launchEmployeeSearchActivity();
        });

        // 收货地
        placeOfReceipt = findViewById(R.id.sp_place_of_receipt);
        remark = findViewById(R.id.et_remark);

        progressBar = findViewById(R.id.progress_bar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoReservedOutBoundAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void launchEmployeeSearchActivity() {
        Intent intent = new Intent(this, EmployeeSearchActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void launchInternalOrderSearchActivity() {
        Intent intent = new Intent(this, InternalOrderSearchActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Timber.d("onActivityResult");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.no_reserved_outbound_activity;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_submit) {
            onMenuSubmitClicked();
        }

        return true;
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(NoreservedOutBoundViewModel.class);
    }

    private void onMenuSubmitClicked() {
        // 提交
    }

    private void loadSpinnerData() {

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        BackendApi backendApi = retrofitClient.getBackendApi();

        Call<ApiResponse<List<MoveType>>> callMoveTypes = backendApi.getMoveTypes(retrofitClient.getFactoryCode());

    }
}
