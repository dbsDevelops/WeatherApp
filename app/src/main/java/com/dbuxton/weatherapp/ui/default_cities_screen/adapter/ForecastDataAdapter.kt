package com.dbuxton.weatherapp.ui.default_cities_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dbuxton.weatherapp.R
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.data.util.DrawableResource

class ForecastDataAdapter(
    private var forecastDataList: List<ForecastData>,
    private val onFavouriteClick: (ForecastData) -> Unit,
    private val onCityClick: (ForecastData) -> Unit
) : RecyclerView.Adapter<ForecastDataViewHolder>() {

    private lateinit var context: Context
    private val drawableResource = DrawableResource.getInstance()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        context = parent.context
        return ForecastDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastDataViewHolder, position: Int) {
        val forecastData = forecastDataList[position]
        holder.cityName.text = forecastData.cityName
        holder.currentTemp.text = forecastData.temperature.toString() + "°C"
        Glide.with(context)
            .load(drawableResource.getDrawable(forecastData.icon))
            .into(holder.weatherIcon)
        holder.maxTemp.text = forecastData.maxTemperature.toString() + "°C"
        holder.minTemp.text = forecastData.minTemperature.toString() + "°C"
        holder.favoriteIcon.setImageResource(
            if (forecastData.isFavourite) R.drawable.ic_favourite_city else R.drawable.ic_not_favourite_city
        )

        holder.favoriteIcon.setOnClickListener {
            onFavouriteClick(forecastData)
        }

        holder.itemView.setOnClickListener {
            onCityClick(forecastData)
        }
    }

    override fun getItemCount() = forecastDataList.size

    fun updateList(newList: List<ForecastData>) {
        forecastDataList = newList
        notifyDataSetChanged()
    }
}