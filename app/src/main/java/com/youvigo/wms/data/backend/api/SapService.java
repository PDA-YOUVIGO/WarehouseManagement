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

import com.youvigo.wms.data.dto.request.DeliverQueryRequest;
import com.youvigo.wms.data.dto.request.DeliverRequest;
import com.youvigo.wms.data.dto.request.FinishedProductsQueryRequest;
import com.youvigo.wms.data.dto.request.InternalOrderRequest;
import com.youvigo.wms.data.dto.request.MaterialQueryRequest;
import com.youvigo.wms.data.dto.request.OnShevingRequest;
import com.youvigo.wms.data.dto.request.PositionMovementRequest;
import com.youvigo.wms.data.dto.request.PrintRequest;
import com.youvigo.wms.data.dto.request.ReservedOutBoundQueryRequest;
import com.youvigo.wms.data.dto.request.ReservedOutBoundRequest;
import com.youvigo.wms.data.dto.request.ShelvingQueryRequest;
import com.youvigo.wms.data.dto.request.WarehouseInventoryQueryRequest;
import com.youvigo.wms.data.dto.request.WarehouseInventoryRequest;
import com.youvigo.wms.data.dto.response.DeliverQueryResponse;
import com.youvigo.wms.data.dto.response.DeliverResponse;
import com.youvigo.wms.data.dto.response.FinishedProductsQueryResponse;
import com.youvigo.wms.data.dto.response.InternalOrderResponse;
import com.youvigo.wms.data.dto.response.MaterialQueryResult;
import com.youvigo.wms.data.dto.response.OnShevlingResponse;
import com.youvigo.wms.data.dto.response.PositionMovementResponse;
import com.youvigo.wms.data.dto.response.PrintResponse;
import com.youvigo.wms.data.dto.response.ReservedOutBoundQueryResponse;
import com.youvigo.wms.data.dto.response.ReservedOutBoundResponse;
import com.youvigo.wms.data.dto.response.ShelvingResult;
import com.youvigo.wms.data.dto.response.WarehouseInventoryQueryResponse;
import com.youvigo.wms.data.dto.response.WarehouseInventoryResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * SAP接口
 */
public interface SapService {

	/**
	 * 获取SAP入库上架单据
	 */
	@POST("RESTAdapter/PDA/On_The_Shelf_Task_Query_Sender")
	Call<ShelvingResult> queryOnShelvings(@Body ShelvingQueryRequest request);

	/**
	 * 入库上架
	 */
	@POST("RESTAdapter/PDA/On_The_Shelf")
	Call<OnShevlingResponse> submitOnSheving(@Body OnShevingRequest request);

	/**
	 * 获取SAP成品上架单据信息
	 */
	@POST("RESTAdapter/PDA/On_The_Shelf_Task_Query_Sender2")
	Call<FinishedProductsQueryResponse> queryFinishedProducts(@Body FinishedProductsQueryRequest request);

	/**
	 * 获取SAP出库下架任务单
	 */
	@POST("RESTAdapter/PDA/Shelf_Task_Query")
	Call<DeliverQueryResponse> queryDeliver(@Body DeliverQueryRequest request);

	/**
	 * 提交出库下架到SAP
	 */
	@POST("RESTAdapter/PDA/Off_The_Shelf")
	Call<DeliverResponse> submitDeliver(@Body DeliverRequest request);

	/**
	 * 获取SAP预留单据
	 */
	@POST("RESTAdapter/PDA/Reservation_Query")
	Call<ReservedOutBoundQueryResponse> queryReservedOutBound(@Body ReservedOutBoundQueryRequest request);

	/**
	 * 提交预留出库到SAP
	 */
	@POST("RESTAdapter/PDA/GI_With_Reservation")
	Call<ReservedOutBoundResponse> submitReservedOutBound(@Body ReservedOutBoundRequest request);

	/**
	 * 获取内部订单
	 */
	@POST("RESTAdapter/PDA/Internal_Order_Query")
	Single<InternalOrderResponse> queryInternalOrder(@Body InternalOrderRequest request);

	/**
	 * 获取物料信息
	 */
	@POST("RESTAdapter/PDA/Stock_Query")
	Flowable<MaterialQueryResult> materialQuery(@Body MaterialQueryRequest request);

	/**
	 * 仓位移动
	 */
	@POST("RESTAdapter/PDA/Stock_Transfer")
	Call<PositionMovementResponse> submitPositionTransfer(@Body PositionMovementRequest request);

	/**
	 * 盘点凭证查询
	 */
	@POST("RESTAdapter/PDA/Stock_Checking_Query")
	Call<WarehouseInventoryQueryResponse> queryWarehouseInventory(@Body WarehouseInventoryQueryRequest request);

	/**
	 * 打印有（无）预留出库单据
	 */
	@POST("RESTAdapter/PDA/GI_Print")
	Call<PrintResponse> submitPrint(@Body PrintRequest request);

	/**
	 * 盘点提交
	 */
	@POST("RESTAdapter/PDA/Stock_Checking_Input")
	Call<WarehouseInventoryResponse> submitWarehouseInventory(@Body WarehouseInventoryRequest request);

}
