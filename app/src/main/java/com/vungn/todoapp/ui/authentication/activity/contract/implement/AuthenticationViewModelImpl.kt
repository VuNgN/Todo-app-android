package com.vungn.todoapp.ui.authentication.activity.contract.implement

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    @SuppressLint("LongLogTag")
    override fun isLoggedIn(): Boolean {
        return configManager.isLoggedIn()
    }

    companion object {
        private val TAG = "AuthenticationViewModelImpl"
    }
}