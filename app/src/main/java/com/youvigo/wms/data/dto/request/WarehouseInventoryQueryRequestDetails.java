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

public class WarehouseInventoryQueryRequestDetails {

    /// 仓库号
    public String LGNUM ;

    /// 查找盘点日期-起始
    public String SPDATU ;

    /// 查找盘点日期-结束
    public String EPDATU ;

    /// 凭证号
    public String IVNUM ;

    public WarehouseInventoryQueryRequestDetails() {
    }

    public WarehouseInventoryQueryRequestDetails(String LGNUM, String SPDATU, String EPDATU, String IVNUM) {
        this.LGNUM = LGNUM;
        this.SPDATU = SPDATU;
        this.EPDATU = EPDATU;
        this.IVNUM = IVNUM;
    }

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
    }

    public String getSPDATU() {
        return SPDATU;
    }

    public void setSPDATU(String SPDATU) {
        this.SPDATU = SPDATU;
    }

    public String getEPDATU() {
        return EPDATU;
    }

    public void setEPDATU(String EPDATU) {
        this.EPDATU = EPDATU;
    }

    public String getIVNUM() {
        return IVNUM;
    }

    public void setIVNUM(String IVNUM) {
        this.IVNUM = IVNUM;
    }
}
