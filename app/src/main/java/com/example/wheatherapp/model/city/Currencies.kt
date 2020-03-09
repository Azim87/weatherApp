package com.example.wheatherapp.model.city
import com.google.gson.annotations.SerializedName

data class Currencies (
	@SerializedName("code") val code : String,
	@SerializedName("name") val name : String,
	@SerializedName("symbol") val symbol : String
)