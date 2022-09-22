package com.vungn.todoapp.ui.main.activity.constract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(application: Application) :
    AndroidViewModel(application), MainViewModel {

    override val listUserGuest: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val liveDataUserGuest: LiveData<List<UserResponse>> = listUserGuest
}