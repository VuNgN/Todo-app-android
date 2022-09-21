package com.vungn.todoapp.data.model.reponse

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String
)