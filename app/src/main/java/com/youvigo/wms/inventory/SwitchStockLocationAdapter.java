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

package com.youvigo.wms.inventory;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.SwitchStockLocation;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SwitchStockLocationAdapter extends ListAdapter<SwitchStockLocation, SwitchStockLocationAdapter.SwitchStockLocationVH> {
    public SwitchStockLocationAdapter() {
        super(new SwitchStockLocationDiffCallback());
    }

    @NonNull
    @Override
    public SwitchStockLocationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_switch_stock_location, parent, false);
        return new SwitchStockLocationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwitchStockLocationVH holder, int position) {
        holder.bind(getItem(position));
    }

    class SwitchStockLocationVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView repository;

        SwitchStockLocationVH(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            repository = itemView.findViewById(R.id.tv_repository);
        }

        @Override
        public void onClick(View v) {
            SwitchStockLocation switchStockLocation = getItem(getAdapterPosition());

            new AlertDialog.Builder(itemView.getContext())
                    .setTitle("库存地")
                    .setMessage("切换到该库存地？")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "成功切换库存地。", Toast.LENGTH_SHORT).show();

                            if (itemView.getContext() instanceof AppCompatActivity) {
                                AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((AppCompatActivity) itemView.getContext()).finish();
                                    }
                                }, 1, TimeUnit.SECONDS);
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }

        void bind(SwitchStockLocation switchStockLocation) {
            repository.setText(switchStockLocation.repositoryName);
        }
    }

    static class SwitchStockLocationDiffCallback extends DiffUtil.ItemCallback<SwitchStockLocation> {
        @Override
        public boolean areItemsTheSame(@NonNull SwitchStockLocation oldItem, @NonNull SwitchStockLocation newItem) {
            return oldItem.repositoryId.equals(newItem.repositoryId);
        }

        @Override
        public boolean areContentsTheSame(@NonNull SwitchStockLocation oldItem, @NonNull SwitchStockLocation newItem) {
            return oldItem.repositoryName.equals(newItem.repositoryName);
        }
    }
}
