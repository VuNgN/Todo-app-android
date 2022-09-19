package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.model.User
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

    override suspend fun insertUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithGoogle(token: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun writeFileJson(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun readFileJson() {
        TODO("Not yet implemented")
    }

//    override suspend fun loginWithGoogle(token: String): User = clientService.loginWithGoogle(token)


}