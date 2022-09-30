package com.vungn.todoapp.ui.main.searchuser.constract.implement

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.ui.main.searchuser.constract.SearchUserViewModel
import com.vungn.todoapp.usecase.search.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModelImpl @Inject constructor(
    application: Application,
    private val searchUserUseCase: SearchUserUseCase,
) : SearchUserViewModel,
    AndroidViewModel(application) {


    private val searchResult: MutableLiveData<List<UserResponse>> = MutableLiveData()
    override val resultSearch: LiveData<List<UserResponse>> = searchResult

    private val key: MutableLiveData<String> = MutableLiveData()
    override fun keySearch(): MutableLiveData<String> = key

    override fun search() {
        viewModelScope.launch {
            val listSearch = searchUserUseCase.execute(key.value.toString())
            Log.d(TAG, "search: ${key.value.toString()}")
            searchResult.postValue(listSearch)
        }
    }

    companion object {
        private val TAG = "SearchUserViewModelImpl"
    }
}