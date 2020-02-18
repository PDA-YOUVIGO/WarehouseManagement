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

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.StockMaterial;
import com.youvigo.wms.util.Constants;

import timber.log.Timber;

public class MaterialsSearchAdapter extends ListAdapter<StockMaterial, MaterialsSearchAdapter.MaterialCheckVH> {
    public MaterialsSearchAdapter() {
        super(new MaterialCheckDiffCallback());
    }

    @NonNull
    @Override
    public MaterialCheckVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materials_search, parent, false);
        return new MaterialCheckVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialCheckVH holder, int position) {
        // 绑定数据在UI上显示
         holder.bind(getItem(position));
    }

    /**
     * 列表item的数量
     */
    class MaterialCheckVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tv_material_coding; // 物料编码
        private final TextView tv_material_name; // 物料名称
        private final TextView tv_specification; // 规格
        private final TextView tv_number; // 数量
        private final TextView tv_unit; // 单位
        private final TextView tv_batch_number; //批号
        private final TextView tv_cargo_space; // 货位
        private final TextView tv_supplier; //供应商

        MaterialCheckVH(@NonNull View itemView) {
            super(itemView);
            tv_material_coding = itemView.findViewById(R.id.tv_material_code);
            tv_material_name = itemView.findViewById(R.id.tv_material_name);
            tv_specification = itemView.findViewById(R.id.tv_specification);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_unit = itemView.findViewById(R.id.tv_unit);
            tv_batch_number = itemView.findViewById(R.id.tv_batch_number);
            tv_cargo_space = itemView.findViewById(R.id.tv_cargo_space);
            tv_supplier = itemView.findViewById(R.id.tv_supplier);

        }
        void bind(StockMaterial StockMaterial) {
            tv_material_coding.setText(StockMaterial.getMaterialCode());
            tv_material_name.setText(StockMaterial.getMaterialCommonName());
            tv_specification.setText(StockMaterial.getSpecification());
            tv_number.setText(String.valueOf(StockMaterial.getActualInventory()));
            tv_unit.setText(StockMaterial.getBaseUnit());
            tv_batch_number.setText(StockMaterial.getBatchNumber());
            tv_cargo_space.setText(StockMaterial.getCargoSpace());
            tv_supplier.setText(StockMaterial.getZZSUPP_NAME());
        }


        @Override
        public void onClick(View v) {
            StockMaterial stockMaterial = getItem(getAdapterPosition());
            Timber.d("onClick > %s", stockMaterial.toString());

            // 点击数据返回到调用到Activity
            if (itemView.getContext() instanceof MaterialsSearchActivity) {
                Intent intent = new Intent();
                intent.putExtra(Constants.MATERIAL_SEARCH_RESULT, stockMaterial);
                ((MaterialsSearchActivity) itemView.getContext()).setResult(Activity.RESULT_OK, intent);
                ((MaterialsSearchActivity) itemView.getContext()).finish();
            }
        }
    }

    static class MaterialCheckDiffCallback extends DiffUtil.ItemCallback<StockMaterial> {
        @Override
        public boolean areItemsTheSame(@NonNull StockMaterial oldItem, @NonNull StockMaterial newItem) {
            return oldItem.getMaterialCode().equals(newItem.getMaterialCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull StockMaterial oldItem, @NonNull StockMaterial newItem) {
            return oldItem.getBatchNumber().equals(newItem.getBatchNumber());
        }
    }
}
