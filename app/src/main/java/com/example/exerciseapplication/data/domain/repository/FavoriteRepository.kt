package com.example.exerciseapplication.data.domain.repository

import androidx.lifecycle.LiveData
import com.example.exerciseapplication.data.domain.entities.MenuItem

interface FavoriteRepository {

    suspend fun updateFavorite(item: MenuItem)

    suspend fun deleteItem(id: Int)
    suspend fun toggleFavorite(item: MenuItem)
    fun getFavoriteList(type: String): LiveData<List<MenuItem>>
}