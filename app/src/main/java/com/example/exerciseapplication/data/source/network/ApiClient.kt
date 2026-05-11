package com.example.exerciseapplication.data.source.network

import com.example.exerciseapplication.data.source.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object RetrofitClient {
    private  const  val BASE_URL = "https://api.sampleapis.com/wines/"

    val apiService: ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}