package com.vungn.todoapp.ui.main.insert.contract

import androidx.lifecycle.MutableLiveData

interface InsertTaskViewModel {
    fun name(): MutableLiveData<String>
    fun repeat(): MutableLiveData<String>
    fun relatedLink(): MutableLiveData<String>
    fun dueDate(): MutableLiveData<String>
    fun save()
}