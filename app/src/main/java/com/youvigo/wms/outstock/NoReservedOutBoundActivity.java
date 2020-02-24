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

package com.youvigo.wms.outstock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.youvigo.wms.R;
import com.youvigo.wms.base.OnItemCompleted;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.entities.Employee;
import com.youvigo.wms.data.entities.InternalOrder;
import com.youvigo.wms.data.entities.MoveType;
import com.youvigo.wms.data.entities.NoReservedOutBoundDetail;
import com.youvigo.wms.data.entities.StockLocal;
import com.youvigo.wms.search.EmployeeSearchActivity;
import com.youvigo.wms.search.InternalOrderSearchActivity;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 无预留出库页面
 */
public class NoReservedOutBoundActivity extends AppCompatActivity implements OnItemCompleted {
	public static final int REQUEST_CODE = 301;
	public static final int EMPLOYEE_REQUEST_CODE = 302;
	public static final int INNER_ORDER_REQUEST_CODE = 303;
	public static final int MATERIAL_REQUEST_CODE = 304;

	private ProgressBar progressBar;
	protected Toolbar toolbar;
	private Spinner moveType;

	private TextView placeOfReceiptTitle;
	private Spinner placeOfReceipt;

	private TextView internalOrder;
	private TextView employer;
	private TextView department;

	private EditText remark;

	private MaterialButton innerOrderQuery;
	private MaterialButton employeeQuery;

	private NoReservedOutBoundAdapter adapter;
	private NoreservedOutBoundViewModel viewModel;

