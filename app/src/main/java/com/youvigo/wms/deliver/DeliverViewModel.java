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

package com.youvigo.wms.deliver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.TakeOff;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class DeliverViewModel extends BaseViewModel {
	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<Boolean>(false);
	private MutableLiveData<MaterialVoucher> _materials = new MutableLiveData<>();
	private MutableLiveData<List<TakeOff>> _takeOffs = new MutableLiveData<List<TakeOff>>();

	public void handleData(MaterialVoucher material) {
		_isLoading.setValue(true);

		_materials.setValue(material);

		Disposable disposable = Flowable.create((FlowableOnSubscribe<List<TakeOff>>) emitter -> {
			emitter.onNext(material.takeOffs);
			emitter.onComplete();
		}, BackpressureStrategy.LATEST)
				//.delay(3, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSubscriber<List<TakeOff>>() {
					@Override
					public void onNext(List<TakeOff> result) {
						_takeOffs.setValue(result);
					}

					@Override
					public void onError(Throwable t) {
						_isLoading.setValue(false);
					}

					@Override
					public void onComplete() {
						_isLoading.setValue(false);
					}
				});
		addSubscription(disposable);
	}

	public LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	public LiveData<List<TakeOff>> takeOffs() {
		return _takeOffs;
	}

	public LiveData<MaterialVoucher> getMaterials() {
		return _materials;
	}
}
