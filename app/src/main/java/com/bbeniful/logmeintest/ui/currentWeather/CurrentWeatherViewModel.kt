package com.bbeniful.logmeintest.ui.currentWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bbeniful.logmeintest.models.Weather
import com.bbeniful.logmeintest.repositories.WeatherRepository

class CurrentWeatherViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository

    fun getWorldID(cityName: String): LiveData<String> {
        return weatherRepository.getWorldID(cityName)
    }

    fun getWeatherData(worldID: String): LiveData<Weather> {
        return weatherRepository.getWeatherData(worldID)
    }


}