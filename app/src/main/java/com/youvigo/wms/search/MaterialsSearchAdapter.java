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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.InventoryCheck;

public class MaterialsSearchAdapter extends ListAdapter<InventoryCheck, MaterialsSearchAdapter.InventoryCheckVH> {
    public MaterialsSearchAdapter() {
        super(new InventoryCheckDiffCallback());
    }

    @NonNull
    @Override
    public InventoryCheckVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materials_search, parent, false);
        return new InventoryCheckVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryCheckVH holder, int position) {
        // 绑定数据在UI上显示
        // holder.bind(getItem(position));
    }

    /**
     * 列表item的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 20;
    }

    class InventoryCheckVH extends RecyclerView.ViewHolder {
        private final TextView itemNumber;
        private final TextView materialName;
        private final TextView basicOrder;
        private final TextView specification;
        private final TextView lotNumber;

        InventoryCheckVH(@NonNull View itemView) {
            super(itemView);

            itemNumber = itemView.findViewById(R.id.tv_item_number);
            materialName = itemView.findViewById(R.id.tv_material_name);
            basicOrder = itemView.findViewById(R.id.tv_basic_order);
            specification = itemView.findViewById(R.id.tv_specification);
            lotNumber = itemView.findViewById(R.id.tv_lot_number);
        }

        void bind(InventoryCheck inventoryCheck) {
            itemNumber.setText(inventoryCheck.itemNumber);
            materialName.setText(inventoryCheck.materialName);
            basicOrder.setText(inventoryCheck.basicOrder);
            specification.setText(inventoryCheck.specification);
            lotNumber.setText(inventoryCheck.lotNumber);
        }
    }

    static class InventoryCheckDiffCallback extends DiffUtil.ItemCallback<InventoryCheck> {
        @Override
        public boolean areItemsTheSame(@NonNull InventoryCheck oldItem, @NonNull InventoryCheck newItem) {
            return oldItem.itemNumber.equals(newItem.itemNumber);
        }

        @Override
        public boolean areContentsTheSame(@NonNull InventoryCheck oldItem, @NonNull InventoryCheck newItem) {
            return oldItem.materialName.equals(newItem.materialName)
                    && oldItem.basicOrder.equals(newItem.basicOrder)
                    && oldItem.lotNumber.equals(newItem.lotNumber)
                    && oldItem.specification.equals(newItem.specification);
        }
    }
}
