package com.vungn.todoapp.ui.main.searchuser.constract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vungn.todoapp.ui.main.searchuser.constract.SearchUserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModelImpl @Inject constructor(application: Application) : SearchUserViewModel,
    AndroidViewModel(application) {

}