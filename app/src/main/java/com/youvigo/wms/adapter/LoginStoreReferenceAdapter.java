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

package com.youvigo.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.model.StoreReferenceModel;

import java.util.List;

public class LoginStoreReferenceAdapter extends BaseAdapter {

	private List<StoreReferenceModel> mStoreEntity;
	private Context mContext;

	public LoginStoreReferenceAdapter(List<StoreReferenceModel> mStoreEntity, Context mContext) {
		this.mStoreEntity = mStoreEntity;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mStoreEntity.size();
	}

	@Override
	public Object getItem(int i) {
		return mStoreEntity.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		if (view == null) {
			LayoutInflater _layoutInflater = LayoutInflater.from(mContext);
			view = _layoutInflater.inflate(R.layout.login_stocklocation_item, null);
		}

		TextView storeTv = view.findViewById(R.id.stockLocationTv);
		storeTv.setText(mStoreEntity.get(i).getStockLocationDisplayName());

		return view;
	}
}
