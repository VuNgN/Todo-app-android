package com.vungn.todoapp.data.model.reponse

import android.os.Parcelable
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import com.vungn.todoapp.data.model.User
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String,
) : Parcelable

@Parcelize
class UserRespons : ArrayList<UserResponse>(), Parcelable