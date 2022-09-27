package com.vungn.todoapp.usecase.auth

import com.vungn.todoapp.data.manager.UserManager
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(private val userManager: UserManager)  {

    suspend fun checkLogin():Boolean{
        return userManager.checkLogin()
    }

}