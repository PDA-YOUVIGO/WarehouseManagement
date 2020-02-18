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
import com.youvigo.wms.data.dto.response.WarehouseInventoryQueryResponseDetails;

public class WarehouseInventoryAdapter extends ListAdapter<WarehouseInventoryQueryResponseDetails, WarehouseInventoryAdapter.WarehouseInventoryCheckVH> {

    public WarehouseInventoryAdapter() { super(new WarehouseInventoryDiffCallback()); }

    /**
     * 初始化界面
     */
    @NonNull
    @Override
    public WarehouseInventoryCheckVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warehouse_inventory, parent, false);
        return new WarehouseInventoryCheckVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseInventoryCheckVH holder, int Inventory) {
        // 绑定数据在UI上显示
         holder.bind(getItem(Inventory));
    }

    /**
     * 列表item数据绑定
     */
    class WarehouseInventoryCheckVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tv_item_position_code; // 仓位

        WarehouseInventoryCheckVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_item_position_code = itemView.findViewById(R.id.tv_item_position_code);
        }
        void bind(WarehouseInventoryQueryResponseDetails inventory) {
            tv_item_position_code.setText(inventory.getIVNUM()); // 仓位
        }

        @Override
        public void onClick(View view) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                WarehouseInventoryQueryResponseDetails position = getItem(getAdapterPosition());
           }
        }
    }

    static class WarehouseInventoryDiffCallback extends DiffUtil.ItemCallback<WarehouseInventoryQueryResponseDetails> {
        @Override
        public boolean areItemsTheSame(@NonNull WarehouseInventoryQueryResponseDetails oldItem, @NonNull WarehouseInventoryQueryResponseDetails newItem) {
            return oldItem.getLGPLA().equals(newItem.getLGPLA());
        }

        @Override
        public boolean areContentsTheSame(@NonNull WarehouseInventoryQueryResponseDetails oldItem, @NonNull WarehouseInventoryQueryResponseDetails newItem) {
            return oldItem.getLGPLA().equals(newItem.getLGPLA());
        }
    }
}
