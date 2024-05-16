package com.dbuxton.weatherapp.ui.city_details_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dbuxton.weatherapp.R
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.data.util.DrawableResource


class HourlyAdapter(private val hourlyList: List<HourlyData>): RecyclerView.Adapter<HourlyViewHolder>() {

    private lateinit var context: Context
    private val drawableResource = DrawableResource.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_hourly, parent, false)
        context = parent.context
        return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val hourly = hourlyList[position]
        holder.tvHour.text = hourly.time.toString()
        Glide.with(context)
            .load(drawableResource.getDrawable(hourly.icon))
            .into(holder.ivCondition)
        holder.tvTemperature.text = hourly.temp.toString() + "°C"
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }
}
