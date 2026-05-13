package com.example.exerciseapplication.di

import com.example.exerciseapplication.data.domain.repository.FavoriteRepository
import com.example.exerciseapplication.data.domain.repository.WineRepository
import com.example.exerciseapplication.data.repository.FavoriteRepositoryImpl
import com.example.exerciseapplication.data.repository.WineRepositoryImpl
import com.example.exerciseapplication.data.source.local.database.AppDatabase
import com.example.exerciseapplication.data.source.network.RetrofitClient
import com.example.exerciseapplication.ui.collection.CollectionViewModel
import com.example.exerciseapplication.ui.home.HomeViewModel
import com.example.exerciseapplication.ui.wine.WineViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val appModule = module {
    // singleton cho AppDatabase
    single { AppDatabase.getInstance(androidContext()) }

    // singleton cho các Dao (để WineRepositoryImpl có thể get())
    single { get<AppDatabase>().collectionWineDao() }
    single { get<AppDatabase>().favoriteDao() }

    // Khai báo ApiService
    single { RetrofitClient.apiService }
    single<WineRepository>{WineRepositoryImpl(get(), get())}
    single<FavoriteRepository>{FavoriteRepositoryImpl(get())}

    single { WineViewModel(get()) }
    single { CollectionViewModel(get()) }
    single { HomeViewModel(get()) }

    // factory : tạo mới mỗi lần inject
    // scoped : tạo một đối tượng gắn liền với thời gian tồn tại của phạm vi liên quan.

}