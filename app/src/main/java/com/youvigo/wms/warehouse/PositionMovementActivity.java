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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.Additional;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.PositionMovementRequest;
import com.youvigo.wms.data.dto.request.PositionMovementRequestDetails;
import com.youvigo.wms.data.dto.response.PositionMovementResponse;
import com.youvigo.wms.data.dto.response.PositionMovementResponseDetails;
import com.youvigo.wms.data.entities.PositionMovementModelView;
import com.youvigo.wms.data.entities.StockMaterial;
import com.youvigo.wms.search.MaterialsSearchActivity;
import com.youvigo.wms.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * 仓位移动
 */
public class PositionMovementActivity extends BaseActivity implements PositionMovementDialogFragment.OnPositionInforCompleted{
    protected static final int REQUEST_CODE = 110;
    private ProgressBar progressBar;
    private PositionMovementAdapter adapter;
    private PositionMovementViewModel viewModel;
    private RecyclerView recyclerView;
    protected List<PositionMovementModelView> positionResult = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        observeData();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PositionMovementAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(PositionMovementViewModel.class);
        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.positions().observe(this, position-> adapter.submitList(position));
        viewModel.getQueryState().observe(this, queryState ->{
            if (queryState == null) return;
            if (!queryState.isSuccess()) {
                Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
            }
        } );
    }

    /**
     * 获取当前LayoutID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.position_movement_activity;
    }

    /**
     * 点击事件
     * @param item menu
     * @return Boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }  else if (item.getItemId() == R.id.menu_search) {
            onMenuSearchClicked();
        }
        else if (item.getItemId() == R.id.tv_submit) {
            onMenuSubmitClicked();
        }
        return true;
    }

    /**
     * 提交数据
     */
    protected  void onMenuSubmitClicked(){
        if(!verify()){ return; }
        positionSubmitBatchData();
    }

    /**
     * 批量提交数据
     */
    private void positionSubmitBatchData(){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        SapService sapService = retrofitClient.getSapService();
        List<PositionMovementModelView> list = viewModel.positions().getValue();
        PositionMovementRequest request = new  PositionMovementRequest();
        request.setControlInfo(new ControlInfo());
        List<PositionMovementRequestDetails> details_list = new ArrayList<>();
        for (PositionMovementModelView p : list) {
            details_list.add(ProData(p,retrofitClient));
        }
        request.setData(details_list);
        Call<PositionMovementResponse> onPOsitionResponseCall = sapService.submitPositionTransfer(request);
        onPOsitionResponseCall.enqueue(new Callback<PositionMovementResponse>() {
            @Override
            public void onResponse(@NotNull Call<PositionMovementResponse> call, @NotNull Response<PositionMovementResponse> response) {
                if (response.isSuccessful()) {
                    PositionMovementResponse onPositinResponse = response.body();
                    PositionMovementResponseDetails responseDetails = onPositinResponse.getPositionMovementResponseDetails();
                    if (responseDetails.getMSGTYPE().equalsIgnoreCase("E")) {
                        showMessage(responseDetails.getMSGTXT());
                    } else if (responseDetails.getMSGTYPE().equalsIgnoreCase("S")) {
                        showMessage(responseDetails.getMSGTXT());
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<PositionMovementResponse> call, @NotNull Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    /**
     * 循环提交数据
     */
    private void positionSubmitData()
    {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        SapService sapService = retrofitClient.getSapService();
        List<PositionMovementModelView> list = viewModel.positions().getValue();
        for (PositionMovementModelView p : list) {
            PositionMovementRequest request = new  PositionMovementRequest();
            request.setControlInfo(new ControlInfo());
            List<PositionMovementRequestDetails> details_list = new ArrayList<>();
            details_list.add(ProData(p,retrofitClient));
            request.setData(details_list);
            Call<PositionMovementResponse> onPOsitionResponseCall = sapService.submitPositionTransfer(request);
            onPOsitionResponseCall.enqueue(new Callback<PositionMovementResponse>() {
                @Override
                public void onResponse(@NotNull Call<PositionMovementResponse> call, @NotNull Response<PositionMovementResponse> response) {
                    if (response.isSuccessful()) {
                        PositionMovementResponse onPositinResponse = response.body();
                        PositionMovementResponseDetails responseDetails = onPositinResponse.getPositionMovementResponseDetails();
                        if (responseDetails.getMSGTYPE().equalsIgnoreCase("E")) {
                            showMessage(responseDetails.getMSGTXT());
                        } else if (responseDetails.getMSGTYPE().equalsIgnoreCase("S")) {
                            viewModel.positions().getValue().clear();
                            adapter.notifyDataSetChanged();
                            showMessage(responseDetails.getMSGTXT());
                        }
                    }
                }
                @Override
                public void onFailure(@NotNull Call<PositionMovementResponse> call, @NotNull Throwable t) {
                    showMessage(t.getMessage());
                }
            });
        }
    }

    private PositionMovementRequestDetails ProData(PositionMovementModelView p,RetrofitClient retrofitClient)
    {
        PositionMovementRequestDetails details = new PositionMovementRequestDetails();
        details.setLGNUM(p.getLGNUM()); ;// 仓库号
        details.setTAPOS(p.getTAPOS());// 项目行号
        details.setMATNR(p.getMATNR());  // 物料编号
        details.setMAKTX(p.getMAKTX());  // 物料描述
        details.setWERKS(p.getWERKS());// 工厂
        details.setLGORT(retrofitClient.getStockLocationCode()); // 库存地点
        details.setCHARG(p.getCHARG()); // 批号
        details.setMEINS(p.getMEINS()); //基本单位
        details.setVSOLM(p.getVSOLM()); //基本单位数量
        details.setALTME(""); //辅助单位query.currentRow.ALTME
        details.setVSOLA(""); //辅助单位数量query.currentRow.VSOLA
        details.setVLTYP(p.getVLTYP()); //下架仓储类型query.currentRow.VLTYP
        details.setNLTYP(p.getNLTYP());// 上架仓储类型query.currentRow.NLTYP
        details.setNLPLA(p.getNLPLA()); //上架仓位
        details.setVLPLA(p.getVLPLA()); // 下架仓位
        details.setBESTQ(p.getBESTQ());
        details.setZZPACKAGING(p.getZZPACKAGING());// 是否合箱
        details.setZZLICHA_MAIN(p.getZZLICHA_MAIN()); // 主批次
        details.setZZMENGE_MAIN(p.getZZMENGE_MAIN()); // 主批次数量
        details.setZZLICHA_AUXILIARY(p.getZZLICHA_AUXILIARY()); // 辅批次
        details.setZZMENGE_AUXILIARY(p.getZZMENGE_AUXILIARY()); // 辅批次数量

        details.setZOPERC(retrofitClient.getAccount()); //用户
        details.setZOPERN(retrofitClient.getUserName()) ; //用户名称
        details.setZOPERT(LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN)));
        //备用字段
        Additional addotonal = new Additional();
        addotonal.setAddit1("");
        addotonal.setAddit2("");
        addotonal.setAddit3("");
        addotonal.setAddit4("");
        addotonal.setAddit5("");
        details.setADDITIONAL(addotonal) ;
        return details;
    }



    /**
     * 数据校验
     */
    private boolean verify() {
        List<PositionMovementModelView> list = viewModel.positions().getValue();
        if (list == null){
            showMessage("无数据提交");
            return false;
        }
        for (int i= 0; i < list.size();i++){
            if (list.get(i).VSOLM.isEmpty()){showMessage("请维护" + i+1 +"行明细数据的上架数量再提交"); return false;}
            if (list.get(i).NLPLA.isEmpty()){showMessage("请维护" + i+1 +"行明细数据的上架货位再提交");return false;}
            if (list.get(i).NLTYP.isEmpty()){showMessage("请维护" + i+1 +"行明细数据的上架货位类型再提交");return false;}
        }
        return true;
    }

    /**
     * 查询数据
     */
    @Override
    protected void onMenuSearchClicked() {
        TextView et_material_coding = findViewById(R.id.et_material_coding);
        TextView et_material_batch = findViewById(R.id.et_material_batch);
        TextView et_position = findViewById(R.id.et_position);
        if (et_material_coding.getText().toString().isEmpty() && et_material_batch.getText().toString().isEmpty()&&et_position.getText().toString().isEmpty()){
            showMessage("请维护查询参数");
            return;
        }
        Intent intent = new Intent(this, MaterialsSearchActivity.class);
        intent.putExtra(MaterialsSearchActivity.KEY_CARGOCODE, et_position.getText().toString()); // 仓位
        intent.putExtra(MaterialsSearchActivity.KEY_MATERIAL_CODE, et_material_coding.getText().toString()); // 物料编码
        intent.putExtra(MaterialsSearchActivity.KEY_BATCH_NUMBER,et_material_batch.getText().toString()); // 物料批号
        intent.putExtra(MaterialsSearchActivity.KEY_CARGOSPACETYPE_FILTER,"^9[\\d]+$");
        intent.putExtra(MaterialsSearchActivity.KEY_SELECTALL_MENU,true);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    List<StockMaterial> stockMaterial = data.getParcelableArrayListExtra(Constants.MATERIAL_SEARCH_RESULT);
                    processorData(positionResult,stockMaterial);
                    Timber.d("onActivityResult material:%s", positionResult.toString());
                }
            }
        }
        if (positionResult != null) {
            viewModel.handleData(positionResult);
        }
    }

    private void processorData(List<PositionMovementModelView> positionResult, List<StockMaterial> StockMaterials){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        for (StockMaterial m : StockMaterials) {
            PositionMovementModelView position = new PositionMovementModelView();
            position.setLGNUM(m.getWarehouseNumber()); //仓库号
            position.setTAPOS(m.getZZAUFNR()); // 行项目号
            position.setMATNR(m.getMaterialCode()); // 物料编码
            position.setZZCOMMONNAME(m.getMaterialCommonName());// 物料通用名称
            position.setMAKTX(m.getMaterialDescription());// 物料描述
            position.setWERKS(m.getFactoryCode());// 工厂
            position.setLGORT(retrofitClient.getStockLocationCode());// 库存地点
            position.setCHARG( m.getBatchNumber());// 批号
            position.setMEINS_TXT(m.getBaseUnitTxt()); //基本单位文本
            position.setMEINS(m.getBaseUnit()); //基本单位
            position.setVSOLM(""); //基本单位数量
            position.setALTME(""); //辅助单位
            position.setVSOLA(""); //辅助单位数量
            position.setVLTYP(m.getLGTYP()); // 下架仓位类型
            position.setNLTYP(""); // 上架仓位类型
            position.setNLPLA(""); // 上架仓位
            position.setVLPLA(m.getCargoSpace()); // 下架仓位
            position.setZZPACKAGING(m.getZZPACKAGING()); // 是否合箱
            position.setZZLICHA_MAIN(m.getZZLICHA_MAIN()); // 主批次
            position.setZZMENGE_MAIN(m.getZZMENGE_MAIN()); // 主批次数量
            position.setZZLICHA_AUXILIARY(m.getZZLICHA_AUXILIARY()); // 辅批次
            position.setZZMENGE_AUXILIARY(m.getZZMENGE_AUXILIARY()); // 辅批次数量
            position.setZZLICHA(m.getZZLICHA()); // 供应商批次
            position.setVLTYP(m.getZZLICHA()); // 下架仓位类型
            position.setVERME(m.getActualInventory()); // 可用库存量
            position.setZZDRUGSPEC(m.getSpecification()); // 规格
            position.setBESTQ(m.getBESTQ()); // 库存类别
            positionResult.add(position);
        }
    }

    /**
     * 更新item
     * @param adapterPosition adapterPosition位置
     */
    @Override
    public void inputPositionInforCompleted(int adapterPosition) {
        adapter.notifyItemChanged(adapterPosition);
    }

    /**
     * 消息提示
     * @param message 消息
     */
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
