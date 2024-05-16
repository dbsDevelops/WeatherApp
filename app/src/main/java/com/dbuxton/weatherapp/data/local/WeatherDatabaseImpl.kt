package com.dbuxton.weatherapp.data.local

import android.app.Application
import androidx.room.Room

class WeatherDatabaseImpl(application: Application) {
    val db = Room.databaseBuilder(
        context = application.applicationContext,
        WeatherDatabase::class.java, "weather_database"
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}