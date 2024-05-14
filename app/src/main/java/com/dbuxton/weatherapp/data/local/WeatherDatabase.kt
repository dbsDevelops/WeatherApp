package com.dbuxton.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dbuxton.weatherapp.data.converters.Converters
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.UserData
import com.dbuxton.weatherapp.data.model.WeatherData

@Database(entities = [UserData::class, ForecastData::class, WeatherData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}