package com.example.wheatherapp.model

import com.google.gson.annotations.SerializedName

data class Sys(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("sunrise")
    val sunrise: String,

    @field:SerializedName("sunset")
    val sunset: String
)