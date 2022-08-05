package com.vungn.todoapp.model

data class Task(
    val title: String,
    val time: String,
    val description: String,
    val users: MutableList<User>
)
