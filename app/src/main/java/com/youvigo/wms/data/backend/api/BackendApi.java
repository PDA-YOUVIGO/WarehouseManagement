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

package com.youvigo.wms.data.backend.api;

import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.dto.response.CargoLocationResponse;
import com.youvigo.wms.data.dto.response.Material;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * PDA后台接口
 */
public interface BackendApi {

	/**
	 * 货位验证
	 * @param warehouseCode 仓库编码
	 * @param cargoLocation 货位编码
	 */
	@GET("blade-data/api/cargolocation/detail")
	Call<ApiResponse<CargoLocationResponse>> verificationCargo(@Query("warehouseCode") String warehouseCode, @Query("cargoLocation") String cargoLocation);

	/**
	 * 获取物料单位
	 * @param matnr 物料编码
	 */
	@GET("blade-data/api/material/detail")
	Call<ApiResponse<List<Material>>> getMaterial(@Query("matnr") String matnr);
}