package com.dbuxton.weatherapp.ui.historical_data_screen.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.databinding.ActivityHistoricalDataScreenBinding
import com.dbuxton.weatherapp.ui.historical_data_screen.adapter.HistoricalDataAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HistoricalDataScreenActivity : AppCompatActivity() {

    private val view by lazy { ActivityHistoricalDataScreenBinding.inflate(layoutInflater) }
    private lateinit var historicalDataAdapter: HistoricalDataAdapter
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        initRecyclerView()
        fetchReport()
    }

    private fun fetchReport() {
        val db = WeatherDatabaseImpl(application).db
        val cityName = intent.getStringExtra("CITY_NAME")
        scope.launch {
            if (cityName != null) {
                val hourlyDataListDeferred = scope.async(Dispatchers.IO) {
                    db.weatherDao().getHourlyDataByCity(cityName)
                }
                val historicalReports = hourlyDataListDeferred.await()
                if (historicalReports.isNotEmpty()) {
                    runOnUiThread {
                        historicalDataAdapter = HistoricalDataAdapter(historicalReports)
                        view.rvHistoricalData.adapter = historicalDataAdapter
                        historicalDataAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@HistoricalDataScreenActivity, "No historical data available", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        view.rvHistoricalData.layoutManager = LinearLayoutManager(this@HistoricalDataScreenActivity)
    }
}