package com.dbuxton.weatherapp.data.repository

import android.util.Log
import com.dbuxton.weatherapp.data.local.WeatherDao
import com.dbuxton.weatherapp.data.mappers.toForecastDataList
import com.dbuxton.weatherapp.data.mappers.toHourlyDataList
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.remote.WeatherApiService

class WeatherRepository(private val weatherDao: WeatherDao, private val apiService: WeatherApiService) {
    suspend fun fetchAndSaveDailyWeatherData(location: String, apiKey: String) {
        val response = apiService.getForecastData(location)
        Log.d("WeatherRepository", "fetchAndSaveDailyWeatherData: $response")
        val hourlyDataList = response.toHourlyDataList()
        Log.d("WeatherRepository", "HourlyDataList: $hourlyDataList")
        weatherDao.insertHourlyData(hourlyDataList)
        val forecastDataList = response.toForecastDataList()
        Log.d("WeatherRepository", "ForecastDataList: $forecastDataList")
        weatherDao.insertForecast(forecastDataList)
    }

    suspend fun getHourlyDataByCity(cityName: String): List<HourlyData> {
        return weatherDao.getHourlyDataByCity(cityName)
    }

    suspend fun getForecastsByCity(cityName: String): ForecastData {
        return weatherDao.getForecastByCity(cityName)
    }
}