package com.dbuxton.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET("timeline/{location}")
    suspend fun getForecastData(
        @Path("location") location: String,
        @Query("key") apiKey: String,
        @Query("unitGroup") unitGroup: String = "metric",
        @Query("contentType") contentType: String = "json",
        @Query("days") dailyParameters: String
        //@Query("hourly") dailyParameters: String
    ): WeatherApiResponse
}