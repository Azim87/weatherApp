package com.example.wheatherapp.ui.search

import androidx.lifecycle.MutableLiveData
import com.example.wheatherapp.base.BaseViewModel
import com.example.wheatherapp.base.SingleLiveEvent
import com.example.wheatherapp.model.city.CityModel
import com.example.wheatherapp.repository.city.CityRepository
import com.example.wheatherapp.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel
    (private val cityRepository: CityRepository
) : BaseViewModel() {

    val cityList = MutableLiveData<List<CityModel>>()
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()

    fun loadCities(city: String) {
        showLoading.value = true

        launch {
            val cityResult = withContext(Dispatchers.IO) {
                cityRepository.getCity(city)
            }
            showLoading.value = false

            when (cityResult) {
                is UseCaseResult.Success -> cityList.value = cityResult.data
                is UseCaseResult.Error -> showError.value = cityResult.exception.localizedMessage
            }
        }
    }

}