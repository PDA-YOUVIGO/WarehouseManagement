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

package com.youvigo.wms.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.youvigo.wms.base.BaseViewModel;
import com.youvigo.wms.data.Repository;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.entities.TakeOff;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class SearchViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<Boolean>(false);

	private MutableLiveData<List<MaterialVoucher>> _materials = new MutableLiveData<List<MaterialVoucher>>();

	// 初始化model层接口，单例提供

	@Inject
	Repository repository;

	/**
	 * 查询数据
	 *
	 * @param startDate        开始日期
	 * @param endDate          结束日期
	 * @param materialDocument 物料编号
	 */
	void query(String startDate, String endDate, String materialDocument) {
		_isLoading.setValue(true);


		Flowable<List<MaterialVoucher>> shelvings = repository.getShelvings(null);

		Disposable disposable = Flowable.create((FlowableOnSubscribe<List<MaterialVoucher>>) emitter -> {


			List<MaterialVoucher> mockData = null;

			emitter.onNext(mockData);
			emitter.onComplete();
		}, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSubscriber<List<MaterialVoucher>>() {
			@Override
			public void onNext(List<MaterialVoucher> materials) {
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

	@NotNull
	private List<TakeOff> produceTakeOffs(int i) {
		List<TakeOff> takeOffs = new ArrayList<>();
		for (int j = 0; j <= i; j++) {
			TakeOff takeOff = new TakeOff();
			takeOff.itemNumber = "1010201111100000" + j;
			takeOff.materialName = "吸氧剂";
			takeOff.basicOrder = "KG";
			takeOff.specification = "药用级";
			takeOff.lotNumber = "O12340000" + j;
			takeOffs.add(takeOff);
		}
		return takeOffs;
	}

	LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	LiveData<List<MaterialVoucher>> materials() {
		return _materials;
	}
}
