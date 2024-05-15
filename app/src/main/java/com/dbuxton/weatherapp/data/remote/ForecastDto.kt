package com.dbuxton.weatherapp.data.remote

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDto(
//    @field:Json(name = "address")
//    val address: String,
//    @field:Json(name = "days")
//    val weatherData: ForecastDataDto
    @SerializedName("address") val address: String,
    @SerializedName("days") val weatherData: List<ForecastDataDto>
)
