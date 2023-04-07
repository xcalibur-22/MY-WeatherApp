package com.example.myweatherapp.repository

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Application
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.example.myweatherapp.BuildConfig
import com.example.myweatherapp.remote.Responses.CurrentWeather
import com.example.myweatherapp.remote.api
import com.example.myweatherapp.util.Constants.API_KEY
import com.example.myweatherapp.util.Resource
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val service: api,
    private val application: Application
) {


    suspend fun getCurrentWeather(lat:Double, lon:Double): Resource<CurrentWeather> {
        return try {
            Resource.Success(
                data=service.getCurrentWeather(
                    lat=lat,lon=lon, appid = API_KEY
                )
            )
        }
        catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }




}