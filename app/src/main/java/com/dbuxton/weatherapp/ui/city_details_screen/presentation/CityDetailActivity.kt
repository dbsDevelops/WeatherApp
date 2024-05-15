package com.dbuxton.weatherapp.ui.city_details_screen.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.databinding.ActivityCityDetailBinding
import com.dbuxton.weatherapp.ui.city_details_screen.adapter.HourlyAdapter
import com.dbuxton.weatherapp.ui.city_details_screen.domain.HourlyWeatherData
import com.dbuxton.weatherapp.ui.city_details_screen.location.LocationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityDetailActivity : AppCompatActivity() {
    private val view by lazy { ActivityCityDetailBinding.inflate(layoutInflater) }
    private val locationManager = LocationManager(this)
    private val scope = CoroutineScope(Dispatchers.IO)
    private val apiKey = "UGGQU4XS3QHQ4VC3KSR2JSL3G"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        val cityDetailViewModel = CityDetailViewModel(application)

        scope.launch {
            locationManager.fetchDeviceLocation { latitude, longitude ->
                scope.launch {
                    fetchCityAndWeather(cityDetailViewModel, latitude, longitude)
                }
            }
        }

        initRecyclerView()
    }

    private suspend fun fetchCityAndWeather(viewModel: CityDetailViewModel, latitude: Double, longitude: Double) {
        locationManager.fetchCityName(latitude, longitude) { cityName ->
            viewModel.fetchWeatherData(cityName, apiKey)
            scope.launch(Dispatchers.Main) {
                view.tvLocation.text = cityName
                updateUI(viewModel, cityName)
            }
        }
    }

    private suspend fun updateUI(viewModel: CityDetailViewModel, cityName: String) {
        val forecast = withContext(Dispatchers.IO) {
            viewModel.db.weatherDao().getForecastsByCity(cityName).first()
        }
        println(forecast)
        forecast?.let {
            withContext(Dispatchers.Main) {
                view.tvTemperature.text = it.temperature.toString() + "°C"
                view.tvDescription.text = it.description
                view.tvCondition.text = it.condition
                view.tvMinTemperatureValue.text = it.minTemperature.toString()
                view.tvMaxTemperatureValue.text = it.maxTemperature.toString()
                view.tvPrecipitationProbabilityValue.text = it.precipitationProbability.toString() + "%"
                view.tvUvIndexValue.text = it.uvIndex.toString()
                view.tvWindSpeedValue.text = it.windSpeed.toString() + " km/h"
            }
        }
    }

    // Initialise the RecyclerView
    private fun initRecyclerView() {
        // Create mock HourlyWeatherData list
        val hourlyList = listOf(
            HourlyWeatherData("12:00", 20, "clear-day"),
            HourlyWeatherData("13:00", 21, "clear-night"),
            HourlyWeatherData("14:00", 22, "cloudy"),
            HourlyWeatherData("15:00", 23, "fog"),
            HourlyWeatherData("16:00", 24, "hail"),
            HourlyWeatherData("17:00", 25, "rain"),
            HourlyWeatherData("18:00", 26, "rain-snow"),
            HourlyWeatherData("19:00", 27, "rain-snow-showers-day"),
            HourlyWeatherData("20:00", 28, "rain-snow-showers-night"),
            HourlyWeatherData("21:00", 29, "showers-day"),
            HourlyWeatherData("22:00", 30, "showers-night"),
            HourlyWeatherData("23:00", 31, "thunder")
        )

        val recyclerView = view.rvToday
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = HourlyAdapter(hourlyList)
    }

//    private val view by lazy { ActivityCityDetailBinding.inflate(layoutInflater) }
//    private val locationManager = LocationManager(this)
//    private val scope = CoroutineScope(Dispatchers.IO)
//
//    override  fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//        val cityDetailViewModel = CityDetailViewModel(application)
//        scope.launch {
//            locationManager.fetchDeviceLocation { latitude, longitude ->
//                scope.launch {
//                    locationManager.fetchCityName(latitude, longitude) { cityName ->
//                        view.tvLocation.text = cityName
//                        scope.launch {
//                            view.tvTemperature.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().temperature.toString()
//                            view.tvDescription.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().description
//                            view.tvCondition.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().condition
//                            view.tvMinTemperatureValue.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().minTemperature.toString()
//                            view.tvMaxTemperatureValue.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().maxTemperature.toString()
//                            view.tvPrecipitationProbabilityValue.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().precipitationProbability.toString()
//                            view.tvUvIndexValue.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().uvIndex.toString()
//                            view.tvWindSpeedValue.text =
//                                cityDetailViewModel.db.weatherDao().getForecastsByCity(cityName).first().windSpeed.toString()
//                        }
//
//                    }
//                }
//            }
//        }
//
//        initRecyclerView()
//    }
//
//    // Initialise the RecyclerView
//    private fun initRecyclerView() {
//        // Create mock HourlyWeatherData list
//        val hourlyList = listOf(
//            HourlyWeatherData("12:00", 20, "clear-day"),
//            HourlyWeatherData("13:00", 21, "clear-night"),
//            HourlyWeatherData("14:00", 22, "cloudy"),
//            HourlyWeatherData("15:00", 23, "fog"),
//            HourlyWeatherData("16:00", 24, "hail"),
//            HourlyWeatherData("17:00", 25, "rain"),
//            HourlyWeatherData("18:00", 26, "rain-snow"),
//            HourlyWeatherData("19:00", 27, "rain-snow-showers-day"),
//            HourlyWeatherData("20:00", 28, "rain-snow-showers-night"),
//            HourlyWeatherData("21:00", 29, "showers-day"),
//            HourlyWeatherData("22:00", 30, "showers-night"),
//            HourlyWeatherData("23:00", 31, "thunder")
//        )
//
//        val recyclerView = view.rvToday
//        recyclerView.layoutManager = LinearLayoutManager(
//            this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = HourlyAdapter(hourlyList)
//    }
}