package com.ma.display.presenter;

import android.util.Log;

import com.ma.display.base.BasePresenter;
import com.ma.display.listener.ProductListListener;
import com.ma.display.network.RequestCallback;
import com.ma.display.response.SearchProductResponse;

/**
 * Created by MA on 10/01/2018.
 */

public class ProductListPresenter extends BasePresenter<ProductListListener> {

    public ProductListPresenter(ProductListListener view, String baseUrl) {
        super.attachView(view, baseUrl);
    }

    public void fetchData(String query, int offset){
        view.showLoading();

        onSubscribe(service.getSearchProduct(query, offset), new RequestCallback<SearchProductResponse>() {
            @Override
            public void onFailure(String str) {
                view.hideLoading();
                Log.e(getClass().getSimpleName(), str);
                view.onFailResponse(str);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(SearchProductResponse model) {
                view.hideLoading();
                view.onSuccessResponse(model);
            }
        });
    }
}
