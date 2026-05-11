package com.example.exerciseapplication.data.domain.repository

import com.example.exerciseapplication.data.domain.entities.WineEntity
import kotlinx.coroutines.flow.Flow

interface WineRepository {
    suspend fun getWines(): Flow<List<WineEntity>>

    suspend fun toggleCollection(item: WineEntity)

    fun getCollectionWines(): Flow<List<WineEntity>>
}