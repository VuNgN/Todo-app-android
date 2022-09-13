package com.vungn.todoapp.ui.authentication.login.contract.implement

import android.app.Application
import androidx.lifecycle.*
import com.vungn.todoapp.R
import com.vungn.todoapp.common.livedata.OneTimeMutableLivedata
import com.vungn.todoapp.data.model.Direction
import com.vungn.todoapp.ui.authentication.login.contract.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    application: Application
) : AndroidViewModel(application), LoginViewModel {
    private val email = MutableLiveData<String>()
    private val password = MutableLiveData<String>()
    private val navigation = OneTimeMutableLivedata<Direction>()

    override fun navigation(): LiveData<Direction> = navigation

    override fun email(): MutableLiveData<String> = email

    override fun password(): MutableLiveData<String> = password

    override fun login() {
        if (email.value == "admin" && password.value == "123") {
            navigation.postValue(Direction(R.id.action_loginFragment_to_verificationFragment, null))
        }
    }

    override fun navigateToVerify() {
        // do something
    }

}