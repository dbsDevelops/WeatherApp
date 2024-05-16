package com.dbuxton.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("address") val address: String,
    @SerializedName("days") val weatherData: List<ForecastDataDto>
)
