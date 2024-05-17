package com.dbuxton.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dbuxton.weatherapp.data.mappers.Converters
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.model.UserData

@Database(entities = [UserData::class, ForecastData::class, HourlyData::class], version = 18, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
//    companion object {
//        private var INSTANCE: WeatherDatabase? = null
//        private val lock = Any()
//
//        @JvmStatic
//        fun getInstance(context: android.content.Context): WeatherDatabase {
//            synchronized(lock) {
//                if (INSTANCE == null) {
//                    INSTANCE = androidx.room.Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "weather_database")
//                        .fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
//                        .build()
//                }
//                return INSTANCE!!
//            }
//        }
//    }
}