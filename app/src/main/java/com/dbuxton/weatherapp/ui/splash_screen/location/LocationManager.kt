package com.dbuxton.weatherapp.ui.splash_screen.location

import android.content.pm.PackageManager
import android.location.Geocoder
import android.widget.Toast
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
        task.addOnSuccessListener {location ->
            if (location != null) {
                onLocationReceived(location.latitude, location.longitude)
            } else {
                // Inform the user that the location could not be retrieved
                Toast.makeText(appCompatActivity, "Location could not be retrieved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fetchCityName(latitude: Double, longitude: Double): String {
        geocoder = Geocoder(appCompatActivity, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses[0].locality
                return cityName
            } else {
                return "City not found"
            }
        } catch (e: IOException) {
            return "Error fetching city name"
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
}