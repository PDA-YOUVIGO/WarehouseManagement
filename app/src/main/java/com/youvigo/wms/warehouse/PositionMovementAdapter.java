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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.PositionMovementModelView;

public class PositionMovementAdapter extends ListAdapter<PositionMovementModelView, PositionMovementAdapter.PositionMoveCheckVH> {

    public PositionMovementAdapter() {
        super(new PositionMoveDiffCallback());
    }

    @NonNull
    @Override
    public PositionMoveCheckVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_position_movement, parent, false);
        return new PositionMoveCheckVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionMoveCheckVH holder, int position) {
        // 绑定数据在UI上显示
         holder.bind(getItem(position));
    }

    /**
     * 列表item的数量
     */
    class PositionMoveCheckVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tv_material_coding; // 物料编码
        private final TextView tv_material_name; // 物料名称
        private final TextView tv_batch_number; // 批次号
        private final TextView tv_number; // 数量
        private final TextView tv_unit; // 单位
        private final TextView tv_supplier_batch_number; //供应商批次
        private final TextView sold_position_number; // 下架货位
        private final TextView tv_put_position_number; //上架货位

        PositionMoveCheckVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_material_coding = itemView.findViewById(R.id.tv_material_coding);
            tv_material_name = itemView.findViewById(R.id.tv_material_name);
            tv_batch_number = itemView.findViewById(R.id.tv_batch_number);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_unit = itemView.findViewById(R.id.tv_unit);
            tv_supplier_batch_number = itemView.findViewById(R.id.tv_supplier_batch_number);
            sold_position_number = itemView.findViewById(R.id.sold_position_number);
            tv_put_position_number = itemView.findViewById(R.id.tv_put_position_number);
        }
        void bind(PositionMovementModelView position) {
            tv_material_coding.setText(position.getMATNR()); // 物料编码
            tv_material_name.setText(position.getMAKTX()); // 物料名称
            tv_batch_number.setText(position.getCHARG()); // 批次号
            tv_number.setText(position.getVSOLM()); // 数量
            tv_unit.setText(position.getMEINS()); //单位
            tv_supplier_batch_number.setText(position.getZZLICHA()); //供应商批次
            sold_position_number.setText(position.getVLPLA()); //下架货位
            tv_put_position_number.setText(position.getNLPLA()); //上架仓位
        }
        @Override
        public void onClick(View v) {
            if (itemView.getContext() instanceof AppCompatActivity) {
                PositionMovementModelView position = getItem(getAdapterPosition());
                FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                PositionMovementDialogFragment.show(fragmentManager, position,this.getAdapterPosition());
            }
        }
    }

    static class PositionMoveDiffCallback extends DiffUtil.ItemCallback<PositionMovementModelView> {
        @Override
        public boolean areItemsTheSame(@NonNull PositionMovementModelView oldItem, @NonNull PositionMovementModelView newItem) {
            return oldItem.getMATNR().equals(newItem.getMATNR());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PositionMovementModelView oldItem, @NonNull PositionMovementModelView newItem) {
            return oldItem.getCHARG().equals(newItem.getCHARG())
                    && oldItem.getNLPLA().equals(newItem.getNLPLA())
                    && oldItem.getNLTYP().equals(newItem.getNLTYP())
                    && oldItem.getVSOLM().equals(newItem.getVSOLM());
        }
    }
}
