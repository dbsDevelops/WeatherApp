package com.dbuxton.weatherapp.city_details_screen.weather

import com.dbuxton.weatherapp.city_details_screen.data.HourlyData

class WeatherApi {

    fun getHourlyWeatherData(city: String): List<HourlyData> {
        return listOf(
            HourlyData("12:00", 20, "01d"),
            HourlyData("13:00", 21, "02d"),
            HourlyData("14:00", 22, "03d"),
            HourlyData("15:00", 23, "04d"),
            HourlyData("16:00", 24, "09d"),
            HourlyData("17:00", 25, "10d"),
            HourlyData("18:00", 26, "11d"),
            HourlyData("19:00", 27, "13d"),
            HourlyData("20:00", 28, "50d")
        )
    }
}