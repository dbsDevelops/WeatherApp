package com.dbuxton.weatherapp.ui.list_cities_screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbuxton.weatherapp.R
import com.dbuxton.weatherapp.data.local.WeatherDatabase
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.data.model.ForecastData
import com.dbuxton.weatherapp.databinding.ActivityBaseCitiesScreenBinding
import com.dbuxton.weatherapp.ui.city_details_screen.presentation.CityDetailActivity
import com.dbuxton.weatherapp.ui.list_cities_screens.default_cities_screen.adapter.CityItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseCitiesScreenActivity : AppCompatActivity() {
    protected val view by lazy { ActivityBaseCitiesScreenBinding.inflate(layoutInflater) }
    private lateinit var cityItemAdapter: CityItemAdapter
    protected var forecastDataList: MutableList<ForecastData> = mutableListOf()
    protected val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        initRecyclerView()
        initSearchView()
        initSpinner()
    }

    private fun initRecyclerView() {
        cityItemAdapter = CityItemAdapter(forecastDataList, { forecastData ->
            forecastData.isFavourite = !forecastData.isFavourite
            scope.launch {
                Log.d("BaseCitiesScreenActivity", "Updating ${forecastData.cityName} with id ${forecastData.id_forecast} favourite status in database")
                val db = WeatherDatabaseImpl(application).db
                db.weatherDao().updateCityFavoriteStatus(forecastData.cityName, forecastData.isFavourite)
            }
            runOnUiThread { cityItemAdapter.notifyDataSetChanged() }
        }, { cityWeather ->
            val intent = Intent(this, CityDetailActivity::class.java).apply {
                putExtra("CITY_NAME", cityWeather.cityName)
            }
            startActivity(intent)
        })
        view.rvCities.layoutManager = LinearLayoutManager(this)
        view.rvCities.adapter = cityItemAdapter
    }

    private fun initSearchView() {
        view.svCities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityItemAdapter.updateList(
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
                    0 -> forecastDataList.sortedByDescending { it.temperature }
                    1 -> forecastDataList.sortedBy { it.minTemperature }
                    2 -> forecastDataList.sortedByDescending { it.maxTemperature }
                    else -> forecastDataList
                }
                cityItemAdapter.updateList(sortedList)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    protected fun updateList(newList: List<ForecastData>) {
        forecastDataList.clear()
        forecastDataList.addAll(newList)
        runOnUiThread { cityItemAdapter.updateList(forecastDataList) }
    }

    abstract suspend fun loadCities(db: WeatherDatabase)
}