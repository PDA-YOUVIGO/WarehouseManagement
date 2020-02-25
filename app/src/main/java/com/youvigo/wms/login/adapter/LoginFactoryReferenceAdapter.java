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
import com.youvigo.wms.data.entities.FactoryReference;

import java.util.List;

/**
 * 库存地点适配器
 */
public class LoginFactoryReferenceAdapter extends BaseAdapter {
	private List<FactoryReference> mFactoryReferences;
	private Context mContext;

	public LoginFactoryReferenceAdapter(List<FactoryReference> mFactoryReferences, Context mContext) {
		this.mFactoryReferences = mFactoryReferences;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mFactoryReferences.size();
	}

	@Override
	public Object getItem(int i) {
		return mFactoryReferences.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		if (view == null) {
			LayoutInflater _layoutInflater = LayoutInflater.from(mContext);
			view = _layoutInflater.inflate(R.layout.login_factory_item, null);
		}

		TextView stockLocationTv = view.findViewById(R.id.factoryTv);

		//stockLocationTv.setText(mFactoryReferenceModels.get(i).getFactoryDisplayName());

		return view;
	}
}
