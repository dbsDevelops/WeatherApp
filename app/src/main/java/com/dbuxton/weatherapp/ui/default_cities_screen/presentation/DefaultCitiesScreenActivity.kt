package com.dbuxton.weatherapp.ui.default_cities_screen.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.R
import com.dbuxton.weatherapp.data.local.WeatherDatabase
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.databinding.ActivityDefaultCitiesScreenBinding
import com.dbuxton.weatherapp.ui.city_details_screen.presentation.CityDetailActivity
import com.dbuxton.weatherapp.ui.default_cities_screen.adapter.ForecastDataAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultCitiesScreenActivity : AppCompatActivity() {
    private val view by lazy { ActivityDefaultCitiesScreenBinding.inflate(layoutInflater) }
    private lateinit var forecastDataAdapter: ForecastDataAdapter
    private var forecastDataList: MutableList<ForecastData> = mutableListOf()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        val db = WeatherDatabaseImpl(application).db

        initRecyclerView()
        initSearchView()
        initSpinner()

        loadDefaultCities(db)
    }

    private fun initRecyclerView() {
        forecastDataAdapter = ForecastDataAdapter(forecastDataList, { forecastData ->
            forecastData.isFavourite = !forecastData.isFavourite
            forecastDataAdapter.notifyDataSetChanged()
        }, { cityWeather ->
            // Handle city item click
            val intent = Intent(this, CityDetailActivity::class.java).apply {
                putExtra("CITY_NAME", cityWeather.cityName)
            }
            startActivity(intent)
        })
        view.rvCities.layoutManager = LinearLayoutManager(this)
        view.rvCities.adapter = forecastDataAdapter
    }

    private fun initSearchView() {
        Log.d("InitSearchView in DefaultCitiesScreenActivity", "Forecast data list: $forecastDataList")
        view.svCities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                forecastDataAdapter.updateList(
                    if (newText.isNullOrEmpty()) forecastDataList
                    else forecastDataList.filter {
                        it.cityName.contains(newText, ignoreCase = true)
                    }
                )
                return true
            }
        })
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.filter_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.spFilterCities.adapter = adapter

        view.spFilterCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sortedList = when (position) {
                    0 -> forecastDataList.sortedBy { it.temperature }
                    1 -> forecastDataList.sortedBy { it.minTemperature }
                    2 -> forecastDataList.sortedByDescending { it.maxTemperature }
                    else -> forecastDataList
                }
                forecastDataAdapter.updateList(sortedList)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun loadDefaultCities(db: WeatherDatabase) {
        scope.launch {
            forecastDataList.add(db.weatherDao().getForecastByCity("Zaragoza"))
            forecastDataList.add(db.weatherDao().getForecastByCity("London"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Tokyo"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Berlin"))
            forecastDataList.add(db.weatherDao().getForecastByCity("New York"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Sydney"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Paris"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Cape Town"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Hong Kong"))
            forecastDataList.add(db.weatherDao().getForecastByCity("Moscow"))
//            db.weatherDao().getForecastByCity("Zaragoza")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("London")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Tokyo")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Berlin")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("New York")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Sydney")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Paris")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Cape Town")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Hong Kong")
//                .let { forecastDataList.add(it) }
//            db.weatherDao().getForecastByCity("Moscow")
//                .let { forecastDataList.add(it) }
        }
        forecastDataAdapter.updateList(forecastDataList)
    }
}