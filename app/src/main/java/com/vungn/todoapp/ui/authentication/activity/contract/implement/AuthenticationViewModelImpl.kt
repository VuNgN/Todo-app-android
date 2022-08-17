package com.vungn.todoapp.ui.authentication.activity.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.authentication.activity.contract.AuthenticationViewModel

class AuthenticationViewModelImpl(application: Application) : AndroidViewModel(
    application
), AuthenticationViewModel {
    private val configManager: ConfigManager by lazy {
        ConfigManager(application.applicationContext)
    }

    override fun isFirstRun(): Boolean =
        if (configManager.isFirstRun()) {
            configManager.setFirstRun(false)
            true
        } else {
            false
        }

    override fun isLoggedIn(): Boolean = configManager.isLoggedIn()


    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthenticationViewModelImpl(application) as T
        }
    }
}