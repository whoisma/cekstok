package com.ma.display.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ma.display.model.AuthModel;

/**
 * Created by MA on 08/01/2018.
 */

public class UsersResponse {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private AuthModel data;

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

    public AuthModel getData() {
        return data;
    }

    public void setData(AuthModel data) {
        this.data = data;
    }
}
