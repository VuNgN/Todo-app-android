package com.vungn.todoapp.di

import android.util.Log
import com.google.gson.Gson
import com.vungn.todoapp.data.restful.ClientService
import com.vungn.todoapp.data.restful.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesClientService(): ClientService {
        return RetrofitBuilder.getRetrofit().create(ClientService::class.java)
            .also { Log.d("TAG", "providesClientService: " + hashCode()) }
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }
}