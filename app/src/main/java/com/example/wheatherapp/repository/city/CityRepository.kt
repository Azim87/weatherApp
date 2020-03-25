package com.example.wheatherapp.repository.city

import com.example.wheatherapp.model.city.CityModel
import com.example.wheatherapp.utils.UseCaseResult

interface CityRepository {
    suspend fun getCity(city: String) : UseCaseResult<List<CityModel>>
}