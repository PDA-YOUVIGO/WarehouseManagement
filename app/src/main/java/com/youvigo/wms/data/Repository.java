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

package com.youvigo.wms.data;

import com.youvigo.wms.data.dto.ShelvingQueryRequest;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.source.local.ILocalDataSource;
import com.youvigo.wms.data.source.local.LocalDataSource;
import com.youvigo.wms.data.source.remote.IRemoteDataSource;
import com.youvigo.wms.data.source.remote.RemoteDataSource;
import com.youvigo.wms.di.Local;
import com.youvigo.wms.di.Remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class Repository implements ILocalDataSource, IRemoteDataSource {

	private final LocalDataSource mLocalDataSource;
	private final RemoteDataSource remoteDataSource;

	@Inject
	public Repository(@Local LocalDataSource localDataSource,
					  @Remote RemoteDataSource sapDataSource) {
		this.mLocalDataSource = localDataSource;
		this.remoteDataSource = sapDataSource;
	}

	@Override
	public String getSapAddress() {
		return null;
	}

	@Override
	public String getPdaAddress() {
		return null;
	}

	@Override
	public String getFactoryCode() {
		return null;
	}

	@Override
	public String getStockLocationCode() {
		return null;
	}

	@Override
	public String getPreference(String key) {
		return null;
	}

	@Override
	public Flowable<List<MaterialVoucher>> getShelvings(ShelvingQueryRequest queryRequest) {
		return remoteDataSource.getShelvings(queryRequest);
	}
}
