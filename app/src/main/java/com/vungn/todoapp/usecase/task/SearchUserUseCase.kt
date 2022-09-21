package com.vungn.todoapp.usecase.task

import com.vungn.todoapp.data.model.reponse.UserResponse
import com.vungn.todoapp.data.repository.UserRepo
import vn.com.vti.learnningdisplay.base.usecase.UseCase
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<String, List<UserResponse>> {
    override suspend fun execute(params: String): List<UserResponse> {
        return userRepo.searchUser(params)
    }
}