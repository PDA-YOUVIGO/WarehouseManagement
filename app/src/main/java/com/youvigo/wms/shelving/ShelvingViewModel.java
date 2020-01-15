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

package com.youvigo.wms.shelving;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.Shelving;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class ShelvingViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<Boolean>(false);

    private MutableLiveData<MaterialVoucher> _material = new MutableLiveData<MaterialVoucher>();

    private MutableLiveData<List<Shelving>> _shelvings = new MutableLiveData<List<Shelving>>();

    public void handleData(MaterialVoucher materialVoucher) {
        _isLoading.setValue(true);

        _material.setValue(materialVoucher);

        Disposable disposable = Flowable.create(new FlowableOnSubscribe<List<Shelving>>() {
            @Override
            public void subscribe(FlowableEmitter<List<Shelving>> emitter) {
                emitter.onNext(materialVoucher.shelvings);
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<Shelving>>() {
                    @Override
                    public void onNext(List<Shelving> shelvings) {
                        _shelvings.setValue(shelvings);
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

    public LiveData<List<Shelving>> shelvings() {
        return _shelvings;
    }

    public LiveData<MaterialVoucher> material() {
        return _material;
    }
}
