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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.StoreEntity;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SwitchStockLocationAdapter extends ListAdapter<StoreEntity,
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
			StoreEntity storeEntity = getItem(getAdapterPosition());

			new AlertDialog.Builder(itemView.getContext())
					.setTitle("库存地切换")
					.setMessage(String.format("您确认要将库存地切换到 %s 吗?", storeEntity.getStockLocationCode()))
					.setPositiveButton(R.string.ok, (dialog, which) -> {

						itemView.getContext()
								.getApplicationContext()
								.getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES, Context.MODE_PRIVATE)
								.edit()
								.putString(Constants.STOCKLOCATION, storeEntity.getStockLocationCode())
								.apply();

						Utils.showToast(itemView.getContext(), String.format("已成功切换到 %s 库存地",
								storeEntity.getStockLocationCode()));

						if (itemView.getContext() instanceof AppCompatActivity) {
							AndroidSchedulers.mainThread().scheduleDirect(() -> ((AppCompatActivity) itemView.getContext()).finish());
						}
					})
					.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel()).show();
		}

		void bind(StoreEntity switchStoreEntity) {
			storelocationCode.setText(switchStoreEntity.getStockLocationCode());
			storelocationName.setText(switchStoreEntity.getStoreLocationName());
		}
	}

	static class SwitchStockLocationDiffCallback extends DiffUtil.ItemCallback<StoreEntity> {
		@Override
		public boolean areItemsTheSame(@NonNull StoreEntity oldItem,
									   @NonNull StoreEntity newItem) {
			return oldItem.getFactoryCode().equals(newItem.getFactoryCode()) && oldItem.getStockLocationCode().equals(newItem.getStockLocationCode());
		}

		@Override
		public boolean areContentsTheSame(@NonNull StoreEntity oldItem,
										  @NonNull StoreEntity newItem) {
			return oldItem.getFactoryCode().equals(newItem.getFactoryCode()) && oldItem.getStockLocationCode().equals(newItem.getStockLocationCode());
		}
	}
}
