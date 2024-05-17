package com.dbuxton.weatherapp.ui.list_cities_screens.default_cities_screen.presentation

//import androidx.appcompat.widget.SearchView.OnQueryTextListener
import android.os.Bundle
import com.dbuxton.weatherapp.data.local.WeatherDatabase
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.ui.list_cities_screens.BaseCitiesScreenActivity
import kotlinx.coroutines.launch

class DefaultCitiesScreenActivity : BaseCitiesScreenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = WeatherDatabaseImpl(application).db
        scope.launch {
            loadCities(db)
        }
    }

    override suspend fun loadCities(db: WeatherDatabase) {
        val defaultCities = listOf("Zaragoza", "London", "Tokyo", "Berlin", "New York", "Sydney", "Paris", "Cape Town", "Hong Kong", "Moscow")
        val forecastDataList = defaultCities.map { db.weatherDao().getForecastByCity(it) }
        updateList(forecastDataList)
    }
}