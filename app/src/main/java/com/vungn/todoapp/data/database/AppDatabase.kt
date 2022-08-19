package com.vungn.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vungn.todoapp.data.database.dao.TaskDao
import com.vungn.todoapp.data.database.dao.UserDao
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.model.User

@Database(entities = [User::class, Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}