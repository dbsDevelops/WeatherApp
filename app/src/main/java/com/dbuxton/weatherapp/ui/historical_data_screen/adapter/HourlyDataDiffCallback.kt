package com.dbuxton.weatherapp.ui.historical_data_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dbuxton.weatherapp.data.model.HourlyData

class HourlyDataDiffCallback : DiffUtil.ItemCallback<HourlyData>() {
    override fun areItemsTheSame(oldItem: HourlyData, newItem: HourlyData): Boolean {
        // Compare unique identifiers
        return (oldItem.time == newItem.time) && (oldItem.cityName == newItem.cityName)
    }

    override fun areContentsTheSame(oldItem: HourlyData, newItem: HourlyData): Boolean {
        // Compare the content of the items
        return oldItem == newItem
    }
}
