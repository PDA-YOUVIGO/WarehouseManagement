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

package com.youvigo.wms.product;

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
import com.youvigo.wms.shelving.ShelvingDetailDialogFragment;

public class FinishedProductsAdapter extends ListAdapter<Shelving, FinishedProductsAdapter.FinishedProductsVM> {

	protected FinishedProductsAdapter() {
		super(new ShelvingDiffCallback());
	}

	@Override
	protected Shelving getItem(int position) {
		return super.getItem(position);
	}


	@NonNull
	@Override
	public FinishedProductsVM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shelving, parent, false);
		return new FinishedProductsVM(view);
	}

	@Override
	public void onBindViewHolder(@NonNull FinishedProductsVM holder, int position) {
		holder.bind(getItem(position));
	}

	class FinishedProductsVM extends RecyclerView.ViewHolder implements View.OnClickListener{

		private final TextView itemNumber;
		private final TextView commonName;
		private final TextView batchNumber;
		private final TextView quantity;
		private final TextView unit;

		public FinishedProductsVM(@NonNull View itemView) {
			super(itemView);

			itemView.setOnClickListener(this);

			itemNumber = itemView.findViewById(R.id.tv_item_number);
			commonName = itemView.findViewById(R.id.tv_common_name);
			batchNumber = itemView.findViewById(R.id.tv_batch_number);
			quantity = itemView.findViewById(R.id.tv_quantity);
			unit = itemView.findViewById(R.id.tv_unit);

		}

		void bind(Shelving shelving) {
			itemNumber.setText(shelving.getMaterialNumber());
			commonName.setText(shelving.getCommonName());
			batchNumber.setText(shelving.getBatchNumber());
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
			return oldItem.getMaterialNumber().equals(newItem.getMaterialNumber());
		}

		@Override
		public boolean areContentsTheSame(@NonNull Shelving oldItem, @NonNull Shelving newItem) {
			return oldItem.getMaterialNumber().equals(newItem.getMaterialNumber()) && oldItem.getBatchNumber().equals(newItem.getBatchNumber());
		}
	}

}
