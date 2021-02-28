package com.bbeniful.logmeintest.models

import org.json.JSONObject

data class Weather(
    var name: String = "",
    var currentTemperature: Int = 0,
    var minTemperature: Int = 0,
    var maxTemperature: Int = 0,
    var cityName: String = "",
    var iconSlug: String = "",
    var created: String = ""
) {

    constructor(jsonObject: JSONObject?) : this() {
        if (jsonObject == null) return
        if (!jsonObject.has("consolidated_weather")) return
        val getFirstFromData =
            jsonObject.getJSONArray("consolidated_weather").getJSONObject(0) ?: return
        this.name = getFirstFromData.getString("weather_state_name")
        this.currentTemperature = getFirstFromData.getInt("the_temp")
        this.minTemperature = getFirstFromData.getInt("min_temp")
        this.maxTemperature = getFirstFromData.getInt("max_temp")
        this.created = splitAndFormatTime(getFirstFromData.getString("created"))
        this.iconSlug = getFirstFromData.getString("weather_state_abbr")
        this.cityName = jsonObject.getString("title")
    }

    private fun splitAndFormatTime(data: String): String {
        val separateDateTime = data.split("T")
        val date = separateDateTime[0].replace("-", ".")
        val time = separateDateTime[1].substringBeforeLast(".")
        return "$date, $time"
    }
}