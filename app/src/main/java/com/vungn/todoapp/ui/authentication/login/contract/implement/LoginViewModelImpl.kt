package com.vungn.todoapp.ui.authentication.login.contract.implement

import android.util.Log
import com.vungn.todoapp.ui.authentication.login.contract.LoginViewModel

class LoginViewModelImpl: LoginViewModel {

    override fun login() {
        // do something
        Log.d(TAG, "login: ")
    }

    override fun navigateToVerify() {
        // do something
    }

    companion object {
        private const val TAG = "LoginViewModelImpl"
    }
}