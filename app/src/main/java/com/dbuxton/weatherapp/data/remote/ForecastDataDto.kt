package com.dbuxton.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

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
    @SerializedName("icon") val icon: String,
    @SerializedName("hours") val hours: List<HourlyDataDto>
)