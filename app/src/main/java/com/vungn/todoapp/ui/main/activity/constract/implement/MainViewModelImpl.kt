package com.vungn.todoapp.ui.main.activity.constract.implement

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(application: Application) :
    AndroidViewModel(application), MainViewModel {

    private val listUserGuest: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val newLiveDataUserGuest: LiveData<List<UserResponse>> = listUserGuest


    override fun addUserInLiveData(userResponse: UserResponse) {
        val list = listUserGuest.value?.toMutableList() ?: mutableListOf()
        list.add(userResponse)
        listUserGuest.postValue(list)
    }

    override fun deleteUserInLiveData(index: Int) {
        val list= listUserGuest.value?.toMutableList() ?: mutableListOf()
        list.removeAt(index)
        listUserGuest.postValue(list)
    }

    companion object {
        private val TAG = "MainViewModelImpl"
    }

}