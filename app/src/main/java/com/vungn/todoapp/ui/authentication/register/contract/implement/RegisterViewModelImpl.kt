package com.vungn.todoapp.ui.authentication.register.contract.implement

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.repository.UserRepo
import com.vungn.todoapp.data.repository.impl.UserRepoImpl
import com.vungn.todoapp.ui.authentication.register.contract.RegisterViewModel
import javax.inject.Inject

class RegisterViewModelImpl @Inject constructor(application: Application) : AndroidViewModel(application),
    RegisterViewModel {
    private val name: MutableLiveData<String> = MutableLiveData()
    private val email: MutableLiveData<String> = MutableLiveData()
    private val passwd: MutableLiveData<String> = MutableLiveData()
    private val rePasswd: MutableLiveData<String> = MutableLiveData()
    private val address: MutableLiveData<String> = MutableLiveData()
    private val userRepo: UserRepo by lazy {
        UserRepoImpl(application)
    }

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
            userRepo.insertUser(user)
        } catch (e: Exception) {
            Log.e("", "register: " + e.message)
            return false
        }
        return true
    }

}