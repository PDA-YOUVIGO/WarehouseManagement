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

import android.graphics.Color;
import android.text.TextUtils;
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
import com.youvigo.wms.data.entities.ReservedOutBound;

public class ReservedOutBoundAdapter extends ListAdapter<ReservedOutBound, ReservedOutBoundAdapter.ReservedOutBoundVH> {
	public ReservedOutBoundAdapter() {
		super(new OutOfStockDiffCallback());
	}

	@NonNull
	@Override
	public ReservedOutBoundVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserved_outbound, parent, false);
		return new ReservedOutBoundVH(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ReservedOutBoundVH holder, int position) {
		// 绑定数据在UI上显示
		holder.bind(getItem(position));

		// 设置背景颜色
		ReservedOutBound item = getItem(position);
		if (!TextUtils.isEmpty(item.getBatchNumber()) && !TextUtils.isEmpty(item.getCargoSpace())) {
			holder.itemView.setBackgroundColor(Color.parseColor("#98F898"));
		}
	}

	class ReservedOutBoundVH extends RecyclerView.ViewHolder implements View.OnClickListener {
		private final TextView materialCode;
		private final TextView materialName;
		private final TextView specification;
		private final TextView batchNumber;
		private final TextView demandQuantity;
		private final TextView demandUnit;
		private final TextView pickedQuantity;
		private final TextView pickedUnit;
		private final TextView cargo;


		ReservedOutBoundVH(@NonNull View itemView) {
			super(itemView);

			itemView.setOnClickListener(this);

			materialCode = itemView.findViewById(R.id.tv_material_code_description);
			materialName = itemView.findViewById(R.id.tv_material_name_description);
			specification = itemView.findViewById(R.id.tv_specification_description);
			batchNumber = itemView.findViewById(R.id.tv_batch_number_description);
			demandQuantity = itemView.findViewById(R.id.tv_demand);
			demandUnit = itemView.findViewById(R.id.tv_demand_unit);
			pickedQuantity = itemView.findViewById(R.id.tv_picked);
			pickedUnit = itemView.findViewById(R.id.tv_picked_unit);
			cargo = itemView.findViewById(R.id.tv_cargo_space);
		}

		void bind(ReservedOutBound reservedOutbound) {
			materialCode.setText(reservedOutbound.getMaterialCode());
			materialName.setText(reservedOutbound.getMaterialName());
			specification.setText(reservedOutbound.getSpecification());
			batchNumber.setText(reservedOutbound.getBatchNumber());
			demandQuantity.setText(reservedOutbound.getBDMNG());
			demandUnit.setText(reservedOutbound.getBaseUnitTxt());
			pickedQuantity.setText(String.valueOf(reservedOutbound.getQuantity()));
			pickedUnit.setText(reservedOutbound.getBaseUnitTxt());
			cargo.setText(reservedOutbound.getCargoSpace());
		}

		@Override
		public void onClick(View v) {
			if (itemView.getContext() instanceof AppCompatActivity) {
				ReservedOutBound outOfStock = getItem(getAdapterPosition());
				FragmentManager fragmentManager = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
				ReservedOutBoundDetailDialogFragment.show(fragmentManager, outOfStock, getAdapterPosition());
			}
		}
	}

	static class OutOfStockDiffCallback extends DiffUtil.ItemCallback<ReservedOutBound> {
		@Override
		public boolean areItemsTheSame(@NonNull ReservedOutBound oldItem, @NonNull ReservedOutBound newItem) {
			return oldItem.getMaterialCode().equals(newItem.getMaterialCode());
		}

		@Override
		public boolean areContentsTheSame(@NonNull ReservedOutBound oldItem, @NonNull ReservedOutBound newItem) {
			return oldItem.getMaterialName().equals(newItem.getMaterialName())
					&& oldItem.getMaterialCode().equals(newItem.getMaterialCode())
					&& oldItem.getRSNUM().equals(newItem.getRSNUM());
		}
	}
}
