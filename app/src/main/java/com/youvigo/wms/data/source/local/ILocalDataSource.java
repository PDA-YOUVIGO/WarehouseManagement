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

package com.youvigo.wms.data.source.local;

public interface ILocalDataSource {

	/**
	 * 获取SAP接口地址
	 * @return
	 */
	String getSapAddress();

	/**
	 * 获取PDA后台地址
	 * @return
	 */
	String getPdaAddress();

	/**
	 * 获取当前用户登陆的工厂编号
	 * @return
	 */
	String getFactoryCode();

	/**
	 * 获取当前用户选择的库存地点
	 * @return
	 */
	String getStockLocationCode();

	/**
	 * 获取程序设置值
	 * @param key Key
	 *
	 * @return
	 */
	String getPreference(String key);
}
