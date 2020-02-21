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
import com.youvigo.wms.data.entities.InternalOrder;
import com.youvigo.wms.util.Constants;

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
        holder.bind(getItem(position));
    }


    class InternalOrderVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView internal_number;
        private final TextView internal_description;

        public InternalOrderVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            internal_number = itemView.findViewById(R.id.tv_internal_order_id);
            internal_description = itemView.findViewById(R.id.tv_description);
        }

        void bind(InternalOrder internalOrder) {
            internal_number.setText(internalOrder.getNumber());
            internal_description.setText(internalOrder.getDescription());
        }

        @Override
        public void onClick(View v) {
            InternalOrder internalOrder = getItem(getAdapterPosition());

            if (itemView.getContext() instanceof InternalOrderSearchActivity) {
                Intent intent = new Intent();
                intent.putExtra(Constants.INTERNAL_ORDER_RESULT, internalOrder);
                ((InternalOrderSearchActivity) itemView.getContext()).setResult(Activity.RESULT_OK, intent);
                ((InternalOrderSearchActivity) itemView.getContext()).finish();
            }
        }
    }

    static class InternalOrderDiffCallback extends DiffUtil.ItemCallback<InternalOrder> {
        @Override
        public boolean areItemsTheSame(@NonNull InternalOrder oldItem, @NonNull InternalOrder newItem) {
            return oldItem.getNumber().equals(newItem.getNumber())
                    && oldItem.getFactory().equals(newItem.getFactory());
        }

        @Override
        public boolean areContentsTheSame(@NonNull InternalOrder oldItem, @NonNull InternalOrder newItem) {
            return oldItem.getNumber().equals(newItem.getNumber())
                    && oldItem.getFactory().equals(newItem.getFactory());
        }
    }
}
