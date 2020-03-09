package com.example.wheatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.model.ResponseModel

interface IWeatherRepository {

    suspend fun getWeather() : MutableLiveData<ResponseModel>
}