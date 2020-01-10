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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.koma.pdaassistant.R;
import com.koma.pdaassistant.data.entities.Material;

public class SearchAdapter extends ListAdapter<Material, SearchAdapter.SearchVH> {
    public SearchAdapter() {
        super(new MaterialDiffCallback());
    }

    @NonNull
    @Override
    public SearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchVH holder, int position) {

    }

    class SearchVH extends RecyclerView.ViewHolder {
        public SearchVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class MaterialDiffCallback extends DiffUtil.ItemCallback<Material> {

        @Override
        public boolean areItemsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem.materialDocument.equals(newItem.materialDocument);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
            return oldItem.name.equals(newItem.name) && oldItem.lotNumber.equals(newItem.lotNumber);
        }
    }
}
