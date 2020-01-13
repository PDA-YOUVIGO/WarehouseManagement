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

package com.youvigo.wms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.youvigo.wms.base.BaseFragment;
import com.youvigo.wms.product.FinishedProductsActivity;
import com.youvigo.wms.shelving.ShelvingActivity;

public class MainFragment extends BaseFragment implements View.OnClickListener {
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tv_shelving).setOnClickListener(this);
        view.findViewById(R.id.tv_finished_products).setOnClickListener(this);
        view.findViewById(R.id.tv_take_off).setOnClickListener(this);
        view.findViewById(R.id.tv_out_of_stock).setOnClickListener(this);
        view.findViewById(R.id.tv_no_reserved_outbound).setOnClickListener(this);
        view.findViewById(R.id.tv_warehouse_inventory).setOnClickListener(this);
        view.findViewById(R.id.tv_position_movement).setOnClickListener(this);
        view.findViewById(R.id.tv_inventory_check).setOnClickListener(this);
        view.findViewById(R.id.tv_switch_stock_locations).setOnClickListener(this);
        view.findViewById(R.id.tv_exit).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shelving:
                launchShelvingActivity();
                break;
            case R.id.tv_finished_products:
                launchFinishedProductActivity();
                break;
            case R.id.tv_take_off:
                break;
            case R.id.tv_out_of_stock:
                break;
            case R.id.tv_no_reserved_outbound:
                break;
            case R.id.tv_warehouse_inventory:
                break;
            case R.id.tv_position_movement:
                break;
            case R.id.tv_inventory_check:
                break;
            case R.id.tv_switch_stock_locations:
                break;
            case R.id.tv_exit:
                break;
            default:
                break;
        }
    }

    private void launchShelvingActivity() {
        Intent intent = new Intent(context, ShelvingActivity.class);
        startActivity(intent);
    }

    private void launchFinishedProductActivity() {
        Intent intent = new Intent(context, FinishedProductsActivity.class);
        startActivity(intent);
    }
}
