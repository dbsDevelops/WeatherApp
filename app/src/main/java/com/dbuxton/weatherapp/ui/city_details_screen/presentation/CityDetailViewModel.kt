package com.dbuxton.weatherapp.ui.city_details_screen.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.remote.WeatherApiService
import com.dbuxton.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityDetailViewModel (application: Application): AndroidViewModel(application) {

    private val weatherRepository: WeatherRepository
    val hourlyDataList: MutableList<HourlyData> = mutableListOf()
    var forecastData: ForecastData? = null
    val db = WeatherDatabaseImpl(application).db

    init {
        val weatherDao = db.weatherDao()
        val apiService = Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)

        weatherRepository = WeatherRepository(weatherDao, apiService)
    }

    fun fetchWeatherData(location: String, apiKey: String) {
        viewModelScope.launch {
            weatherRepository.fetchAndSaveDailyWeatherData(location, apiKey)

            hourlyDataList.addAll(weatherRepository.getHourlyDataByCity(location))
            Log.d("Fetch Hourly Data", "Hourly data fetched: ${hourlyDataList}")

            forecastData = weatherRepository.getForecastsByCity(location)
            Log.d("Fetch Forecast Data", "Forecast data fetched: ${forecastData}")
        }
    }
}