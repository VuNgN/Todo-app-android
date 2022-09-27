package com.vungn.todoapp.ui.authentication.activity.contract.implement

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.authentication.activity.contract.AuthenticationViewModel
import com.vungn.todoapp.usecase.auth.CheckLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModelImpl @Inject constructor(
    application: Application,
    private val configManager: ConfigManager,
    private val checkLogin: CheckLoginUseCase,
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

        runBlocking {
            Log.d(TAG, "isLoggedIn: ${checkLogin.checkLogin()}")
            return@runBlocking checkLogin.checkLogin()
        }

        return false
    }

    companion object {
        private val TAG = "AuthenticationViewModelImpl"
    }
}