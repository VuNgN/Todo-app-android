package com.vungn.todoapp.usecase.home

import com.vungn.todoapp.data.manager.UserManager
import com.vungn.todoapp.data.model.User
import javax.inject.Inject

class LoadInfoUserUseCase @Inject constructor(
    private val userManager: UserManager
) {

    suspend fun getUserFromJson(): User {
        val user = userManager.getUser()
        return user
    }
}