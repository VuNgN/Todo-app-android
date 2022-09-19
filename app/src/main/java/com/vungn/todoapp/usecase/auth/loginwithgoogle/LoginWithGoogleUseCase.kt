package com.vungn.todoapp.usecase.auth.loginwithgoogle

import android.app.Application
import com.google.gson.Gson
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.restful.ClientService
import com.vungn.todoapp.util.constants.Constants
import java.io.FileWriter
import javax.inject.Inject


class LoginWithGoogleUseCase @Inject constructor(
    application: Application,
    private val clientService: ClientService,
) {

    suspend fun sendTokenToServer(token: String): User {
        val userRequest = clientService.loginWithGoogle(token)
        val user = User(userRequest.id,
            userRequest.name,
            userRequest.email,
            "",
            userRequest.avatar,
            "address")
        val gson = Gson()
        val file = FileWriter(Constants.FILE_USER_JSON)
//        Gson().toJson(user)
//        sharedPreferences.save(Constants.KEY_USER, jsonUser)
        gson.toJson(User::class.java,file)
        return user
    }

}