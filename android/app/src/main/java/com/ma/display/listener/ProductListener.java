package com.ma.display.listener;

import com.ma.display.base.BaseListener;
import com.ma.display.response.ProductResponse;

/**
 * Created by MA on 10/01/2018.
 */

public interface ProductListener extends BaseListener{

    void onSuccessResponse(ProductResponse response);

    void onFailResponse(String err);
}
