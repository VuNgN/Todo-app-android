package com.vungn.todoapp.usecase.setting

import com.vungn.todoapp.data.manager.UserManager
import javax.inject.Inject

class LogOutUserUseCase @Inject constructor(private val userManager: UserManager) {

    suspend fun DeleteUserInSharePreferences(){
        userManager.deleteUser()
    }
}