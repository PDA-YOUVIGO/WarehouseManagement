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

package com.youvigo.wms.di.module;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.youvigo.wms.BuildConfig;
import com.youvigo.wms.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetWorkModule {

	@Singleton
	@Provides
	Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
		return new Retrofit.Builder().baseUrl(Constants.SAP_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
	}

	@Singleton
	@Provides
	OkHttpClient provideOkHttpClient(Cache cache, final SharedPreferences sharedPreferences, final Gson gson) {
		final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

		if (BuildConfig.DEBUG) {
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		}

		return new OkHttpClient.Builder().authenticator((route, response) -> {
			String sap_url = sharedPreferences.getString("sap_url", "");
			String sap_user = sharedPreferences.getString("sap_user", "");
			String sap_password = sharedPreferences.getString("sap_password", "");

			String credential = Credentials.basic(sap_user, sap_password);
			return response.request().newBuilder().header("Authorization", credential).build();

		}).addInterceptor(loggingInterceptor).cache(cache).connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS).readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS).writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();

	}
}
