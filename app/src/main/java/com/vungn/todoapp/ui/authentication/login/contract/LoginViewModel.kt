package com.vungn.todoapp.ui.authentication.login.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.Direction

interface LoginViewModel {
    fun navigation(): LiveData<Direction>
    fun email(): MutableLiveData<String>
    fun password(): MutableLiveData<String>
    fun login()
    fun navigateToVerify()
}