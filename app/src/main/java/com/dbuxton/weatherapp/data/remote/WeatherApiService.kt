package com.dbuxton.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService {
    // api key: UGGQU4XS3QHQ4VC3KSR2JSL3G
    // api key: A58D6UR5PXC67DAHDQNMJJVGD

    @GET("timeline/{location}?key=A58D6UR5PXC67DAHDQNMJJVGD&contentType=json&unitGroup=metric")
    suspend fun getForecastData(
        @Path("location") location: String
    ): ForecastDto
}