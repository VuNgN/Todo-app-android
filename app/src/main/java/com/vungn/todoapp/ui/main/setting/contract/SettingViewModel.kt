package com.vungn.todoapp.ui.main.setting.contract

import androidx.lifecycle.LiveData

interface SettingViewModel {
    val avatar: LiveData<String>
    val name: LiveData<String>
    val email: LiveData<String>

    fun logout()
    fun loadUser()
}