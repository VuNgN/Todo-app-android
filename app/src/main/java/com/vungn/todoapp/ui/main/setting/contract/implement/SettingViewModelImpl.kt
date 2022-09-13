package com.vungn.todoapp.ui.main.setting.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.main.setting.contract.SettingViewModel

class SettingViewModelImpl(application: Application,private val configManager: ConfigManager) : AndroidViewModel(application),
    SettingViewModel {


    override fun logout() {
        configManager.setLoggedIn(false)
    }

}