package com.vungn.todoapp.ui.main.insert.contract

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

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
}