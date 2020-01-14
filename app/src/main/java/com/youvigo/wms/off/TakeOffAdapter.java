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

package com.youvigo.wms.off;

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

public class TakeOffAdapter extends ListAdapter<TakeOff, TakeOffAdapter.TakeOffVH> {
    public TakeOffAdapter() {
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
        private final TextView itemNumber;
        private final TextView materialName;
        private final TextView basicOrder;
        private final TextView specification;
        private final TextView lotNumber;

        TakeOffVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            itemNumber = itemView.findViewById(R.id.tv_item_number);
            materialName = itemView.findViewById(R.id.tv_material_name);
            basicOrder = itemView.findViewById(R.id.tv_basic_order);
            specification = itemView.findViewById(R.id.tv_specification);
            lotNumber = itemView.findViewById(R.id.tv_lot_number);
        }

        void bind(TakeOff takeOff) {
            itemNumber.setText(takeOff.itemNumber);
            materialName.setText(takeOff.materialName);
            basicOrder.setText(takeOff.basicOrder);
            specification.setText(takeOff.specification);
            lotNumber.setText(takeOff.lotNumber);
        }

        @Override
        public void onClick(View v) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                TakeOff takeOff = getItem(getAdapterPosition());
                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                TakeOffDetailDialogFragment.show(fragmentManager, takeOff);
            }
        }
    }

    static class TakeOffDiffCallback extends DiffUtil.ItemCallback<TakeOff> {
        @Override
        public boolean areItemsTheSame(@NonNull TakeOff oldItem, @NonNull TakeOff newItem) {
            return oldItem.itemNumber.equals(newItem.itemNumber);
        }

        @Override
        public boolean areContentsTheSame(@NonNull TakeOff oldItem, @NonNull TakeOff newItem) {
            return oldItem.materialName.equals(newItem.materialName)
                    && oldItem.basicOrder.equals(newItem.basicOrder)
                    && oldItem.lotNumber.equals(newItem.lotNumber)
                    && oldItem.specification.equals(newItem.specification);
        }
    }
}
