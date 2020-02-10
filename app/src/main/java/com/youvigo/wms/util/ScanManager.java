package com.youvigo.wms.util;

import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

/**
 * 扫描程序管理
 */
public class ScanManager {

	public Context mContext;

	public ScanManager(Context mContext) {
		this.mContext = mContext;
	}

	/**
	 * 设置新大陆扫描头开关
	 */
	public boolean setScannerEnable(boolean enable) {
		boolean result;
		if (enable) {
			result = Settings.System.putInt(mContext.getContentResolver(), Constants.BROADCAST_ENABLE, 1);
		} else {
			result = Settings.System.putInt(mContext.getContentResolver(), Constants.BROADCAST_ENABLE, 0);
		}
		return result;
	}

	/**
	 * 获取扫描头开关状态
	 */
	public boolean getScannerEnable() {
		boolean result = true;
		try {
			int status = Settings.System.getInt(mContext.getContentResolver(), Constants.BROADCAST_ENABLE);
			if (status == 1) {
				result = true;
			} else if (status == 0) {
				result = false;
			}
		} catch (SettingNotFoundException e) {
			e.printStackTrace();

		}

		return result;
	}
}
