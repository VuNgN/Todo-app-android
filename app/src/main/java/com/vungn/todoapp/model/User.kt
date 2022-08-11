package com.vungn.todoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val name: String, val email: String, val avatar: String): Parcelable
