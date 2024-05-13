package com.dbuxton.weatherapp.city_details_screen.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.city_details_screen.adapter.HourlyAdapter
import com.dbuxton.weatherapp.city_details_screen.domain.HourlyWeatherData
import com.dbuxton.weatherapp.databinding.ActivityCityDetailBinding
import com.dbuxton.weatherapp.default_cities_screen.LocationManager

class CityDetailActivity : AppCompatActivity() {

    private val view by lazy { ActivityCityDetailBinding.inflate(layoutInflater) }

    private val locationManager = LocationManager(this)

    // Set parameters to fetch hourly weather data
    private val baseUrl: String = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
    private val location: String = "Zaragoza"
    private val unitGroup: String = "metric"
    private val apiKey: String = "UGGQU4XS3QHQ4VC3KSR2JSL3G"
    private val contentType: String = "json"
    private val includeHourly: String = "hours"
    private val includeDaily: String = "days"
    private val includeCurrent: String = "current"
    private val hourlyElements: String = "datetime,icon,temp"
    private val dailyElements: String = "datetime,temp,description,tempmin,tempmax,precipprob,uvindex,windspeed"
    private val currentElements: String = "latitude, longitude, address"

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        val tvLocation: TextView = view.tvLocation
        locationManager.fetchCityName(tvLocation)

        initRecyclerView()
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
}