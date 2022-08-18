package com.vungn.todoapp.data.database

import android.app.Application
import androidx.room.Room

class Db private constructor(application: Application) {
    val db: AppDatabase = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "Todo-app"
    ).allowMainThreadQueries().build()

    private object Holder {
        fun instance(application: Application) = Db(application)
    }

    companion object {
        @JvmStatic
        fun getInstance(application: Application): AppDatabase {
            return Holder.instance(application).db
        }
    }
}
