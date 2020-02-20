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

package com.youvigo.wms.outstock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.InventoryCheck;
import com.youvigo.wms.data.entities.NoReservedOutbound;

public class NoReservedOutBoundAdapter extends ListAdapter<NoReservedOutbound, NoReservedOutBoundAdapter.NoReservedOutboundVH> {
    public NoReservedOutBoundAdapter() {
        super(new NoReservedOutboundDiffCallback());
    }

    @NonNull
    @Override
    public NoReservedOutboundVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_reserved_outbound, parent, false);
        return new NoReservedOutboundVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoReservedOutboundVH holder, int position) {
        // 绑定数据在UI上显示
        // holder.bind(getItem(position));
    }

    /**
     * 显示的item数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 20;
    }

    class NoReservedOutboundVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView itemNumber;
        private final TextView materialName;
        private final TextView basicOrder;
        private final TextView specification;
        private final TextView lotNumber;

        NoReservedOutboundVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            itemNumber = itemView.findViewById(R.id.tv_item_material_code);
            materialName = itemView.findViewById(R.id.tv_material_name_description);
            basicOrder = itemView.findViewById(R.id.tv_quantity);
            specification = itemView.findViewById(R.id.tv_specification_description);
            lotNumber = itemView.findViewById(R.id.tv_batch_number_description);
        }

        void bind(InventoryCheck inventoryCheck) {
            itemNumber.setText(inventoryCheck.itemNumber);
            materialName.setText(inventoryCheck.materialName);
            basicOrder.setText(inventoryCheck.basicOrder);
            specification.setText(inventoryCheck.specification);
            lotNumber.setText(inventoryCheck.lotNumber);
        }

        @Override
        public void onClick(View v) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                NoReservedOutBoundDetailDialogFragment.show(fragmentManager);
            }
        }
    }

    static class NoReservedOutboundDiffCallback extends DiffUtil.ItemCallback<NoReservedOutbound> {
        @Override
        public boolean areItemsTheSame(@NonNull NoReservedOutbound oldItem, @NonNull NoReservedOutbound newItem) {
            return oldItem.itemNumber.equals(newItem.itemNumber);
        }

        @Override
        public boolean areContentsTheSame(@NonNull NoReservedOutbound oldItem, @NonNull NoReservedOutbound newItem) {
            return oldItem.materialName.equals(newItem.materialName)
                    && oldItem.basicOrder.equals(newItem.basicOrder)
                    && oldItem.lotNumber.equals(newItem.lotNumber)
                    && oldItem.specification.equals(newItem.specification);
        }
    }
}
