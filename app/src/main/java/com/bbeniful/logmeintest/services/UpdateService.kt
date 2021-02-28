package com.bbeniful.logmeintest.services

import android.content.Context
import android.content.SharedPreferences
import com.bbeniful.MainApplication

object UpdateService {


    private const val MAIN_PREFERENCES = "MAIN_PREFERENCES"
    private const val LAST_UPDATE = "LAST_UPDATE"
    internal const val MINIMUM_UPDATE_TIME = 1

    private var sharedPreferences: SharedPreferences =
        MainApplication.applicationContext().getSharedPreferences(
            MAIN_PREFERENCES, Context.MODE_PRIVATE
        )

    internal fun saveUpdateTimestamp() {
        with(sharedPreferences.edit()) {
            putLong(LAST_UPDATE, System.currentTimeMillis())
            apply()
        }
    }

    private fun getLastSavedUpdate(): Long {
        return this.sharedPreferences.getLong(LAST_UPDATE, 0L)
    }

    internal fun sinceLastUpdate(): Long {
        return getDifference(getLastSavedUpdate())
    }

    private fun getDifference(lastUpdated: Long): Long {
        if (lastUpdated == 0L) return 0L
        return (System.currentTimeMillis() / 1000 - lastUpdated / 1000) / 60
    }


}