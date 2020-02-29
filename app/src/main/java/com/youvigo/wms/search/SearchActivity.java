package com.youvigo.wms.search;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youvigo.wms.R;
import com.youvigo.wms.deliver.DeliverActivity;
import com.youvigo.wms.outstock.ReservedOutBoundActivity;
import com.youvigo.wms.shelving.ShelvingActivity;
import com.youvigo.wms.util.Constants;
import com.youvigo.wms.util.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * 查询页面
 */
public class SearchActivity extends AppCompatActivity {

	private ProgressBar progressBar;
	private EditText orderNumnerTxt;
	private TextView startDate, endDate;

	private SearchAdapter adapter;
	private ArrayList filter_movetypes;
	private SearchViewModel viewModel;

	// 查询类型
	private String category;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		initViews();
		observeData();
		initIntent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void initIntent() {
		if (getIntent() != null) {

			String orderNumber;

			if (category == null) {
				return;
			}

			switch (category) {

				case Constants.TYPE_SHELVING:
					getSupportActionBar().setTitle("上架查询");
					orderNumber = getIntent().getStringExtra(ShelvingActivity.ORDER_NUMBER);

					if (!orderNumber.isEmpty()) {
						orderNumnerTxt.setText(orderNumber);
					}

					break;
				case Constants.TYPE_DELIVER:
					getSupportActionBar().setTitle("下架查询");
					orderNumber = getIntent().getStringExtra(DeliverActivity.ORDER_NUMBER);

					if (!orderNumber.isEmpty()) {
						orderNumnerTxt.setText(orderNumber);
					}
					break;
				case Constants.TYPE_RESERVED_OUT_BOUND:
					getSupportActionBar().setTitle("预留查询");
					orderNumber = getIntent().getStringExtra(ReservedOutBoundActivity.ORDER_NUMBER);
					filter_movetypes = getIntent().getStringArrayListExtra(ReservedOutBoundActivity.FILTER_MOVETYPES);

					// 如果传递进来单据号则直接查询
					if (!orderNumber.isEmpty()) {
						orderNumnerTxt.setText(orderNumber);
					}
					break;
				default:
					query(category);

			}
		}
	}

	private void initViews() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		startDate = findViewById(R.id.tv_start);
		endDate = findViewById(R.id.tv_end);

		category = getIntent().getStringExtra(Constants.CATEGORY);

		// create localdate instance for takeing current year,month,day...
		LocalDate dateNow = LocalDate.now();
		String dateNowStr = dateNow.format(DateTimeFormatter.ofPattern(Constants.DATE_PATTERN_DEFAULT));
		int Day = dateNow.getDayOfMonth();
		int Month = dateNow.getMonthValue() - 1;
		int Year = dateNow.getYear();

		// set view default value
		startDate.setText(dateNowStr);
		endDate.setText(dateNowStr);

		// set listener
		startDate.setOnClickListener(v ->
				new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
						startDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString()), Year, Month, Day - 1)
						.show()
		);

		endDate.setOnClickListener(v ->
				new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
						endDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString()), Year, Month, Day)
						.show()
		);

		orderNumnerTxt = findViewById(R.id.edit_text);
		progressBar = findViewById(R.id.progress_bar);
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new SearchAdapter();
		recyclerView.setAdapter(adapter);
	}

	/**
	 * 查询
	 */
	private void query(String category) {
		hideKeyboard();
		orderNumnerTxt.clearFocus();

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
		LocalDate localDateStart = LocalDate.parse(this.startDate.getText().toString());
		LocalDate localDateEnd = LocalDate.parse(this.endDate.getText().toString());
		String year = String.valueOf(localDateStart.getYear());

		switch (category) {
			case Constants.TYPE_SHELVING:
				viewModel.shelvingQuery(year, localDateStart.format(dateTimeFormatter),
						localDateEnd.format(dateTimeFormatter), orderNumnerTxt.getText() == null ? "" :
								orderNumnerTxt.getText().toString(), getApplicationContext());
				break;
			case Constants.TYPE_DELIVER:
				viewModel.tackOffQuery(localDateStart.format(dateTimeFormatter),
						localDateEnd.format(dateTimeFormatter), orderNumnerTxt.getText() == null ? "" :
								orderNumnerTxt.getText().toString(), getApplicationContext());
				break;
			case Constants.TYPE_RESERVED_OUT_BOUND:
				viewModel.reservedOutBoundQuery(orderNumnerTxt.getText() == null ? "" :
								orderNumnerTxt.getText().toString(), localDateStart.format(dateTimeFormatter),
						localDateEnd.format(dateTimeFormatter), filter_movetypes, getApplicationContext());
				break;
			default:
				break;
		}
	}

	/**
	 * 观察数据变化
	 */
	private void observeData() {
		viewModel = new ViewModelProvider.NewInstanceFactory().create(SearchViewModel.class);

		//viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

		viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE :
				View.GONE));

		viewModel.materials().observe(this, materials -> {
			adapter.submitList(materials);
		});

		// 显示查询结果信息
		viewModel.getQueryState().observe(this, queryState -> {
			if (queryState == null) return;
			if (!queryState.isSuccess()) {
				Utils.showToast(this, queryState.getMessage());
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		} else if (item.getItemId() == R.id.menu_search) {
			query(category);
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
			inputMethodManager.hideSoftInputFromWindow(orderNumnerTxt.getWindowToken(), 0);
		}
	}
}
