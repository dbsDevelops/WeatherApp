package com.dbuxton.weatherapp.data.repository

import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.local.WeatherDao
import com.dbuxton.weatherapp.data.mappers.toForecastDataMap
import com.dbuxton.weatherapp.data.model.CityData
import com.dbuxton.weatherapp.data.remote.WeatherApiService
import com.dbuxton.weatherapp.data.remote.ForecastDto

class WeatherRepository(private val weatherDao: WeatherDao, private val apiService: WeatherApiService) {
    private val dailyWeatherParameters = "temp,description,condition,icon,tempmin,tempmax,precipprob,uvindex,windspeed"
    suspend fun fetchAndSaveDailyWeatherData(location: String, apiKey: String) {
        val response = apiService.getForecastData(location, apiKey, dailyParameters = dailyWeatherParameters)
        val city = CityData(
            address = response.forecastDto.address,
            forecasts = response.forecastDto.weatherData.toForecastDataMap().values.flatten()
        )
        weatherDao.insertCity(city)
    }

    suspend fun getForecastsByCity(cityName: String): List<ForecastData> {
        return weatherDao.getForecastsByCity(cityName)
    }
}