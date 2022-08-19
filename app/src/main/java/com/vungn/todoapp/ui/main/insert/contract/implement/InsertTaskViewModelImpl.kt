package com.vungn.todoapp.ui.main.insert.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.data.repository.impl.TaskRepoImpl
import com.vungn.todoapp.ui.main.insert.contract.InsertTaskViewModel
import com.vungn.todoapp.util.TimeUtil.formatToISO8601
import java.util.*

class InsertTaskViewModelImpl(application: Application) : AndroidViewModel(application),
    InsertTaskViewModel {
    private val name: MutableLiveData<String> = MutableLiveData()
    private val repeat: MutableLiveData<String> = MutableLiveData()
    private val relatedLink: MutableLiveData<String> = MutableLiveData()
    private val dueDate: MutableLiveData<String> = MutableLiveData()
    private val taskRepo: TaskRepo by lazy {
        TaskRepoImpl(application)
    }

    override fun name(): MutableLiveData<String> = name

    override fun repeat(): MutableLiveData<String> = repeat

    override fun relatedLink(): MutableLiveData<String> = relatedLink

    override fun dueDate(): MutableLiveData<String> = dueDate

    override fun save() {
        val currentDate = Calendar.getInstance().apply {}.time
        val formattedDate = formatToISO8601(currentDate)
        val task = Task(title = name.value.toString(),
            description = relatedLink.value.toString(),
            repeat = repeat.value.toString(),
            startOn = formattedDate,
            dueDate = dueDate.value.toString())
        taskRepo.insertNewTask(task)
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return InsertTaskViewModelImpl(application) as T
        }
    }
}