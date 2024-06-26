package com.dbuxton.weatherapp.data.mappers

import androidx.room.TypeConverter
import com.dbuxton.weatherapp.data.model.ForecastData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    @TypeConverter
    fun fromForecastList(value: List<ForecastData>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<ForecastData>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toForecastList(value: String): List<ForecastData>? {
        val gson = Gson()
        val type = object : TypeToken<List<ForecastData>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let {
            LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    fun toLocalDateTime(dateString: String?): LocalDateTime? {
        return dateString?.let {
            LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }

    @TypeConverter
    fun fromLocalTime(date: LocalTime?): String? {
        return date?.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }

    @TypeConverter
    fun toLocalTime(dateString: String?): LocalTime? {
        return dateString?.let {
            LocalTime.parse(it, DateTimeFormatter.ISO_LOCAL_TIME)
        }
    }
}