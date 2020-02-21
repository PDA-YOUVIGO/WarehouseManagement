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
import com.youvigo.wms.data.entities.NoReservedOutBoundDetail;

public class NoReservedOutBoundAdapter extends ListAdapter<NoReservedOutBoundDetail, NoReservedOutBoundAdapter.NoReservedOutboundVH> {
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
         holder.bind(getItem(position));
    }

    class NoReservedOutboundVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView materialCode;
        private final TextView materialName;
        private final TextView pickedQuantity;
        private final TextView pickedUnit;
        private final TextView specification;
        private final TextView batchNumber;
        private final TextView cargoSpace;

        NoReservedOutboundVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            materialCode = itemView.findViewById(R.id.tv_material_code_description);
            materialName = itemView.findViewById(R.id.tv_material_name_description);
            specification = itemView.findViewById(R.id.tv_specification_description);
            batchNumber = itemView.findViewById(R.id.tv_batch_number_description);
            pickedQuantity = itemView.findViewById(R.id.tv_picked);
            pickedUnit = itemView.findViewById(R.id.tv_picked_unit);
            cargoSpace = itemView.findViewById(R.id.tv_cargo_space);
        }

        void bind(NoReservedOutBoundDetail noReservedOutBoundDetail) {
            materialCode.setText(noReservedOutBoundDetail.getMaterialCode());
            materialName.setText(noReservedOutBoundDetail.getMaterialDescription());
            pickedQuantity.setText(String.valueOf(noReservedOutBoundDetail.getQuantity()));
            pickedUnit.setText(noReservedOutBoundDetail.getBaseUnit());
            specification.setText(noReservedOutBoundDetail.getSpecification());
            batchNumber.setText(noReservedOutBoundDetail.getBatchNumber());
            cargoSpace.setText(noReservedOutBoundDetail.getCargoSpace());
        }

        @Override
        public void onClick(View v) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                NoReservedOutBoundDetail noReservedOutBoundDetail = getItem(getAdapterPosition());
                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                NoReservedOutBoundDetailDialogFragment.show(fragmentManager, noReservedOutBoundDetail, getAdapterPosition());
            }
        }
    }

    static class NoReservedOutboundDiffCallback extends DiffUtil.ItemCallback<NoReservedOutBoundDetail> {
        @Override
        public boolean areItemsTheSame(@NonNull NoReservedOutBoundDetail oldItem, @NonNull NoReservedOutBoundDetail newItem) {
            return oldItem.getMaterialCode().equals(newItem.getMaterialCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull NoReservedOutBoundDetail oldItem, @NonNull NoReservedOutBoundDetail newItem) {
            return oldItem.getMaterialCode().equals(newItem.getMaterialCode())
                    && oldItem.getBatchNumber().equals(newItem.getBatchNumber())
                    && oldItem.getSpecification().equals(newItem.getSpecification());
        }
    }
}
