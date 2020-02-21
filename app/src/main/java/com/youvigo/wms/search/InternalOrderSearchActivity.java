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

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.entities.OrderType;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 内部订单查询页面
 */
public class InternalOrderSearchActivity extends BaseActivity {

    private EditText et_order_number;
    private Spinner sp_order_type;

    private ProgressBar progressBar;

    private InternalOrderSearchAdapter adapter;
    private InternalOrderViewModel viewModel;
    private ArrayAdapter<OrderType> orderTypeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        loadOrderType();
        observeData();
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(InternalOrderViewModel.class);

        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));

        viewModel.resultState().observe(this, queryState -> {
            if (queryState == null) return;
            if (!queryState.isSuccess()) {
                Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        viewModel.internalOrders().observe(this, internalOrders -> adapter.submitList(internalOrders));
    }

    private void initViews() {
        et_order_number = findViewById(R.id.et_inner_order);
        sp_order_type = findViewById(R.id.sp_order_type);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new InternalOrderSearchAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.internal_order_search_activity;
    }

    @Override
    protected void onMenuSearchClicked() {
        // 提交数据到后端进行查询
        String currentOrderNumber = et_order_number.getText().toString();
        OrderType orderType = (OrderType) sp_order_type.getSelectedItem();
        viewModel.queryInternalOrder(currentOrderNumber, orderType.getTypeCode());
    }

    @SuppressLint("CheckResult")
    private void loadOrderType() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        BackendApi backendApi = retrofitClient.getBackendApi();

        backendApi.getOrderType(retrofitClient.getFactoryCode())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponse<List<OrderType>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<OrderType>> listApiResponse) {
                        orderTypeAdapter = new ArrayAdapter<>(getApplication(), R.layout.item_spinner,
                                listApiResponse.getData());
                        sp_order_type.setAdapter(orderTypeAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMessage(String.format("单据类型数据加载失败，%s", e.getMessage()));
                    }
                });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
