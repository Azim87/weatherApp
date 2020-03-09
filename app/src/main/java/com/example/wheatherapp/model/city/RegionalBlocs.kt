package com.example.wheatherapp.model.city

import com.google.gson.annotations.SerializedName

data class RegionalBlocs (

	@SerializedName("acronym") val acronym : String,
	@SerializedName("name") val name : String,
	@SerializedName("otherAcronyms") val otherAcronyms : List<String>,
	@SerializedName("otherNames") val otherNames : List<String>
)