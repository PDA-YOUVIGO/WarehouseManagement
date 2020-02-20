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
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.WarehouseInventoryRequest;
import com.youvigo.wms.data.dto.request.WarehouseInventoryRequestDetails;
import com.youvigo.wms.data.dto.response.WarehouseInventoryResponse;
import com.youvigo.wms.data.dto.response.WarehouseInventoryResponseResult;
import com.youvigo.wms.data.entities.WarehouseInventoryModelView;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WarehouseInventoryCommon {
    private Context context;
    private List<WarehouseInventoryModelView> data;

    public WarehouseInventoryCommon(Context context,List<WarehouseInventoryModelView> data) {
        this.context = context;
        this.data = data;
    }


    public void inventorySubmit()
    {
        if (!verify()){ return; }
        submit();
    }

    /**
     * 提交数据
     */
    private void submit(){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        SapService sapService = retrofitClient.getSapService();
        WarehouseInventoryRequest request = new WarehouseInventoryRequest();
        request.setControlInfo(new ControlInfo());
        request.setRequestDetails(proData(retrofitClient));
        Call<WarehouseInventoryResponse> call = sapService.submitWarehouseInventory(request);
        call.enqueue(new Callback<WarehouseInventoryResponse>() {
            @Override
            public void onResponse(@NotNull Call<WarehouseInventoryResponse> call, @NotNull Response<WarehouseInventoryResponse> response) {
                if (response.isSuccessful()) {
                    WarehouseInventoryResponse Response = response.body();
                    WarehouseInventoryResponseResult responseResult = Response.getData();
                    if (responseResult.getMSGTYPE().equalsIgnoreCase("E")) {
                        showMessage(responseResult.getMSGTXT());
                    } else if (responseResult.getMSGTYPE().equalsIgnoreCase("S")) {
                        showMessage(responseResult.getMSGTXT());
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<WarehouseInventoryResponse> call, @NotNull Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    /**
     * 构建数据
     * @param retrofitClient 通用处理类
     * @return List
     */
    private List<WarehouseInventoryRequestDetails> proData(RetrofitClient retrofitClient){
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
     * @return
     */
    private boolean verify() {
        if (data == null){
            showMessage("无数据提交");
            return false;
        }
        for (int i= 0; i < data.size();i++){
            if (data.get(i).getMENGA()==0){showMessage("请维护" + i+1 +"行明细数据的盘点数量再提交"); return false;}
//            if (data.get(i).getZZMENGE_AUXILIARY().isEmpty()){showMessage("请维护" + i+1 +"行明细数据的辅数量再提交");return false;}
        }
        return true;
    }

    /**
     * 消息提示
     * @param message 消息
     */
    private void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
