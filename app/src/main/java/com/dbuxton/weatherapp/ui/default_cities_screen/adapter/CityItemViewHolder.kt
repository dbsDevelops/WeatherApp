package com.dbuxton.weatherapp.ui.default_cities_screen.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dbuxton.weatherapp.R

class CityItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val cityName: TextView = view.findViewById(R.id.tvCityName)
    val currentTemp: TextView = view.findViewById(R.id.tvCityTemperature)
    val weatherIcon: ImageView = view.findViewById(R.id.ivCityWeatherIcon)
    val maxTemp: TextView = view.findViewById(R.id.tvCityMaxTemperatureValue)
    val minTemp: TextView = view.findViewById(R.id.tvCityMinTemperatureValue)
    val favoriteIcon: ImageView = view.findViewById(R.id.ivIsFavouriteCity)
}