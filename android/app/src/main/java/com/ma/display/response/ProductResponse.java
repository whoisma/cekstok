package com.ma.display.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ma.display.model.ProductModel;

/**
 * Created by MA on 08/01/2018.
 */

public class ProductResponse {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private ProductModel data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProductModel getData() {
        return data;
    }

    public void setData(ProductModel data) {
        this.data = data;
    }
}
