package com.ma.display.network;

import com.ma.display.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MA on 07/01/2018.
 */

public class ApiClient {

    private static Retrofit retrofit;
    private static String url;

    public static Retrofit request(String base_url) {
        url = base_url;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        return retrofit;
    }

    public static void changeBaseUrlRetrofit(String baseUrl){
        url = baseUrl;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }
}
