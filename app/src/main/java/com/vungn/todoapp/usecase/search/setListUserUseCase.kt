package com.vungn.todoapp.usecase.search

import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.repository.TaskRepo
import vn.com.vti.learnningdisplay.base.usecase.UseCase
import javax.inject.Inject

class setListUserUseCase @Inject constructor(private val taskRepo: TaskRepo) {

    suspend fun execute(params: List<UserResponse>) {
        taskRepo.addUserInTask(params)
    }
}