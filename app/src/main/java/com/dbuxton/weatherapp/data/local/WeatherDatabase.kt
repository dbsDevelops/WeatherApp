package com.dbuxton.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dbuxton.weatherapp.data.mappers.Converters
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.model.UserData

@Database(entities = [UserData::class, ForecastData::class, HourlyData::class], version = 17, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}