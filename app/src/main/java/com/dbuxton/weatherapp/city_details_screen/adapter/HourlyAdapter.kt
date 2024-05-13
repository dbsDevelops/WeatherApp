package com.dbuxton.weatherapp.city_details_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dbuxton.weatherapp.R
import com.dbuxton.weatherapp.city_details_screen.domain.HourlyWeatherData


class HourlyAdapter(private val hourlyList: List<HourlyWeatherData>): RecyclerView.Adapter<HourlyViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_hourly, parent, false)
        context = parent.context
        return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val hourly = hourlyList[position]
        holder.tvHour.text = hourly.time
        holder.tvTemperature.text = hourly.temperature.toString() + "°C"

        Glide.with(context)
            .load(getDrawable(hourly.weatherIcon))
            .into(holder.ivCondition)
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    private fun getDrawable(weatherIcon: String): Int {
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
