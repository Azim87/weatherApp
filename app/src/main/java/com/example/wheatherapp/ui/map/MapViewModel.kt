package com.example.wheatherapp.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.base.SingleLiveEvent
import com.example.wheatherapp.model.ResponseModel
import com.example.wheatherapp.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MapViewModel constructor(private val repository: WeatherRepository) : ViewModel(){


    var weatherData = MutableLiveData<ResponseModel>()
    val showLoading = MutableLiveData<Boolean>()


    fun getRemoteWeatherData(): MutableLiveData<ResponseModel> {
        showLoading.value = true

        viewModelScope.launch {
            weatherData = repository.data
            showLoading.value = false
        }
        return weatherData
    }
}