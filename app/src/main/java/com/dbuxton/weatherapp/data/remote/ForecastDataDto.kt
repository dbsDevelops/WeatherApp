package com.dbuxton.weatherapp.data.remote

import com.squareup.moshi.Json

data class ForecastDataDto(
    @field:Json(name = "datetime")
    val datetime: List<String>,
    @field:Json(name = "temp")
    val temperatures: List<Double>,
    @field:Json(name = "description")
    val descriptions: List<String>,
    @field:Json(name = "conditions")
    val conditions: List<String>,
    @field:Json(name = "icon")
    val icons: List<String>,
    @field:Json(name = "tempmin")
    val minTemperatures: List<Double>,
    @field:Json(name = "tempmax")
    val maxTemperatures: List<Double>,
    @field:Json(name = "preicpprob")
    val precipitationProbabilities: List<Int>,
    @field:Json(name = "uvindex")
    val uvIndexes: List<Int>,
    @field:Json(name = "windspeed")
    val windSpeeds: List<Double>
)