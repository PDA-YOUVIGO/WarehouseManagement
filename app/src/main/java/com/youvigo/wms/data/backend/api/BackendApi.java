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
import com.youvigo.wms.data.dto.response.CargoLocation;
import com.youvigo.wms.data.dto.response.Material;
import com.youvigo.wms.data.entities.MoveType;
import com.youvigo.wms.data.entities.StockLocal;

import java.util.List;

import io.reactivex.Observable;
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
	Call<ApiResponse<List<CargoLocation>>> verificationCargo(@Query("warehouseCode") String warehouseCode, @Query("cargoLocation") String cargoLocation);

	/**
	 * 获取物料单位
	 * @param matnr 物料编码
	 */
	@GET("blade-data/api/material/detail")
	Call<ApiResponse<List<Material>>> getMaterial(@Query("matnr") String matnr);

	/**
	 * 获取移动类型参照
	 * @param factoryCode 工厂编码
	 */
	@GET("blade-data/api/movetype/list")
	Call<ApiResponse<List<MoveType>>> getMoveTypes(@Query("factoryCode") String factoryCode);

	/**
	 * 获取库存地参照
	 * @param factoryCode 工厂编码
	 */
	@GET("blade-data/api/stocklocaltion/list")
	Observable<ApiResponse<StockLocal>> getStockLocal(@Query("factoryCode") String factoryCode);
}
