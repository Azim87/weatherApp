package com.example.wheatherapp.repository.weather

import MainWeatherModel
import com.example.wheatherapp.utils.UseCaseResult

interface WeatherRepository {
    suspend fun getWeather(
        longitude: Double,
        latitude: Double,
        metric: String
    ): UseCaseResult<MainWeatherModel>
}