package com.vungn.todoapp.ui.authentication.verification.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface VerificationViewModel {
    fun otp(): MutableLiveData<String>
    fun navigation(): LiveData<Boolean>
    fun checkOtp()
    fun otpCompleted(otp: String)
}