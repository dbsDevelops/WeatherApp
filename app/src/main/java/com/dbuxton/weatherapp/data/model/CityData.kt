package com.dbuxton.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val latitude: Double,
//    val longitude: Double,
    val address: String,
    val forecasts: List<ForecastData>
)
