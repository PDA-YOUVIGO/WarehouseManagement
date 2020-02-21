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
import com.youvigo.wms.data.dto.response.SapResponseMessage;
import com.youvigo.wms.data.entities.NoReservedOutBoundDetail;
import com.youvigo.wms.data.entities.NoReservedOutBound;

import java.util.ArrayList;
import java.util.List;

public class NoreservedOutBoundViewModel extends BaseViewModel {

	private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
	private MutableLiveData<NoReservedOutBound> _order = new MutableLiveData<>();
	private MutableLiveData<List<NoReservedOutBoundDetail>> _details = new MutableLiveData<>();
	private MutableLiveData<SapResponseMessage> _sap_result = new MutableLiveData<>();

	void init() {
		NoReservedOutBound noReservedOutBound = new NoReservedOutBound();
		List<NoReservedOutBoundDetail> noReservedOutBoundDetails = new ArrayList<>();
		noReservedOutBound.setDetails(noReservedOutBoundDetails);

		_order.setValue(noReservedOutBound);


	}

	public LiveData<Boolean> isLoading() {
		return _isLoading;
	}

	public LiveData<NoReservedOutBound> getMaterialVoucher() {
		return _order;
	}

	public LiveData<SapResponseMessage> getSapResult() {
		return _sap_result;
	}
}
