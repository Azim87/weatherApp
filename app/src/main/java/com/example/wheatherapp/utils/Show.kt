package com.example.wheatherapp.utils

import android.content.Context
import android.widget.Toast

class Show {
    companion object {
        fun message(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}