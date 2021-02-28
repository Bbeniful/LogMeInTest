package com.bbeniful.logmeintest.utils

object BaseUrl {

    private const val baseUrl = "https://www.metaweather.com/api"

    fun getUrl(param: String) = this.baseUrl + param
}