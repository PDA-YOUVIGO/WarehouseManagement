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
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.data.entities.MaterialVoucher;

import java.util.Calendar;
import java.util.List;

import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

	private ProgressBar progressBar;
	private AppCompatEditText editText;
	private SearchAdapter adapter;
	private TextView startDate;
	private TextView endDate;

	private SearchViewModel viewModel;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.search_activity);

		initViews();

		observeData();
	}

	private void initViews() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		Calendar mCalendar = Calendar.getInstance();
		int currentYear = mCalendar.get(Calendar.YEAR);
		int currentMonth = mCalendar.get(Calendar.MONTH) + 1;
		int currentDay = mCalendar.get(Calendar.DAY_OF_MONTH);

		startDate = findViewById(R.id.tv_start);
		startDate.setText(String.format("%s年%s月%s日", currentYear, currentMonth, currentDay));

		startDate.setOnClickListener(v -> new DatePickerDialog(SearchActivity.this, (view, year, month, dayOfMonth) -> {
			String text = String.format("%s年%s月%s日", year, month, dayOfMonth);
			startDate.setText(text);
		}, currentYear, currentMonth, currentDay).show());

		endDate = findViewById(R.id.tv_end);
		endDate.setText(String.format("%s年%s月%s日", currentYear, currentMonth, currentDay));

		endDate.setOnClickListener(v -> new DatePickerDialog(SearchActivity.this, (view, year, month, dayOfMonth) -> {
			String text = String.format("%s年%s月%s日", year, month, dayOfMonth);
			endDate.setText(text);
		}, currentYear, currentMonth, currentDay).show());

		MaterialButton materialButton = findViewById(R.id.mbt_query);
		materialButton.setOnClickListener(v -> {
			hideKeyboard();
			editText.clearFocus();

			if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
				viewModel.query(startDate.getText().toString(), endDate.getText().toString(), editText.getText().toString());
			} else {
				viewModel.query(startDate.getText().toString(), endDate.getText().toString(), "");
			}

		});

		editText = findViewById(R.id.edit_text);
		progressBar = findViewById(R.id.progress_bar);
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new SearchAdapter();
		recyclerView.setAdapter(adapter);
	}

	/**
	 * 观察数据变化
	 */
	private void observeData() {
		viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

		viewModel.isLoading().observe(this, new Observer<Boolean>() {
			@Override
			public void onChanged(Boolean isActive) {
				progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE);
			}
		});

		viewModel.materials().observe(this, new Observer<List<MaterialVoucher>>() {
			@Override
			public void onChanged(List<MaterialVoucher> materialVouchers) {
				adapter.submitList(materialVouchers);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		setResult(Activity.RESULT_CANCELED);
		Timber.d("onBackPressed");
	}

	private void hideKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null) {
			inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
		}
	}
}
