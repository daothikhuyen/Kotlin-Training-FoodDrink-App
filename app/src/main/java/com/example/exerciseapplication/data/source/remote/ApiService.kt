package com.example.exerciseapplication.data.source.remote

import com.example.exerciseapplication.data.domain.entities.WineEntity
import retrofit2.http.GET

interface ApiService {
    @GET("reds")
    suspend fun getWines(): List<WineEntity>
}