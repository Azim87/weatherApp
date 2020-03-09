package com.example.wheatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.data.remote.WeatherApiService
import com.example.wheatherapp.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private var weatherApi: WeatherApiService) : IWeatherRepository {
    private val appKey = "4bb7471659f7cdb381168351ef966d4f"

    val data = MutableLiveData<ResponseModel>()

    override suspend  fun getWeather(): MutableLiveData<ResponseModel> {
        weatherApi = RetrofitClient.buildService()
        weatherApi.getWeather(
            139.01,
            35.02,
            "ru",
            appKey
        ).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                data.value = response.body()
            }

            override fun onFailure(
                call: Call<ResponseModel>,
                t: Throwable
            ) {
                data.value = null
            }
        })
        return data
    }
}