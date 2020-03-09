package com.example.wheatherapp.ui.map

import MainWeatherModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class MapViewModel (private val repository: WeatherRepository) : ViewModel(){
    var weatherData = MutableLiveData<MainWeatherModel>()
    val showLoading = MutableLiveData<Boolean>()

    fun getRemoteWeatherData(lon: Double, lat: Double, metric: String): MutableLiveData<MainWeatherModel> {
        showLoading.value = true
        viewModelScope.launch {
            weatherData = repository.getWeather(lon, lat, metric)
            showLoading.value = false
        }
        return weatherData
    }
}