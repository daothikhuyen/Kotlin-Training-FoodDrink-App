package com.example.exerciseapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.exerciseapplication.data.source.local.dao.FavoriteDao
import com.example.exerciseapplication.data.domain.entities.MenuItem
import com.example.exerciseapplication.data.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FavoriteRepositoryImpl (private val dao : FavoriteDao)  : FavoriteRepository {
    override suspend fun updateFavorite(item: MenuItem) = withContext(Dispatchers.IO){
        dao.updateItem(item)
    }

    override suspend fun deleteItem(id: Int)  = withContext(Dispatchers.IO){
        dao.deleteItem(id)
    }

    override suspend fun toggleFavorite(item: MenuItem)  = withContext(Dispatchers.IO){
        if(dao.getFavoriteListOnce(item.id).isNotEmpty()){
            dao.deleteItem(item.id)
            return@withContext
        }
        dao.insertItem(item)
    }

    override fun getFavoriteList(type : String): Flow<List<MenuItem>> {
        return dao.getFavoriteList(type)
    }
}