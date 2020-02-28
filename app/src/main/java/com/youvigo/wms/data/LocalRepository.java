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

package com.youvigo.wms.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.youvigo.wms.login.LoggedInUser;
import com.youvigo.wms.util.Constants;

public class LocalRepository {

	/**
	 * 设置登录后的用户信息
	 */
	public static void setLoggedInPreferences(Context context, LoggedInUser loggedInUser) {
		SharedPreferences sharedPreferences = context
				.getApplicationContext()
				.getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES, Context.MODE_PRIVATE);
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
	 */
	public static void clearLoggedInPreferences(Context context) {
		SharedPreferences sharedPreferences = context
				.getApplicationContext()
				.getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES,
						Context.MODE_PRIVATE);
		sharedPreferences.edit().clear().apply();
	}

	/**
	 * 打印预留单
	 *
	 * @param pdaOrderNumber Pda生成的单据号
	 */
	//public Call<PrintResponse> printOrder(String pdaOrderNumber) {
		//SapService sapService = getSapService();
		//SharedPreferences sharedPreferences =
		//		PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
		//
		//PrintRequest request = new PrintRequest();
		//request.setControlInfo(new ControlInfo());
		//
		//PrintRequestDetails details = new PrintRequestDetails();
		//details.setPdaOrderNumber(pdaOrderNumber);
		//details.setPrinterName(sharedPreferences.getString(Constants.PRINTER_NAME, null));
		//details.setProgramName(sharedPreferences.getString(Constants.BUSINESS_TYPE, null));
		//request.setPrintRequestDetails(details);
		//
		//return sapService.submitPrint(request);

	//}
}
