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

package com.youvigo.wms.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.StoreLocationReference;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 切换库存地页面
 */
public class SwitchStockLocationActivity extends AppCompatActivity {
    private SwipeRefreshLayout refreshLayout;

    private SwitchStockLocationAdapter adapter;

    private List<StoreLocationReference> storeLocationReferenceList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        storeLocationReferenceList = intent.getParcelableArrayListExtra("data");

        setContentView(R.layout.switch_stock_location_activity);

        initViews();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setRefreshing(true);
        // 下拉刷新
        refreshLayout.setOnRefreshListener(this::refresh);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SwitchStockLocationAdapter();
        recyclerView.setAdapter(adapter);

        refresh();
    }

    private void refresh() {
        AndroidSchedulers.mainThread().scheduleDirect(() -> {
            adapter.submitList(storeLocationReferenceList);
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
