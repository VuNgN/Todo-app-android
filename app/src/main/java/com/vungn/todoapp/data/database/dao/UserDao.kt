package com.vungn.todoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vungn.todoapp.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Insert(entity = User::class)
    fun insertUser(user: User): Long
}