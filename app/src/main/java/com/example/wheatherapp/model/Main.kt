package com.example.wheatherapp.model

import com.google.gson.annotations.SerializedName

data class Main(
	@field:SerializedName("temp")
    val temp: Double,

	@field:SerializedName("pressure")
    val pressure: Double,

	@field:SerializedName("humidity")
    val humidity: Int,

	@field:SerializedName("temp_min")
    val tempMin: Double,

	@field:SerializedName("temp_max")
    val tempMax: Double,

	@field:SerializedName("sea_level")
    val seaLevel: Double,

	@field:SerializedName("grnd_level")
    val grndLevel: Double
)