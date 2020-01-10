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

package com.koma.pdaassistant.search;

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

import com.koma.pdaassistant.R;
import com.koma.pdaassistant.data.entities.Material;

import static com.koma.pdaassistant.util.Constants.SEARCH_RESULT;

public class SearchAdapter extends ListAdapter<Material, SearchAdapter.SearchVH> {
    private static final int TYPE_HEADER = 100;
    private static final int TYPE_NORMAL = 101;

    public SearchAdapter() {
        super(new MaterialDiffCallback());
    }

    @NonNull
    @Override
    public SearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        }
        return new SearchVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchVH holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    class SearchVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView materialDocument;
        private final TextView date;
        private final TextView creator;

        SearchVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            materialDocument = itemView.findViewById(R.id.tv_material_document);
            date = itemView.findViewById(R.id.tv_date);
            creator = itemView.findViewById(R.id.tv_creator);
        }

        void bind(Material material) {
            materialDocument.setText(material.materialDocument);
            date.setText(material.creator);
            creator.setText(material.creator);
        }

        @Override
        public void onClick(View v) {
            Material material = getItem(getAdapterPosition());
            if (itemView.getContext() instanceof SearchActivity) {
                Intent intent = new Intent();
                intent.putExtra(SEARCH_RESULT, material);
                ((SearchActivity) itemView.getContext()).setResult(Activity.RESULT_OK, intent);
                ((SearchActivity) itemView.getContext()).finish();
            }
        }
    }

    static class MaterialDiffCallback extends DiffUtil.ItemCallback<Material> {

        @Override
        public boolean areItemsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem.materialDocument.equals(newItem.materialDocument);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem.creator.equals(newItem.creator) && oldItem.date.equals(newItem.date);
        }
    }
}
