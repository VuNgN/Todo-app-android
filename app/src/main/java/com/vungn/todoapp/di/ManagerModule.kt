package com.vungn.todoapp.di

import com.vungn.todoapp.data.manager.UserManager
import com.vungn.todoapp.data.manager.implement.UserManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Singleton
    @Binds
    abstract fun bindUserManager(userManager: UserManagerImpl): UserManager
}