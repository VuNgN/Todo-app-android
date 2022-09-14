package com.vungn.todoapp.data.restful

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://204e-118-70-81-192.ap.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}