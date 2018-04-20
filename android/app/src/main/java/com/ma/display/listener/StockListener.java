package com.ma.display.listener;

import com.ma.display.base.BaseListener;
import com.ma.display.response.DefaultResponse;

/**
 * Created by MA on 10/01/2018.
 */

public interface StockListener extends BaseListener {

    void onSuccessResponse(DefaultResponse response);

    void onFailResponse(String err);
}
