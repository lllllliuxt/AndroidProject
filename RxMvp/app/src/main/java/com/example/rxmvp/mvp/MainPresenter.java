package com.example.rxmvp.mvp;

import com.example.rxmvp.utity.WeatherInfo;

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mainModel = new MainModel();

    public void loadData(){
//        download
        mainModel.download("101010100", new MainModel.DownloadListener() {
            @Override
            public void showLoading() {
                mvpView.showLoading();
            }

            @Override
            public void hideLoading() {
                mvpView.hideLoading();
            }

            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                mvpView.getDataSuccess(weatherInfo);
            }

            @Override
            public void onFail(String msg) {
                mvpView.getDataFail(msg);
            }
        });

    }


}
