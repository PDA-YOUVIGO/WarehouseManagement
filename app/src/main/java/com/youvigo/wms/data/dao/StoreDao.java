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

package com.youvigo.wms.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.youvigo.wms.data.entities.StoreEntity;

import java.util.List;

@Dao
public interface StoreDao {

	@Query("SELECT * FROM store")
	List<StoreEntity> getAllStoreInfo();

	@Query("SELECT * FROM store group by factoryCode")
	List<StoreEntity> getAllFactoryInfo();

	@Query("SELECT * FROM store where factoryCode = :facory")
	List<StoreEntity> getStockLocaltionInfo(String facory);

	@Insert
	void batchInsert(List<StoreEntity> storeEntities);

	@Delete
	void delete(StoreEntity storeEntity);

}
