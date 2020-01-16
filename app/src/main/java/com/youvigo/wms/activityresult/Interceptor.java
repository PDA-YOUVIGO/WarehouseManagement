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

package com.youvigo.wms.activityresult;

import android.content.Context;
import android.content.Intent;

public abstract class Interceptor {
    private int requestCode;

    /**
     * Request code used to start activity for result.
     *
     * @return request code
     */
    public final int getRequestCode() {
        return requestCode;
    }

    /**
     * You should not set request code by yourself, it'll set by {@link ActivityResult} automatically.
     *
     * @param requestCode request code
     */
    public final void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    /**
     * Check interceptor's condition is meet or no.
     *
     * @param context Android context
     * @return condition is meet or no
     */
    public abstract boolean isValid(Context context);

    /**
     * if condition was not satisfied, it'll be called to acquire resource or permission and so on.
     */
    public abstract Intent getTargetIntent(Context context);
}