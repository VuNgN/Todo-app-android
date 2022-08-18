package com.vungn.todoapp.ui.authentication.register.contract

import androidx.lifecycle.MutableLiveData

interface RegisterViewModel {
    fun name(): MutableLiveData<String>
    fun email(): MutableLiveData<String>
    fun passwd(): MutableLiveData<String>
    fun rePasswd(): MutableLiveData<String>
    fun address(): MutableLiveData<String>
    fun register()
}