package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.model.request.UserRequest
import com.vungn.todoapp.data.repository.UserRepo
import com.vungn.todoapp.data.restful.ClientService
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    application: Application,
    private val clientService: ClientService,
) : UserRepo {

    override suspend fun users(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: UserRequest) {
        clientService.register(user)
    }

    override suspend fun loginWithGoogle(token: String): UserResponse {
        return clientService.loginWithGoogle(token)
    }

    override suspend fun searchUser(key: String): List<UserResponse> {
        return clientService.searchUser(key)
    }


}