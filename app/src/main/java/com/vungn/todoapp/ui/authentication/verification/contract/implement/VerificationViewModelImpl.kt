package com.vungn.todoapp.ui.authentication.verification.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.authentication.verification.contract.VerificationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationViewModelImpl @Inject constructor(application: Application,private val configManager: ConfigManager) : AndroidViewModel(application),
    VerificationViewModel {

    private val otp = MutableLiveData<String>()
    private val navigation = MutableLiveData<Boolean>()

    override fun otp(): MutableLiveData<String> = otp

    override fun navigation(): MutableLiveData<Boolean> = navigation

    override fun checkOtp() {
        if (otp.value == "0000") {
            navigation.postValue(true)
            configManager.setLoggedIn(true)
        }
    }

    override fun otpCompleted(otp: String) {
        this.otp.value = otp
    }
}