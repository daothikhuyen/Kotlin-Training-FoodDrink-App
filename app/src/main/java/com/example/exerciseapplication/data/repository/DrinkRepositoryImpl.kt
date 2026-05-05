package com.example.exerciseapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.exerciseapplication.data.source.local.dao.DrinkDao
import com.example.exerciseapplication.domain.entities.MenuItem
import com.example.exerciseapplication.domain.repository.DrinkRepository

class DrinkRepositoryImpl(private val dao: DrinkDao) : DrinkRepository {
    override fun getDrinkList(): LiveData<List<MenuItem>> {
        return dao.getDrinkList()
    }

    override suspend fun getDrinkListOnce(): List<MenuItem> {
        return dao.getDrinkListOnce()
    }

    override suspend fun insertAll(list: List<MenuItem>) {
        dao.insertAll(list)
    }

    override suspend fun updateItem(item: MenuItem) {
        dao.updateDrinkItem(item)
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