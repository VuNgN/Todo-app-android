package com.vungn.todoapp.data.repository

import android.app.Application
import com.vungn.todoapp.util.sharepreferences.AppSharedPreferences
import com.vungn.todoapp.util.sharepreferences.save
import javax.inject.Inject

class ConfigManager @Inject constructor(application: Application) {
    private val prefs: AppSharedPreferences by lazy {
        AppSharedPreferences(application, "ConfigManager")
    }

    fun isFirstRun() = prefs.getBoolean(KEY_FIRST_RUN, true)
    fun setFirstRun(isFirstRun: Boolean) = prefs.save(KEY_FIRST_RUN, isFirstRun)
    fun isLoggedIn() = prefs.getBoolean(KEY_LOGGED_IN, false)
    fun setLoggedIn(isLoggedIn: Boolean) = prefs.save(KEY_LOGGED_IN, isLoggedIn)

    companion object {
        private const val KEY_FIRST_RUN = "FIRST_RUN"
        private const val KEY_LOGGED_IN = "LOGGED_IN"
    }
}