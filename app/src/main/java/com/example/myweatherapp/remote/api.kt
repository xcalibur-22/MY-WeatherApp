package com.example.myweatherapp.remote

import com.example.myweatherapp.remote.Responses.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {

    @GET("weather?units=metric")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
    ): CurrentWeather
}