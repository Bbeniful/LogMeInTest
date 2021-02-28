package com.bbeniful.logmeintest.ui.currentWeather

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bbeniful.logmeintest.R
import com.bbeniful.logmeintest.databinding.ActivityCurrentWeatherBinding
import com.bbeniful.logmeintest.manager.SaveLastStatus
import com.bbeniful.logmeintest.manager.WorldIDManager
import com.bbeniful.logmeintest.models.Weather
import com.bbeniful.logmeintest.services.UpdateService
import com.bbeniful.logmeintest.utils.Keys
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


class CurrentWeatherActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var binding: ActivityCurrentWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.currentWeatherSwipeRefreshLayout.setOnRefreshListener(this)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        shouldRefreshWeatherData()

    }

    private fun shouldRefreshWeatherData() {
        if (SaveLastStatus.getLastSavedWeather() == null || UpdateService.sinceLastUpdate() >= UpdateService.MINIMUM_UPDATE_TIME) {
            getWeatherData()
        } else {
            setUpUI(SaveLastStatus.getLastSavedWeather()!!)
        }
    }

    private fun getWeatherData() {
        binding.currentWeatherSwipeRefreshLayout.isRefreshing = true
        if (shouldSaveWorldID()) {
            viewModel.getWorldID(Keys.BUDAPEST).observe(this, {
                WorldIDManager.setWorldID(it)
                viewModel.getWeatherData(WorldIDManager.getWorldID()!!).observe(this, { weather ->
                    binding.currentWeatherSwipeRefreshLayout.isRefreshing = false
                    setUpUI(weather)
                })
            })
        } else {
            viewModel.getWeatherData(WorldIDManager.getWorldID()!!).observe(this, {
                UpdateService.saveUpdateTimestamp()
                binding.currentWeatherSwipeRefreshLayout.isRefreshing = false
                SaveLastStatus.saveWeather(it)
                setUpUI(it)
            })
        }


    }


    override fun onRefresh() {
        getWeatherData()
    }

    private fun shouldSaveWorldID(): Boolean {
        return WorldIDManager.getWorldID().isNullOrEmpty()
    }

    private fun setUpUI(weather: Weather) {
        with(binding) {
            weatherName.text = weather.name
            weatherCurrentTemperature.text = getString(R.string.celsius, weather.currentTemperature)
            weatherMaxTemperature.text = getString(R.string.max_celsius, weather.maxTemperature)
            weatherMinTemperature.text = getString(R.string.min_celsius, weather.minTemperature)
            weatherCityName.text = weather.cityName
            weatherCreated.text = weather.created
            val imageUrl = "https://www.metaweather.com/static/img/weather/${weather.iconSlug}.svg"
            GlideToVectorYou
                .init()
                .with(applicationContext)
                .load(Uri.parse(imageUrl), weatherIcon)
        }
    }

    override fun onResume() {
        super.onResume()
        if (SaveLastStatus.getLastSavedWeather() == null || UpdateService.sinceLastUpdate() >= UpdateService.MINIMUM_UPDATE_TIME
        ) {
            getWeatherData()
        } else {
            setUpUI(SaveLastStatus.getLastSavedWeather()!!)
        }
    }

}