package com.vungn.todoapp.ui.main.adduser.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vungn.todoapp.ui.main.adduser.contract.AddUserInTaskViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddUserInTaskViewModelImpl @Inject constructor(application: Application) :
    AndroidViewModel(application),AddUserInTaskViewModel {
}