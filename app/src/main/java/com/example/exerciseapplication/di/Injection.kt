package com.example.exerciseapplication.di

import android.content.Context
import com.example.exerciseapplication.data.repository.FavoriteRepositoryImpl
import com.example.exerciseapplication.data.repository.WineRepositoryImpl
import com.example.exerciseapplication.data.source.local.database.AppDatabase
import com.example.exerciseapplication.data.source.network.RetrofitClient
import com.example.exerciseapplication.ui.collection.CollectionWineViewModelFactory
import com.example.exerciseapplication.ui.wine.WineViewModelFactory
import com.example.exerciseapplication.ui.home.HomeViewModelFactory

object Injection {
    fun provideHomeVMFactory(context: Context): HomeViewModelFactory {
        val db = AppDatabase.getInstance(context)

        return HomeViewModelFactory(
            FavoriteRepositoryImpl(db.favoriteDao())
        )
    }

    fun provideWineVMFactory(context: Context): WineViewModelFactory {
        val db = AppDatabase.getInstance(context)
        return WineViewModelFactory(WineRepositoryImpl(RetrofitClient.apiService, db.collectionWineDao()))
    }

    fun provideWineCollectionVMFactory(context: Context): CollectionWineViewModelFactory {
        val db = AppDatabase.getInstance(context)
        return CollectionWineViewModelFactory(WineRepositoryImpl(RetrofitClient.apiService, db.collectionWineDao()))
    }
}