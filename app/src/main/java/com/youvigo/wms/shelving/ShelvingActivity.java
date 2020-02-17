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

package com.youvigo.wms.shelving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.util.Constants;

import timber.log.Timber;

/**
 * 入库上架页面
 */
public class ShelvingActivity extends BaseActivity {
    private ProgressBar progressBar;
    private TextView materialDocument;
    private TextView sourceUnit;
    private TextView date;
    private ShelvingAdapter adapter;

    private ShelvingViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        observeData();
    }

    private void init() {
        MaterialButton materialButton = findViewById(R.id.mbt_query);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        sourceUnit = findViewById(R.id.tv_source_unit_description);
        date = findViewById(R.id.tv_date_description);
        materialDocument = findViewById(R.id.tv_materials_description);
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShelvingAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(ShelvingViewModel.class);

        viewModel.material().observe(this, materialVoucher -> {
            if (materialVoucher != null) {
                materialDocument.setText(materialVoucher.orderNumber);
                sourceUnit.setText(materialVoucher.supplierName);
                date.setText(materialVoucher.date);
            }
        });

        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));

        viewModel.shelvings().observe(this, shelvings -> {
            if (shelvings != null && !shelvings.isEmpty()) {
                adapter.submitList(shelvings);
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
        return R.layout.shelving_activity;
    }

    @Override
    protected void onMenuSearchClicked() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.CATEGORY, Constants.TYPE_SHELVING);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
    }
}
