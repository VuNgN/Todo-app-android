package com.vungn.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vungn.todoapp.data.database.dao.UserDao
import com.vungn.todoapp.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}