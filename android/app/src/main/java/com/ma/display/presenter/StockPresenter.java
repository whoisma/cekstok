package com.ma.display.presenter;

import com.ma.display.base.BasePresenter;
import com.ma.display.listener.StockListener;
import com.ma.display.network.RequestCallback;
import com.ma.display.response.DefaultResponse;

/**
 * Created by MA on 10/01/2018.
 */

public class StockPresenter extends BasePresenter<StockListener> {

    public StockPresenter(StockListener view, String baseUrl) {
        super.attachView(view, baseUrl);
    }

    public void save(String upc, String toko, String jml, String lokasi){
        view.showLoading();

        onSubscribe(service.editStock(toko, upc, jml, lokasi), new RequestCallback<DefaultResponse>() {
            @Override
            public void onFailure(String str) {
                view.hideLoading();
                view.onFailResponse(str);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(DefaultResponse o) {
                view.hideLoading();
                view.onSuccessResponse(o);
            }
        });
    }
}
