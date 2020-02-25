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

package com.youvigo.wms.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.youvigo.wms.login.LoggedInUser;

import java.util.regex.Pattern;

public class Utils {

	private static Toast toast;

	/**
	 * 验证字符串是否为数字或小数
	 *
	 * @param str 字符串
	 */
	public static boolean isNumericzidal(String str) {
		final String regex = "^([\\+ \\-]?(([1-9]\\d*)|(0)))([.]\\d+)?$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}

	/**
	 * 显示Toast提示框
	 *
	 * @param context Activity
	 * @param message 消息
	 */
	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		} else {
			toast.setText(message);
		}

		toast.show();
	}

	/**
	 * 显示Dialog弹出框
	 *
	 * @param context  Activity
	 * @param title    标题
	 * @param message  消息
	 * @param buttonTitle 按钮标题
	 * @param listener 确认事件
	 */
	public static void showDialog(Context context, String title, String message, String buttonTitle,
								  DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(TextUtils.isEmpty(title) ? "确认" : title);
		builder.setMessage(message);
		builder.setPositiveButton(buttonTitle, listener);

		builder.setNegativeButton("取消", (dialog, which) -> {
			dialog.cancel();
		});

		builder.show();
	}

	/**
	 * 设置登录后的用户信息
	 */
	public static void setLoggedInPreferences(Context context, LoggedInUser loggedInUser) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = sharedPreferences.edit();
		edit.putBoolean(Constants.LOGIN_STATE, true);
		edit.putString(Constants.FACTORYCODE, loggedInUser.getFactoryCode());
		edit.putString(Constants.STOCKLOCATION, loggedInUser.getStockLocation());
		edit.putString(Constants.WAREHOUSE_NUMBER, loggedInUser.getStockCode());
		edit.putString(Constants.ACCOUNT, loggedInUser.getAccount());
		edit.putString(Constants.USERNAME, loggedInUser.getDisplayName());
		edit.apply();
	}

	/**
	 * 清除用户登录信息
	 * @param context
	 */
	public static void clearLoggedInPreferences(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES,
				Context.MODE_PRIVATE);
		sharedPreferences.edit().clear().apply();

	}

}
