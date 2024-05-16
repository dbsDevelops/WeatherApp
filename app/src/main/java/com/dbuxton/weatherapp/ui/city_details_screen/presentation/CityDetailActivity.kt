package com.dbuxton.weatherapp.ui.city_details_screen.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.databinding.ActivityCityDetailBinding
import com.dbuxton.weatherapp.ui.city_details_screen.adapter.HourlyAdapter
import com.dbuxton.weatherapp.ui.default_cities_screen.presentation.DefaultCitiesScreenActivity
import com.dbuxton.weatherapp.ui.favourite_cities_screen.presentation.FavouriteCitiesScreenActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityDetailActivity : AppCompatActivity() {
    private val view by lazy { ActivityCityDetailBinding.inflate(layoutInflater) }
    //private val apiKey = "UGGQU4XS3QHQ4VC3KSR2JSL3G"
    private val apiKey = "A58D6UR5PXC67DAHDQNMJJVGD"
    private val scope = CoroutineScope(Dispatchers.IO)
    private val cityNames = mutableListOf("Zaragoza", "London", "Paris", "Berlin", "New York", "Tokyo", "Sydney", "Cape Town", "Hong Kong", "Moscow")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        val cityDetailViewModel = CityViewModel(application)

        // Get the city name from the intent
        val cityName = intent.getStringExtra("CITY_NAME")
        cityNames.add(cityName!!)
        Log.d("CityDetailActivity", "City name: $cityName")
        if (cityName != null) {
            // Fetch weather data for the given city name
            getCityWeatherData(cityName, cityDetailViewModel)
        }
        // Fetch and save weather data for default cities
        fetchAndSaveDefaultCitiesWeatherData()

        // Initialise the buttons
        initButtons()
    }

    private fun getCityWeatherData(cityName:String, cityDetailViewModel: CityViewModel) {
        scope.launch {
            cityDetailViewModel.fetchWeatherData(cityName, apiKey)
            updateUI(cityName, cityDetailViewModel)
        }
    }

    private suspend fun updateUI(cityName: String, cityDetailViewModel: CityViewModel) {
        val forecastData = withContext(Dispatchers.IO) {
            cityDetailViewModel.db.weatherDao().getForecastByCity(cityName)
        }
        withContext(Dispatchers.Main) {
            view.tvLocation.text = forecastData.cityName
            view.tvTemperature.text = forecastData.temperature.toString() + "°C"
            view.tvDescription.text = forecastData.condition
            view.tvCondition.text = forecastData.condition
            view.tvMinTemperature.append(forecastData.minTemperature.toString())
            view.tvMaxTemperature.append(forecastData.maxTemperature.toString())
            view.tvPrecipitationProbabilityValue.text = forecastData.precipitationProbability.toString() + "%"
            view.tvUvIndexValue.text = forecastData.uvIndex.toString()
            view.tvWindSpeedValue.text = forecastData.windSpeed.toString() + " km/h"
        }

        val hourlyDataList = withContext(Dispatchers.IO) {
            cityDetailViewModel.db.weatherDao().getHourlyDataByCity(cityName)
        }
        withContext(Dispatchers.Main) {
            initRecyclerView(hourlyDataList)
        }

        Log.d("CityDetailActivity", "Hourly data: ${cityDetailViewModel.hourlyDataList}")
        Log.d("CityDetailActivity", "Forecast: ${cityDetailViewModel.forecastData}")
    }

    // Initialise the RecyclerView
    private fun initRecyclerView(hourlyList: List<HourlyData>) {
        val recyclerView = view.rvToday
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = HourlyAdapter(hourlyList)
    }

    private fun fetchAndSaveDefaultCitiesWeatherData() {
        scope.launch {
            for (city in cityNames) {
                val cityDetailViewModel = CityViewModel(application)
                cityDetailViewModel.fetchWeatherData(city, apiKey)
            }
        }
    }

    private fun initButtons() {
        view.ibDefaultCities.setOnClickListener {
            val intent = Intent(this, DefaultCitiesScreenActivity::class.java)
            startActivity(intent)
        }
        view.ibFavouriteCities.setOnClickListener {
            val intent = Intent(this, FavouriteCitiesScreenActivity::class.java)
            startActivity(intent)
        }
    }
}