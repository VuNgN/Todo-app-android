package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.model.reponse.UserResponse
import java.util.*


interface TaskRepo {
    fun tasks(): List<Task>
    fun taskOn(date: Date): List<Task>
    fun upComingTasks(start: Date): List<Task>
    fun insertNewTask(task: Task): Boolean

     suspend fun loadUserInTask(): List<UserResponse>
     suspend fun addUserInTask(list: List<UserResponse>)
}