package com.vungn.todoapp.data.restful

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {



    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://405e-118-70-81-192.ap.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}