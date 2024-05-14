package com.dbuxton.weatherapp.data.mappers

import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.remote.ForecastDataDto
import com.dbuxton.weatherapp.data.remote.ForecastDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private data class IndexedForecastData(
    val index: Int,
    val data: ForecastData
)


fun ForecastDto.toForecastDataMap(): Map<Int, List<ForecastData>> {
    return weatherData.datetime.mapIndexed { index, date ->
        val cityName = address[index]
        val temperature = weatherData.temperatures[index]
        val description = weatherData.descriptions[index]
        val condition = weatherData.conditions[index]
        val icon = weatherData.icons[index]
        val minTemperature = weatherData.minTemperatures[index]
        val maxTemperature = weatherData.maxTemperatures[index]
        val precipitationProbability = weatherData.precipitationProbabilities[index]
        val uvIndex = weatherData.uvIndexes[index]
        val windSpeed = weatherData.windSpeeds[index]
        IndexedForecastData(
            index = index,
            data = ForecastData(
                cityName = cityName.toString(),
                date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE),
                temperature = temperature.toInt(),
                description = description,
                condition = condition,
                icon = icon,
                minTemperature = minTemperature.toInt(),
                maxTemperature = maxTemperature.toInt(),
                precipitationProbability = precipitationProbability,
                uvIndex = uvIndex,
                windSpeed = windSpeed
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map(IndexedForecastData::data)
    }
}