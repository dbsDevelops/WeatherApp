package com.dbuxton.weatherapp.ui.city_details_screen.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.remote.WeatherApiService
import com.dbuxton.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityViewModel (application: Application): AndroidViewModel(application) {

    private val weatherRepository: WeatherRepository
    val hourlyDataList: LiveData<List<HourlyData>>
    var forecastData: LiveData<ForecastData>
    val db = WeatherDatabaseImpl(application).db

    init {
        val weatherDao = db.weatherDao()
        val apiService = Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)

        weatherRepository = WeatherRepository(weatherDao, apiService)
        hourlyDataList = MutableLiveData()
        forecastData = MutableLiveData()
    }

    fun fetchWeatherData(location: String, apiKey: String) {
        viewModelScope.launch {
            weatherRepository.fetchAndSaveDailyWeatherData(location, apiKey)

            (hourlyDataList as MutableLiveData).postValue(weatherRepository.getHourlyDataByCity(location))
            //hourlyDataList.addAll(weatherRepository.getHourlyDataByCity(location))
            Log.d("Fetch Hourly Data", "Hourly data fetched: ${hourlyDataList}")

            (forecastData as MutableLiveData).postValue(weatherRepository.getForecastsByCity(location))
            //forecastData = weatherRepository.getForecastsByCity(location)
            Log.d("Fetch Forecast Data", "Forecast data fetched: ${forecastData}")
        }
    }
}