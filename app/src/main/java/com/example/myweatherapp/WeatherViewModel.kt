package com.example.myweatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.domain.location.LocationTracker
import com.example.myweatherapp.repository.WeatherRepository
import com.example.myweatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.annotation.meta.When
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor (
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
):ViewModel() {
    fun loadWeatherInfo() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                println("loc ${location.latitude}")
                when (val result =
                    repository.getCurrentWeather(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        result.data?.main?.let { println( "temp: "+it.temp) }
                    }
                    is Resource.Error -> {
                        println("error")
                    }
                    else -> {}
                }
            }
        }
    }
}