package com.ma.display.base;

import android.util.Log;

import com.ma.display.network.ApiClient;
import com.ma.display.network.ApiService;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MA on 07/01/2018.
 */

public class BasePresenter<V> {

    private CompositeSubscription compositeSubscription;
    protected ApiService service;
    private Subscriber subscriber;
    public V view;

    private static final String TAG = "BasePresenter";

    public void attachView(V view, String base_url) {
        this.view = view;
        this.service = (ApiService) ApiClient.request(base_url).create(ApiService.class);
    }

    public void dettachView() {
        this.view = null;
        if (this.compositeSubscription != null && this.compositeSubscription.hasSubscriptions()) {
            this.compositeSubscription.unsubscribe();
            Log.e(TAG, "dettachView");
        }
    }

    protected void onSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(
                observable.debounce(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber));
    }

    protected void onZipSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    protected void stop() {
        if (this.subscriber != null) {
            this.subscriber.unsubscribe();
        }
    }
}
