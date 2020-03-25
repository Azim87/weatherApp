package com.example.wheatherapp.di

import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.repository.city.CityRepository
import com.example.wheatherapp.repository.city.CityRepositoryImpl
import com.example.wheatherapp.repository.weather.WeatherRepository
import com.example.wheatherapp.repository.weather.WeatherRepositoryImpl
import com.example.wheatherapp.ui.map.MapViewModel
import com.example.wheatherapp.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    viewModel { MapViewModel(get()) }
    viewModel { SearchViewModel(get()) }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<CityRepository> { CityRepositoryImpl(get()) }

    single { RetrofitClient() }
}
val appModules = listOf(repoModule)