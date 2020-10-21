package com.example.rxmvp.mvp;

import com.example.rxmvp.utity.WeatherInfo;

public interface MainView extends BaseView {
    void getDataSuccess(WeatherInfo weatherInfo);

    void getDataFail(String msg);
}
