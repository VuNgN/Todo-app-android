package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.User

interface UserRepo {
    fun users(): List<User>
    fun insertUser(user: User)
}