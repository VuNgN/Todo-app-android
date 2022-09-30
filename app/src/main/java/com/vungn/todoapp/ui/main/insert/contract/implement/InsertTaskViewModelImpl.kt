package com.vungn.todoapp.ui.main.insert.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.common.livedata.CombinedLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.insert.contract.InsertTaskViewModel
import com.vungn.todoapp.usecase.search.GetListUserUseCase
import com.vungn.todoapp.usecase.search.SetListUserUseCase
import com.vungn.todoapp.util.extention.ValidatorEx.isValidDesc
import com.vungn.todoapp.util.extention.ValidatorEx.isValidDueDate
import com.vungn.todoapp.util.extention.ValidatorEx.isValidName
import com.vungn.todoapp.util.extention.ValidatorEx.isValidRepeat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsertTaskViewModelImpl @Inject constructor(
    application: Application,
    private val setListUserUseCase: SetListUserUseCase,
    private val getListUserUseCase: GetListUserUseCase,
) : AndroidViewModel(application),
    InsertTaskViewModel {
    private val name: MutableLiveData<String> = MutableLiveData()
    private val repeat: MutableLiveData<String> = MutableLiveData()
    private val description: MutableLiveData<String> = MutableLiveData()
    private val dueDate: MutableLiveData<String> = MutableLiveData()
    private val isSaveSuccess: MutableLiveData<Boolean> = MutableLiveData()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val users = mutableListOf<UserResponse>()

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
//        isLoading.postValue(true)
//        val currentDate = Calendar.getInstance().apply {}.time
//        val formattedDate = formatToISO8601(currentDate)
//        val task = Task(title = name.value.toString(),
//            description = description.value.toString(),
//            repeat = repeat.value.toString(),
//            startOn = formattedDate,
//            dueDate = dueDate.value.toString())
//        if (taskRepo.insertNewTask(task)) {
//            isSaveSuccess.postValue(true)
//        } else {
//            isSaveSuccess.postValue(false)
//        }
//        isLoading.postValue(false)
    }

    override fun setListUser(list: List<UserResponse>) {
        users.clear()
        users.addAll(list)
    }

    override fun getListUser(): List<UserResponse> {
        return users
    }
}