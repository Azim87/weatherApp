package com.example.wheatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.App

class NetworkHelper {
    fun isOnline(): Boolean {
        val connectivityManager = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}