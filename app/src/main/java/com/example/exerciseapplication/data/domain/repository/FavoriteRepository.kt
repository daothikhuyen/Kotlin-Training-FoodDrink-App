package com.example.exerciseapplication.data.domain.repository

import com.example.exerciseapplication.data.domain.entities.MenuItem
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun updateFavorite(item: MenuItem)

    suspend fun deleteItem(id: Int)
    suspend fun toggleFavorite(item: MenuItem)
    fun getFavoriteList(type: String): Flow<List<MenuItem>>
}