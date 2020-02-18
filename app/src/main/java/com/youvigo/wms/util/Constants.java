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

package com.youvigo.wms.util;

public class Constants {

    // 扫描广播
    public static final String BROADCAST_ENABLE = "enable_disable_barcode";
    public static final String BROADCAST_RESULT = "nlscan.action.SCANNER_RESULT";

    public static final String SAP_URL = "http://52.82.87.90:50000/";
    public static final String PDA_URL = "http://52.83.32.16:8081/";
    public static final String SAP_USERNAME = "zengzx";
    public static final String SAP_PASSWORD = "abcd1234";
    public static final String ACCOUNT = "account";
    public static final String USERNAME = "username";

    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
    public static final String SAP_ADDRESS = "sap_address";
    public static final String PDA_ADDRESS = "pda_address";
    public static final String FACTORYCODE = "factory_code";
    public static final String STOCKLOCATION = "stocklocation";
    public static final String WAREHOUSE_NUMBER = "warehouseNumber";
    public static final int TIME_OUT = 60;

    public static final String SEARCH_RESULT = "search_result";
    public static final String MATERIAL_SEARCH_RESULT = "material_search_result";
    public static final String CATEGORY = "category";
    public static final String TYPE_SHELVING = "type_shelving";
    public static final String TYPE_WAREHOUSE_INVENTORY= "type_warehouse_inventory";
    public static final String WAREHOUSE_INVENTORY_DETAILS_RESULT= "warehouse_inventory_details_result";
    public static final String WAREHOUSE_INVENTORY_RESULT= "warehouse_inventory_result";

    /**
     * 出库下架
     */
    public static final String TYPE_DELIVER = "type_deliver";
    public static final String TYPE_RESERVED_OUT_BOUND = "type_reserved_out_bound";

    public static final String TYPE_NO_RESERVED_OUTBOUND = "no_reserved_outbound";
    public static final String TYPE_WAREHOURE_INVENTORY = "warehouse_inventory";
    public static final String TYPE_POSITION_MOVEMENT = "position_movement";
    public static final String TYPE_INVENTORY_CHECK = "inventory_check";
    public static final String TYPE_SWITCH_STOCK_LOCATIONS = "switch_stock_locations";
}
