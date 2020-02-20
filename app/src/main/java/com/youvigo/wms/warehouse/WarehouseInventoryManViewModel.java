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

package com.youvigo.wms.warehouse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.data.entities.WarehouseInventoryModelView;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class WarehouseInventoryManViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<Boolean>(false);
    private MutableLiveData<List<WarehouseInventoryModelView>> _inventorys = new MutableLiveData<>();

    public void handleData(List<WarehouseInventoryModelView> inventorys) {
        _isLoading.setValue(true);
        _inventorys.setValue(inventorys);
        Disposable disposable = Flowable.create((FlowableOnSubscribe<List<WarehouseInventoryModelView>>) emitter -> {
            emitter.onNext(inventorys);
            emitter.onComplete();
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<WarehouseInventoryModelView>>() {
                    @Override
                    public void onNext(List<WarehouseInventoryModelView> modelView) {
                        _inventorys.setValue(modelView);
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

    public LiveData<List<WarehouseInventoryModelView>> inventorys() {
        return _inventorys;
    }
}
