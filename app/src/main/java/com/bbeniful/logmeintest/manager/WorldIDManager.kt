package com.bbeniful.logmeintest.manager

import android.content.Context
import android.content.SharedPreferences
import com.bbeniful.MainApplication


object WorldIDManager {

    private var sharedPreferences: SharedPreferences
    private const val MAIN_PREFERENCES = "WORLD_ID_PREFERENCES"
    private const val WORLD_ID = "WORLD_ID"

    init {
        this.sharedPreferences = MainApplication.applicationContext().getSharedPreferences(
            MAIN_PREFERENCES, Context.MODE_PRIVATE
        )
    }

    fun getWorldID(): String? {
        return this.sharedPreferences.getString(WORLD_ID, "")
    }

    fun setWorldID(worldID: String) {
        with(this.sharedPreferences.edit()) {
            putString(WORLD_ID, worldID)
            apply()
        }
    }

    fun clearWorldID(context: Context) {
        this.setWorldID("")
    }
}