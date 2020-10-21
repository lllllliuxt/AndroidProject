package com.example.rxmvp.netUtil;

import com.example.rxmvp.utity.Weather;
import com.example.rxmvp.utity.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiStore {
    String BASE_URL = "http://www.weather.com.cn/";

    @GET("adat/sk/{cityId}.html")
    Observable<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);
}
