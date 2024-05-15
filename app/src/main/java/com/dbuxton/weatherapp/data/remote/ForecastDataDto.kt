package com.dbuxton.weatherapp.data.remote

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDataDto(
    @SerializedName("datetime") val datetime: String,
    @SerializedName("tempmax") val tempmax: Double,
    @SerializedName("tempmin")val tempmin: Double,
    @SerializedName("temp") val temp: Double,
    @SerializedName("precipprob") val precipprob: Double,
    @SerializedName("windspeed") val windspeed: Double,
    @SerializedName("uvindex") val uvindex: Int,
    @SerializedName("conditions") val conditions: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
    //@field:Json(name = "datetime")
//    val datetime: List<String>,
//    @field:Json(name = "tempmax")
//    val maxTemperatures: List<Double>,
//    @field:Json(name = "tempmin")
//    val minTemperatures: List<Double>,
//    @field:Json(name = "temp")
//    val temperatures: List<Double>,
//    @field:Json(name = "precipprob")
//    val precipitationProbabilities: List<Int>,
//    @field:Json(name = "windspeed")
//    val windSpeeds: List<Double>,
//    @field:Json(name = "uvindex")
//    val uvIndexes: List<Int>,
//    @field:Json(name = "conditions")
//    val conditions: List<String>,
//    @field:Json(name = "description")
//    val descriptions: List<String>,
//    @field:Json(name = "icon")
//    val icons: List<String>,
)