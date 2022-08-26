package com.vungn.todoapp.ui.main.tracker.contract

import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.Task
import java.util.*

interface TrackerViewModel {
    fun date(): MutableLiveData<Date>
    fun tasks(): MutableLiveData<MutableList<Task>>
    fun getTasks(): List<Task>
}