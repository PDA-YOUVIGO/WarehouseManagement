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

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * Callback of {@link Activity#startActivityForResult(Intent, int)}
 * or {@link Fragment#startActivityForResult(Intent, int)}
 */
public interface OnResultCallback {

    /**
     * Called while activity result is responded.
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
