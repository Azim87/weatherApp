package com.example.wheatherapp.data.remote

import MainWeatherModel
import com.example.wheatherapp.model.city.CityModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("units") metric: String,
        @Query("lang") language: String,
        @Query("appid") appKey: String
    ): Call<MainWeatherModel>

    @GET("rest/v2/capital/{city}")
     fun getCity(@Path("city") capital: String): Call<List<CityModel>>
}