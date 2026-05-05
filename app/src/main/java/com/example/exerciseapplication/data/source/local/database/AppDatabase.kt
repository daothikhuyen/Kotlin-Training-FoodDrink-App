package com.example.exerciseapplication.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exerciseapplication.data.source.local.dao.DrinkDao
import com.example.exerciseapplication.data.source.local.dao.FoodDao
import com.example.exerciseapplication.domain.entities.MenuItem

// AppDatabaselà lớp cơ sở dữ liệu Room, exportSchema: xuất file json db
@Database(
    entities = [MenuItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun drinkDao(): DrinkDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "drink_food_db"
                    ).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}