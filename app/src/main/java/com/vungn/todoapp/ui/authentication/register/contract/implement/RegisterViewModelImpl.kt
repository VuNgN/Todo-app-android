package com.vungn.todoapp.ui.authentication.register.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vungn.todoapp.data.model.request.UserRequest
import com.vungn.todoapp.data.repository.UserRepo
import com.vungn.todoapp.ui.authentication.register.contract.RegisterViewModel
import com.vungn.todoapp.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    application: Application,
    private val userRepo: UserRepo,
    private val registerUseCase: RegisterUseCase,
) : AndroidViewModel(application),
    RegisterViewModel {
    private val name: MutableLiveData<String> = MutableLiveData()
    private val email: MutableLiveData<String> = MutableLiveData()
    private val passwd: MutableLiveData<String> = MutableLiveData()
    private val rePasswd: MutableLiveData<String> = MutableLiveData()
    private val address: MutableLiveData<String> = MutableLiveData()


    override fun name(): MutableLiveData<String> = name

    override fun email(): MutableLiveData<String> = email

    override fun passwd(): MutableLiveData<String> = passwd

    override fun rePasswd(): MutableLiveData<String> = rePasswd

    override fun address(): MutableLiveData<String> = address

    override fun register(): Boolean {
        if (passwd.value != rePasswd.value) {
            return false
        }
        val userRequest = UserRequest(
            name = name.value.toString(),
            email = email.value.toString(),
            ""
        )

        viewModelScope.launch {
           registerUseCase.execute(userRequest)
        }

        return true
    }

}