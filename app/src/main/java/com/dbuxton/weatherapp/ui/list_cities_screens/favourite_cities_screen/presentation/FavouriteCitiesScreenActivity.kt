package com.dbuxton.weatherapp.ui.list_cities_screens.favourite_cities_screen.presentation

import android.os.Bundle
import com.dbuxton.weatherapp.data.local.WeatherDatabase
import com.dbuxton.weatherapp.data.local.WeatherDatabaseImpl
import com.dbuxton.weatherapp.ui.list_cities_screens.BaseCitiesScreenActivity
import kotlinx.coroutines.launch

class FavouriteCitiesScreenActivity : BaseCitiesScreenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = WeatherDatabaseImpl(application).db
        scope.launch {
            loadCities(db)
        }
    }

    override suspend fun loadCities(db: WeatherDatabase) {
        val favouriteCities = db.weatherDao().getFavouriteCities()
        updateList(favouriteCities)
    }
}