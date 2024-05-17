package com.dbuxton.weatherapp.ui.city_details_screen.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.databinding.ActivityCityDetailBinding
import com.dbuxton.weatherapp.ui.city_details_screen.adapter.HourlyAdapter
import com.dbuxton.weatherapp.ui.historical_data_screen.presentation.HistoricalDataScreenActivity
import com.dbuxton.weatherapp.ui.list_cities_screens.default_cities_screen.presentation.DefaultCitiesScreenActivity
import com.dbuxton.weatherapp.ui.list_cities_screens.favourite_cities_screen.presentation.FavouriteCitiesScreenActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityDetailActivity : AppCompatActivity() {
    private val view by lazy { ActivityCityDetailBinding.inflate(layoutInflater) }
    private val apiKey = "UGGQU4XS3QHQ4VC3KSR2JSL3G"
    //private val apiKey = "A58D6UR5PXC67DAHDQNMJJVGD"
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        val cityDetailViewModel = CityViewModel(application)

        // Get the city name from the intent
        val cityName = intent.getStringExtra("CITY_NAME")
        val fromSplashScreen = intent.getBooleanExtra("FROM_SPLASH_SCREEN", false)
        Log.d("CityDetailActivity", "City name: $cityName")

        if (cityName != null) {
            if (fromSplashScreen) {
                // Fetch and save weather data for the given city name
                cityDetailViewModel.fetchWeatherData(cityName)
            }
            // Fetch weather data for the given city name
            getCityWeatherData(cityName, cityDetailViewModel)
        } else {
            Toast.makeText(this@CityDetailActivity, "City name could not be retrieved", Toast.LENGTH_SHORT).show()
        }

        // Initialise the buttons
        initButtons()
    }

    private fun getCityWeatherData(cityName:String, cityDetailViewModel: CityViewModel) {
        scope.launch {
            updateUI(cityName, cityDetailViewModel)
        }
    }

    private suspend fun updateUI(cityName: String, cityDetailViewModel: CityViewModel) {
        val forecastDataDeferred = scope.async(Dispatchers.IO) {
            cityDetailViewModel.db.weatherDao().getForecastByCity(cityName)
        }
        val hourlyDataListDeferred = scope.async(Dispatchers.IO) {
            cityDetailViewModel.db.weatherDao().getHourlyDataByCity(cityName)
        }

        val forecastData = forecastDataDeferred.await()
        if(forecastData != null) {
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
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@CityDetailActivity, "No forecast data available", Toast.LENGTH_SHORT).show()
            }
        }

        val hourlyDataList = hourlyDataListDeferred.await()
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

    private fun initButtons() {
        view.ibDefaultCities.setOnClickListener {
            val intent = Intent(this, DefaultCitiesScreenActivity::class.java)
            startActivity(intent)
        }
        view.ibFavouriteCities.setOnClickListener {
            val intent = Intent(this, FavouriteCitiesScreenActivity::class.java)
            startActivity(intent)
        }
        view.ibHistoricalData.setOnClickListener {
            val intent = Intent(this, HistoricalDataScreenActivity::class.java)
            intent.putExtra("CITY_NAME", view.tvLocation.text.toString())
            startActivity(intent)
        }
    }
}