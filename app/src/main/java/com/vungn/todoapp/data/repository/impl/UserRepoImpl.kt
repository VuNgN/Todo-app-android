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
        return data()
//        return clientService.searchUser(key)
    }

    inline fun data(): List<UserResponse> {
        val lst = mutableListOf(
            UserResponse(0,"người số 1", " email 1", ""),
            UserResponse(0,"đồng chí A", " email 2", ""),
            UserResponse(0,"fake data", " email 3", ""),
            UserResponse(0,"user ảo", " email 4", ""),
            UserResponse(0,"name", " email 5", ""),
        )
        return lst
    }

}