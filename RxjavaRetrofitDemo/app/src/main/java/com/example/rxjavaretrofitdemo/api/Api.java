package com.example.rxjavaretrofitdemo.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//封装Retrofit请求过程
public class Api {
    public static String BaseUrl = "https://gank.io/api/v2/";

    public static ApiService apiService;

//    单例
    public static ApiService getApiService(){
        if(apiService == null){
            synchronized (Api.class){
                if(apiService == null){
                    new Api();
                }
            }
        }

        return apiService;
    }


    private Api(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//        动态代理
        apiService = retrofit.create(ApiService.class);
    }
}
