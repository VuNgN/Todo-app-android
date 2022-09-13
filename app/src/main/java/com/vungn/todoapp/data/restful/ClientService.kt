package com.vungn.todoapp.data.restful

import com.vungn.todoapp.common.api.User
import com.vungn.todoapp.data.model.request.UserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientService {

    @GET("user")
    suspend fun getAllUsers(): List<User>

    @POST("user")
    suspend fun register(@Body body: UserRequest): User

    @POST("auth")
    suspend fun loginWithGoogle(@Body token: String): User
}