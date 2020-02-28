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

package com.youvigo.wms.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.youvigo.wms.MainActivity;
import com.youvigo.wms.R;
import com.youvigo.wms.SettingsActivity;
import com.youvigo.wms.data.AppDatabase;
import com.youvigo.wms.data.LocalRepository;
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.dao.StoreDao;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.entities.StoreEntity;
import com.youvigo.wms.login.adapter.LoginStockLocationAdapter;
import com.youvigo.wms.login.adapter.LoginFactoryAdapter;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

	private LoginViewModel loginViewModel;
	private Spinner factorySp;
	private Spinner storeLocationSp;
	protected Toolbar toolbar;

	private LoginStockLocationAdapter stockLocationAdapter;
	private LoginFactoryAdapter factoryAdapter;

	private List<StoreEntity> stockLocationEntity = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		init();

		InitApplication();

		loginViewModel = new ViewModelProvider.NewInstanceFactory().create(LoginViewModel.class);

		final EditText usernameEditText = findViewById(R.id.username);
		final EditText passwordEditText = findViewById(R.id.password);
		final Button loginButton = findViewById(R.id.loginBt);
		final ProgressBar loadingProgressBar = findViewById(R.id.loading);
		factorySp = findViewById(R.id.factorySp);
		storeLocationSp = findViewById(R.id.stockLocationSp);

		processSpinner();

		// 处理用户密码字段验证
		loginViewModel.getLoginFormState().observe(this, loginFormState -> {
			if (loginFormState == null) {
				return;
			}

			loginButton.setEnabled(loginFormState.isDataValid());

			if (loginFormState.getUsernameError() != null) {
				usernameEditText.setError(getString(loginFormState.getUsernameError()));
			}

			if (loginFormState.getPasswordError() != null) {
				passwordEditText.setError(getString(loginFormState.getPasswordError()));
			}

		});

		loginViewModel.getLoginResult().observe(this, loginResult -> {
			if (loginResult == null) {
				return;
			}
			loadingProgressBar.setVisibility(View.GONE);

			if (loginResult.getError() != null) {
				Utils.showToast(LoginActivity.this, loginResult.getError().getMessage());
				return;
			}

			if (loginResult.getSuccess() != null) {
				loginSuccess(loginResult.getSuccess());
				setResult(Activity.RESULT_OK);

				//Complete and destroy login activity once successful
				finish();
			}
		});

		TextWatcher afterTextChangedListener = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// ignore
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// ignore
			}

			@Override
			public void afterTextChanged(Editable s) {
				loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
						passwordEditText.getText().toString());
			}
		};
		usernameEditText.addTextChangedListener(afterTextChangedListener);
		passwordEditText.addTextChangedListener(afterTextChangedListener);

		// 绑定键盘Done按键
		passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
			if (actionId == EditorInfo.IME_ACTION_DONE) {

				StoreEntity storeEntity = (StoreEntity) storeLocationSp.getSelectedItem();

				loginViewModel.loginIn(usernameEditText.getText().toString(),
						passwordEditText.getText().toString(),
						storeEntity.getFactoryCode(),
						storeEntity.getStockLocationCode());
			}

			return false;
		});

		//登录按钮监听事件
		loginButton.setOnClickListener(v -> {
			loadingProgressBar.setVisibility(View.VISIBLE);
			StoreEntity storeEntity = (StoreEntity) storeLocationSp.getSelectedItem();
			loginViewModel.loginIn(usernameEditText.getText().toString(),
					passwordEditText.getText().toString(),
					storeEntity.getFactoryCode(),
					storeEntity.getStockLocationCode()
			);
		});
	}

	/**
	 * 处理Spinner数据初始化及数据加载
	 */
	private void processSpinner() {
		StoreDao storeDao = AppDatabase.getInstance(this).storeDao();
		List<StoreEntity> allFactoryInfo = storeDao.getAllFactoryInfo();
		factoryAdapter = new LoginFactoryAdapter(allFactoryInfo, this);
		factorySp.setAdapter(factoryAdapter);

		stockLocationAdapter = new LoginStockLocationAdapter(stockLocationEntity, this);
		storeLocationSp.setAdapter(stockLocationAdapter);

		// 工厂选择按钮监听点击
		factorySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				StoreEntity adapterItem = (StoreEntity) factoryAdapter.getItem(i);
				stockLocationEntity.clear();
				stockLocationEntity.addAll(storeDao.getStockLocaltionInfo(adapterItem.getFactoryCode()));
				stockLocationAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
	}

	/**
	 * 设置ToolBar
	 */
	private void init() {
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
	}

	/**
	 * 用户登陆成功处理方法
	 *
	 * @param loggedInUser 登陆成功的用户对象
	 */
	private void loginSuccess(LoggedInUser loggedInUser) {

		LocalRepository.setLoggedInPreferences(LoginActivity.this, loggedInUser);

		String msg = String.format("Login Success %s", loggedInUser.getDisplayName());
		Utils.showToast(LoginActivity.this, msg);

		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);

		setResult(Activity.RESULT_OK);
		finish();
	}

	/***
	 * 获取登录状态
	 * @param context Activity
	 * @return 登录状态
	 */
	public static boolean isLogin(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(Constants.LOGIN_STATE, false);
	}

	/**
	 * 初始化库存地数据
	 */
	@SuppressLint("CheckResult")
	private void InitApplication() {

		StoreDao storeDao = AppDatabase.getInstance(this).storeDao();

		List<StoreEntity> storeEntities = storeDao.getAllStoreInfo();
		if (!storeEntities.isEmpty()) return;

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		retrofitClient.getBackendApi().getStores()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<List<StoreEntity>>>() {
					@Override
					public void onSuccess(ApiResponse<List<StoreEntity>> listApiResponse) {
						List<StoreEntity> stores = listApiResponse.getData();

						if (listApiResponse.getCode() != 200 || stores.size() == 0) {
							Utils.showToast(LoginActivity.this, "获取库存地数据失败。");
						}

						storeDao.batchInsert(stores);
						processSpinner();
					}

					@Override
					public void onError(Throwable e) {
						Utils.showToast(LoginActivity.this, "获取库存地数据失败。\n" + e.getMessage());
					}
				});
	}

	/**
	 * 添加 ToolBar按钮
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_settings, menu);
		return true;
	}

	/**
	 * 添加ToolBar设置按钮事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Intent mainIntent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(mainIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
