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

package com.youvigo.wms.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.youvigo.wms.util.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalDataSource implements ILocalDataSource {

	private static final String TAG = LocalDataSource.class.getSimpleName();

	private final SharedPreferences mSharedPreferences;
	private final Context mContext;

	@Inject
	public LocalDataSource(SharedPreferences sharedPreferences, Context context) {
		this.mSharedPreferences = sharedPreferences;
		this.mContext = context;
	}

	@Override
	public String getSapAddress() {
		return mSharedPreferences.getString(Constants.SAP_ADDRESS, "52.82.87.90:50000");
	}

	@Override
	public String getPdaAddress() {
		return mSharedPreferences.getString(Constants.PDA_ADDRESS, "52.83.32.16:8081");
	}

	@Override
	public String getFactoryCode() {
		return mSharedPreferences.getString(Constants.FACTORY, null);
	}

	@Override
	public String getStockLocationCode() {
		return mSharedPreferences.getString(Constants.STOCKLOCATION, null);
	}

	@Override
	public String getPreference(String key) {
		return mSharedPreferences.getString(key, null);
	}

}
