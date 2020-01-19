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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import androidx.fragment.app.Fragment;

/**
 * It's a interceptor fragment and it is used to startActivityForResult()
 * and pass activity result to its observer via callback.
 */
public class ResultFragment extends Fragment {
    private static final String TAG = "ResultFragment";

    private SparseArray<OnResultCallback> mResultCallbackStorage = new SparseArray<>();
    private int mRequestCode = 200;
    private boolean mLogging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    int startActivityForResult(Intent intent, OnResultCallback callback) {
        mResultCallbackStorage.put(++mRequestCode, callback);
        startActivityForResult(intent, mRequestCode);
        return mRequestCode;
    }

    void setResultCallback(OnResultCallback callback) {
        mResultCallbackStorage.put(++mRequestCode, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OnResultCallback callback = mResultCallbackStorage.get(requestCode);
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, data);
        }
    }

    void setLogging(boolean logging) {
        mLogging = logging;
    }

    void log(String message) {
        if (mLogging) {
            Log.d(TAG, message);
        }
    }
}
