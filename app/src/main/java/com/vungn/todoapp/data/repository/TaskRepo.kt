package com.vungn.todoapp.data.repository

import com.vungn.todoapp.data.model.Task

interface TaskRepo {
    fun insertNewTask(task: Task): Boolean
}