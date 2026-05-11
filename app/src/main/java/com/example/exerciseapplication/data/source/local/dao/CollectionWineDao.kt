package com.example.exerciseapplication.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.exerciseapplication.data.domain.entities.WineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionWineDao {
    @Query("SELECT * FROM collection_wine WHERE id = :id")
    suspend fun getCollectionListOnce(id: Int): List<WineEntity>

    @Insert
    suspend fun insertItem(item: WineEntity)

    @Update
    suspend fun updateItem(item: WineEntity)

    @Query("DELETE FROM collection_wine WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("SELECT * FROM collection_wine ")
    fun getCollectionListOnce(): List<WineEntity>

    @Query("SELECT * FROM collection_wine ")
    fun getCollectionList(): Flow<List<WineEntity>>
}