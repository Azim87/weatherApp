package com.example.wheatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateFormatHelper {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun showTime(date: Long): String {
            val date = Date(date * 1000L)
            val format = SimpleDateFormat("dd.MM.yyyy  HH:mm")
            val formatted = format.format(date)
            return formatted
        }
    }
}