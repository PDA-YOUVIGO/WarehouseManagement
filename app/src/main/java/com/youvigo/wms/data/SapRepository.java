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

import com.youvigo.wms.data.dto.ControlInfo;
import com.youvigo.wms.data.dto.ShelvingQueryRequest;
import com.youvigo.wms.data.dto.ShelvingQueryRequestDetails;
import com.youvigo.wms.data.entities.MaterialVoucher;
import com.youvigo.wms.data.source.local.ILocalDataSource;
import com.youvigo.wms.data.source.local.LocalDataSource;
import com.youvigo.wms.data.source.remote.ISapDataSource;
import com.youvigo.wms.data.source.remote.SapDataSource;
import com.youvigo.wms.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SapRepository implements ILocalDataSource, ISapDataSource {

	private final LocalDataSource mLocalDataSource;
	private final SapDataSource mSapDataSource;

	@Inject
	public SapRepository(@Local LocalDataSource localDataSource,
						 @Remote SapDataSource sapDataSource) {
		this.mLocalDataSource = localDataSource;
		this.mSapDataSource = sapDataSource;
	}

	public List<MaterialVoucher> getShelvings(String startDate, String endDate, String materialVoucherCode) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
		LocalDate localDate = LocalDate.parse(startDate, dateTimeFormatter);

		ShelvingQueryRequest queryRequest = new ShelvingQueryRequest();
		ControlInfo controlInfo = new ControlInfo();
		queryRequest.setControlInfo(controlInfo);

		ShelvingQueryRequestDetails requestDetails = new ShelvingQueryRequestDetails();
		requestDetails.setWarehouseNumber(mLocalDataSource.getPreference(Constants.WAREHOUSE_NUMBER));
		requestDetails.setStockLocationCode(mLocalDataSource.getStockLocationCode());
		requestDetails.setMaterialVoucherCode(materialVoucherCode);
		requestDetails.setYear(String.valueOf(localDate.getYear()));
		requestDetails.setStartDate(startDate);
		requestDetails.setEndDate(endDate);

		queryRequest.setRequestDetails(requestDetails);

		return mSapDataSource.getShelvings(queryRequest);
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
	public List<MaterialVoucher> getShelvings(ShelvingQueryRequest queryRequest) {
		return null;
	}
}
