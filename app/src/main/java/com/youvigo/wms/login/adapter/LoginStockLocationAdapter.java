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

package com.youvigo.wms.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.StoreEntity;

import java.util.List;

/**
 * 库存地点适配器
 */
public class LoginStockLocationAdapter extends BaseAdapter {
	private List<StoreEntity> mStockLocations;
	private Context mContext;

	public LoginStockLocationAdapter(List<StoreEntity> mStores, Context mContext) {
		this.mStockLocations = mStores;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mStockLocations.size();
	}

	@Override
	public Object getItem(int i) {
		return mStockLocations.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater _layoutInflater = LayoutInflater.from(mContext);
			view = _layoutInflater.inflate(R.layout.login_spinner, null);
		}

		TextView tvItem = view.findViewById(R.id.tv_item);
		StoreEntity item = (StoreEntity) getItem(i);

		tvItem.setText(String.format("%s - %s", item.getStockLocationCode(), item.getStoreLocationName()));

		return view;
	}
}
