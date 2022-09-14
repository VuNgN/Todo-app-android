package com.vungn.todoapp.ui.authentication.register.contract.implement

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.repository.UserRepo
import com.vungn.todoapp.data.repository.impl.UserRepoImpl
import com.vungn.todoapp.ui.authentication.register.contract.RegisterViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModelImpl @Inject constructor(
    application: Application,
    private val userRepo: UserRepo,
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
        val user = User(
            name = name.value.toString(),
            email = email.value.toString(),
            passwd = passwd.value.toString(),
            address = address.value.toString()
        )
        try {
            viewModelScope.launch {
                userRepo.insertUser(user)
            }
        } catch (e: Exception) {
            Log.e("", "register: " + e.message)
            return false
        }
        return true

    }

}