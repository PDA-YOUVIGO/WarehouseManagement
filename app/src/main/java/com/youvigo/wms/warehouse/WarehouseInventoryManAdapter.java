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

package com.youvigo.wms.warehouse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.WarehouseInventoryModelView;

public class WarehouseInventoryManAdapter extends ListAdapter<WarehouseInventoryModelView, WarehouseInventoryManAdapter.WarehouseInventoryManCheckVH> {

    public WarehouseInventoryManAdapter() { super(new WarehouseInventoryManDiffCallback()); }

    /**
     * 初始化界面
     */
    @NonNull
    @Override
    public WarehouseInventoryManCheckVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warehouse_inventory_man, parent, false);
        return new WarehouseInventoryManCheckVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseInventoryManCheckVH holder, int InventoryMan) {
        // 绑定数据在UI上显示
         holder.bind(getItem(InventoryMan));
    }

    /**
     * 列表item数据绑定
     */
    class WarehouseInventoryManCheckVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tv_item_position_code;

        WarehouseInventoryManCheckVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_item_position_code = itemView.findViewById(R.id.tv_item_position_code);
        }
        void bind(WarehouseInventoryModelView inventory) {
            tv_item_position_code.setText(inventory.getIVNUM());
        }

        @Override
        public void onClick(View view) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                WarehouseInventoryModelView position = getItem(getAdapterPosition());
           }
        }
    }

    static class WarehouseInventoryManDiffCallback extends DiffUtil.ItemCallback<WarehouseInventoryModelView> {
        @Override
        public boolean areItemsTheSame(@NonNull WarehouseInventoryModelView oldItem, @NonNull WarehouseInventoryModelView newItem) {
            return oldItem.getLGPLA().equals(newItem.getLGPLA());
        }

        @Override
        public boolean areContentsTheSame(@NonNull WarehouseInventoryModelView oldItem, @NonNull WarehouseInventoryModelView newItem) {
            return oldItem.getLGPLA().equals(newItem.getLGPLA());
        }
    }
}
