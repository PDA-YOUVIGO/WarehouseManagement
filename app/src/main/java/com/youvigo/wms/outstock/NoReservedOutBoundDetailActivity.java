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

package com.youvigo.wms.outstock;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.youvigo.wms.R;

public class NoReservedOutBoundDetailActivity extends AppCompatActivity {

	// 物料编码
	private EditText materialCode;

	// 批次号（输入）
	private EditText batchNumber;

	private EditText cargoSpace;

	private EditText quantity;
	private EditText remark;

	// 物料名称
	private TextView materialName;
	// 规格
	private TextView specification;
	// 供应商批次
	private TextView supplierBatch;

	private TextView stockQuantity;
	private TextView stockUnit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_no_reserved_out_bound_detail);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		initViews();
	}

	private void initViews() {
		materialCode = findViewById(R.id.et_material_code);
		materialName = findViewById(R.id.tv_material_name);
		batchNumber = findViewById(R.id.et_batch_number);
		specification = findViewById(R.id.tv_specification);
		supplierBatch = findViewById(R.id.tv_supplier_batch);
		stockQuantity = findViewById(R.id.tv_required_quantity_description);
		stockUnit = findViewById(R.id.tv_required_unit_description);
		cargoSpace = findViewById(R.id.tv_out_shelf_description);
		quantity = findViewById(R.id.tv_number_description);
		remark = findViewById(R.id.et_remark);
	}

}
