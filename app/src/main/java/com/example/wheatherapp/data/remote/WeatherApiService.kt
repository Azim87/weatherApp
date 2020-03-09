package com.example.wheatherapp.data.remote

import MainWeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
     fun getWeather(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("metric") metric: String,
        @Query("appid") appKey: String
    ): Call<MainWeatherModel>
}