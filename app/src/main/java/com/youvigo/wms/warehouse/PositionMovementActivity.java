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
import com.youvigo.wms.data.entities.PositionMovementModelView;

import java.util.List;

/**
 * 仓位移动
 */
public class PositionMovementActivity extends BaseActivity implements PositionMovementDialogFragment.OnPositionInforCompleted{

    private ProgressBar progressBar;
    private PositionMovementAdapter adapter;
    private PositionMovementViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.position_movement_menu, menu);
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
        else if (item.getItemId() == R.id.position_submit) {
            onMenuSubmitClicked();
        }
        return true;
    }

    /**
     * 提交数据
     */
    protected  void onMenuSubmitClicked(){
        showMessage("我被点击了");
        if(!verify()){ return; }
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
        for (int i= 0; i <= list.size();i++){
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
        viewModel.query(
                et_material_coding.getText() == null ? "" : et_material_coding.getText().toString(),
                et_material_batch.getText() == null ? "" : et_material_batch.getText().toString(),
                et_position.getText() == null ? "" : et_position.getText().toString());
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
