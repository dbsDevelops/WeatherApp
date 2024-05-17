package com.dbuxton.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.model.UserData

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userData: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlyData(hourlyData: List<HourlyData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecast: List<ForecastData>)

    @Query("SELECT * FROM hourly_data WHERE city_name = :cityName")
    suspend fun getHourlyDataByCity(cityName: String): List<HourlyData>

    @Query("SELECT * FROM forecast_data WHERE city_name = :cityName")
    suspend fun getForecastByCity(cityName: String): ForecastData

    @Query("SELECT * FROM forecast_data WHERE is_favourite = 1")
    fun getFavouriteCities(): List<ForecastData>

    @Query("UPDATE forecast_data SET is_favourite = :isFavourite WHERE city_name = :cityName")
    suspend fun updateCityFavoriteStatus(cityName: String, isFavourite: Boolean)

    @Query("SELECT * FROM forecast_data WHERE is_saved = 1")
    fun getHistoricalReports(): List<ForecastData>
}