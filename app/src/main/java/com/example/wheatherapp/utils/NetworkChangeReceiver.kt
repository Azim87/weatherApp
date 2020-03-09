package com.example.wheatherapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkChangeReceiver() : BroadcastReceiver() {
    companion object {
      var isConnected: Boolean? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork: NetworkInfo? = cm.activeNetworkInfo
        isConnected = activityNetwork?.isConnected == true
        Show.message(
            context,
            if (isConnected as Boolean) "Есть интернет соединение"
            else "Нет интернет соединения"
        )
    }
}