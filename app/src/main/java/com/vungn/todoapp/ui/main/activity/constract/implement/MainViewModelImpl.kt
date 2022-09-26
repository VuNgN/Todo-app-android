package com.vungn.todoapp.ui.main.activity.constract.implement

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(application: Application) :
    AndroidViewModel(application), MainViewModel {

    private val list: MutableList<UserResponse> = mutableListOf()
    private val listUserGuest: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val liveDataUserGuest: LiveData<List<UserResponse>> = listUserGuest

    override fun addUserInLiveData(userResponse: UserResponse) {
        try {
            list.addAll(liveDataUserGuest.value!!.size, liveDataUserGuest.value!!)
        } catch (e: Exception) {
            Log.d(TAG, "$e")
        }
        list.add(userResponse)
        listUserGuest.value = list
    }

    companion object {
        private val TAG = "MainViewModelImpl"
    }

}