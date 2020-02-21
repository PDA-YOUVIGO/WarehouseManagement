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

package com.youvigo.wms.search;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.Employee;
import com.youvigo.wms.util.Constants;

public class EmployeeSearchAdapter extends ListAdapter<Employee, EmployeeSearchAdapter.EmployeeSearchVH> {
	public EmployeeSearchAdapter() {
		super(new EmployeeDiffCallback());
	}

	@NonNull
	@Override
	public EmployeeSearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_search, parent, false);
		return new EmployeeSearchVH(view);
	}

	@Override
	public void onBindViewHolder(@NonNull EmployeeSearchVH holder, int position) {
		holder.bind(getItem(position));
	}

	class EmployeeSearchVH extends RecyclerView.ViewHolder implements View.OnClickListener {

		private final TextView tv_employeeCode;
		private final TextView tv_employeeName;
		private final TextView tv_departmentCode;
		private final TextView tv_departmentName;

		public EmployeeSearchVH(@NonNull View itemView) {
			super(itemView);

			itemView.setOnClickListener(this);

			tv_employeeCode = itemView.findViewById(R.id.tv_employee_code);
			tv_employeeName = itemView.findViewById(R.id.tv_employee_name);
			tv_departmentCode = itemView.findViewById(R.id.tv_department_code);
			tv_departmentName = itemView.findViewById(R.id.tv_department_name);
		}

		void bind(Employee employee) {
			tv_employeeCode.setText(employee.getEmployeeCode());
			tv_employeeName.setText(employee.getEmployeeName());
			tv_departmentCode.setText(employee.getDepartmentCode());
			tv_departmentName.setText(employee.getDepartmentName());
		}

		@Override
		public void onClick(View v) {
			Employee employee = getItem(getAdapterPosition());

			if (itemView.getContext() instanceof EmployeeSearchActivity) {
				Intent intent = new Intent();
				intent.putExtra(Constants.EMPLOYEE_RESULT, employee);
				((EmployeeSearchActivity) itemView.getContext()).setResult(Activity.RESULT_OK, intent);
				((EmployeeSearchActivity) itemView.getContext()).finish();
			}
		}
	}

	static class EmployeeDiffCallback extends DiffUtil.ItemCallback<Employee> {
		@Override
		public boolean areItemsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
			return oldItem.getEmployeeCode().equals(newItem.getEmployeeCode())
					&& oldItem.getDepartmentCode().equals(newItem.getDepartmentCode());
		}

		@Override
		public boolean areContentsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
			return oldItem.getEmployeeCode().equals(newItem.getEmployeeCode())
					&& oldItem.getDepartmentCode().equals(newItem.getDepartmentCode());
		}
	}
}