	private ArrayAdapter<MoveType> moveTypeAdapter;
	private ArrayAdapter<StockLocal> placeOfReceiptAdapter;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.no_reserved_out_bound_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getLayoutId());
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		initViews();
		loadSpinnerData();
		observeData();
	}

	private void initViews() {
		moveType = findViewById(R.id.sp_move_type);
		placeOfReceipt = findViewById(R.id.sp_place_of_receipt);
		placeOfReceiptTitle = findViewById(R.id.tv_place_of_receipt_description);

		// 移动类型改不隐藏收货地，只有311移动类型才显示收货地
		moveType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				MoveType adapterItem = moveTypeAdapter.getItem(position);
				if (adapterItem.getMoveCode().equals("311")) {
					placeOfReceiptTitle.setVisibility(View.VISIBLE);
					placeOfReceipt.setVisibility(View.VISIBLE);
				} else {
					// 默认隐藏收货地
					placeOfReceiptTitle.setVisibility(View.GONE);
					placeOfReceipt.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		internalOrder = findViewById(R.id.tv_internal_order);

		FloatingActionButton newDelivery = findViewById(R.id.fab_add);
		newDelivery.setOnClickListener(v -> {
			// 新增发货
			launchMaterialActivity();
		});

		employer = findViewById(R.id.tv_employer);
		department = findViewById(R.id.tv_department);

		// 收货地
		placeOfReceipt = findViewById(R.id.sp_place_of_receipt);
		remark = findViewById(R.id.et_remark);

		progressBar = findViewById(R.id.progress_bar);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		// 滑动删除
		new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
								  @NonNull RecyclerView.ViewHolder target) {
				return false;
			}

			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
				Utils.showDialog(NoReservedOutBoundActivity.this, "", "您确认要删除该行数据吗？",
						(dialog, which) -> {
							viewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
							adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
							Utils.showToast(NoReservedOutBoundActivity.this, "删除成功。");
						});
			}
		}).attachToRecyclerView(recyclerView);

		adapter = new NoReservedOutBoundAdapter();
		recyclerView.setAdapter(adapter);
	}

	private void launchMaterialActivity() {
		//FragmentManager fragmentManager = this.getSupportFragmentManager();
		//NoReservedOutBoundDetailDialogFragment.show(fragmentManager, null, -1);
		Intent intent = new Intent(this, NoReservedOutBoundDetailActivity.class);
		startActivityForResult(intent, MATERIAL_REQUEST_CODE);
	}

	private void launchEmployeeSearchActivity() {
		Intent intent = new Intent(this, EmployeeSearchActivity.class);
		startActivityForResult(intent, EMPLOYEE_REQUEST_CODE);
	}

	private void launchInternalOrderSearchActivity() {
		Intent intent = new Intent(this, InternalOrderSearchActivity.class);
		startActivityForResult(intent, INNER_ORDER_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (data != null) {
				if (requestCode == EMPLOYEE_REQUEST_CODE) {
					Employee currentEmployee = data.getParcelableExtra(Constants.EMPLOYEE_RESULT);
					employer.setText(currentEmployee.getEmployeeName());
					department.setText(currentEmployee.getDepartmentName());
				} else if (requestCode == INNER_ORDER_REQUEST_CODE) {
					// todo 逻辑
					InternalOrder currentInternalOrder = data.getParcelableExtra(Constants.INTERNAL_ORDER_RESULT);
					internalOrder.setText(currentInternalOrder.getDescription());
				} else if (requestCode == MATERIAL_REQUEST_CODE) {
					NoReservedOutBoundDetail noReservedOutBoundDetail =
							data.getParcelableExtra(NoReservedOutBoundDetailActivity.NO_RESERVED_OUT_BOUND_DETAIL_RESULT);
					viewModel.insert(noReservedOutBoundDetail);
				}
			}
		}

		super.onActivityResult(requestCode, resultCode, data);

	}

	protected int getLayoutId() {
		return R.layout.no_reserved_outbound_activity;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		} else if (item.getItemId() == R.id.menu_submit) {
			onMenuSubmitClicked();
		} else if (item.getItemId() == R.id.menu_employee_search) {
			launchEmployeeSearchActivity();
		} else if (item.getItemId() == R.id.menu_internal_order_search) {
			launchInternalOrderSearchActivity();
		}

		return true;
	}

	private void observeData() {
		viewModel = new ViewModelProvider.NewInstanceFactory().create(NoreservedOutBoundViewModel.class);
		viewModel.init();

		viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE :
				View.GONE));

		viewModel.details().observe(this, noReservedOutBoundDetails -> {
			adapter.submitList(noReservedOutBoundDetails);
			adapter.notifyDataSetChanged();
		});
	}

	private void onMenuSubmitClicked() {
		// 提交
	}

	@SuppressLint("CheckResult")
	private void loadSpinnerData() {

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		BackendApi backendApi = retrofitClient.getBackendApi();

		backendApi.getMoveTypes(retrofitClient.getFactoryCode())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<List<MoveType>>>() {
					@Override
					public void onSuccess(ApiResponse<List<MoveType>> listApiResponse) {
						moveTypeAdapter = new ArrayAdapter<>(getApplication(), R.layout.item_spinner,
								listApiResponse.getData());
						moveType.setAdapter(moveTypeAdapter);
					}

					@Override
					public void onError(Throwable e) {
						Utils.showToast(NoReservedOutBoundActivity.this, String.format("移动类型数据加载失败，%s",
								e.getMessage()));
					}
				});

		backendApi.getStockLocal(retrofitClient.getFactoryCode())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<List<StockLocal>>>() {
					@Override
					public void onSuccess(ApiResponse<List<StockLocal>> listApiResponse) {
						if (listApiResponse != null) {
							placeOfReceiptAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,
									listApiResponse.getData());
							placeOfReceipt.setAdapter(placeOfReceiptAdapter);
							placeOfReceipt.setSelection(Spinner.INVALID_POSITION, false);
						}
					}

					@Override
					public void onError(Throwable e) {
						Utils.showToast(NoReservedOutBoundActivity.this, String.format("目的库存地数据加载失败，%s",
								e.getMessage()));
					}
				});
	}


	@Override
	public void itemCompleted(int adapterPosition) {
		adapter.notifyItemChanged(adapterPosition);
	}
}
