package com.vungn.todoapp.ui.main.home.contract

import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.ui.main.home.constant.TabType

interface HomeViewModel {
    fun tasks(): MutableLiveData<List<Task>>
    fun onTabChanges(type: TabType)
    fun getTodayTask()
    fun loadUserFromJson()
}