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
import com.youvigo.wms.data.entities.InternalOrder;

public class InternalOrderSearchAdapter extends ListAdapter<InternalOrder, InternalOrderSearchAdapter.InternalOrderVH> {
    public InternalOrderSearchAdapter() {
        super(new InternalOrderDiffCallback());
    }

    @NonNull
    @Override
    public InternalOrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_internal_order_search, parent, false);
        return new InternalOrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InternalOrderVH holder, int position) {
        // 数据绑定UI
    }

    /**
     * 列表item的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 30;
    }

    class InternalOrderVH extends RecyclerView.ViewHolder {

        public InternalOrderVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class InternalOrderDiffCallback extends DiffUtil.ItemCallback<InternalOrder> {
        @Override
        public boolean areItemsTheSame(@NonNull InternalOrder oldItem, @NonNull InternalOrder newItem) {
            return oldItem.orderId.equals(newItem.orderId);
        }

        @Override
        public boolean areContentsTheSame(@NonNull InternalOrder oldItem, @NonNull InternalOrder newItem) {
            return oldItem.description.equals(newItem.description)
                    && oldItem.factory.equals(newItem.factory);
        }
    }
}
