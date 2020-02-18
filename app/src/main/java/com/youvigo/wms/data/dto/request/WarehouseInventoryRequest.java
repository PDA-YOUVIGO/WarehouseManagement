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

package com.youvigo.wms.data.dto.request;

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.ControlInfo;

public class WarehouseInventoryRequest {

    @SerializedName("ControlInfo")
    private ControlInfo controlInfo;

    @SerializedName("ITEM")
    private WarehouseInventoryRequestDetails requestDetails;

    public WarehouseInventoryRequest() {
    }

    public WarehouseInventoryRequest(ControlInfo controlInfo, WarehouseInventoryRequestDetails requestDetails) {
        this.controlInfo = controlInfo;
        this.requestDetails = requestDetails;
    }

    public ControlInfo getControlInfo() {
        return controlInfo;
    }

    public void setControlInfo(ControlInfo controlInfo) {
        this.controlInfo = controlInfo;
    }

    public WarehouseInventoryRequestDetails getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(WarehouseInventoryRequestDetails requestDetails) {
        this.requestDetails = requestDetails;
    }
}