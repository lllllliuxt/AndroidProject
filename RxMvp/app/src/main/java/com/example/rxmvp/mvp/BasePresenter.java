package com.example.rxmvp.mvp;

import com.example.rxmvp.netUtil.ApiStore;
import com.example.rxmvp.netUtil.RetrofitClient;
import com.example.rxmvp.utity.WeatherInfo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Retrofit;

public class BasePresenter<V> {
    public V mvpView;

//    view的绑定与解绑
    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    public void detachView(){
        if(this.mvpView != null){
            this.mvpView = null;
        }
    }
}
