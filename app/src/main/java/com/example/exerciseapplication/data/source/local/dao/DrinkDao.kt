package com.example.exerciseapplication.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.exerciseapplication.domain.entities.MenuItem

@Dao
interface DrinkDao {

    @Query("SELECT * FROM table_menu WHERE type = 'drink'")
    fun getDrinkList(): LiveData<List<MenuItem>>

    @Query("SELECT * FROM table_menu WHERE type = 'drink'")
    suspend fun getDrinkListOnce(): List<MenuItem>

    @Insert
    suspend fun insertAll(list: List<MenuItem>)

    @Update
    suspend fun updateDrinkItem(item: MenuItem)

    @Query("DELETE FROM table_menu WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("""
        UPDATE table_menu 
        SET is_favorite = CASE WHEN is_favorite = 1 THEN 0 ELSE 1 END 
        WHERE id = :id
    """)
    suspend fun toggleFavorite(id: Int)

    @Query("SELECT * FROM table_menu WHERE is_favorite = 1 AND type = 'drink'")
    fun getFavoriteList(): LiveData<List<MenuItem>>
}