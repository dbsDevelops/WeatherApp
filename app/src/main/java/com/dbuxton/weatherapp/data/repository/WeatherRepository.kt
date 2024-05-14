package com.dbuxton.weatherapp.data.repository

import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.local.WeatherDao
import com.dbuxton.weatherapp.data.mappers.toForecastDataMap
import com.dbuxton.weatherapp.data.remote.WeatherApiService

class WeatherRepository(private val weatherDao: WeatherDao, private val apiService: WeatherApiService) {
    private val dailyWeatherParameters = "temp,description,condition,icon,tempmin,tempmax,precipprob,uvindex,windspeed"
    suspend fun fetchAndSaveDailyWeatherData(location: String, apiKey: String) {
        val response = apiService.getForecastData(location, apiKey, dailyParameters = dailyWeatherParameters)
        println(response.toString())
        val forecastData = response.forecastDto.toForecastDataMap()
        weatherDao.insertForecast(forecastData.flatMap { it.value })
    }

    suspend fun getForecastsByCity(cityName: String): List<ForecastData> {
        return weatherDao.getForecastsByCity(cityName)
    }
}