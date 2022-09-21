package com.vungn.todoapp.usecase.task

import com.vungn.todoapp.data.model.request.UserRequest
import javax.inject.Inject

class HandleDataUserUseCase @Inject constructor() {

    suspend fun execute(
        listLocal: List<UserRequest>,
        listServer: List<UserRequest>,
    ): List<UserRequest> {

        var listReturn: MutableList<UserRequest> = mutableListOf()

        for (userServer: UserRequest in listServer) {
            for (userLocal: UserRequest in listLocal) {
                if (userLocal.name != userServer.name && userLocal.email != userServer.email) {
                    listReturn.add(userServer)
                }
            }
        }

        return listReturn
    }

}