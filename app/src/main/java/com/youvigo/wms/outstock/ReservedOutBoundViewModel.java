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

package com.youvigo.wms.outstock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;

import java.util.List;

public class ReservedOutBoundViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<?> _order = new MutableLiveData<>();
	private MutableLiveData<List<?>>_items = new MutableLiveData<>();

	public void handleDate() {

		_isLoading.setValue(true);

	}

	public LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	public LiveData<?> getOrder() {
		return _order;
	}

	public LiveData<List<?>> getItems() {
		return _items;
	}
}
