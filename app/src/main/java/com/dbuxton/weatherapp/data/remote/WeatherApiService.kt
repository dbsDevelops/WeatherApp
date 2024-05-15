package com.dbuxton.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService {
    @GET("timeline/{location}?key=UGGQU4XS3QHQ4VC3KSR2JSL3G&contentType=json&unitGroup=metric")
    suspend fun getForecastData(
        @Path("location") location: String,
    ): ForecastDto
}