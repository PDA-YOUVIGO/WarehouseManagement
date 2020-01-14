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

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.Material;
import com.youvigo.wms.product.FinishedProductsActivity;
import com.youvigo.wms.util.Constants;

import java.util.List;

import timber.log.Timber;

/**
 * 搜索页面
 */
public class SearchActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private AppCompatEditText editText;
    private SearchAdapter adapter;
    private TextView startDate;
    private TextView endDate;

    private SearchViewModel viewModel;

    // 从成品上架主页传过来的物料编码和批次号
    private String materialCoding, batchNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity);

        initViews();

        initIntent();

        observeData();
    }

    private void initIntent() {
        if (getIntent() != null) {
            String category = getIntent().getStringExtra(Constants.CATEGORY);
            if (category == null) {
                return;
            }
            if (category.equals(Constants.TYPE_PRODUCT)) {
                materialCoding = getIntent().getStringExtra(FinishedProductsActivity.MATERIAL_CODING);
                batchNumber = getIntent().getStringExtra(FinishedProductsActivity.BATCH_NUMBER);
            } else if (category.equals(Constants.TYPE_TAKE_OFF)) {

            } else if (category.equals(Constants.TYPE_OUT_OF_STOCK)) {

            }
        }
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        startDate = findViewById(R.id.tv_start);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "-" + month + "-" + dayOfMonth;
                        startDate.setText(text);
                    }
                }, 2018, 12, 1)
                        .show();
            }
        });
        endDate = findViewById(R.id.tv_end);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "-" + month + "-" + dayOfMonth;
                        endDate.setText(text);
                    }
                }, 2018, 12, 1).show();
            }
        });
        MaterialButton materialButton = findViewById(R.id.mbt_query);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                editText.clearFocus();
                if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
                    viewModel.query(startDate.getText().toString(), endDate.getText().toString(), editText.getText().toString());
                }
            }
        });
        editText = findViewById(R.id.edit_text);
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * 观察数据变化
     */
    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        viewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isActive) {
                progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.materials().observe(this, new Observer<List<Material>>() {
            @Override
            public void onChanged(List<Material> materials) {
                adapter.submitList(materials);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(Activity.RESULT_CANCELED);
        Timber.d("onBackPressed");
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
