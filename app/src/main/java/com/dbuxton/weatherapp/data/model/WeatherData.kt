package com.dbuxton.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "weather_data", primaryKeys = ["city_id", "forecast_id"])
data class WeatherData(
    @ColumnInfo(name = "city_id") val cityName: Int,
    @ColumnInfo(name = "forecast_id") val forecastDataId: Int
)