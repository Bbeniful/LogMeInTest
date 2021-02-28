package com.bbeniful.logmeintest.services

import com.bbeniful.MainApplication
import com.bbeniful.logmeintest.models.Error
import com.bbeniful.logmeintest.models.Weather
import com.bbeniful.logmeintest.models.WorldID
import com.bbeniful.logmeintest.utils.BaseUrl
import org.json.JSONArray
import org.json.JSONObject

object WeatherApi {


    fun getWorldID(
        cityName: String,
        completionHandler: (worldId: WorldID?, error: Error?) -> Unit
    ) {

        val url = BaseUrl.getUrl("/location/search/?query=$cityName")
        ApiCalls.getRequest(
            context = MainApplication.applicationContext(),
            url = url
        ) { response, error ->
            if (error != null) {
                completionHandler(null, Error(errorObject = error))
            } else {
                completionHandler(WorldID(JSONArray(response)), null)
            }
        }

    }

    fun getWeather(
        worldID: String, completionHandler: (weather: Weather?, error: Error?) -> Unit
    ) {

        val url = BaseUrl.getUrl("/location/$worldID")
        ApiCalls.getRequest(
            context = MainApplication.applicationContext(),
            url = url
        ) { response, error ->
            if (response != null) {
                val data = JSONObject(response)
                completionHandler(Weather(data), null)
            } else if (error != null) {
                completionHandler(null, Error(errorObject = error))
            }
        }


    }

}