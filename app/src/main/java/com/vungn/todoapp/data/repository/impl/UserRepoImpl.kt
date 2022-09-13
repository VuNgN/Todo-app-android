package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.database.Db
import com.vungn.todoapp.data.database.dao.UserDao
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.repository.UserRepo
import javax.inject.Inject

class UserRepoImpl @Inject constructor(application: Application) : UserRepo {

    private val userDao: UserDao by lazy {
        Db.getInstance(application).userDao()
    }

    override fun users() = userDao.getAll()

    override fun insertUser(user: User): Boolean =
        try {
            userDao.insertUser(user)
            true
        } catch (e: Exception) {
            false
        }
}