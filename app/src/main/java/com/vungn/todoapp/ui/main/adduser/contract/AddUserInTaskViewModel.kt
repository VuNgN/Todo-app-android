package com.vungn.todoapp.ui.main.adduser.contract

import com.vungn.todoapp.data.model.reponse.UserResponse

interface AddUserInTaskViewModel {
    fun setListUser(list: List<UserResponse>)
    fun getListUser(): List<UserResponse>
}