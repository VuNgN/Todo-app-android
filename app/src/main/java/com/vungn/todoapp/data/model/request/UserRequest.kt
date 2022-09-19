package com.vungn.todoapp.data.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avartar")
    val avatar: String
)