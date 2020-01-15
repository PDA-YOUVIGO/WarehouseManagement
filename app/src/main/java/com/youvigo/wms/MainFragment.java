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
import android.os.Process;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.youvigo.wms.base.BaseFragment;
import com.youvigo.wms.inventory.InventoryCheckActivity;
import com.youvigo.wms.off.TakeOffActivity;
import com.youvigo.wms.outstock.NoReservedOutboundActivity;
import com.youvigo.wms.outstock.OutOfStockActivity;
import com.youvigo.wms.product.FinishedProductsActivity;
import com.youvigo.wms.search.MaterialsSearchActivity;
import com.youvigo.wms.shelving.ShelvingActivity;
import com.youvigo.wms.warehouse.PositionMovementActivity;
import com.youvigo.wms.warehouse.WarehouseInventoryActivity;

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
                launchTakeOffActivity();
                break;
            case R.id.tv_out_of_stock:
                launchOutOfStockActivity();
                break;
            case R.id.tv_no_reserved_outbound:
                launchNoReservedOutBoundActivity();
                break;
            case R.id.tv_warehouse_inventory:
                launchWarehouseInventoryActivity();
                break;
            case R.id.tv_position_movement:
                launchPositionMovementActivity();
                break;
            case R.id.tv_inventory_check:
                launchInventoryCheckActivity();
                break;
            case R.id.tv_switch_stock_locations:
                launchSwitchLocationsActivity();
                break;
            case R.id.tv_exit:
                Process.killProcess(Process.myPid());
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

    private void launchTakeOffActivity() {
        Intent intent = new Intent(context, TakeOffActivity.class);
        startActivity(intent);
    }

    private void launchOutOfStockActivity() {
        Intent intent = new Intent(context, OutOfStockActivity.class);
        startActivity(intent);
    }

    private void launchNoReservedOutBoundActivity() {
        Intent intent = new Intent(context, NoReservedOutboundActivity.class);
        startActivity(intent);
    }

    private void launchWarehouseInventoryActivity() {
        Intent intent = new Intent(context, WarehouseInventoryActivity.class);
        startActivity(intent);
    }

    private void launchPositionMovementActivity() {
        Intent intent = new Intent(context, PositionMovementActivity.class);
        startActivity(intent);
    }

    private void launchInventoryCheckActivity() {
        Intent intent = new Intent(context, MaterialsSearchActivity.class);
        startActivity(intent);
    }

    private void launchSwitchLocationsActivity() {
        // todo switch locations
        Intent intent = new Intent(context, InventoryCheckActivity.class);
        startActivity(intent);
    }
}
