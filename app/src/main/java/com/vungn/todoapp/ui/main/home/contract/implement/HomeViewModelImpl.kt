package com.vungn.todoapp.ui.main.home.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.ui.main.home.constant.TabType
import com.vungn.todoapp.ui.main.home.contract.HomeViewModel
import com.vungn.todoapp.usecase.home.LoadInfoUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    application: Application,
    private val taskRepo: TaskRepo,
    private val loadInfoUserUseCase: LoadInfoUserUseCase,
) : AndroidViewModel(application), HomeViewModel {
    private val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    private val mutableName: MutableLiveData<String> = MutableLiveData()
    override val name: LiveData<String>
        get() = mutableName

    private val mutableAvartar: MutableLiveData<String> = MutableLiveData()
    override val avatar: LiveData<String>
        get() = mutableAvartar

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

    override fun loadUser() {
        viewModelScope.launch {
            val user = loadInfoUserUseCase.getUserFromJson()
            mutableName.postValue(user.name)
            mutableAvartar.postValue(user.avatar)
        }
    }

}