package com.example.wheatherapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.base.SingleLiveEvent
import com.example.wheatherapp.model.city.CityModel
import com.example.wheatherapp.repository.city.CityRepository
import kotlinx.coroutines.launch

class SearchViewModel constructor(private val cityRepository: CityRepository) : ViewModel() {

    var cityData = MutableLiveData<List<CityModel>>()
    var loading = SingleLiveEvent<Boolean>()

    fun getCity(city: String)  {
        loading.value = true
        viewModelScope.launch {
            cityData = cityRepository.getCityByCapital(city)
            Log.d("ololo", " view model " + cityData.value)
            loading.value = false
        }
    }
}