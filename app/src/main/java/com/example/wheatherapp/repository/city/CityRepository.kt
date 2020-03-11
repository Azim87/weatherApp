package com.example.wheatherapp.repository.city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.BuildConfig
import com.example.wheatherapp.base.BaseRetrofitCallback
import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.model.city.CityModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityRepository(private val retrofitClient: RetrofitClient) {

    private val BASE_URL = BuildConfig.BASE_URL_CITY
    var data = MutableLiveData<List<CityModel>>()

    fun getCityByCapital(city: String): MutableLiveData<List<CityModel>> {
        retrofitClient
            .buildRetrofit(BASE_URL)
            .getCity(city)
            .enqueue(object: Callback<List<CityModel>> {
                override fun onResponse(call: Call<List<CityModel>>, response: Response<List<CityModel>>) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<List<CityModel>>, t: Throwable) {
                    Log.d("ololo", "repo  "  + t.localizedMessage)
                }
            })
        return data
    }
}