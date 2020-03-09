package com.example.wheatherapp.repository

import MainWeatherModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.data.remote.WeatherApiService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val retrofitClient: RetrofitClient){

    private val appKey = "4bb7471659f7cdb381168351ef966d4f"

    private val data = MutableLiveData<MainWeatherModel>()
    fun getWeather(lon: Double, lat: Double, metric: String): MutableLiveData<MainWeatherModel> {
       retrofitClient.buildRetrofit().getWeather(
            lon,
            lat,
            metric,
            appKey
        ).enqueue(object : Callback<MainWeatherModel> {
            override fun onResponse(
                call: Call<MainWeatherModel>,
                response: Response<MainWeatherModel>) {
                data.value = response.body()
                Log.d("ololo", "repo" + response.body())
            }

            override fun onFailure(call: Call<MainWeatherModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}