package com.youvigo.wms.data.backend.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.youvigo.wms.util.Constants;


public class LocalDataSource {

	private final SharedPreferences mSharedPreferences;

	public LocalDataSource(Context context) {
		this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public String getSapAddress() {
		return mSharedPreferences.getString(Constants.SAP_ADDRESS, "52.82.87.90:50000");
	}

	public String getPdaAddress() {
		return mSharedPreferences.getString(Constants.PDA_ADDRESS, "52.83.32.16:8081");
	}

	public String getFactoryCode() {
		return mSharedPreferences.getString(Constants.FACTORY, null);
	}

	public String getStockLocationCode() {
		return mSharedPreferences.getString(Constants.STOCKLOCATION, null);
	}

	public String getPreference(String key) {
		return mSharedPreferences.getString(key, null);
	}

}
