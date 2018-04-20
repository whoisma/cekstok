package com.ma.display.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.ma.display.BuildConfig;

/**
 * Created by MA on 07/01/2018.
 */

public class UsersSession {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "DisplayToko";

    private static final String PHONE = "phone";
    private static final String ISLOGIN = "loged";
    private static final String BASE_URL = "baseUrl";

    public UsersSession(Context context) {
        this.context = context;

        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = this.sharedPreferences.edit();
    }

    public void setPhone(String phone){
        editor.putString(PHONE, phone);
        editor.commit();
    }

    public String getPhone(){
        return sharedPreferences.getString(PHONE, "");
    }

    public void setLogin(boolean b){
        editor.putBoolean(ISLOGIN, b);
        editor.commit();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }

    public void setBaseUrl(String s){
        editor.putString(BASE_URL, s);
        editor.commit();
    }

    public String getBaseUrl(){
        return sharedPreferences.getString(BASE_URL, BuildConfig.BASE_URL_VPS);
    }
}
