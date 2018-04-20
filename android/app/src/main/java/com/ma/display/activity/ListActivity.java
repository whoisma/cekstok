package com.ma.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ma.display.base.App;
import com.ma.display.R;
import com.ma.display.adapter.ProductAdapter;
import com.ma.display.listener.ProductListListener;
import com.ma.display.model.ProductModel;
import com.ma.display.presenter.ProductListPresenter;
import com.ma.display.response.SearchProductResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MA on 09/01/2018.
 */

public class ListActivity extends App implements ProductListListener{

    private XRecyclerView recyclerView;
    private RelativeLayout layout;
    private ProductAdapter adapter;
    private List<ProductModel> list = new ArrayList<>();
    private EditText inputSearch;
    private FrameLayout btnSearch;
    private ProductListPresenter presenter;
    private String searchText = "";
    private boolean isLoading = true, isFist = true;

    public static final String RESULT = "RESULT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        inputSearch = findViewById(R.id.input_search);
        btnSearch = findViewById(R.id.btn_search);
        layout = findViewById(R.id.layout);

        presenter = new ProductListPresenter(this, usersSession.getBaseUrl());
        adapter = new ProductAdapter(list, this, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setArrowImageView(R.mipmap.ic_arrows);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setFootViewText(getResources().getString(R.string.txt_loading), "Tidak ada lagi");
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                adapter.notifyDataSetChanged();
                isFist = true;

                searchText = inputSearch.getText().toString();
                presenter.fetchData(searchText, 0);
            }

            @Override
            public void onLoadMore() {

                if (isLoading) presenter.fetchData(searchText, list.size());
            }
        });
        recyclerView.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchProduct();
            }
        });

        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchProduct();

                    return true;
                }

                return false;
            }
        });
    }

    private void searchProduct(){
        if (inputSearch.getText().toString().isEmpty()){
            Snackbar.make(layout, getString(R.string.err_search_text), Snackbar.LENGTH_SHORT).show();
            inputSearch.requestFocus();

            return;
        }

        if (inputSearch.getText().toString().length() < 3){
            Snackbar.make(layout, getString(R.string.err_search_text_3), Snackbar.LENGTH_SHORT).show();
            inputSearch.requestFocus();

            return;
        }

        recyclerView.refresh();
    }

    @Override
    public void onClickItems(int position) {
        Intent intent = getIntent();
        Gson gson = new Gson();
        String s = gson.toJson(list.get(position));
        intent.putExtra(RESULT, s);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onSuccessResponse(SearchProductResponse response) {
        if (response.isStatus()){
            list.addAll(response.getData());
            adapter.notifyDataSetChanged();

            if (isFist){
                recyclerView.refreshComplete();
            }else{
                if (response.getData().size() == 0){
                    recyclerView.setNoMore(true);
                }else{
                    recyclerView.loadMoreComplete();
                }
            }
        }

        isFist = false;
    }

    @Override
    public void onFailResponse(String err) {
        recyclerView.refreshComplete();

        showALert(err, new AlertListener() {
            @Override
            public void onOke() {

            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != android.R.id.home) {

            return super.onOptionsItemSelected(item);
        }

        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        finish();
    }
}
