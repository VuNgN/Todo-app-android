package com.vungn.todoapp.ui.authentication.login.contract.implement

import android.app.Application
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.*
import com.vungn.todoapp.R
import com.vungn.todoapp.common.livedata.CombinedLiveData
import com.vungn.todoapp.common.livedata.OneTimeMutableLivedata
import com.vungn.todoapp.data.model.Direction
import com.vungn.todoapp.ui.authentication.login.contract.LoginViewModel
import com.vungn.todoapp.usecase.auth.loginwithgoogle.LoginWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    application: Application,
    private val loginWithGoogleUseCase: LoginWithGoogleUseCase,

    ) : AndroidViewModel(application), LoginViewModel {
    private val email = MutableLiveData<String>()
    private val password = MutableLiveData<String>()


    private val singleLiveEventLogin = OneTimeMutableLivedata<Boolean>()
    override val checkLogin: LiveData<Boolean> = singleLiveEventLogin

    private val navigation = OneTimeMutableLivedata<Direction>()

    override fun navigation(): LiveData<Direction> = navigation

    override fun email(): MutableLiveData<String> = email

    override fun password(): MutableLiveData<String> = password

    override fun login() {
        if (email.value == "admin" && password.value == "123") {
            navigation.postValue(Direction(R.id.action_loginFragment_to_verificationFragment, null))
        }
    }

    override fun loginWithGoogle(token: String) {
        runBlocking {
            loginWithGoogleUseCase.execute(token)
            singleLiveEventLogin.setValue(true)
            Log.d(TAG, "loginWithGoogle: " + checkLogin.value)
        }
    }

    override fun navigateToVerify() {
        // do something
    }

    companion object {
        private val TAG = "LoginViewModelImpl"
    }

}