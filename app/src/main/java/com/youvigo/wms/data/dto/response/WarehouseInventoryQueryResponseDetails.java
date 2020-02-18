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

public class WarehouseInventoryQueryResponseDetails {

    /// <summary>
    /// 系统盘点记录号
    /// </summary>
    private String IVNUM ;
    /// <summary>
    /// 库存文件项目号
    /// </summary>
    private String IVPOS ;
    /// <summary>
    /// 仓位
    /// </summary>
    private String LGPLA ;

    public WarehouseInventoryQueryResponseDetails() {
    }

    public WarehouseInventoryQueryResponseDetails(String IVNUM, String IVPOS, String LGPLA) {
        this.IVNUM = IVNUM;
        this.IVPOS = IVPOS;
        this.LGPLA = LGPLA;
    }

    public String getIVNUM() {
        return IVNUM;
    }

    public void setIVNUM(String IVNUM) {
        this.IVNUM = IVNUM;
    }

    public String getIVPOS() {
        return IVPOS;
    }

    public void setIVPOS(String IVPOS) {
        this.IVPOS = IVPOS;
    }

    public String getLGPLA() {
        return LGPLA;
    }

    public void setLGPLA(String LGPLA) {
        this.LGPLA = LGPLA;
    }
}
