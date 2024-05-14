package com.dbuxton.weatherapp.data.remote

import com.squareup.moshi.Json
data class ForecastDto(
    @field:Json(name = "address")
    val address: String,
    @field:Json(name = "days")
    val weatherData: ForecastDataDto
)
