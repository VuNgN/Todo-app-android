package com.vungn.todoapp.usecase.auth

import android.app.Application
import android.util.Log
import com.vungn.todoapp.data.model.request.UserRequest
import com.vungn.todoapp.data.repository.UserRepo
import vn.com.vti.learnningdisplay.base.usecase.UseCase
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepo: UserRepo,
) : UseCase<UserRequest, Boolean> {

    override suspend fun execute(params: UserRequest): Boolean {
        try {
            userRepo.insertUser(params)
            return true
        } catch (e: Exception) {
            Log.e("RegisterUseCase", "register: " + e.message)
            return false
        }
    }

}