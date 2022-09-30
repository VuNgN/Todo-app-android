package com.vungn.todoapp.usecase.auth.loginwithgoogle

import com.vungn.todoapp.data.manager.UserManager
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.repository.UserRepo
import vn.com.vti.learnningdisplay.base.usecase.UseCase
import javax.inject.Inject


class LoginWithGoogleUseCase @Inject constructor(
    private val userRepo: UserRepo,
    private val userManager: UserManager,
) : UseCase<String, Boolean> {

    override suspend fun execute(params: String): Boolean {
        val userRequest = userRepo.loginWithGoogle(params)
            val user = User(0,
                userRequest.name,
                userRequest.email,
                "",
                userRequest.avatar,
                "address")

            userManager.saveUser(user)
            return true
    }

}