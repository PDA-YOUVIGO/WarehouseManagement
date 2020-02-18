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

package com.youvigo.wms.data.entities;

import com.youvigo.wms.data.dto.response.WarehouseInventoryQueryResponseDetails;

import java.util.List;

public class WarehouseInventoryQueryModelView {

    /// 凭证号
    private String IVNUM ;

    /// 凭证行记录
    private List<WarehouseInventoryQueryResponseDetails> lines ;

    public WarehouseInventoryQueryModelView() {
    }

    public WarehouseInventoryQueryModelView(String IVNUM, List<WarehouseInventoryQueryResponseDetails> lines) {
        this.IVNUM = IVNUM;
        this.lines = lines;
    }

    public String getIVNUM() {
        return IVNUM;
    }

    public void setIVNUM(String IVNUM) {
        this.IVNUM = IVNUM;
    }

    public List<WarehouseInventoryQueryResponseDetails> getLines() {
        return lines;
    }

    public void setLines(List<WarehouseInventoryQueryResponseDetails> lines) {
        this.lines = lines;
    }
}
