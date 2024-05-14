package com.dbuxton.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "forecast")
data class ForecastData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val temperature: Int,
    val description: String,
    val condition: String,
    val icon: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val precipitationProbability: Int,
    val uvIndex: Int,
    val windSpeed: Double
)
