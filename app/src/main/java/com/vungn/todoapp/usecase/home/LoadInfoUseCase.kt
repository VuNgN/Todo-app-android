package com.vungn.todoapp.usecase.home

import android.app.Application
import com.google.gson.Gson
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.util.constants.Constants
import com.vungn.todoapp.util.sharepreferences.AppSharedPreferences
import java.io.FileReader
import javax.inject.Inject

class LoadInfoUseCase @Inject constructor(application: Application) {

    private val sharedPreferences: AppSharedPreferences =
        AppSharedPreferences(application, Constants.KEY_SHAREDPREFEREN)

    suspend fun getUser(): User{
        val gson = Gson()
        val user=gson.fromJson(FileReader(Constants.FILE_USER_JSON),User::class.java)
        return user
    }
}