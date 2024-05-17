package com.dbuxton.weatherapp.ui.historical_data_screen.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.dbuxton.weatherapp.data.model.HourlyData
import com.dbuxton.weatherapp.databinding.ItemHistoricalDataBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.time.LocalDate

class HistoricalDataViewHolder(private val binding: ItemHistoricalDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hourlyDataList: List<HourlyData>) {
        val cityName =  hourlyDataList.firstOrNull()?.cityName ?: "Unknwon"
        binding.tvCityName.text = cityName
        binding.tvSavedTime.text = LocalDate.now().toString()

        val averageTemperature = hourlyDataList.map { it.temp }.average().toInt()
        binding.tvAverageTemp.text = "Avg Temp: $averageTemperature ºC"

        setupGraph(hourlyDataList)
    }

    fun setupGraph(hourlyDataList: List<HourlyData>) {
        val chart = binding.chartTemperature

        val entries = hourlyDataList.mapIndexed { index, hourlyData ->
            Entry(index.toFloat(), hourlyData.temp.toFloat())
        }

        if (entries.isEmpty()) {
            chart.clear()
            chart.invalidate()
            return
        }

        val lineDataSet = LineDataSet(entries, "Temperature")
        lineDataSet.color = Color.RED

        val lineData = LineData(lineDataSet)
        chart.data = lineData

        val xAxis: XAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(hourlyDataList.map { it.time.toString() })
        xAxis.labelCount = entries.size
        xAxis.granularity = 1f
        xAxis.textColor = Color.WHITE

        val leftAxis = chart.axisLeft
        leftAxis.textColor = Color.WHITE

        val rightAxis = chart.axisRight
        rightAxis.textColor = Color.WHITE

        chart.invalidate()
    }
}