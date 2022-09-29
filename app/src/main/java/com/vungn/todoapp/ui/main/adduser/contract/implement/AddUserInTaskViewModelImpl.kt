package com.vungn.todoapp.ui.main.adduser.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import com.vungn.todoapp.usecase.search.getListUserUseCase
import com.vungn.todoapp.usecase.search.setListUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserInTaskViewModelImpl @Inject constructor(
    application: Application,
    private val setListUserUseCase: setListUserUseCase,
    private val getListUserUseCase: getListUserUseCase,
) :
    AndroidViewModel(application), AddUserInTaskViewModel {

    override fun setListUser(list: List<UserResponse>) {
        viewModelScope.launch {
            setListUserUseCase.execute(list)
        }
    }

    override fun getListUser(): List<UserResponse> {
        val listUser = mutableListOf<UserResponse>()
        viewModelScope.launch {
            listUser.addAll(getListUserUseCase.execute())
        }
        return listUser
    }
}