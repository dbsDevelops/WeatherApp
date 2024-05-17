package com.dbuxton.weatherapp.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.dbuxton.weatherapp.databinding.ActivitySplashScreenBinding
import com.dbuxton.weatherapp.ui.city_details_screen.presentation.CityDetailActivity
import com.dbuxton.weatherapp.ui.city_details_screen.presentation.CityViewModel
import com.dbuxton.weatherapp.ui.splash_screen.location.LocationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var view: ActivitySplashScreenBinding
    private val scope = CoroutineScope(Dispatchers.IO)
    private val locationManager = LocationManager(this)
    private val cityNames = listOf("Zaragoza", "London", "Paris", "Berlin", "New York", "Tokyo", "Sydney", "Cape Town", "Hong Kong", "Moscow")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(view.root)

        // Start the Lottie animation
        val animationView: LottieAnimationView = view.lavWeatherLoading
        animationView.playAnimation()

        // Fetch and save default cities weather data
        fetchAndSaveDefaultCitiesWeatherData()

        // Fetch location and weather data
        fetchLocationAndStartCityDetailActivity()
    }

    private fun fetchAndSaveDefaultCitiesWeatherData() {
        scope.launch {
            for (city in cityNames) {
                val cityDetailViewModel = CityViewModel(application)
                cityDetailViewModel.fetchWeatherData(city)
            }
        }
    }

    private fun fetchLocationAndStartCityDetailActivity() {
        scope.launch {
            // Fetch location and start the CityDetailActivity
            locationManager.fetchDeviceLocation { latitude, longitude ->
                val cityName = locationManager.fetchCityName(latitude, longitude)
                Log.d("SplashScreenActivity", "City name: $cityName")
                val intent = Intent(this@SplashScreenActivity, CityDetailActivity::class.java).apply {
                    putExtra("CITY_NAME", cityName)
                    putExtra("FROM_SPLASH_SCREEN", true)
                }
                startActivity(intent)
                finish()
            }
        }
    }
}