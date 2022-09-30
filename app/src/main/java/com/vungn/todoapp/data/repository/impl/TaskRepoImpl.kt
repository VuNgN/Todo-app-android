package com.vungn.todoapp.data.repository.impl

import android.app.Application
import android.util.Log
import com.vungn.todoapp.data.database.Db
import com.vungn.todoapp.data.database.dao.TaskDao
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.util.TimeUtil.formatFromISO8601ToDay
import com.vungn.todoapp.util.TimeUtil.formatToISO8601
import java.util.*
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(application: Application) : TaskRepo {
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

    override fun upComingTasks(start: Date): List<Task> =
        try {
            val tasks = taskDao.upComingTasks(formatToISO8601(start))
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

    private val list: MutableList<UserResponse> = mutableListOf()

    override suspend fun addUserInTask(list: List<UserResponse>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override suspend fun loadUserInTask(): List<UserResponse> {
        return list
    }

    companion object{
        private val TAG = "TaskRepoImpl"
    }

}