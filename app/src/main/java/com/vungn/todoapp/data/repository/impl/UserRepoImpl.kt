package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.database.Db
import com.vungn.todoapp.data.database.dao.UserDao
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.repository.UserRepo
import com.vungn.todoapp.data.restful.ClientService
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    application: Application,
    private val clientService: ClientService,
) : UserRepo {

    //    private val userDao: UserDao by lazy {
//        Db.getInstance(application).userDao()
//    }
//
//    override fun users() = userDao.getAll()
//
//    override fun insertUser(user: User): Boolean =
//        try {
//            userDao.insertUser(user)
//            true
//        } catch (e: Exception) {
//            false
//        }
    override suspend fun users(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithGoogle(token: String): User {
        TODO("Not yet implemented")
    }

//    override suspend fun loginWithGoogle(token: String): User = clientService.loginWithGoogle(token)


}