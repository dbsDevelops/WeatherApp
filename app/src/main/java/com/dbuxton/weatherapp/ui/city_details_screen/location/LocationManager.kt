package com.dbuxton.weatherapp.ui.city_details_screen.location

import android.content.pm.PackageManager
import android.location.Geocoder
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

class LocationManager(
    private val appCompatActivity: AppCompatActivity
) {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var geocoder: Geocoder

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0


    fun fetchDeviceLocation(onLocationReceived: (Double, Double) -> Unit) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appCompatActivity)
        val hasAccessFineLocationPermission = ActivityCompat.checkSelfPermission(appCompatActivity,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ActivityCompat.checkSelfPermission(appCompatActivity,
            android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if(!hasAccessFineLocationPermission && !hasAccessCoarseLocationPermission) {
            ActivityCompat.requestPermissions(appCompatActivity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION), 101)
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            onLocationReceived(it.latitude, it.longitude)
        }
    }

//    public fun fetchCityName(tvLocation: TextView) {
//        fetchDeviceLocation { latitude, longitude ->
//            try {
//                geocoder = Geocoder(appCompatActivity, Locale.getDefault())
//                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
//                if (addresses != null) {
//                    val cityName = addresses[0].locality
//                    tvLocation.text = cityName
//                } else {
//                    tvLocation.text = "City not found"
//                }
//            } catch (e: IOException) {
//                tvLocation.text = "Error fetching city name"
//            }
//        }
//    }
    fun fetchCityName(latitude: Double, longitude: Double, onCityNameReceived: (String) -> Unit) {
        geocoder = Geocoder(appCompatActivity, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val cityName = addresses[0].locality
                onCityNameReceived(cityName)
            } else {
                onCityNameReceived("City not found")
            }
        } catch (e: IOException) {
            onCityNameReceived("Error fetching city name")
        }
    }

    public fun getLatitude(): Double {
        return this.latitude
    }

    public fun getLongitude(): Double {
        return this.longitude
    }

    public fun setLatitude(latitude: Double) {
        this.latitude = latitude
    }

    public fun setLongitude(longitude: Double) {
        this.longitude = longitude
    }

    public fun fetchData() {

    }
}