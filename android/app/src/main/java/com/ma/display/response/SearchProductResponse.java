package com.ma.display.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ma.display.model.ProductModel;

import java.util.List;

/**
 * Created by MA on 08/01/2018.
 */

public class SearchProductResponse {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private List<ProductModel> data;

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

    public List<ProductModel> getData() {
        return data;
    }

    public void setData(List<ProductModel> data) {
        this.data = data;
    }
}
