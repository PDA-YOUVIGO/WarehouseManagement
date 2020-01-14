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

package com.youvigo.wms.off;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.entities.TakeOff;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.util.Constants;

import java.util.List;

import timber.log.Timber;

/**
 * 出库下架架页面
 */
public class TakeOffActivity extends BaseActivity {
    public static final String TASK_NUMBER = "task_number";
    public static final String RECEIVING_DEPARTMENT = "receiving_department";
    public static final String DOCUMENT_DATE = "document_date";

    private EditText taskNumber, receivingDepartment, documentDate;

    private ProgressBar progressBar;
    private TakeOffAdapter adapter;

    private TakeOffViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        observeData();
    }

    private void initViews() {
        taskNumber = findViewById(R.id.et_task_number);
        receivingDepartment = findViewById(R.id.et_receiving_department);
        documentDate = findViewById(R.id.et_document_date);
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TakeOffAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(TakeOffViewModel.class);
        viewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isActive) {
                progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.takeOffs().observe(this, new Observer<List<TakeOff>>() {
            @Override
            public void onChanged(List<TakeOff> takeOffs) {
                if (takeOffs != null && !takeOffs.isEmpty()) {
                    adapter.submitList(takeOffs);
                }
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
        String task = taskNumber.getText().toString();
        String department = receivingDepartment.getText().toString();
        String date = documentDate.getText().toString();
        if (TextUtils.isEmpty(task) || TextUtils.isEmpty(department) || TextUtils.isEmpty(date)) {
            return;
        }
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.CATEGORY, Constants.TYPE_TAKE_OFF);
        intent.putExtra(TASK_NUMBER, task);
        intent.putExtra(RECEIVING_DEPARTMENT, department);
        intent.putExtra(DOCUMENT_DATE, date);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
