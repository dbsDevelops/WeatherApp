package com.dbuxton.weatherapp.default_cities_screen.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dbuxton.weatherapp.city_details_screen.presentation.CityDetailActivity
import com.dbuxton.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var view: ActivityMainBinding

    private val cityName: String = "Zaragoza"
    private val apiKey: String = "UGGQU4XS3QHQ4VC3KSR2JSL3G"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        val intent = Intent(this, CityDetailActivity::class.java)
        startActivity(intent)
    }
}