package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.Task
import java.util.*

interface TaskRepo {
    fun insertNewTask(task: Task): Boolean
    fun taskOn(date: Date): List<Task>
}