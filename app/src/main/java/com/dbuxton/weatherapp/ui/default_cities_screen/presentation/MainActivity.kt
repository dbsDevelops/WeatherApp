package com.dbuxton.weatherapp.ui.default_cities_screen.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dbuxton.weatherapp.R
import com.dbuxton.weatherapp.databinding.ActivityMainBinding
import com.dbuxton.weatherapp.ui.city_details_screen.presentation.CityDetailActivity
import com.dbuxton.weatherapp.ui.default_cities_screen.location.LocationManager

class MainActivity : AppCompatActivity() {

    private lateinit var view: ActivityMainBinding

    private val cityName: String = "Zaragoza"
    private val apiKey: String = "UGGQU4XS3QHQ4VC3KSR2JSL3G"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        val intent = Intent(this, CityDetailActivity::class.java)
        startActivity(intent)
    }
//    private lateinit var locationManager: LocationManager
//    private val mainViewModel: MainViewModel by lazy { MainViewModel(application = application) }
//
//    private val requestPermissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//            if (isGranted) {
//                locationManager.fetchDeviceLocation { latitude, longitude ->
//                    fetchWeatherData(latitude, longitude)
//                }
//            } else {
//                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        locationManager = LocationManager(this)
//
//        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            locationManager.fetchDeviceLocation { latitude, longitude ->
//                fetchWeatherData(latitude, longitude)
//            }
//        } else {
//            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//
//        mainViewModel.weatherData.observe(this, Observer { weatherData ->
//            if (weatherData != null) {
//                val intent = Intent(this, CityDetailActivity::class.java)
//                intent.putExtra("cityName", "Current Location")
//                startActivity(intent)
//            }
//        })
//    }
//
//    private fun fetchWeatherData(latitude: Double, longitude: Double) {
//        val locationString = "$latitude,$longitude"
//        mainViewModel.fetchWeatherData(locationString, "YOUR_API_KEY")
//    }
}