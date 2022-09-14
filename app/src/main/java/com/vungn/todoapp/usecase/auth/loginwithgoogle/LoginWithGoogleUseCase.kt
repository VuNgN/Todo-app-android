package com.vungn.todoapp.usecase.auth.loginwithgoogle

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.vungn.todoapp.data.model.User
import com.vungn.todoapp.data.restful.ClientService
import javax.inject.Inject

class LoginWithGoogleUseCase @Inject constructor(
    application: Application,
    private val clientService: ClientService,
) {

    suspend fun sendTokenToServer(token: String) = clientService.loginWithGoogle(token)

}