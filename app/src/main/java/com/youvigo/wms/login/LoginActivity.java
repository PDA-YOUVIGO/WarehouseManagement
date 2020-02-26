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
import com.youvigo.wms.data.backend.RetrofitClient;
import com.youvigo.wms.data.dto.base.ApiResponse;
import com.youvigo.wms.data.entities.StoreEntity;
import com.youvigo.wms.data.entities.FactoryReference;
import com.youvigo.wms.data.entities.StoreLocationReference;
import com.youvigo.wms.login.adapter.LoginFactoryReferenceAdapter;
import com.youvigo.wms.login.adapter.LoginStoreReferenceAdapter;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

	private LoginViewModel loginViewModel;
	private Spinner factorySp;
	private List<FactoryReference> mFactoryReferenceList;
	private List<StoreLocationReference> mStoreLocationReferenceList = new ArrayList<>();
	protected Toolbar toolbar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		loginViewModel = new ViewModelProvider.NewInstanceFactory().create(LoginViewModel.class);

		final EditText usernameEditText = findViewById(R.id.username);
		final EditText passwordEditText = findViewById(R.id.password);
		final Button loginButton = findViewById(R.id.loginBt);
		final ProgressBar loadingProgressBar = findViewById(R.id.loading);
		factorySp = findViewById(R.id.factorySp);
		final Spinner storeLocationSp = findViewById(R.id.stockLocationSp);

		LoginStoreReferenceAdapter loginStoreReferenceAdapter =
				new LoginStoreReferenceAdapter(mStoreLocationReferenceList, getApplicationContext());
		storeLocationSp.setAdapter(loginStoreReferenceAdapter);

		init();
		loadSpinnerData();

		// 工厂选择按钮监听点击
		factorySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				FactoryReference factoryReference = mFactoryReferenceList.get(i);
				mStoreLocationReferenceList.clear();
				mStoreLocationReferenceList.addAll(factoryReference.getStores());
				loginStoreReferenceAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

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

				FactoryReference factoryReference = (FactoryReference) factorySp.getSelectedItem();
				StoreLocationReference storeLocationReference = (StoreLocationReference) storeLocationSp.getSelectedItem();

				loginViewModel.loginIn(usernameEditText.getText().toString(),
						passwordEditText.getText().toString(),
						factoryReference.getFactoryCode(),
						storeLocationReference.getStockLocationCode());
			}

			return false;
		});

		//登录按钮监听事件
		loginButton.setOnClickListener(v -> {
			loadingProgressBar.setVisibility(View.VISIBLE);
			FactoryReference factoryReference = (FactoryReference) factorySp.getSelectedItem();
			StoreLocationReference storeLocationReference = (StoreLocationReference) storeLocationSp.getSelectedItem();
			loginViewModel.loginIn(usernameEditText.getText().toString(),
					passwordEditText.getText().toString(),
					factoryReference.getFactoryCode(),
					storeLocationReference.getStockLocationCode()
			);
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

		Utils.setLoggedInPreferences(LoginActivity.this, loggedInUser);

		String msg = String.format("Login Success %s", loggedInUser.getDisplayName());
		Utils.showToast(LoginActivity.this, msg);

		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		intent.putParcelableArrayListExtra(Constants.FACTORY_SELECT_ITEMS, new ArrayList<>(mStoreLocationReferenceList));
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
	 * 异步加载库存地参照数据
	 */
	@SuppressLint("CheckResult")
	private void loadSpinnerData() {

		RetrofitClient retrofitClient = RetrofitClient.getInstance();
		retrofitClient.getBackendApi().getStores()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<ApiResponse<List<StoreEntity>>>() {
					@Override
					public void onSuccess(ApiResponse<List<StoreEntity>> listApiResponse) {
						List<StoreEntity> stores = listApiResponse.getData();

						if (listApiResponse.getCode() != 200 || stores.size() == 0) {
							Utils.showToast(LoginActivity.this, "加载库存地数据失败。");
						}

						// 将获取的数据按工厂进行分组
						Map<String, FactoryReference> groupFactoryMap = new HashMap<>();
						for (StoreEntity storeEntity : stores) {

							FactoryReference factoryReference =
									groupFactoryMap.get(storeEntity.getFactoryCode());

							if (factoryReference == null) {
								factoryReference = new FactoryReference();
								factoryReference.setCompanyCode(storeEntity.getCompanyCode());
								factoryReference.setCompanyName(storeEntity.getCompanyName());
								factoryReference.setFactoryCode(storeEntity.getFactoryCode());
								factoryReference.setFactoryName(storeEntity.getFactoryName());

								List<StoreLocationReference> storeList = new ArrayList<>();
								storeList.add(new StoreLocationReference(storeEntity.getStockLocation(),
										storeEntity.getStoreLocationName(), storeEntity.getStoreCode(),
										storeEntity.getStoreName()));
								factoryReference.setStores(storeList);

								groupFactoryMap.put(storeEntity.getFactoryCode(), factoryReference);
							} else {
								factoryReference.getStores().add(new StoreLocationReference(
										storeEntity.getStockLocation(),
										storeEntity.getStoreLocationName(),
										storeEntity.getStoreCode(),
										storeEntity.getStoreName()));
							}
						}

						// 将分组后的数据转为List
						mFactoryReferenceList = new ArrayList<>(groupFactoryMap.values());

						// 排序，具体可以修改实体类的 compareTo 方法
						Collections.sort(mFactoryReferenceList);

						LoginFactoryReferenceAdapter mLoginFactoryReferenceAdapter = new LoginFactoryReferenceAdapter(
								mFactoryReferenceList,
								getApplicationContext()
						);
						factorySp.setAdapter(mLoginFactoryReferenceAdapter);
					}

					@Override
					public void onError(Throwable e) {
						Utils.showToast(LoginActivity.this, "加载库存地数据失败。\n" + e.getMessage());
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
