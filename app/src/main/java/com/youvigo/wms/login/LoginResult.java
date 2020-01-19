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

package com.youvigo.wms.login;

import androidx.annotation.Nullable;

public class LoginResult {

	@Nullable
	private LoggedInUser success;

	@Nullable
	private Exception error;

	@Nullable
	public LoggedInUser getSuccess() {
		return success;
	}

	@Nullable
	public Exception getError() {
		return error;
	}

	public LoginResult(@Nullable Exception error) {
		this.error = error;
	}

	public LoginResult(@Nullable LoggedInUser success) {
		this.success = success;
	}
}
