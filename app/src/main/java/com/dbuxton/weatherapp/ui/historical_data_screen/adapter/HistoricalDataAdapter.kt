package com.dbuxton.weatherapp.ui.historical_data_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.databinding.ItemHistoricalDataBinding

class HistoricalDataAdapter(private val hourlyDataList: List<HourlyData>) : RecyclerView.Adapter<HistoricalDataViewHolder>() {

//    private lateinit var binding: ItemHistoricalDataBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalDataViewHolder {
        val binding = ItemHistoricalDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        this.binding = binding
//        binding.tvAverageTemp.text = "Avg Temp: ${hourlyDataList.map { it.temp }.average().toInt()} ºC"
        return HistoricalDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricalDataViewHolder, position: Int) {
//        val hourlyData = hourlyDataList[position]
//        holder.bind(hourlyData)
//        if (position == itemCount - 1) { // if it's the last item
//            holder.setupGraph(hourlyDataList)
//        }
        holder.bind(hourlyDataList)
    }

    override fun getItemCount(): Int {
        return 1
        //return hourlyDataList.size
    }
}