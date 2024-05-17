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

//class DefaultCitiesScreenActivity : AppCompatActivity() {
//    private val view by lazy { ActivityDefaultCitiesScreenBinding.inflate(layoutInflater) }
//    private lateinit var forecastDataAdapter: CityItemAdapter
//    private var forecastDataList: MutableList<ForecastData> = mutableListOf()
//    private val scope = CoroutineScope(Dispatchers.IO)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//        val db = WeatherDatabaseImpl(application).db
//
//        initRecyclerView()
//        initSearchView()
//        initSpinner()
//
//        scope.launch {
//            loadDefaultCities(db)
//        }
//    }
//
//    private fun initRecyclerView() {
//        forecastDataAdapter = CityItemAdapter(forecastDataList, { forecastData ->
//            forecastData.isFavourite = !forecastData.isFavourite
//            forecastDataAdapter.notifyDataSetChanged()
//        }, { cityWeather ->
//            // Handle city item click
//            val intent = Intent(this@DefaultCitiesScreenActivity, CityDetailActivity::class.java).apply {
//                putExtra("CITY_NAME", cityWeather.cityName)
//            }
//            startActivity(intent)
//        })
//        view.rvCities.layoutManager = LinearLayoutManager(this@DefaultCitiesScreenActivity)
//        view.rvCities.adapter = forecastDataAdapter
//    }
//
//    private fun initSearchView() {
//        Log.d("InitSearchView in DefaultCitiesScreenActivity", "Forecast data list: $forecastDataList")
//        view.svCities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                forecastDataAdapter.updateList(
//                    if (newText.isNullOrEmpty()) forecastDataList
//                    else forecastDataList.filter {
//                        it.cityName.contains(newText, ignoreCase = true)
//                    }
//                )
//                return true
//            }
//        })
//    }
//
//    private fun initSpinner() {
//        val adapter = ArrayAdapter.createFromResource(
//            this@DefaultCitiesScreenActivity,
//            R.array.filter_options,
//            android.R.layout.simple_spinner_item
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        view.spFilterCities.adapter = adapter
//
//        view.spFilterCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val sortedList = when (position) {
//                    0 -> forecastDataList.sortedBy { it.temperature }
//                    1 -> forecastDataList.sortedBy { it.minTemperature }
//                    2 -> forecastDataList.sortedByDescending { it.maxTemperature }
//                    else -> forecastDataList
//                }
//                forecastDataAdapter.updateList(sortedList)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//    }
//
//    private suspend fun loadDefaultCities(db: WeatherDatabase) {
//        forecastDataList.add(db.weatherDao().getForecastByCity("Zaragoza"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("London"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Tokyo"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Berlin"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("New York"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Sydney"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Paris"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Cape Town"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Hong Kong"))
//        forecastDataList.add(db.weatherDao().getForecastByCity("Moscow"))
//        forecastDataAdapter.updateList(forecastDataList)
//    }
//}