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

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youvigo.wms.data.api.SapApi;
import com.youvigo.wms.data.source.local.LocalDataSource;
import com.youvigo.wms.data.source.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

@Module
public class RepositoryModule {

	private static final String TAG = RepositoryModule.class.getSimpleName();

	@Singleton
	@Provides
	LocalDataSource provideLocalDataSource(SharedPreferences sharedPreferences, Context context) {
		return new LocalDataSource(sharedPreferences, context);
	}

	@Singleton
	@Provides
	RemoteDataSource provideRemoteDataSource(SapApi sapApi) {
		return new RemoteDataSource(sapApi);
	}

	@Singleton
	@Provides
	SharedPreferences provideSharePreferences(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	@Singleton
	@Provides
	Cache provideCache(Context context) {
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		return new Cache(context.getCacheDir(), cacheSize);
	}

	@Singleton
	@Provides
	Gson provideGson() {
		GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
		return gsonBuilder.create();
	}


}
