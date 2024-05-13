package com.dbuxton.weatherapp.city_details_screen.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dbuxton.weatherapp.R

class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvHour: TextView = itemView.findViewById(R.id.tvHour)
    val ivCondition: ImageView = itemView.findViewById(R.id.ivCondition)
    val tvTemperature: TextView = itemView.findViewById(R.id.tvHourlyTemperature)
}