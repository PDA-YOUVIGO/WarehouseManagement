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

package com.youvigo.wms.product;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.entities.Shelving;
import com.youvigo.wms.search.SearchActivity;
import com.youvigo.wms.shelving.ShelvingAdapter;
import com.youvigo.wms.shelving.ShelvingViewModel;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.ScanManager;

import java.util.List;

import timber.log.Timber;

/**
 * 成品上架页面
 */
public class FinishedProductsActivity extends BaseActivity {
    public static final String MATERIAL_CODING = "material_coding";
    public static final String BATCH_NUMBER = "batch_number";

    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private ScanManager mManager;

    private EditText materialCoding, batchNumber;

    private ProgressBar progressBar;
    private ShelvingAdapter adapter;

    private ShelvingViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        mManager = new ScanManager(this);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Timber.i("recicve");

                // 让其他广播注册者无法获取广播信息
                this.abortBroadcast();

                final String scanResult = intent.getStringExtra("value");
                Timber.d("ScanResult ==> " + scanResult);

                if (!scanResult.isEmpty()) {
                    String[] split = scanResult.split("-");
                    materialCoding.setText(split[0]);
                    batchNumber.setText(split[1]);
                    onMenuSearchClicked();
                }
            }
        };

        if (mManager.getScannerEnable()) {
            this.sendBroadcast(new Intent("android.intent.action.SIMSCAN"));
        } else {
            showMessage("请开启扫描");
        }

        observeData();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    private void initViews() {
        materialCoding = findViewById(R.id.et_material_coding);
        batchNumber = findViewById(R.id.et_batch_number);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShelvingAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(ShelvingViewModel.class);
        viewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isActive) {
                progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.shelvings().observe(this, new Observer<List<Shelving>>() {
            @Override
            public void onChanged(List<Shelving> shelvings) {
                if (shelvings != null && !shelvings.isEmpty()) {
                    adapter.submitList(shelvings);
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
        return R.layout.finished_products_activity;
    }

    @Override
    protected void onMenuSearchClicked() {
        String code = materialCoding.getText().toString();
        String number = batchNumber.getText().toString();
        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(number)) {
            return;
        }
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.CATEGORY, Constants.TYPE_PRODUCT);
        intent.putExtra(MATERIAL_CODING, code);
        intent.putExtra(BATCH_NUMBER, number);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mFilter = new IntentFilter(Constants.BROADCAST_RESULT);

        // 在用户自行获取数据时，将广播的优先级调整到最高
        mFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

        // 注册广播来获取扫描结果
        this.registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onPause() {

        Timber.d("注销获取扫描结果的广播");

        // 注销获取扫描结果的广播
        this.unregisterReceiver(mReceiver);

        super.onPause();
    }

    @Override
    public void onDestroy() {
        mReceiver = null;
        mFilter = null;
        super.onDestroy();
    }
}
