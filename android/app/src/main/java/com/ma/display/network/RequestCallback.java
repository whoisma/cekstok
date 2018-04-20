package com.ma.display.network;

import android.util.Log;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by MA on 07/01/2018.
 */

public abstract class RequestCallback<M> extends Subscriber<M> {

    public abstract void onFailure(String str);

    public abstract void onFinish();

    public abstract void onSuccess(M m);

    private static final String TAG = "RequestCallback";

    public void onError(Throwable e) {
        onFailure("Terjadi kesalahan Koneksi, Silahkan Ulangi beberapa saat lagi !");

//        if (e instanceof HttpException) {
//            onFailure("Terjadi kesalahan Koneksi, Silahkan Ulangi beberapa saat lagi !");
//            HttpException error = (HttpException) e;
//            int str = error.code();
//
//            if (str == 404){
//
//            }
//            onFailure(((HttpException) e).getMessage());
//        } else {
//            onFailure(e.getMessage());
//        }
        Log.e(TAG, e.getMessage());
        e.printStackTrace();
        onFinish();
    }

    public void onNext(M model) {
        onSuccess(model);
    }

    public void onCompleted() {
        onFinish();
    }
}
