package com.dbuxton.weatherapp.ui.default_cities_screen.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.dbuxton.weatherapp.data.local.WeatherDatabase
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.remote.WeatherApiService
import com.dbuxton.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val weatherRepository: WeatherRepository
    val weatherData: LiveData<List<ForecastData>>
    val db = Room.databaseBuilder(
        context = application.applicationContext,
        WeatherDatabase::class.java, "weather_database"
    ).build()

    init {
        val weatherDao = db.weatherDao()
        val apiService = Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)

        weatherRepository = WeatherRepository(weatherDao, apiService)
        weatherData = MutableLiveData()
    }

    fun fetchWeatherData(location: String, apiKey: String) {
        viewModelScope.launch {
            weatherRepository.fetchAndSaveDailyWeatherData(location, apiKey)
            (weatherData as MutableLiveData).postValue(weatherRepository.getForecastsByCity(location))
        }
    }
}