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

package com.youvigo.wms.warehouse;

import android.content.Context;
import android.widget.Toast;

import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.request.WarehouseInventoryRequestDetails;
import com.youvigo.wms.data.entities.WarehouseInventoryModelView;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class WarehouseInventoryCommon {
    private Context context;

    public WarehouseInventoryCommon(Context context) {
        this.context = context;
    }

    /**
     * 构建数据
     * @param retrofitClient 通用处理类
     * @return List
     */
    public List<WarehouseInventoryRequestDetails> proData(RetrofitClient retrofitClient,List<WarehouseInventoryModelView> data){
        List<WarehouseInventoryRequestDetails> details = new ArrayList<>();
        for (WarehouseInventoryModelView m : data) {
            WarehouseInventoryRequestDetails requestItem = new WarehouseInventoryRequestDetails();
            requestItem.setADDFLAG(m.getADDFLAG()); // 盘点标识
            requestItem.setCHARG(m.getCHARG()); // 批号
            requestItem.setIVNUM(m.getIVNUM()); // 凭证号
            requestItem.setIVPOS(m.getIVPOS() ==null ? "": m.getIVPOS()); // 盘点凭证行项目
            requestItem.setLGNUM(m.getLGNUM()); // 仓库号
            requestItem.setMAKTX(m.getMAKTX()); // 物料描述
            requestItem.setMATNR(m.getMATNR()); //物料编码
            requestItem.setMEINS(m.getMEINS()); // 基本单位
            requestItem.setMENGA(m.getMENGA()); // 盘点数量
            requestItem.setWERKS(m.getWERKS()); // 工厂
            requestItem.setLGPLA(m.getLGPLA()); // 仓位
            requestItem.setLGORT(retrofitClient.getStockLocationCode()); // 库存地
            requestItem.setBESTQ(m.getBESTQ()); // 库存类别
            requestItem.setZOPERC(retrofitClient.getAccount()); // 用户
            requestItem.setZOPERN(retrofitClient.getUserName()); //用户名称
            requestItem.setZOPERT(LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN))); //操作时间
            //备用字段
            Additional addotonal = new Additional();
            addotonal.setAddit1("");
            addotonal.setAddit2("");
            addotonal.setAddit3("");
            addotonal.setAddit4("");
            addotonal.setAddit5("");
            requestItem.setADDITIONAL(addotonal);
            details.add(requestItem);
        }
        return details;
    }

    /**
     * 数据验证
     * @return Boolean
     */
    public boolean verify(List<WarehouseInventoryModelView> data) {
        boolean flag = true;
        if (data == null || data.size() ==0){
            Utils.showDialog(context, "数据校验","无数据提交","确定",(dialog, which) -> dialog.dismiss());
            flag = false;
        }
        for (int i= 0; i < data.size();i++){
            if (data.get(i).getMENGA()==0){
                flag = false;
                Utils.showDialog(context, "数据校验","请维护" + i+1 +"行明细数据的盘点数量再提交","确定",(dialog, which) -> dialog.dismiss());
                break;
            }
        }
        return flag;
    }

}
