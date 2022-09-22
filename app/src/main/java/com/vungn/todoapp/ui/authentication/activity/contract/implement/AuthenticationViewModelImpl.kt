package com.vungn.todoapp.ui.authentication.activity.contract.implement

import android.app.Application
import androidx.lifecycle.*
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.authentication.activity.contract.AuthenticationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModelImpl @Inject constructor(
    application: Application,
    private val configManager: ConfigManager,
) : AndroidViewModel(
    application
), AuthenticationViewModel {

    override fun isFirstRun(): Boolean =
        if (configManager.isFirstRun()) {
            configManager.setFirstRun(false)
            true
        } else {
            false
        }

    override fun isLoggedIn(): Boolean = configManager.isLoggedIn()

}