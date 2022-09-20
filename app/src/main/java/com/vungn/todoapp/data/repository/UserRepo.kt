package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.model.request.UserRequest

interface UserRepo {
    suspend fun users(): List<User>
    suspend fun insertUser(user: UserRequest)
    suspend fun loginWithGoogle(token: String): UserRequest

}