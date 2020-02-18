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

package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.Additional;

import java.util.List;

public class WarehouseInventoryQueryResponseReturn {
    @SerializedName("ITEM")
    private List<WarehouseInventoryQueryResponseDetails> data ;

    /// 附加字段
    private Additional ADDITIONAL;

    private WarehouseInventoryQueryResponseMessage RETURN ;

    public WarehouseInventoryQueryResponseReturn() {
    }

    public WarehouseInventoryQueryResponseReturn(List<WarehouseInventoryQueryResponseDetails> data, Additional ADDITIONAL, WarehouseInventoryQueryResponseMessage RETURN) {
        this.data = data;
        this.ADDITIONAL = ADDITIONAL;
        this.RETURN = RETURN;
    }

    public List<WarehouseInventoryQueryResponseDetails> getData() {
        return data;
    }

    public void setData(List<WarehouseInventoryQueryResponseDetails> data) {
        this.data = data;
    }

    public Additional getADDITIONAL() {
        return ADDITIONAL;
    }

    public void setADDITIONAL(Additional ADDITIONAL) {
        this.ADDITIONAL = ADDITIONAL;
    }

    public WarehouseInventoryQueryResponseMessage getRETURN() {
        return RETURN;
    }

    public void setRETURN(WarehouseInventoryQueryResponseMessage RETURN) {
        this.RETURN = RETURN;
    }
}
