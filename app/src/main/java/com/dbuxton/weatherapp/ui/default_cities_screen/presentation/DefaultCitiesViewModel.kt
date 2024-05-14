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

class DefaultCitiesViewModel {

}