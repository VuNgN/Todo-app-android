package com.vungn.todoapp.usecase.search

import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.repository.TaskRepo
import vn.com.vti.learnningdisplay.base.usecase.UseCase
import javax.inject.Inject

class GetListUserUseCase @Inject constructor(private val taskRepo: TaskRepo) :
    UseCase<Unit, List<UserResponse>> {

    override suspend fun execute(params: Unit): List<UserResponse> {
        return taskRepo.loadUserInTask()
    }
}