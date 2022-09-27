package com.vungn.todoapp.ui.main.insert.contract.implement

import android.app.Application
import androidx.lifecycle.*
import com.vungn.todoapp.common.livedata.CombinedLiveData
import com.vungn.todoapp.data.model.Task
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.data.repository.impl.TaskRepoImpl
import com.vungn.todoapp.ui.main.insert.contract.InsertTaskViewModel
import com.vungn.todoapp.util.TimeUtil.formatToISO8601
import com.vungn.todoapp.util.extention.ValidatorEx.isValidDesc
import com.vungn.todoapp.util.extention.ValidatorEx.isValidDueDate
import com.vungn.todoapp.util.extention.ValidatorEx.isValidName
import com.vungn.todoapp.util.extention.ValidatorEx.isValidRepeat
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class InsertTaskViewModelImpl @Inject constructor(
    application: Application,
    private val taskRepo: TaskRepo,
) : AndroidViewModel(application),
    InsertTaskViewModel {
    private val name: MutableLiveData<String> = MutableLiveData()
    private val repeat: MutableLiveData<String> = MutableLiveData()
    private val description: MutableLiveData<String> = MutableLiveData()
    private val dueDate: MutableLiveData<String> = MutableLiveData()
    private val isSaveSuccess: MutableLiveData<Boolean> = MutableLiveData()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    override fun name(): MutableLiveData<String> = name

    override fun repeat(): MutableLiveData<String> = repeat

    override fun description(): MutableLiveData<String> = description

    override fun dueDate(): MutableLiveData<String> = dueDate

    override fun isSaveSuccess(): MutableLiveData<Boolean> = isSaveSuccess

    override fun isLoading(): MutableLiveData<Boolean> = isLoading

    override fun clearTextField() {
        name.postValue("")
        repeat.postValue("")
        description.postValue("")
        dueDate.postValue("")
    }

    override fun isValid(): MediatorLiveData<Boolean> {
        return CombinedLiveData(name, repeat, description, dueDate) {
            name.value?.isValidName() == true &&
                    repeat.value?.isValidRepeat() == true &&
                    description.value?.isValidDesc() == true &&
                    dueDate.value?.isValidDueDate() == true
        }
    }

    override fun save() {
        isLoading.postValue(true)
        val currentDate = Calendar.getInstance().apply {}.time
        val formattedDate = formatToISO8601(currentDate)
        val task = Task(title = name.value.toString(),
            description = description.value.toString(),
            repeat = repeat.value.toString(),
            startOn = formattedDate,
            dueDate = dueDate.value.toString())
        if (taskRepo.insertNewTask(task)) {
            isSaveSuccess.postValue(true)
        } else {
            isSaveSuccess.postValue(false)
        }
        isLoading.postValue(false)
    }
}