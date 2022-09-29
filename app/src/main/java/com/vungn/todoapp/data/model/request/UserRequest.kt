package com.vungn.todoapp.data.model.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String
): Serializable