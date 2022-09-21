package com.vungn.todoapp.data.restful

import com.vungn.todoapp.common.api.User
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.model.request.UserRequest
import retrofit2.http.*


interface ClientService {

    @GET("user")
    suspend fun getAllUsers(): List<User>

    @POST("user")
    suspend fun register(@Body body: UserRequest): User

    @FormUrlEncoded
    @POST("auth")
    suspend fun loginWithGoogle(@Field("token") token: String): UserResponse

    @GET("user/search")
    suspend fun searchUser(@Field("keyword") key: String): List<UserResponse>
}