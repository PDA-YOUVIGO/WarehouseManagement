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

package com.youvigo.wms.data.source.remote;

import com.youvigo.wms.data.api.SapApi;
import com.youvigo.wms.data.dto.ShelvingQueryRequest;
import com.youvigo.wms.data.entities.MaterialVoucher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class RemoteDataSource implements IRemoteDataSource {

	private final String TAG = this.getClass().getSimpleName();

	private SapApi sapApi;

	@Inject
	public RemoteDataSource(SapApi sapApi) {
		this.sapApi = sapApi;
	}


	@Override
	public Flowable<List<MaterialVoucher>> getShelvings(ShelvingQueryRequest queryRequest) {
		return sapApi.getShelvings(queryRequest);
	}

}
