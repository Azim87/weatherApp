package com.example.wheatherapp.repository

import MainWeatherModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.BuildConfig
import com.example.wheatherapp.BuildConfig.API_KEY
import com.example.wheatherapp.base.BaseRetrofitCallback
import com.example.wheatherapp.data.remote.RetrofitClient

class WeatherRepository(private val retrofitClient: RetrofitClient) {

    private val appKey = BuildConfig.API_KEY

    private val data = MutableLiveData<MainWeatherModel>()
    fun getWeather(lon: Double, lat: Double, metric: String): MutableLiveData<MainWeatherModel> {
        retrofitClient.buildRetrofit().getWeather(
            lon, lat, metric, appKey
        ).enqueue(object : BaseRetrofitCallback<MainWeatherModel>() {
            override fun onSuccess(results: MainWeatherModel?) {
                data.value = results
                Log.d("ololo", "weather data ${data.value}")
            }

            override fun onFailure(e: Exception?) {
                onFailure(e)
            }
        })
        return data
    }
}