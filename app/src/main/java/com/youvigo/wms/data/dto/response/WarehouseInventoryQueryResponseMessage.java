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

public class WarehouseInventoryQueryResponseMessage {

    /// 消息类型
    private String MSGTYPE;

    /// 消息文本
    private String MSGTXT;

    /// 仓库
    private String LGNUM;

    /// 转储单编号
    private String TANUM;

    /// 物料凭证编号
    private String MBLNR;

    /// 物料凭证年度
    private String MJAHR;

    public WarehouseInventoryQueryResponseMessage() {
    }

    public WarehouseInventoryQueryResponseMessage(String MSGTYPE, String MSGTXT, String LGNUM, String TANUM, String MBLNR, String MJAHR) {
        this.MSGTYPE = MSGTYPE;
        this.MSGTXT = MSGTXT;
        this.LGNUM = LGNUM;
        this.TANUM = TANUM;
        this.MBLNR = MBLNR;
        this.MJAHR = MJAHR;
    }

    public String getMSGTYPE() {
        return MSGTYPE;
    }

    public void setMSGTYPE(String MSGTYPE) {
        this.MSGTYPE = MSGTYPE;
    }

    public String getMSGTXT() {
        return MSGTXT;
    }

    public void setMSGTXT(String MSGTXT) {
        this.MSGTXT = MSGTXT;
    }

    public String getLGNUM() {
        return LGNUM;
    }

    public void setLGNUM(String LGNUM) {
        this.LGNUM = LGNUM;
    }

    public String getTANUM() {
        return TANUM;
    }

    public void setTANUM(String TANUM) {
        this.TANUM = TANUM;
    }

    public String getMBLNR() {
        return MBLNR;
    }

    public void setMBLNR(String MBLNR) {
        this.MBLNR = MBLNR;
    }

    public String getMJAHR() {
        return MJAHR;
    }

    public void setMJAHR(String MJAHR) {
        this.MJAHR = MJAHR;
    }
}
