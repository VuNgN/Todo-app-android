package com.vungn.todoapp.data.model.reponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserSearchResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("status")
    val status: Int
): Serializable {
}