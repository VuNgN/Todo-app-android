package com.vungn.todoapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val title: String,
    val time: String,
    val description: String,
    val users: MutableList<User>,
    val startOn: String,
    val repeat: String,
    val dueDate: String
) : Parcelable
