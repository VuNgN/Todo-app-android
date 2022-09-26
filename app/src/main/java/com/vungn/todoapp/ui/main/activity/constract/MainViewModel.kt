package com.vungn.todoapp.ui.main.activity.constract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse

interface MainViewModel {
    val liveDataUserGuest: LiveData<List<UserResponse>>
    fun addUserInLiveData(userResponse: UserResponse)
}