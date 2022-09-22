package com.vungn.todoapp.data.restful

import com.vungn.todoapp.common.api.User
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.model.request.UserRequest
import retrofit2.http.*


interface ClientService {

    @POST("user")
    suspend fun register(@Body body: UserRequest): User

    @FormUrlEncoded
    @POST("auth")
    suspend fun loginWithGoogle(@Field("token") token: String): UserResponse

    @GET("user/search")
    @Headers(
        "Content-type: application/json"
    )
    suspend fun searchUser(@Query("keyword") keyword: String): List<UserResponse>
}