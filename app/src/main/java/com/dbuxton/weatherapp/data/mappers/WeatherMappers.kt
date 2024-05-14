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

fun ForecastDataDto.toForecastDataMap(): Map<Int, List<ForecastData>> {
    return datetime.mapIndexed { index, date ->
        val temperature = temperatures[index]
        val description = descriptions[index]
        val condition = conditions[index]
        val icon = icons[index]
        val minTemperature = minTemperatures[index]
        val maxTemperature = maxTemperatures[index]
        val precipitationProbability = precipitationProbabilities[index]
        val uvIndex = uvIndexes[index]
        val windSpeed = windSpeeds[index]
        IndexedForecastData(
            index = index,
            data = ForecastData(
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