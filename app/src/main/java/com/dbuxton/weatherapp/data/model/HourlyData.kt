package com.dbuxton.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.time.LocalTime

@Entity(tableName = "hourly_data",
    primaryKeys = ["city_name", "time"])
data class HourlyData (
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "time") val time: LocalTime,
    @ColumnInfo(name = "temperature") val temp: Int,
    @ColumnInfo(name = "icon") val icon: String
)