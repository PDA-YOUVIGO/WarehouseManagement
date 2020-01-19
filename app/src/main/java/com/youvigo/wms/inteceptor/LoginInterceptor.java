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

package com.youvigo.wms.inteceptor;

import android.content.Context;
import android.content.Intent;

import com.youvigo.wms.activityresult.Interceptor;
import com.youvigo.wms.login.LoginActivity;

public class LoginInterceptor extends Interceptor {


	@Override
	public boolean isValid(Context context) {
		return LoginActivity.isLogin(context);
	}

	@Override
	public Intent getTargetIntent(Context context) {
		return new Intent(context, LoginActivity.class);
	}
}
