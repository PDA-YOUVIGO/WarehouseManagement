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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.Employee;

public class EmployeeSearchAdapter extends ListAdapter<Employee, EmployeeSearchAdapter.EmployeeVH> {
    public EmployeeSearchAdapter() {
        super(new EmployeeDiffCallback());
    }

    @NonNull
    @Override
    public EmployeeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materials_search, parent, false);
        return new EmployeeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeVH holder, int position) {

    }

    /**
     * 列表item的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 20;
    }

    class EmployeeVH extends RecyclerView.ViewHolder {
        public EmployeeVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class EmployeeDiffCallback extends DiffUtil.ItemCallback<Employee> {
        @Override
        public boolean areItemsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.employeeCode.equals(newItem.employeeCode);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.employeeName.equals(newItem.employeeName)
                    && oldItem.departmentCode.equals(newItem.departmentCode)
                    && oldItem.departmentName.equals(newItem.departmentName);
        }
    }
}
