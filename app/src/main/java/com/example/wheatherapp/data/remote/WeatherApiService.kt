package com.example.wheatherapp.data.remote

import com.example.wheatherapp.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/weather")
    suspend fun getWeather(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("metric") metric: String,
        @Query("appid") appKey: String
    ) : Call<ResponseModel>
}