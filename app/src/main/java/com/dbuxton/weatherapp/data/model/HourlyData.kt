package com.dbuxton.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "hourly_data")
data class HourlyData (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val datetime: LocalTime,
    val temp: Int,
    val icon: String
)