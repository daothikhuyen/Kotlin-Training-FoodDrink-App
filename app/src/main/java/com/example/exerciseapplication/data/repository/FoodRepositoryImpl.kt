package com.example.exerciseapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.exerciseapplication.data.source.local.dao.FoodDao
import com.example.exerciseapplication.domain.entities.MenuItem
import com.example.exerciseapplication.domain.repository.FoodRepository

class FoodRepositoryImpl(private val dao : FoodDao) : FoodRepository {
    override fun getFoodList(): LiveData<List<MenuItem>>{
        return dao.getFoodList()
    }

    override suspend fun getFoodListOnce(): List<MenuItem>{
        return dao.getFoodListOnce()
    }

    override suspend fun insertAll(list: List<MenuItem>){
        dao.insertAll(list)
    }

    override suspend fun updateItem(item: MenuItem) {
        dao.updateFoodItem(item)
    }

    override suspend fun deleteItem(id: Int) {
        dao.deleteItem(id)
    }

    override suspend fun toggleFavorite(id: Int) {
        dao.toggleFavorite(id)
    }

    override fun getFavoriteList(): LiveData<List<MenuItem>> {
        return dao.getFavoriteList()
    }
}