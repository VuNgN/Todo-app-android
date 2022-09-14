package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.User

interface UserRepo {
    suspend fun users(): List<User>
    suspend fun insertUser(user: User): Boolean
    suspend fun loginWithGoogle(token: String): User
}