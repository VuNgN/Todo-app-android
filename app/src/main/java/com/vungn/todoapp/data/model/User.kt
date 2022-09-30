package com.vungn.todoapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vungn.todoapp.common.api.User.Companion.DEFAULT_AVATAR
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String,

    @SerializedName("password")
    @ColumnInfo(name = "password")
    val passwd: String,

    @SerializedName("avatar")
    @ColumnInfo(
        name = "avatar",
        defaultValue = DEFAULT_AVATAR
    )
    val avatar: String = DEFAULT_AVATAR,

    @SerializedName("address")
    @ColumnInfo(name = "address")
    val address: String,
) : Parcelable