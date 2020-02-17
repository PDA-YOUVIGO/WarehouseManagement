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
import com.youvigo.wms.data.entities.Material;

public class MaterialsSearchAdapter extends ListAdapter<Material, MaterialsSearchAdapter.MaterialCheckVH> {
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
    class MaterialCheckVH extends RecyclerView.ViewHolder {
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
            tv_material_coding = itemView.findViewById(R.id.tv_material_coding);
            tv_material_name = itemView.findViewById(R.id.tv_material_name);
            tv_specification = itemView.findViewById(R.id.tv_specification);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_unit = itemView.findViewById(R.id.tv_unit);
            tv_batch_number = itemView.findViewById(R.id.tv_batch_number);
            tv_cargo_space = itemView.findViewById(R.id.tv_cargo_space);
            tv_supplier = itemView.findViewById(R.id.tv_supplier);

        }
        void bind(Material Material) {
            tv_material_coding.setText(Material.getMATNR());
            tv_material_name.setText(Material.getMAKTX());
            tv_specification.setText(Material.getZZDRUGSPEC());
            tv_number.setText(Material.getZZMENGE_MAIN());
            tv_unit.setText(Material.getMEINS());
            tv_batch_number.setText(Material.getZZLICHA_MAIN());
            tv_cargo_space.setText(Material.getLGPLA());
            tv_supplier.setText(Material.getZZSUPP_NAME());
        }
        // 查看详情
//        @Override
//        public void onClick(View v) {
//            if (itemView.getContext() instanceof AppCompatActivity) {
//                //  ReservedOutbound outOfStock = getItem(getAdapterPosition());
//                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
//                OutOfStockDetailDialogFragment.show(fragmentManager);
//            }
//        }
    }

    static class MaterialCheckDiffCallback extends DiffUtil.ItemCallback<Material> {
        @Override
        public boolean areItemsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem.getMATNR().equals(newItem.getMATNR());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem.getCHARG().equals(newItem.getCHARG());
        }
    }
}
