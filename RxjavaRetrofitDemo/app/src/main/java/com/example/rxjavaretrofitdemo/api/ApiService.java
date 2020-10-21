package com.example.rxjavaretrofitdemo.api;

import com.example.rxjavaretrofitdemo.utity.Banner;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("banners")
    Observable<Banner> getBanner();
}
