package com.vungn.todoapp.ui.main.setting.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.main.setting.contract.SettingViewModel

class SettingViewModelImpl(application: Application) : AndroidViewModel(application),
    SettingViewModel {
    private val configManager: ConfigManager by lazy {
        ConfigManager(application.applicationContext)
    }

    override fun logout() {
        configManager.setLoggedIn(false)
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SettingViewModelImpl(application) as T
        }
    }
}