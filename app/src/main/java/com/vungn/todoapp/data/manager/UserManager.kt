package com.vungn.todoapp.data.manager

import com.vungn.todoapp.data.model.User

interface UserManager {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User
    suspend fun deleteUser()
}