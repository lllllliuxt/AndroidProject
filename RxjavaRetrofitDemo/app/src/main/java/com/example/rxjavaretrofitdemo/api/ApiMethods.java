package com.example.rxjavaretrofitdemo.api;


import com.example.rxjavaretrofitdemo.utity.Banner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiMethods {
    public static void ApiSubscribe(Observable observable, Observer observer){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void getBanner(Observer<Banner> observer){
        ApiSubscribe(Api.getApiService().getBanner(), observer);
    }
}
