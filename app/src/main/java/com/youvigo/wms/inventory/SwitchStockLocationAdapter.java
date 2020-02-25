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

package com.youvigo.wms.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.StoreLocationReference;
import com.youvigo.wms.util.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SwitchStockLocationAdapter extends ListAdapter<StoreLocationReference,
		SwitchStockLocationAdapter.SwitchStockLocationVH> {
	public SwitchStockLocationAdapter() {
		super(new SwitchStockLocationDiffCallback());
	}

	@NonNull
	@Override
	public SwitchStockLocationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_switch_stock_location, parent,
				false);
		return new SwitchStockLocationVH(view);
	}

	@Override
	public void onBindViewHolder(@NonNull SwitchStockLocationVH holder, int position) {
		holder.bind(getItem(position));
	}

	class SwitchStockLocationVH extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView storelocationCode;
		private TextView storelocationName;

		SwitchStockLocationVH(@NonNull View itemView) {
			super(itemView);

			itemView.setOnClickListener(this);

			storelocationCode = itemView.findViewById(R.id.tv_storelocation_code);
			storelocationName = itemView.findViewById(R.id.tv_storelocation_name);
		}

		@Override
		public void onClick(View v) {
			StoreLocationReference switchStockLocation = getItem(getAdapterPosition());

			new AlertDialog.Builder(itemView.getContext())
					.setTitle("库存地")
					.setMessage("切换到该库存地？")
					.setPositiveButton(R.string.ok, (dialog, which) -> {

						itemView.getContext().getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES,
                                Context.MODE_PRIVATE).edit().putString(Constants.FACTORYCODE,
                                switchStockLocation.getStockLocationCode()).apply();

						Toast.makeText(itemView.getContext(), "成功切换库存地。", Toast.LENGTH_SHORT).show();

						if (itemView.getContext() instanceof AppCompatActivity) {
							AndroidSchedulers.mainThread().scheduleDirect(() -> ((AppCompatActivity) itemView.getContext()).finish());
						}
					})
					.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel()).show();
		}

		void bind(StoreLocationReference switchStockLocation) {
			storelocationCode.setText(switchStockLocation.getStockLocationCode());
			storelocationName.setText(switchStockLocation.getStoreLocationName());
		}
	}

	static class SwitchStockLocationDiffCallback extends DiffUtil.ItemCallback<StoreLocationReference> {
		@Override
		public boolean areItemsTheSame(@NonNull StoreLocationReference oldItem,
                                       @NonNull StoreLocationReference newItem) {
			return oldItem.getStoreCode().equals(newItem.getStoreCode()) && oldItem.getStockLocationCode().equals(newItem.getStockLocationCode());
		}

		@Override
		public boolean areContentsTheSame(@NonNull StoreLocationReference oldItem,
                                          @NonNull StoreLocationReference newItem) {
			return oldItem.getStoreCode().equals(newItem.getStoreCode()) && oldItem.getStockLocationCode().equals(newItem.getStockLocationCode());
		}
	}
}
