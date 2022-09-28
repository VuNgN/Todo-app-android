package com.vungn.todoapp.ui.main.activity.constract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse

interface MainViewModel {

    val oldLiveDataUserGuest: MutableLiveData<List<UserResponse>>
    val guests: LiveData<List<UserResponse>>

    fun addUser(userResponse: UserResponse)
    fun deleteUser(index: Int)
    fun cancelAddUser()
}