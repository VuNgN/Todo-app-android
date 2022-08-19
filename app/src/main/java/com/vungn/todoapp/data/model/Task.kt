package com.vungn.todoapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "start_on")
    val startOn: String,
    @ColumnInfo(name = "repeat")
    val repeat: String,
    @ColumnInfo(name = "due_date")
    val dueDate: String,
) : Parcelable {
    @IgnoredOnParcel
    @Ignore
    val users: MutableList<User>? = null
}
