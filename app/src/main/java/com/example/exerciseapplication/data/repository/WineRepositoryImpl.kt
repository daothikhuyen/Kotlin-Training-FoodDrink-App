package com.example.exerciseapplication.data.repository

import com.example.exerciseapplication.data.domain.entities.Wine
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.data.domain.repository.WineRepository
import com.example.exerciseapplication.data.mapper.toEntity
import com.example.exerciseapplication.data.source.local.dao.CollectionWineDao
import com.example.exerciseapplication.data.source.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WineRepositoryImpl(private val api: ApiService, private val dao: CollectionWineDao) :
    WineRepository {
    override suspend fun getWines(): Flow<List<WineEntity>> = flow { // convert
        val wines = api.getWines()
        emit(wines.map { it.toEntity() })
    }

    override suspend fun toggleCollection(item: WineEntity) {
        val item = item.copy(isCollected = !item.isCollected)
        if (dao.getCollectionListOnce(item.id).isNotEmpty()) {
            dao.deleteItem(item.id)
            return
        }
        dao.insertItem(item)
    }

    override fun getCollectionWines(): Flow<List<WineEntity>> {
        return dao.getCollectionList()
    }
}