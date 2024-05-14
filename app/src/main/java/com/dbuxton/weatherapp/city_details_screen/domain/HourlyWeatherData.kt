package com.dbuxton.weatherapp.city_details_screen.domain

data class HourlyWeatherData(
    val datetime: String,
    val temp: Int,
    val icon: String
)