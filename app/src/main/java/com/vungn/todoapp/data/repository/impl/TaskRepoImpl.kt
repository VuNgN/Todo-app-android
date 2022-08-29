package com.vungn.todoapp.data.repository.impl

import android.app.Application
import com.vungn.todoapp.data.database.Db
import com.vungn.todoapp.data.database.dao.TaskDao
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.util.TimeUtil.formatFromISO8601ToDay
import java.util.*

class TaskRepoImpl(application: Application) : TaskRepo {
    private val taskDao: TaskDao by lazy {
        Db.getInstance(application).taskDao()
    }

    override fun tasks(): List<Task> =
        try {
            val tasks = taskDao.allTask()
            tasks
        } catch (e: Exception) {
            listOf()
        }

    override fun taskOn(date: Date): List<Task> =
        try {
            val tasks = taskDao.taskOn(formatFromISO8601ToDay(date)!! + "%")
            tasks
        } catch (e: Exception) {
            listOf()
        }

    override fun insertNewTask(task: Task): Boolean =
        try {
            taskDao.save(task)
            true
        } catch (e: Exception) {
            false
        }
}