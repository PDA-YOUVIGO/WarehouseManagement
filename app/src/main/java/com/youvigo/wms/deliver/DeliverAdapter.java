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

package com.youvigo.wms.deliver;

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
import com.youvigo.wms.data.entities.TakeOff;

public class DeliverAdapter extends ListAdapter<TakeOff, DeliverAdapter.TakeOffVH> {
    public DeliverAdapter() {
        super(new TakeOffDiffCallback());
    }

    @NonNull
    @Override
    public TakeOffVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_take_off, parent, false);
        return new TakeOffVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TakeOffVH holder, int position) {
        holder.bind(getItem(position));
    }

    class TakeOffVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView materialCode;
        private final TextView materialName;
        private final TextView quantity;
        private final TextView unit;
        private final TextView specification;
        private final TextView batchNumber;

        TakeOffVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            materialCode = itemView.findViewById(R.id.tv_item_material_code);
            materialName = itemView.findViewById(R.id.tv_material_name_description);
            quantity = itemView.findViewById(R.id.tv_quantity);
            unit = itemView.findViewById(R.id.tv_unit);
            specification = itemView.findViewById(R.id.tv_specification_description);
            batchNumber = itemView.findViewById(R.id.tv_batch_number_description);
        }

        void bind(TakeOff takeOff) {
            materialCode.setText(takeOff.getMaterialCode());
            materialName.setText(takeOff.getMaterialName());
            quantity.setText(String.valueOf(takeOff.getQuantity()));
            unit.setText(takeOff.getBaseUnitDescription());
            specification.setText(takeOff.getSpecification());
            batchNumber.setText(takeOff.getBatchNumber());
        }

        @Override
        public void onClick(View v) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                TakeOff takeOff = getItem(getAdapterPosition());
                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                DeliverDetailDialogFragment.show(fragmentManager, takeOff,getAdapterPosition());
            }
        }
    }

    static class TakeOffDiffCallback extends DiffUtil.ItemCallback<TakeOff> {
        @Override
        public boolean areItemsTheSame(@NonNull TakeOff oldItem, @NonNull TakeOff newItem) {
            return oldItem.getMaterialCode().equals(newItem.getMaterialCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull TakeOff oldItem, @NonNull TakeOff newItem) {
            return oldItem.getMaterialName().equals(newItem.getMaterialName())
                    && oldItem.getOrderNumber().equals(newItem.getOrderNumber())
                    && oldItem.getBatchNumber().equals(newItem.getBatchNumber())
                    && oldItem.getSpecification().equals(newItem.getSpecification());
        }
    }
}
