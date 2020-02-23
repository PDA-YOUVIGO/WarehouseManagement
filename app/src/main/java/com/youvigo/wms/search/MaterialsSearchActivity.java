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

package com.youvigo.wms.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.base.BaseActivity;
import com.youvigo.wms.util.Constants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * 物料查询
 */
public class MaterialsSearchActivity extends BaseActivity implements MaterialSearchDialogFragment.OnMaterialSearchInforCompleted {

	public static final String KEY_MATERIAL_CODE = "key_material_code";
	public static final String KEY_BATCH_NUMBER = "key_batch_number";
	public static final String KEY_MATERIAL_DESCRIPTION = "key_material_description";
	public static final String KEY_MATERIAL_COMMONNAME = "key_material_commonname";
	public static final String KEY_SPECIFICATION = "key_specification";
	public static final String KEY_CARGOCODE = "key_cargoCode";
	public static final String KEY_SELECTALL_MENU = "key_selectall_menu"; //控制全选按钮显示 True 显示，False 不显示
	public static final String KEY_INITQUERY = "key_initquery"; // 控制初始化查询
	public static final String KEY_FILTER = "key_filter";
	public static final String KEY_CARGOSPACETYPE_FILTER = "key_cargospacetype_filter";

	/**
	 * 过滤规则
	 */
	private List<String> filter = null;

	/**
	 * 仓位类型过滤正则
	 */
	private String cargoSpaceTypeFilter = null;

	private ProgressBar progressBar;
	private MaterialsSearchAdapter adapter;
	private MaterialSearchViewModel viewModel;
	private Boolean displaySelectALlMenu;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViews();
		observeData();
		initIntent();
	}

	/**
	 * 初始化传入参数
	 */
	private void initIntent() {
		Intent intent = getIntent();
		if (intent != null) {
			String materialCode = intent.getStringExtra(KEY_MATERIAL_CODE);
			String batchNumber = intent.getStringExtra(KEY_BATCH_NUMBER);
			String materialDescription = intent.getStringExtra(KEY_MATERIAL_DESCRIPTION);
			String materialCommonName = intent.getStringExtra(KEY_MATERIAL_COMMONNAME);
			String specification = intent.getStringExtra(KEY_SPECIFICATION);
			String cargoCode = intent.getStringExtra(KEY_CARGOCODE);
			displaySelectALlMenu = intent.getBooleanExtra(KEY_SELECTALL_MENU, false);

			// 物料过滤规则
			filter = intent.getStringArrayListExtra(KEY_FILTER);
			cargoSpaceTypeFilter = intent.getStringExtra(KEY_CARGOSPACETYPE_FILTER);

			// 初始化过滤参数，如果未传递参数则赋予默认值
			if (filter == null) {
				filter = new ArrayList<>();
			} else if (cargoSpaceTypeFilter == null) {
				cargoSpaceTypeFilter = "";
			}

			if (intent.getBooleanExtra(KEY_INITQUERY, true)) {

				viewModel.query(materialCode, batchNumber, materialDescription, materialCommonName, specification,
						cargoCode, filter, cargoSpaceTypeFilter);
			}
		}
	}

	private void initViews() {
		progressBar = findViewById(R.id.progress_bar);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new MaterialsSearchAdapter();
		recyclerView.setAdapter(adapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Timber.d("onActivityResult");
	}

	@Override
	protected void onMenuSearchClicked() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		MaterialSearchDialogFragment.show(fragmentManager);
	}

	@Override
	protected int getLayoutId() { return R.layout.materials_search_activity; }

	/**
	 * 点击事件
	 *
	 * @param item menu
	 *
	 * @return Boolean
	 */
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		} else if (item.getItemId() == R.id.menu_search) {
			onMenuSearchClicked();
		} else if (item.getItemId() == R.id.tv_check_all) {
			onMenuCheckAllClicked();
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (displaySelectALlMenu) {
			getMenuInflater().inflate(R.menu.check_all_menu, menu);
		}
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 全选
	 */
	protected void onMenuCheckAllClicked() {
		if (viewModel.materials().getValue() != null && viewModel.materials().getValue().size() <= 0) {
			return;
		}
		Intent intent = new Intent();
		intent.putParcelableArrayListExtra(Constants.MATERIAL_SEARCH_RESULT,
				(ArrayList<? extends Parcelable>) viewModel.materials().getValue());
		this.setResult(Activity.RESULT_OK, intent);
		this.finish();
	}

	/**
	 * 接口查询
	 *
	 * @param materialCode         物料编码
	 * @param batchNumber          批次号
	 * @param materialDescription  物料描述
	 * @param materialCommonName   通用名称
	 * @param specification        规格
	 * @param cargoCode            仓位
	 * @param materilaFilter       过滤物料 <p>"" 正常库存， Q 质检控制中的库存， R 退回库存， S 已冻结的库存</p>
	 * @param cargoSpaceTypeFilter 仓位类型过滤规则
	 */
	@Override
	public void inputMaterialInforCompleted(String materialCode, String batchNumber, String materialDescription,
											String materialCommonName, String specification, String cargoCode,
											List<String> materilaFilter, String cargoSpaceTypeFilter) {
		Timber.d(materialCode);
		viewModel.query(materialCode, batchNumber, materialDescription, materialCommonName, specification, cargoCode,
				materilaFilter, cargoSpaceTypeFilter);
	}

	private void observeData() {
		viewModel = new ViewModelProvider.NewInstanceFactory().create(MaterialSearchViewModel.class);
		viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE :
				View.GONE));

		viewModel.materials().observe(this, material -> adapter.submitList(material));

		viewModel.getQueryState().observe(this, queryState -> {
			if (queryState == null) return;
			if (!queryState.isSuccess()) {
				Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
	}


}
