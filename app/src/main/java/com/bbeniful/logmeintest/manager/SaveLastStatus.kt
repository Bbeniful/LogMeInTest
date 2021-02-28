package com.bbeniful.logmeintest.manager

import com.bbeniful.logmeintest.models.Weather

class SaveLastStatus {

    companion object {
        private var lastDownloadedWeather: Weather? = null

        internal fun getLastSavedWeather() = this.lastDownloadedWeather

        internal fun saveWeather(weather: Weather) {
            this.lastDownloadedWeather = weather
        }

        internal fun clearLastSavedWeather() {
            this.lastDownloadedWeather = null
        }
    }
}