package com.ma.display.presenter;

import com.ma.display.base.BasePresenter;
import com.ma.display.listener.ProductListener;
import com.ma.display.network.RequestCallback;
import com.ma.display.response.ProductResponse;

/**
 * Created by MA on 10/01/2018.
 */

public class ProductPresenter extends BasePresenter<ProductListener> {

    public ProductPresenter(ProductListener view, String baseUrl) {
        super.attachView(view, baseUrl);
    }

    public void product(String toko, String kode){
        view.showLoading();

        onSubscribe(service.getProduct(toko, kode), new RequestCallback<ProductResponse>() {
            @Override
            public void onFailure(String str) {
                view.hideLoading();
                view.onFailResponse(str);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(ProductResponse o) {
                view.hideLoading();
                view.onSuccessResponse(o);
            }
        });
    }
}
