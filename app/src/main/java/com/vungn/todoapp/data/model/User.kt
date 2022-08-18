package com.vungn.todoapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vungn.todoapp.common.api.User.Companion.DEFAULT_AVATAR
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val passwd: String,
    @ColumnInfo(
        name = "avatar",
        defaultValue = DEFAULT_AVATAR
    )
    val avatar: String = DEFAULT_AVATAR,
    @ColumnInfo(name = "address")
    val address: String,
) : Parcelable