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

package com.youvigo.wms.shelving;

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
import com.youvigo.wms.data.entities.Shelving;

public class ShelvingAdapter extends ListAdapter<Shelving, ShelvingAdapter.ShelvingVH> {
    public ShelvingAdapter() {
        super(new ShelvingDiffCallback());
    }

    @NonNull
    @Override
    public ShelvingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shelving, parent, false);
        return new ShelvingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelvingVH holder, int position) {
        holder.bind(getItem(position));
    }

    class ShelvingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView itemNumber;
        private final TextView commonName;
        private final TextView batchNumber;
        private final TextView quantity;
        private final TextView unit;

        ShelvingVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            itemNumber = itemView.findViewById(R.id.tv_item_number);
            commonName = itemView.findViewById(R.id.tv_common_name);
            batchNumber = itemView.findViewById(R.id.tv_batch_number);
            quantity = itemView.findViewById(R.id.tv_quantity);
            unit = itemView.findViewById(R.id.tv_unit);
        }

        void bind(Shelving shelving) {
            itemNumber.setText(shelving.getMaterialVoucherCode());
            commonName.setText(shelving.commonName);
            batchNumber.setText(shelving.batchNumber);
            quantity.setText(String.valueOf(shelving.getBasicQuantity()));
            unit.setText(shelving.getBaseUnitTxt());
        }

        @Override
        public void onClick(View v) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                Shelving shelving = getItem(getAdapterPosition());
                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                ShelvingDetailDialogFragment.show(fragmentManager, shelving);
            }
        }
    }

    static class ShelvingDiffCallback extends DiffUtil.ItemCallback<Shelving> {
        @Override
        public boolean areItemsTheSame(@NonNull Shelving oldItem, @NonNull Shelving newItem) {
            return oldItem.getMaterialVoucherCode().equals(newItem.getMaterialVoucherCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Shelving oldItem, @NonNull Shelving newItem) {
            return oldItem.commonName.equals(newItem.commonName)
                    && oldItem.batchNumber.equals(newItem.batchNumber);
        }
    }
}
