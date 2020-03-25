package com.example.wheatherapp.data.remote

import MainWeatherModel
import com.example.wheatherapp.model.city.CityModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("units") metric: String,
        @Query("lang") language: String = Locale.getDefault().language
    ): Deferred<MainWeatherModel>

    @GET("rest/v2/capital/{city}")
     fun getCity(@Path("city") capital: String): Deferred<List<CityModel>>
}