package com.vungn.todoapp.data.manager.implement

import android.app.Application
import com.google.gson.Gson
import com.vungn.todoapp.data.manager.UserManager
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.util.constants.Constants
import com.vungn.todoapp.util.sharepreferences.AppSharedPreferences
import com.vungn.todoapp.util.sharepreferences.save
import javax.inject.Inject

class UserManagerImpl @Inject constructor(application: Application, private val gson: Gson) :
    UserManager {

    private val sharedPreferences: AppSharedPreferences by lazy {
        AppSharedPreferences(application, Constants.KEY_SHAREDPREFEREN)
    }

    override suspend fun saveUser(user: User) {
        val strJsonUser = gson.toJson(user)
        sharedPreferences.save(Constants.KEY_USER, strJsonUser)
    }

    override suspend fun getUser(): User {
        val strJsonUser = sharedPreferences.getString(Constants.KEY_USER)
        return gson.fromJson(strJsonUser, User::class.java)
    }


}