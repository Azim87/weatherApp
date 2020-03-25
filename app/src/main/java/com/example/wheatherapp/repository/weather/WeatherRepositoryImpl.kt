package com.example.wheatherapp.repository.weather

import MainWeatherModel
import com.example.wheatherapp.BuildConfig
import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.utils.UseCaseResult
import java.util.*

class WeatherRepositoryImpl(
    private val retrofitClient: RetrofitClient
) : WeatherRepository {

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL_WEATHER
    }

    override suspend fun getWeather(
        longitude: Double,
        latitude: Double,
        metric: String

    ): UseCaseResult<MainWeatherModel> {
        return try {
            val weatherData = retrofitClient
                .buildRetrofit(BASE_URL)
                .getWeather(longitude, latitude, metric)
                .await()
            UseCaseResult.Success(weatherData)

        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}
