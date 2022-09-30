package com.vungn.todoapp.ui.main.adduser.contract

import androidx.lifecycle.LiveData
import com.vungn.todoapp.data.model.reponse.UserResponse

interface AddUserInTaskViewModel {
    val users: LiveData<List<UserResponse>>
    fun setListUser(list: List<UserResponse>)
    fun deleteUser(index: Int)
    fun addUser(user: UserResponse)
}