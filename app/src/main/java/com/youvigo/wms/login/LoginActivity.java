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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.youvigo.wms.R;
import com.youvigo.wms.SettingsActivity;
import com.youvigo.wms.login.adapter.LoginFactoryReferenceAdapter;
import com.youvigo.wms.login.adapter.LoginStoreReferenceAdapter;
import com.youvigo.wms.data.model.FactoryReferenceModel;
import com.youvigo.wms.data.model.StoreReferenceModel;
import com.youvigo.wms.dto.StoreEntity;
import com.youvigo.wms.dto.StoreResponseEntity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

	private LoginViewModel loginViewModel;
	private Spinner factorySp;
	private List<FactoryReferenceModel> mFactoryReferenceModelList;
	private List<StoreReferenceModel> mStoreReferenceModelList = new ArrayList<>();
	protected Toolbar toolbar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);
		final EditText usernameEditText = findViewById(R.id.username);
		final EditText passwordEditText = findViewById(R.id.password);
		final Button loginButton = findViewById(R.id.loginBt);
		final ProgressBar loadingProgressBar = findViewById(R.id.loading);
		factorySp = findViewById(R.id.factorySp);
		final Spinner storeSp = findViewById(R.id.stockLocationSp);
		LoginStoreReferenceAdapter loginStoreReferenceAdapter = new LoginStoreReferenceAdapter(mStoreReferenceModelList, getApplicationContext());
		storeSp.setAdapter(loginStoreReferenceAdapter);

		init();
		loadSpinnerData();

		// 工厂选择按钮监听点击
		factorySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				FactoryReferenceModel factoryReferenceModel = mFactoryReferenceModelList.get(i);
				mStoreReferenceModelList.clear();
				mStoreReferenceModelList.addAll(factoryReferenceModel.getStores());
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
				showLoginFailed(loginResult.getError().getMessage());
			}
			if (loginResult.getSuccess() != null) {
				loginSuccess(loginResult.getSuccess());
			}
			setResult(Activity.RESULT_OK);

			//Complete and destroy login activity once successful
			finish();
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
				loginViewModel.loginDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString());
			}
		};
		usernameEditText.addTextChangedListener(afterTextChangedListener);
		passwordEditText.addTextChangedListener(afterTextChangedListener);

		// 绑定键盘Done按键
		passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
			if (actionId == EditorInfo.IME_ACTION_DONE) {

				FactoryReferenceModel factoryReferenceModel = (FactoryReferenceModel) factorySp.getSelectedItem();
				StoreReferenceModel storeReferenceModel = (StoreReferenceModel) storeSp.getSelectedItem();

				loginViewModel.loginIn(usernameEditText.getText().toString(),
						passwordEditText.getText().toString(),
						factoryReferenceModel.getFactoryCode(),
						storeReferenceModel.getStockLocationCode());
			}

			return false;
		});

		//登录按钮监听事件
		loginButton.setOnClickListener(v -> {
			loadingProgressBar.setVisibility(View.VISIBLE);
			FactoryReferenceModel factoryReferenceModel = (FactoryReferenceModel) factorySp.getSelectedItem();
			StoreReferenceModel storeReferenceModel = (StoreReferenceModel) storeSp.getSelectedItem();
			loginViewModel.loginIn(usernameEditText.getText().toString(),
					passwordEditText.getText().toString(),
					factoryReferenceModel.getFactoryCode(),
					storeReferenceModel.getStockLocationCode()
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
	 * 错误信息
	 * @param errorString
	 */
	private void showLoginFailed(String errorString) {
		Toast.makeText(LoginActivity.this, errorString, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 用户登陆成功处理方法
	 * @param loggedInUser 登陆成功的用户对象
	 */
	private void loginSuccess(LoggedInUser loggedInUser) {
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("loginState", true).apply();
		String msg = String.format("Login Success %s", loggedInUser.getDisplayName());
		Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	/***
	 * 获取登录状态
	 * @param context
	 * @return 登录状态
	 */
	public static boolean isLogin(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("loginState", false);
	}

	/**
	 * 异步加载库存地参照数据
	 */
	private void loadSpinnerData() {

		OkHttpClient okHttpClient = new OkHttpClient();

		String pda_interface_address = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pda_interface_address", "");
		if (pda_interface_address.equals("")){return;};
		String interfaceUrl = String.format("http://%s/blade-data/api/store/list", pda_interface_address);

		Request request = new Request.Builder().get().url(interfaceUrl).build();

		okHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(@NotNull Call call, @NotNull IOException e) {
				runOnUiThread(() -> Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show());
			}

			@Override
			public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

				try (ResponseBody responseBody = response.body()) {
					if (!response.isSuccessful())
						throw new IOException("Unexpected code " + response);

					final Gson gson = new Gson();
					StoreResponseEntity storeRespEntity;
					if (responseBody != null) {

						storeRespEntity = gson.fromJson(responseBody.charStream(), StoreResponseEntity.class);

						// 将获取的数据按工厂进行分组
						Map<String, FactoryReferenceModel> groupFactoryMap = new HashMap<>();
						for (StoreEntity storeEntity : storeRespEntity.getData()) {

							FactoryReferenceModel factoryReferenceModel = groupFactoryMap.get(storeEntity.getFactoryCode());

							if (factoryReferenceModel == null) {
								factoryReferenceModel = new FactoryReferenceModel();
								factoryReferenceModel.setCompanyCode(storeEntity.getCompanyCode());
								factoryReferenceModel.setCompanyName(storeEntity.getCompanyName());
								factoryReferenceModel.setFactoryCode(storeEntity.getFactoryCode());
								factoryReferenceModel.setFactoryName(storeEntity.getFactoryName());

								List<StoreReferenceModel> storeList = new ArrayList<>();
								storeList.add(new StoreReferenceModel(storeEntity.getStockLocation(), storeEntity.getStoreLocationName(), storeEntity.getStoreCode(), storeEntity.getStoreName()));
								factoryReferenceModel.setStores(storeList);

								groupFactoryMap.put(storeEntity.getFactoryCode(), factoryReferenceModel);
							} else {
								factoryReferenceModel.getStores().add(new StoreReferenceModel(storeEntity.getStockLocation(), storeEntity.getStoreLocationName(), storeEntity.getStoreCode(), storeEntity.getStoreName()));
							}
						}

						// 将分组后的数据转为List
						mFactoryReferenceModelList = new ArrayList<>(groupFactoryMap.values());

						// 排序，具体可以修改实体类的 compareTo 方法
						Collections.sort(mFactoryReferenceModelList);

						runOnUiThread(() -> {
							LoginFactoryReferenceAdapter mLoginFactoryReferenceAdapter = new LoginFactoryReferenceAdapter(mFactoryReferenceModelList, getApplicationContext());
							factorySp.setAdapter(mLoginFactoryReferenceAdapter);
						});
					}
				}
			}
		});
	}

	/**
	 * 添加 ToolBar按钮
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login_menu, menu);
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
