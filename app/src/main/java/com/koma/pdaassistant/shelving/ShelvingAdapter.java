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

package com.koma.pdaassistant.shelving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.koma.pdaassistant.R;
import com.koma.pdaassistant.data.entities.Shelving;

public class ShelvingAdapter extends ListAdapter<Shelving, ShelvingAdapter.ShelvingVH> {
    public ShelvingAdapter() {
        super(new ShelvingDiffCallback());
    }

    @NonNull
    @Override
    public ShelvingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shelving, parent, false);
        return new ShelvingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelvingVH holder, int position) {

    }

    class ShelvingVH extends RecyclerView.ViewHolder {
        public ShelvingVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class ShelvingDiffCallback extends DiffUtil.ItemCallback<Shelving> {
        @Override
        public boolean areItemsTheSame(@NonNull Shelving oldItem, @NonNull Shelving newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Shelving oldItem, @NonNull Shelving newItem) {
            return false;
        }
    }
}
