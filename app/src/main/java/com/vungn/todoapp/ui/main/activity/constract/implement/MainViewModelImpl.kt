package com.vungn.todoapp.ui.main.activity.constract.implement

import android.app.Application
import androidx.lifecycle.*
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    application: Application,
) :
    AndroidViewModel(application), MainViewModel {

    //livedata các user cũ
    override val oldLiveDataUserGuest: MutableLiveData<List<UserResponse>> = MutableLiveData()

    // livedata lưu các user
    private val _guests: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val guests: LiveData<List<UserResponse>> = _guests

    // fun confirm việc thêm các user search được vào list
    override fun addUser(userResponse: UserResponse) {
        val list = _guests.value?.toMutableList() ?: mutableListOf()
        list.add(userResponse)
        _guests.postValue(list)
    }

    //fun delete các user nằm trong list user của task
    override fun deleteUser(index: Int) {
        val list = _guests.value?.toMutableList() ?: mutableListOf()
        list.removeAt(index)
        _guests.postValue(list)
    }

    //fun bỏ các thay đổi của list user
    override fun cancelAddUser() {
        val list = _guests.value?.toMutableList() ?: mutableListOf()
        list.removeAll(list)
        if (oldLiveDataUserGuest.value != null) {
            _guests.postValue(oldLiveDataUserGuest.value)
        }
    }

    companion object {
        private val TAG = "MainViewModelImpl"
    }
}