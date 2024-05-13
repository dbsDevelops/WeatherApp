package com.dbuxton.weatherapp.city_details_screen.domain

data class HourlyWeatherData(
    val time: String,
    val temperature: Int,
    val weatherIcon: String
)