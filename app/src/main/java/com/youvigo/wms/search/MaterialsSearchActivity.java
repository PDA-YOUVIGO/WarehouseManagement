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

package com.youvigo.wms.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;

import timber.log.Timber;

/**
 * 物料查询
 */
public class MaterialsSearchActivity extends BaseActivity implements MaterialSearchDialogFragment.OnMaterialSearchInforCompleted {

    private ProgressBar progressBar;
    private MaterialsSearchAdapter adapter;
    private MaterialSearchViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        observeData();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MaterialsSearchAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.d("onActivityResult");
    }

    @Override
    protected void onMenuSearchClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MaterialSearchDialogFragment.show(fragmentManager);
    }

    @Override
    protected int getLayoutId() { return R.layout.materials_search_activity; }

    /**
     * 接口查询
     * @param material_coding        物料编码
     * @param batch_number          批次号
     * @param material_description  物料描述
     * @param common_name           规格
     * @param specification         仓位
     * @param position              通用名称
     */
    @Override
    public void inputMaterialInforCompleted(String material_coding, String batch_number, String material_description, String common_name, String specification, String position) {
        Timber.d(material_coding);
        viewModel.query(material_coding, batch_number,material_description,common_name,specification, position);
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MaterialSearchViewModel.class);
        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.materials().observe(this, material-> adapter.submitList(material));
        viewModel.getQueryState().observe(this, queryState ->{
            if (queryState == null) return;
            if (!queryState.isSuccess()) {
                Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
            }
        } );
    }
}
