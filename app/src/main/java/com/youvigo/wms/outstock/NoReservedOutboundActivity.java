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
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.search.InternalOrderSearchActivity;

import timber.log.Timber;

/**
 * 无预留出库页面
 */
public class NoReservedOutboundActivity extends BaseActivity {
    public static final int REQUEST_CODE = 103;

    private ProgressBar progressBar;

    private TextView movementType, internalOrder, employer, receivingDepartment, harvest;

    private EditText orderInput, remark;

    private MaterialButton query;

    private NoReservedOutboundAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    private void initViews() {
        movementType = findViewById(R.id.tv_movement_type_description);
        internalOrder = findViewById(R.id.tv_internal_order_description);
        orderInput = findViewById(R.id.et_internal_order);
        query = findViewById(R.id.mb_query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchInternalOrderSearchActivity();
            }
        });
        FloatingActionButton newDelivery = findViewById(R.id.fab_add);
        newDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新增发货
            }
        });
        employer = findViewById(R.id.tv_employer_description);
        receivingDepartment = findViewById(R.id.tv_receiving_department_description);
        harvest = findViewById(R.id.tv_harvest_description);
        remark = findViewById(R.id.et_remark);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoReservedOutboundAdapter();
        recyclerView.setAdapter(adapter);
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
    protected void onMenuSearchClicked() {

    }
}
