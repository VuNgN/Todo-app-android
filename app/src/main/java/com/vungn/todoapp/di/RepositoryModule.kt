package com.vungn.todoapp.di

import com.vungn.todoapp.data.repository.TaskRepo
import com.vungn.todoapp.data.repository.UserRepo
import com.vungn.todoapp.data.repository.impl.TaskRepoImpl
import com.vungn.todoapp.data.repository.impl.UserRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskRepository(taskRepo: TaskRepoImpl): TaskRepo

    @Binds
    abstract fun bindUserRepository(userRepo: UserRepoImpl): UserRepo

}