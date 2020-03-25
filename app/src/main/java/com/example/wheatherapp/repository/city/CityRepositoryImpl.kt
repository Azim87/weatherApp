package com.example.wheatherapp.repository.city

import com.example.wheatherapp.BuildConfig
import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.model.city.CityModel
import com.example.wheatherapp.utils.UseCaseResult


class CityRepositoryImpl(
    private val retrofitClient: RetrofitClient
) : CityRepository {
    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL_CITY
    }

    override suspend fun getCity(city: String): UseCaseResult<List<CityModel>> {
        return try {
            val cityResult = retrofitClient
                .buildRetrofit(BASE_URL)
                .getCity(city)
                .await()
            UseCaseResult.Success(cityResult)

        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}