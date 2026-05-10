package com.example.exerciseapplication.data.repository

import com.example.exerciseapplication.data.domain.entities.Wine
import com.example.exerciseapplication.data.domain.repository.WineRepository
import com.example.exerciseapplication.data.source.remote.ApiService

class WineRepositoryImpl(private val api: ApiService): WineRepository {
    override suspend fun getWines(): List<Wine> {
        return api.getWines()
    }
}