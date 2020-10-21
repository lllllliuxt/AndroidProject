package com.example.rxmvp.mvp;


import com.example.rxmvp.netUtil.ApiStore;
import com.example.rxmvp.netUtil.RetrofitClient;
import com.example.rxmvp.utity.WeatherInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

// 实现网络数据的下载
public class MainModel {

    interface DownloadListener{
        void showLoading();
        void hideLoading();
        void onSuccess(WeatherInfo weatherInfo);
        void onFail(String msg);
    }

    public void download(String cityId, final DownloadListener listener){
        RetrofitClient.getRetrofit().create(ApiStore.class)
                .getWeatherInfo(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        listener.showLoading();
                    }

                    @Override
                    public void onNext(WeatherInfo weatherInfo) {
//                        Weather tmp  = weatherInfo.getWeather();
//                        Log.e("lxt", tmp.toString());
                        listener.onSuccess(weatherInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        listener.hideLoading();
                    }
                });
    }
}
