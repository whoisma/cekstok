package com.ma.display.listener;

import com.ma.display.base.BaseListener;
import com.ma.display.response.SearchProductResponse;

/**
 * Created by MA on 09/01/2018.
 */

public interface ProductListListener extends BaseListener{

    void onClickItems(int position);

    void onSuccessResponse(SearchProductResponse response);

    void onFailResponse(String err);
}
