package com.dbuxton.weatherapp.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dbuxton.weatherapp.databinding.ActivitySplashScreenBinding
import com.dbuxton.weatherapp.ui.city_details_screen.presentation.CityDetailActivity
import com.dbuxton.weatherapp.ui.default_cities_screen.presentation.DefaultCitiesScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var view: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(view.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, CityDetailActivity::class.java)
            startActivity(intent)
            finish()
        }, 6000)
    }
}