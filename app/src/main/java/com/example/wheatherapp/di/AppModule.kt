package com.example.wheatherapp.di

import com.example.wheatherapp.data.remote.RetrofitClient
import com.example.wheatherapp.repository.WeatherRepository
import com.example.wheatherapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    viewModel { MapViewModel(get()) }
    single{ WeatherRepository(get()) }
    single { RetrofitClient() }
}
val appModules = listOf(repoModule)