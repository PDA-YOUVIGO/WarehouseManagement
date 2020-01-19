package com.youvigo.wms.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ApplicationModule {

	private final Context mContext;

	public ApplicationModule(Context mContext) {
		this.mContext = mContext;
	}

	@Singleton
	@Provides
	public Context provideContext() {
		return mContext;
	}
}
