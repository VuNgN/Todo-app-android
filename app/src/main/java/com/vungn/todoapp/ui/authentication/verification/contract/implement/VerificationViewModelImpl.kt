package com.vungn.todoapp.ui.authentication.verification.contract.implement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vungn.todoapp.ui.authentication.verification.contract.VerificationViewModel

class VerificationViewModelImpl(application: Application) : AndroidViewModel(application),
    VerificationViewModel {
    private val otp = MutableLiveData<String>()
    private val navigation = MutableLiveData<Boolean>()

    override fun otp(): MutableLiveData<String> = otp

    override fun navigation(): MutableLiveData<Boolean> = navigation

    override fun checkOtp() {
        if (otp.value == "0000") {
            navigation.postValue(true)
        }
    }

    override fun otpCompleted(otp: String) {
        this.otp.value = otp
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VerificationViewModelImpl(application) as T
        }
    }
}