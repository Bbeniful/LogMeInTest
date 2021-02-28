package com.bbeniful.logmeintest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bbeniful.MainApplication
import com.bbeniful.logmeintest.manager.ErrorHandler
import com.bbeniful.logmeintest.models.Weather
import com.bbeniful.logmeintest.services.WeatherApi

object WeatherRepository {

    fun getWorldID(cityName: String): LiveData<String> {
        val worldIdData = MutableLiveData<String>()
        WeatherApi.getWorldID(cityName) { worldId, error ->
            if (error == null && worldId != null) {
                worldIdData.postValue(worldId.woeid)
            } else {
                ErrorHandler.getError(MainApplication.applicationContext(), error)
            }
        }
        return worldIdData
    }

    fun getWeatherData(worldID: String): LiveData<Weather> {
        val weatherData = MutableLiveData<Weather>()
        WeatherApi.getWeather(worldID) { weather, error ->
            if (error == null && weather != null) {
                weatherData.postValue(weather)
            } else {
                ErrorHandler.getError(MainApplication.applicationContext(), error)
            }
        }
        return weatherData
    }
}