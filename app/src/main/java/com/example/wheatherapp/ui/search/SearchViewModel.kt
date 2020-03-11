package com.example.wheatherapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.base.SingleLiveEvent
import com.example.wheatherapp.model.city.CityModel
import com.example.wheatherapp.repository.city.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel constructor(private val cityRepository: CityRepository) : ViewModel() {

    var cityData = MutableLiveData<List<CityModel>>()
    var loading = SingleLiveEvent<Boolean>()


    fun getCity(city: String)  {
        loading.value = true
        viewModelScope.launch {
            getCityData(city)
        }
    }

    suspend fun getCityData(city: String) = withContext(Dispatchers.IO) {
        cityData = cityRepository.getCityByCapital(city)
        loading.postValue(false)
    }
}