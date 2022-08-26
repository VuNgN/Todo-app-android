package com.vungn.todoapp.ui.main.tracker.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.data.repository.impl.TaskRepoImpl
import com.vungn.todoapp.ui.main.tracker.contract.TrackerViewModel
import java.util.*

class TrackerViewModelImpl(application: Application) : AndroidViewModel(application),
    TrackerViewModel {
    private var date: MutableLiveData<Date> = MutableLiveData()
    private var tasks: MutableLiveData<MutableList<Task>> = MutableLiveData()
    private val taskRepo: TaskRepo by lazy {
        TaskRepoImpl(application)
    }

    override fun date(): MutableLiveData<Date> = date
    override fun tasks(): MutableLiveData<MutableList<Task>> = tasks

    override fun getTasks() = taskRepo.taskOn(date.value ?: Calendar.getInstance().time)

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TrackerViewModelImpl(application) as T
        }
    }
}