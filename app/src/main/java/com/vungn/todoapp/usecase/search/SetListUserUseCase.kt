package com.vungn.todoapp.usecase.search

import android.app.Application
import com.google.gson.Gson
import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.util.constants.Constants
import com.vungn.todoapp.util.sharepreferences.AppSharedPreferences
import vn.com.vti.learnningdisplay.base.usecase.UseCase
import javax.inject.Inject

class SetListUserUseCase @Inject constructor(
    private val taskRepo: TaskRepo,
) : UseCase<List<UserResponse>, Unit> {
    override suspend fun execute(params: List<UserResponse>) {
        taskRepo.addUserInTask(params)
    }
}