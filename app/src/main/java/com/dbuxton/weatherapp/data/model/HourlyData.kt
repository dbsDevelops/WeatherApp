package com.dbuxton.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "hourly_data")
data class HourlyData (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "time") val time: LocalTime,
    @ColumnInfo(name = "temperature") val temp: Int,
    @ColumnInfo(name = "icon") val icon: String
)