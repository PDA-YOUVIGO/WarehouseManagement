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

package com.youvigo.wms.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 保存到本地的配置文件
 */
public class SharedPreferenceUtils {

	private static SharedPreferences mSharedPreferences = null;

	/**
	 * 单例模式
	 */
	private static synchronized SharedPreferences getInstance(Context context) {
		if (mSharedPreferences == null) {
			mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		}
		return mSharedPreferences;
	}

	/**
	 * SharedPreferences常用的10个操作方法
	 */
	public static void putBoolean(String key, boolean value, Context context) {
		SharedPreferenceUtils.getInstance(context).edit().putBoolean(key, value).apply();
	}

	public static boolean getBoolean(String key, boolean defValue, Context context) {
		return SharedPreferenceUtils.getInstance(context).getBoolean(key, defValue);
	}

	public static void putString(String key, String value, Context context) {
		SharedPreferenceUtils.getInstance(context).edit().putString(key, value).apply();
	}

	public static String getString(String key, String defValue, Context context) {
		return SharedPreferenceUtils.getInstance(context).getString(key, defValue);
	}

	public static void putInt(String key, int value, Context context) {
		SharedPreferenceUtils.getInstance(context).edit().putInt(key, value).apply();
	}

	public static int getInt(String key, int defValue, Context context) {
		return SharedPreferenceUtils.getInstance(context).getInt(key, defValue);
	}

	/**
	 * 移除某个key值已经对应的值
	 */
	public static void remove(String key, Context context) {
		SharedPreferenceUtils.getInstance(context).edit().remove(key).apply();
	}

	/**
	 * 清除所有内容
	 */
	public static void clear(Context context) {
		SharedPreferenceUtils.getInstance(context).edit().clear().apply();
	}
}