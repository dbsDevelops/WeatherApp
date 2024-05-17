package com.dbuxton.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "forecast_data")
data class ForecastData(
    @PrimaryKey val id_forecast: Int = 0,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "temperature") val temperature: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "condition") val condition: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "min_temperature") val minTemperature: Int,
    @ColumnInfo(name = "max_temperature") val maxTemperature: Int,
    @ColumnInfo(name = "precipitation_probability") val precipitationProbability: Int,
    @ColumnInfo(name = "uv_index") val uvIndex: Int,
    @ColumnInfo(name = "wind_speed") val windSpeed: Double,
    @ColumnInfo(name = "is_favourite") var isFavourite: Boolean,
    @ColumnInfo(name = "is_saved") var isSaved: Boolean
)
