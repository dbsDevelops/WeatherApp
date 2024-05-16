package com.dbuxton.weatherapp.data.util

import com.dbuxton.weatherapp.R

class DrawableResource {

    // Singleton
    companion object {
        private var instance: DrawableResource? = null
        fun getInstance(): DrawableResource {
            if (instance == null) {
                instance = DrawableResource()
            }
            return instance as DrawableResource
        }
    }
    fun getDrawable(weatherIcon: String): Int {
        return when (weatherIcon) {
            "clear-day" -> R.drawable.clear_day
            "clear-night" -> R.drawable.clear_day
            "cloudy" -> R.drawable.cloudy
            "fog" -> R.drawable.fog
            "hail" -> R.drawable.hail
            "partly-cloudy-day" -> R.drawable.partly_cloudy_day
            "partly-cloudy-night" -> R.drawable.partly_cloudy_night
            "rain" -> R.drawable.rain
            "rain-snow" -> R.drawable.rain_snow
            "rain-snow-showers-day" -> R.drawable.rain_snow_showers_day
            "rain-snow-showers-night" -> R.drawable.rain_snow_showers_night
            "showers-day" -> R.drawable.showers_day
            "showers-night" -> R.drawable.showers_night
            "sleet" -> R.drawable.sleet
            "snow" -> R.drawable.snow
            "snow-showers-day" -> R.drawable.snow_showers_day
            "snow-showers-night" -> R.drawable.snow_showers_night
            "thunder" -> R.drawable.thunder
            "thunder-rain" -> R.drawable.thunder_rain
            "thunder-showers-day" -> R.drawable.thunder_showers_day
            "thunder-showers-night" -> R.drawable.thunder_showers_night
            "wind" -> R.drawable.wind
            else -> {
                R.drawable.weather_not_available
            }
        }
    }
}