package com.youvigo.wms.data.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youvigo.wms.BaseApplication;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RetrofitClient {

	private SapService sapService;
	private BackendApi backendApi;

	private static class SingletonHolder {
		private static RetrofitClient INSTANCE = new RetrofitClient();
	}

	public static RetrofitClient getInstance() {
		Timber.d("Init RetrofitClient.");
		return SingletonHolder.INSTANCE;
	}

	public static RetrofitClient getInstance(String sapUrl, String sapAccount, String sapPassword, String pdaUrl) {
		return new RetrofitClient(sapUrl, sapAccount, sapPassword, pdaUrl);
	}

	private RetrofitClient() {
		this(null, null, null, null);
	}

	/**
	 * 初始化RetrofitClient
	 *
	 * @param sapUrl      sap地址
	 * @param sapAccount  sap用户
	 * @param sapPassword sap密码
	 * @param pdaUrl      PDA地址
	 */
	private RetrofitClient(String sapUrl, String sapAccount, String sapPassword, String pdaUrl) {

		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		// 设置Basic 认证
		OkHttpClient okHttpClient = new OkHttpClient.Builder().authenticator((route, response) -> {
			// 设置Basic 认证
			String credential = getCredentials(sapAccount, sapPassword);
			return response.request().newBuilder().header("Authorization", credential).build();
		}).addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
				.addInterceptor(loggingInterceptor)
				.connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
				.build();

		// PDA后台Http客户端
		OkHttpClient okHttpClientBackend = new OkHttpClient.Builder()
				.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
				.addInterceptor(loggingInterceptor)
				.connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
				.build();

		Retrofit retrofit = new Retrofit.Builder()
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(TextUtils.isEmpty(sapUrl) ? getSapUrl() : sapUrl)
				.build();

		Retrofit retrofitBackend = new Retrofit.Builder()
				.client(okHttpClientBackend)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(TextUtils.isEmpty(pdaUrl) ? getPdaUrl() : pdaUrl)
				.build();

		sapService = retrofit.create(SapService.class);
		backendApi = retrofitBackend.create(BackendApi.class);

	}

	public SapService getSapService() {
		return sapService;
	}

	public BackendApi getBackendApi() {
		return backendApi;
	}

	private SharedPreferences getDefaultSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
	}

	private Gson getGson() {
		return new GsonBuilder().create();
	}

	/** SAP接口地址 */
	private String getSapUrl() {
		return getDefaultSharedPreferences().getString(Constants.SAP_URL, "http://172.30.5.11:50000");
	}

	/** SAP Credentials */
	private String getCredentials(String sapAccount, String sapPassword) {
		return Credentials.basic(TextUtils.isEmpty(sapAccount) ? getSapAccount() : sapAccount,
				TextUtils.isEmpty(sapPassword) ? getSapPassword() : sapPassword);
	}

	/** SAP账户 */
	private String getSapAccount() {
		return getDefaultSharedPreferences().getString(Constants.SAP_ACCOUNT, "zengzx");
	}

	/** SAP密码 */
	private String getSapPassword() {
		return getDefaultSharedPreferences().getString(Constants.SAP_PASSWORD, "abcd1234");
	}

	/** PDA接口地址 */
	private String getPdaUrl() {
		return getDefaultSharedPreferences().getString(Constants.PDA_URL, "http://172.30.16.16:8081");
	}

	// 获取用户登录信息
	private SharedPreferences getLoggedPreferences() {
		return BaseApplication.getContext().getSharedPreferences(Constants.LOGIN_SHAREDPREFERENCES,	Context.MODE_PRIVATE);
	}

	/** 登录工厂 */
	public String getFactoryCode() {
		return getLoggedPreferences().getString(Constants.FACTORYCODE, "1010");
	}

	/** 登录库存地 */
	public String getStockLocationCode() {
		return getLoggedPreferences().getString(Constants.STOCKLOCATION, "FZ01");
	}

	/** 登录仓库 */
	public String getWarehouseNumber() {
		return getLoggedPreferences().getString(Constants.WAREHOUSE_NUMBER, "X01");
	}

	/** 登录用户账号 */
	public String getAccount() {
		return getLoggedPreferences().getString(Constants.ACCOUNT, "unknow");
	}

	/** 登录用户名称 */
	public String getUserName() {
		return getLoggedPreferences().getString(Constants.USERNAME, "unknow");
	}

	// 获取设置信息

	/** 业务类型 */
	public String getBusinessType() {
		return getLoggedPreferences().getString(Constants.BUSINESS_TYPE, null);
	}

	/** 混批存储 */
	public boolean getHybridStorage() {
		return getLoggedPreferences().getBoolean(Constants.HYBRID_STORAGE, false);
	}

	/** 混批存储策略 */
	public String getHybridStorageStrategy() {
		return getLoggedPreferences().getString(Constants.HYBRID_STORAGE_STRATEGY, null);
	}

	/** 盘点方式 */
	public String getInventoryMethod() {
		return getDefaultSharedPreferences().getString(Constants.INVENTORY_METHOD, null);
	}

	/**
	 * 是否显示合箱
	 */
	public Boolean getDisplayMergeBox(){
		return getDefaultSharedPreferences().getBoolean(Constants.DISPLAY_MERGEBOX, false);
	}

}
