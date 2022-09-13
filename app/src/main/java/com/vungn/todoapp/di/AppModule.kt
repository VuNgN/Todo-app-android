package com.vungn.todoapp.di

import com.vungn.todoapp.data.restful.ClientService
import com.vungn.todoapp.data.restful.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesClientService(): ClientService {
        return RetrofitBuilder.getRetrofit().create(ClientService::class.java)
    }
}