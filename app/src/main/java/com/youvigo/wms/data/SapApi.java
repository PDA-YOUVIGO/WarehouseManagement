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

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SapApi {

	/**
	 * 获取SAP入库上架单据
	 * @param request
	 *
	 * @return
	 */
	@POST("RESTAdapter/PDA/On_The_Shelf_Task_Query_Sender")
	Flowable<List<MaterialVoucher>> getShelvings(@Body ShelvingQueryRequest request);

}
