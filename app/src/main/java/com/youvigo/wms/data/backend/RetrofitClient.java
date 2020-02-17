package com.youvigo.wms.data.backend;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

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

	private static RetrofitClient instance;

	private static class SingletonHolder {
		private static RetrofitClient INSTANCE = new RetrofitClient();
	}

	public static RetrofitClient getInstance() {

		Timber.d("Init RetrofitClient.");

		return SingletonHolder.INSTANCE;
	}

	public static RetrofitClient getInstance(String sapUrl, String pdaUrl) {

		instance = new RetrofitClient(sapUrl, pdaUrl);
		return instance;
	}

	private RetrofitClient() {
		this(null, null);
	}

	private RetrofitClient(String sapUrl, String pdaUrl) {
		if (null == sapUrl || sapUrl.isEmpty()) {
			sapUrl = Constants.SAP_URL;
		}

		if (null == pdaUrl || pdaUrl.isEmpty()) {
			pdaUrl = Constants.PDA_URL;
		}

		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		// 设置Basic 认证
		OkHttpClient okHttpClient = new OkHttpClient.Builder().authenticator((route, response) -> {
			// 设置Basic 认证
			String credential = Credentials.basic(Constants.SAP_USERNAME, Constants.SAP_PASSWORD);
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
				.baseUrl(sapUrl)
				.build();

		Retrofit retrofitBackend = new Retrofit.Builder()
				.client(okHttpClientBackend)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(pdaUrl)
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

	/**
	 * 工厂
	 * @return
	 */
	public String getFactoryCode() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
		return sharedPreferences.getString(Constants.FACTORYCODE, "1010");
	}

	/**
	 * 库存地
	 * @return
	 */
	public String getStockLocationCode() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
		return sharedPreferences.getString(Constants.STOCKLOCATION, "FZ01");
	}

	/**
	 * 仓库
	 * @return
	 */
	public String getWarehouseNumber() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
		return sharedPreferences.getString(Constants.WAREHOUSE_NUMBER, "X01");
	}

	/**
	 * 用户
	 * @return
	 */
	public String getAccount() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
		return sharedPreferences.getString(Constants.ACCOUNT, "unknow");
	}

	/**
	 * 用户名称
	 * @return
	 */
	public String getUserName() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getContext());
		return sharedPreferences.getString(Constants.USERNAME, "unknow");
	}
}
