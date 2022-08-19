package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.database.Db
import com.vungn.todoapp.data.database.dao.TaskDao
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo

class TaskRepoImpl(application: Application) : TaskRepo {
    private val taskDao: TaskDao by lazy {
        Db.getInstance(application).taskDao()
    }

    override fun insertNewTask(task: Task): Boolean =
        try {
            taskDao.save(task)
            true
        } catch (e: Exception) {
            false
        }
}