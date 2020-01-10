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

package com.koma.pdaassistant.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.koma.pdaassistant.base.BaseViewModel;
import com.koma.pdaassistant.data.entities.Material;

import java.util.ArrayList;
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

public class SearchViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<Boolean>(false);

    LiveData<Boolean> isLoading = _isLoading;

    private MutableLiveData<List<Material>> _materials = new MutableLiveData<List<Material>>(
    );
    LiveData<List<Material>> materials = _materials;

    void query(String materialDocument) {
        _isLoading.setValue(true);

        Disposable disposable = Flowable.create(new FlowableOnSubscribe<List<Material>>() {
            @Override
            public void subscribe(FlowableEmitter<List<Material>> emitter) {
                List<Material> materials = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    Material material = new Material();
                    material.lotNumber = "00000" + i;
                    material.materialDocument = materialDocument;
                    material.name = "没有名字";
                }
            }
        }, BackpressureStrategy.LATEST)
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<Material>>() {
                    @Override
                    public void onNext(List<Material> materials) {
                        _materials.setValue(materials);
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
}
