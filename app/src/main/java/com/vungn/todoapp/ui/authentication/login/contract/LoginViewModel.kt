package com.vungn.todoapp.ui.authentication.login.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.Direction
import com.vungn.todoapp.data.model.User

interface LoginViewModel {
    val checkLogin: LiveData<Boolean>
    fun navigation(): LiveData<Direction>
    fun email(): MutableLiveData<String>
    fun password(): MutableLiveData<String>
    fun login()
    fun loginWithGoogle(token: String)
    fun navigateToVerify()
}