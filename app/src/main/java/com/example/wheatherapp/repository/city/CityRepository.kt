package com.example.wheatherapp.repository.city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.BuildConfig
import com.example.wheatherapp.base.BaseRetrofitCallback
import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.model.city.CityModel

class CityRepository(private val retrofitClient: RetrofitClient) {

    private val data = MutableLiveData<List<CityModel>>()
    private val BASE_URL = BuildConfig.BASE_URL_CITY

    fun getCityByCapital(city: String): MutableLiveData<List<CityModel>> {
        retrofitClient
            .buildRetrofit(BASE_URL)
            .getCity(city)
            .enqueue(object : BaseRetrofitCallback<List<CityModel>>() {
                override fun onSuccess(results: List<CityModel>?) {
                    data.value = results
                    Log.d("ololo", "city data ${data.value}")
                }


                override fun onFailure(e: Exception?) {

                }
            })
        return data
    }
}