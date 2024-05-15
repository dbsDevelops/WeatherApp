package com.dbuxton.weatherapp.data.repository

import com.dbuxton.weatherapp.data.local.WeatherDao
import com.dbuxton.weatherapp.data.mappers.toForecastDataList
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.remote.WeatherApiService

class WeatherRepository(private val weatherDao: WeatherDao, private val apiService: WeatherApiService) {
    private val dailyWeatherParameters = "temp,description,condition,icon,tempmin,tempmax,precipprob,uvindex,windspeed"
    suspend fun fetchAndSaveDailyWeatherData(location: String, apiKey: String) {
        val response = apiService.getForecastData(location)
        println(response.toString())
        weatherDao.insertForecast(response.toForecastDataList())
    }

    suspend fun getForecastsByCity(cityName: String): List<ForecastData> {
        return weatherDao.getForecastsByCity(cityName)
    }
}