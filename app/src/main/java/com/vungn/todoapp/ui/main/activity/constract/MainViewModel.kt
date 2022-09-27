package com.vungn.todoapp.ui.main.activity.constract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse

interface MainViewModel {
    val newLiveDataUserGuest: LiveData<List<UserResponse>>

    //    val livedataUser: MutableLiveData<List<UserResponse>>
    fun addUserInLiveData(userResponse: UserResponse)
    fun deleteUserInLiveData(index: Int)
}