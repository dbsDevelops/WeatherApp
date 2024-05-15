package com.dbuxton.weatherapp.data.mappers

import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.remote.ForecastDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private data class IndexedForecastData(
    val index: Int,
    val data: ForecastData
)


fun ForecastDto.toForecastDataList(): List<ForecastData> {
    for (i in 0 until weatherData.size) {
        val forecastData = weatherData[i]
        val forecast = ForecastData(
            cityName = address,
            datetime = LocalDate.parse(forecastData.datetime.toString(), DateTimeFormatter.ISO_LOCAL_DATE),
            maxTemperature = forecastData.tempmax.toInt(),
            minTemperature = forecastData.tempmin.toInt(),
            temperature = forecastData.temp.toInt(),
            precipitationProbability = forecastData.precipprob.toInt(),
            windSpeed = forecastData.windspeed,
            uvIndex = forecastData.uvindex,
            condition = forecastData.conditions,
            description = forecastData.description,
            icon = forecastData.icon
        )
        return listOf(forecast)
    }
    return emptyList()
}