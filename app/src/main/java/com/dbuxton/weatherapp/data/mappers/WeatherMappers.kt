package com.dbuxton.weatherapp.data.mappers

import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.remote.ForecastDto
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// Static counter to avoid duplicate ids
var idForecast = 0
fun idForecast() = idForecast++

fun ForecastDto.toHourlyDataList(): List<HourlyData> {
    val hourlyDataList = mutableListOf<HourlyData>()
    weatherData.first().hours.map {
        val hourlyData = HourlyData(
            cityName = address,
            time = LocalTime.parse(it.datetime, DateTimeFormatter.ISO_LOCAL_TIME),
            temp = it.temp.toInt(),
            icon = it.icon
        )
        hourlyDataList.add(hourlyData)
    }
    return hourlyDataList
}

fun ForecastDto.toForecastDataList(): List<ForecastData> {
    for (i in 0 until weatherData.size) {
        val forecastData = weatherData[i]
        val forecast = ForecastData(
            id_forecast = idForecast,
            cityName = address,
            date = LocalDate.parse(forecastData.datetime, DateTimeFormatter.ISO_LOCAL_DATE),
            maxTemperature = forecastData.tempmax.toInt(),
            minTemperature = forecastData.tempmin.toInt(),
            temperature = forecastData.temp.toInt(),
            precipitationProbability = forecastData.precipprob.toInt(),
            windSpeed = forecastData.windspeed,
            uvIndex = forecastData.uvindex,
            condition = forecastData.conditions,
            description = forecastData.description,
            icon = forecastData.icon,
            isFavourite = false,
            isSaved = false
        )
        idForecast()
        return listOf(forecast)
    }
    return emptyList()
}