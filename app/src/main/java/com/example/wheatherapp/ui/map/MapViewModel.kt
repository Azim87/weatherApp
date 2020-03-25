package com.example.wheatherapp.ui.map

import MainWeatherModel
import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.base.BaseViewModel
import com.example.wheatherapp.base.SingleLiveEvent
import com.example.wheatherapp.repository.weather.WeatherRepository
import com.example.wheatherapp.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel(
    private val repository: WeatherRepository
) : BaseViewModel() {

    var weatherList = MutableLiveData<MainWeatherModel>()
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()


    fun loadWeatherData(lon: Double, lat: Double, metric: String) {
        showLoading.value = true

        launch {
            val weatherResult = withContext(Dispatchers.IO) {
                repository.getWeather(lon, lat, metric)
            }
            showLoading.value = false

            when (weatherResult) {
                is UseCaseResult.Success -> weatherList.value = weatherResult.data
                is UseCaseResult.Error -> showError.value = weatherResult.exception.localizedMessage
            }
        }
    }
}