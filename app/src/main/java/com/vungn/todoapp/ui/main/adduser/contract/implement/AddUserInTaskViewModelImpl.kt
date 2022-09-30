package com.vungn.todoapp.ui.main.adduser.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import com.vungn.todoapp.usecase.search.GetListUserUseCase
import com.vungn.todoapp.usecase.search.SetListUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddUserInTaskViewModelImpl @Inject constructor(
    application: Application,
) :
    AndroidViewModel(application), AddUserInTaskViewModel {

    private val _users: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val users: LiveData<List<UserResponse>> = _users

    override fun setListUser(list: List<UserResponse>) {
        _users.postValue(list)
    }


    override fun deleteUser(index: Int) {
        val list = _users.value?.toMutableList() ?: mutableListOf()
        list.removeAt(index)
        _users.postValue(list)
    }

    override fun addUser(user: UserResponse) {
        val list = _users.value?.toMutableList() ?: mutableListOf()
        list.add(user)
        _users.postValue(list)
    }
}