package com.example.wheatherapp.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import com.example.wheatherapp.utils.NetworkChangeReceiver

class NetworkService : Service() {
    private lateinit var mBroadcastReceiver: BroadcastReceiver

    private fun registerConnectionReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val intentFilter = IntentFilter()
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            registerReceiver(mBroadcastReceiver, intentFilter)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mBroadcastReceiver = NetworkChangeReceiver()
        registerConnectionReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}