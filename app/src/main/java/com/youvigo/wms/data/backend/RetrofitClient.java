package com.youvigo.wms.data.backend;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.preference.PreferenceManager;

import com.youvigo.wms.BaseApplication;
import com.youvigo.wms.data.backend.api.BackendApi;
import com.youvigo.wms.data.backend.api.SapService;
import com.youvigo.wms.data.dto.base.ControlInfo;
import com.youvigo.wms.data.dto.request.PrintRequest;
import com.youvigo.wms.data.dto.request.PrintRequestDetails;
import com.youvigo.wms.data.dto.response.PrintResponse;
import com.youvigo.wms.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
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
		return new RetrofitClient(sapUrl,sapAccount, sapPassword, pdaUrl);
	}

	private RetrofitClient() {
		this(null, null,null,null);
	}

	/**
	 * 初始化RetrofitClient
	 * @param sapUrl sap地址
	 * @param sapAccount sap用户
	 * @param sapPassword sap密码
	 * @param pdaUrl PDA地址
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
				.baseUrl(TextUtils.isEmpty(sapUrl)?getSapUrl():sapUrl)
				.build();

		Retrofit retrofitBackend = new Retrofit.Builder()
				.client(okHttpClientBackend)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(TextUtils.isEmpty(pdaUrl)?getPdaUrl():pdaUrl)
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

	private SharedPreferences getSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
	}

	/** SAP接口地址 */
	private String getSapUrl() {
		return getSharedPreferences().getString(Constants.SAP_URL, "http://52.82.87.90:50000");
	}

	/** SAP Credentials */
	private String getCredentials(String sapAccount, String sapPassword) {
		return Credentials.basic(TextUtils.isEmpty(sapAccount) ? getSapAccount() : sapAccount, TextUtils.isEmpty(sapPassword) ? getSapPassword() : sapPassword);
	}

	/** SAP账户 */
	private String getSapAccount() {
		return getSharedPreferences().getString(Constants.SAP_ACCOUNT, "zengzx");
	}

	/** SAP密码 */
	private String getSapPassword() {
		return getSharedPreferences().getString(Constants.SAP_PASSWORD, "abcd1234");
	}

	/** PDA接口地址 */
	private String getPdaUrl() {
		return getSharedPreferences().getString(Constants.PDA_URL, "http://52.83.32.16:8081");
	}

	/**
	 * 打印预留单
	 * @param pdaOrderNumber Pda生成的单据号
	 */
	public Call<PrintResponse> printOrder(String pdaOrderNumber) {
		SapService sapService = getSapService();
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());

		PrintRequest request = new PrintRequest();
		request.setControlInfo(new ControlInfo());

		PrintRequestDetails details = new PrintRequestDetails();
		details.setPdaOrderNumber(pdaOrderNumber);
		details.setPrinterName(sharedPreferences.getString(Constants.PRINTER_NAME, null));
		details.setProgramName(sharedPreferences.getString(Constants.BUSINESS_TYPE, null));
		request.setPrintRequestDetails(details);

		return sapService.submitPrint(request);

	}

	// 获取用户登录信息
	/** 登录工厂 */
	public String getFactoryCode() {
		return getSharedPreferences().getString(Constants.FACTORYCODE, "1010");
	}

	/** 登录库存地 */
	public String getStockLocationCode() {
		return getSharedPreferences().getString(Constants.STOCKLOCATION, "FZ01");
	}

	/** 登录仓库 */
	public String getWarehouseNumber() {
		return getSharedPreferences().getString(Constants.WAREHOUSE_NUMBER, "X01");
	}

	/** 登录用户账号 */
	public String getAccount() {
		return getSharedPreferences().getString(Constants.ACCOUNT, "unknow");
	}

	/** 登录用户名称 */
	public String getUserName() {
		return getSharedPreferences().getString(Constants.USERNAME, "unknow");
	}

	// 获取设置信息
	/** 业务类型 */
	public String getBusinessType() {
		return getSharedPreferences().getString(Constants.BUSINESS_TYPE, null);
	}

	/** 混批存储 */
	public boolean getHybridStorage() {
		return getSharedPreferences().getBoolean(Constants.HYBRID_STORAGE, false);
	}

	/** 混批存储策略 */
	public String getHybridStorageStrategy() {
		return getSharedPreferences().getString(Constants.HYBRID_STORAGE_STRATEGY, null);
	}

	/** 盘点方式 */
	private String getInventoryMethod() {
		return getSharedPreferences().getString(Constants.INVENTORY_METHOD, null);
	}

}
