package com.ma.display.presenter;

import com.ma.display.base.BasePresenter;
import com.ma.display.listener.LoginListener;
import com.ma.display.network.RequestCallback;
import com.ma.display.response.UsersResponse;

/**
 * Created by MA on 08/01/2018.
 */

public class LoginPresenter extends BasePresenter<LoginListener> {

    public LoginPresenter(LoginListener view, String base_url) {
        super.attachView(view, base_url);
    }

    public void doLogin(String phone){
        view.showLoading();

        onSubscribe(service.getLogin(phone), new RequestCallback<UsersResponse>() {
            @Override
            public void onFailure(String str) {
                view.hideLoading();
                view.onLoginFail(str);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(UsersResponse model) {
                view.hideLoading();
                view.onLoginSuccess(model);
            }
        });
    }
}
