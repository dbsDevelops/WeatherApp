package com.dbuxton.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class HourlyDataDto(
    @SerializedName("datetime") val datetime: String,
    @SerializedName("temp") val temp: Double,
    @SerializedName("icon") val icon: String
)