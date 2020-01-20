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

package com.youvigo.wms.common;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;


public class CrashCatchHandler implements UncaughtExceptionHandler {

	private static final String TAG = "CrashCatchHandler";
	private static CrashCatchHandler crashHandler = null;
	private Context mContext;
	private UncaughtExceptionHandler mDefaultCaughtExceptionHandler;

	/**
	 * 单例模式
	 *
	 * @return CrashCatchHandler
	 */
	public static CrashCatchHandler getInstance() {
		if (crashHandler == null) {
			synchronized (CrashCatchHandler.class) {
				if (crashHandler == null) {
					synchronized (CrashCatchHandler.class) {
						crashHandler = new CrashCatchHandler();
					}
				}
			}
		}

		return crashHandler;
	}

	public CrashCatchHandler() {
	}

	public void init(Context context) {
		mContext = context;
		// 获取默认的系统异常捕获器
		mDefaultCaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

		// 把当前的crash捕获器设置成默认的crash捕获器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}


	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		if (!handleException(throwable) && mDefaultCaughtExceptionHandler != null) {
			//如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultCaughtExceptionHandler.uncaughtException(thread, throwable);
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Log.e(TAG, "error : " + e);
			}

			// 退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
			System.exit(1);
			Process.killProcess(Process.myPid());

		}

	}

	/**
	 * 自定义错误处理
	 *
	 * @param ex
	 *
	 * @return true:如果处理了该异常信息;否则返回false
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}

		final String msg = ex.getLocalizedMessage();
		if (msg == null) {
			return false;
		}

		//使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, "很抱歉,程序出现异常！", Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}.start();

		return true;
	}
}