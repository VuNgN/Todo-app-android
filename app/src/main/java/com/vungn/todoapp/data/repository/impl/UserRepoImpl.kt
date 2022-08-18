package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.database.Db
import com.vungn.todoapp.data.database.dao.UserDao
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.repository.UserRepo

class UserRepoImpl(application: Application) : UserRepo {
    private val userDao: UserDao by lazy {
        Db.getInstance(application).userDao()
    }

    override fun users() = userDao.getAll()

    override fun insertUser(user: User) {
        userDao.insertUser(user)
    }
}