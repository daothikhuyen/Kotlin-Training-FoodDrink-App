package com.example.exerciseapplication.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.exerciseapplication.data.domain.entities.MenuItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM table_menu WHERE id = :id")
    suspend fun getFavoriteListOnce(id: Int): List<MenuItem>

    @Insert
    suspend fun insertItem(item: MenuItem)

    @Update
    suspend fun updateItem(item: MenuItem)

    @Query("DELETE FROM table_menu WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("SELECT * FROM table_menu WHERE type = :type ")
    fun getFavoriteList(type: String): Flow<List<MenuItem>>
}