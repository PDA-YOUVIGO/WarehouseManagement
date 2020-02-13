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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.youvigo.wms.R;
import com.youvigo.wms.deliver.DeliverActivity;
import com.youvigo.wms.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import timber.log.Timber;

/**
 * 查询页面
 */
public class SearchActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private AppCompatEditText orderNumnerTxt;
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

        initIntent();

    }

    private void initIntent() {
        if (getIntent() != null) {

            String category = getIntent().getStringExtra(Constants.CATEGORY);
            String orderNumber = getIntent().getStringExtra(DeliverActivity.TASK_NUMBER);
            if (category == null) {
                return;
            }

            switch (category) {
                case Constants.TYPE_SHELVING:
                    getSupportActionBar().setTitle("上架查询");
                    break;
                case Constants.TYPE_TAKE_OFF:
                    getSupportActionBar().setTitle("下架查询");
                    orderNumnerTxt.setText(orderNumber);

                    // 如果传递进来单据号则直接查询
                    if (!orderNumber.isEmpty()) {
                        query(category);
                    }

                    break;
                case Constants.TYPE_OUT_OF_STOCK:
                    getSupportActionBar().setTitle("预留查询");
                    break;
            }
        }
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String category = getIntent().getStringExtra(Constants.CATEGORY);

        LocalDate localDate = LocalDate.now();

        startDate = findViewById(R.id.tv_start);
        startDate.setText(localDate.toString());

        startDate.setOnClickListener(v -> new DatePickerDialog(SearchActivity.this, (view, year, month, dayOfMonth) -> {
            startDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString());
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).show());

        endDate = findViewById(R.id.tv_end);
        endDate.setText(localDate.toString());
        endDate.setOnClickListener(v -> new DatePickerDialog(SearchActivity.this, (view, year, month, dayOfMonth) -> {
            endDate.setText(LocalDate.of(year, month + 1, dayOfMonth).toString());
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).show());

        // 查询
        MaterialButton materialButton = findViewById(R.id.mbt_query);
        materialButton.setOnClickListener(v -> {
            query(category);

        });

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
                viewModel.shelvingQuery(year,localDateStart.format(dateTimeFormatter), localDateEnd.format(dateTimeFormatter), orderNumnerTxt.getText() == null ? "" : orderNumnerTxt.getText().toString());
                break;
            case Constants.TYPE_TAKE_OFF:
                viewModel.tackOffQuery(localDateStart.format(dateTimeFormatter), localDateEnd.format(dateTimeFormatter), orderNumnerTxt.getText() == null ? "" : orderNumnerTxt.getText().toString());
                break;
            case Constants.TYPE_OUT_OF_STOCK:
                getSupportActionBar().setTitle("预留查询");
                break;
        }
    }

    /**
     * 观察数据变化
     */
    private void observeData() {
        viewModel = new ViewModelProvider.NewInstanceFactory().create(SearchViewModel.class);

        //viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        viewModel.isLoading().observe(this, isActive -> progressBar.setVisibility(isActive ? View.VISIBLE : View.GONE));
        viewModel.materials().observe(this, materials -> {
            adapter.submitList(materials);
            adapter.notifyDataSetChanged();
        });

        // 显示查询结果信息
        viewModel.getQueryState().observe(this, queryState -> {
            if (queryState == null) return;
            if (!queryState.isSuccess()) {
                Toast.makeText(this, queryState.getMessage(), Toast.LENGTH_LONG).show();
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
            inputMethodManager.hideSoftInputFromWindow(orderNumnerTxt.getWindowToken(), 0);
        }
    }
}
