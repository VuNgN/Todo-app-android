package com.vungn.todoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vungn.todoapp.data.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun allTask(): List<Task>

    @Query("SELECT * FROM tasks WHERE due_date LIKE :date ORDER BY due_date ASC")
    fun taskOn(date: String): List<Task>

    @Query("SELECT * FROM tasks WHERE due_date > :start ORDER BY due_date ASC")
    fun upComingTasks(start: String): List<Task>

    @Insert(entity = Task::class)
    fun save(task: Task): Long
}