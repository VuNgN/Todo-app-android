package com.vungn.todoapp.ui.main.searchuser.constract.implement

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.searchuser.constract.SearchUserViewModel
import com.vungn.todoapp.usecase.task.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModelImpl @Inject constructor(
    application: Application,
    private val searchUserUseCase: SearchUserUseCase,
) : SearchUserViewModel,
    AndroidViewModel(application) {


    private val listUser: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val listUserSearch: LiveData<List<UserResponse>> = listUser

    private val key: MutableLiveData<String> = MutableLiveData()

    override fun keySearch(): MutableLiveData<String> = key

    override fun search() {
        viewModelScope.launch {
            try {
                val list = searchUserUseCase.execute(key.value.toString())

                Log.d(TAG, "search:  : ${list.get(0).name}")

                listUser.postValue(list)
            } catch (e: Exception) {
                Log.e(TAG, "search: $e", e)
            }
        }
    }

    companion object {
        private val TAG = "SearchUserViewModelImpl"
    }
}