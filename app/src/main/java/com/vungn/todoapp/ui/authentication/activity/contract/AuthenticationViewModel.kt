package com.vungn.todoapp.ui.authentication.activity.contract

interface AuthenticationViewModel {
    fun isFirstRun(): Boolean
    fun isLoggedIn(): Boolean
}