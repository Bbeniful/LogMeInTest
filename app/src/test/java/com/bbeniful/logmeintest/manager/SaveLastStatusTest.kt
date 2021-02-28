package com.bbeniful.logmeintest.manager

import com.bbeniful.logmeintest.models.Weather
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SaveLastStatusTest {

    private val emptyWeather = Weather()
    private val filledWeather = Weather(name = "Test weather", currentTemperature = 11)

    @Test
    fun shouldBeNull() {
        SaveLastStatus.clearLastSavedWeather()
        assertNull(SaveLastStatus.getLastSavedWeather())
    }

    @Test
    fun shouldBeEmptyWeather() {
        SaveLastStatus.saveWeather(weather = emptyWeather)
        assertEquals(emptyWeather, SaveLastStatus.getLastSavedWeather())
    }

    @Test
    fun shouldBeSameData() {
        SaveLastStatus.saveWeather(filledWeather)
        val savedWeather = SaveLastStatus.getLastSavedWeather()
        assertTrue(savedWeather?.name == filledWeather.name)
        assertTrue(savedWeather?.currentTemperature == filledWeather.currentTemperature)
    }
}