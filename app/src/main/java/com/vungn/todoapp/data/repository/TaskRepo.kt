package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.Task
import java.util.*

interface TaskRepo {
    fun tasks(): List<Task>
    fun taskOn(date: Date): List<Task>
    fun insertNewTask(task: Task): Boolean
}