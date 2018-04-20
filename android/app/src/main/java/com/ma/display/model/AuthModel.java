package com.ma.display.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MA on 08/01/2018.
 */

public class AuthModel {

    @SerializedName("user")
    @Expose
    private UsersModel user;

    @SerializedName("token")
    @Expose
    private String token;

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
