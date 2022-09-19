package com.vungn.todoapp.ui.main.home.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.data.repository.impl.TaskRepoImpl
import com.vungn.todoapp.ui.main.home.constant.TabType
import com.vungn.todoapp.ui.main.home.contract.HomeViewModel
import com.vungn.todoapp.usecase.home.LoadInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    application: Application,
    private val taskRepo: TaskRepo,
    private val loadInfoUseCase: LoadInfoUseCase
) : AndroidViewModel(application), HomeViewModel {
    private val tasks: MutableLiveData<List<Task>> = MutableLiveData()
    private val name: MutableLiveData<String> = MutableLiveData()
    private val avartar: MutableLiveData<String> = MutableLiveData()


    override fun tasks(): MutableLiveData<List<Task>> = tasks

    override fun onTabChanges(type: TabType) {
        val today = Calendar.getInstance().time
        val tomorrow = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
        }.time
        val tasks = when (type) {
            TabType.TODAY -> taskRepo.taskOn(today)
            TabType.TOMORROW -> taskRepo.taskOn(tomorrow)
            TabType.UPCOMING -> taskRepo.upComingTasks(today)
        }
        this.tasks.postValue(tasks)
    }

    override fun getTodayTask() {
        val today = Calendar.getInstance().time
        val tasks = taskRepo.taskOn(today)
        this.tasks.postValue(tasks)
    }

}