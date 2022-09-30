package com.vungn.todoapp.ui.main.searchuser.constract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse

interface SearchUserViewModel {
    val resultSearch: LiveData<List<UserResponse>>
    fun keySearch(): MutableLiveData<String>
    fun search()
}