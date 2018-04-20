package com.ma.display.listener;

import com.ma.display.base.BaseListener;
import com.ma.display.response.UsersResponse;

/**
 * Created by MA on 08/01/2018.
 */

public interface LoginListener extends BaseListener{

    void onLoginSuccess(UsersResponse response);

    void onLoginFail(String err);
}
