package com.vungn.todoapp.ui.main.insert.contract

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.vungn.todoapp.data.model.reponse.UserResponse

interface InsertTaskViewModel {
    fun name(): MutableLiveData<String>
    fun repeat(): MutableLiveData<String>
    fun description(): MutableLiveData<String>
    fun dueDate(): MutableLiveData<String>
    fun isValid(): MediatorLiveData<Boolean>
    fun isSaveSuccess(): MutableLiveData<Boolean>
    fun isLoading(): MutableLiveData<Boolean>
    fun clearTextField()
    fun save()
    fun setListUser(list: List<UserResponse>)
    fun getListUser(): List<UserResponse>
}