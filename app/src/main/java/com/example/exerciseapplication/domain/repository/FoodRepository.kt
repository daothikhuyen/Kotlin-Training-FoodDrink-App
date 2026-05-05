package com.example.exerciseapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.exerciseapplication.domain.entities.MenuItem

interface FoodRepository {
    fun getFoodList(): LiveData<List<MenuItem>>

    suspend fun getFoodListOnce(): List<MenuItem>
    suspend fun insertAll(list: List<MenuItem>)

    suspend fun updateItem(item: MenuItem)

    suspend fun deleteItem(id: Int)

    suspend fun toggleFavorite(id: Int)

    fun getFavoriteList(): LiveData<List<MenuItem>>
}