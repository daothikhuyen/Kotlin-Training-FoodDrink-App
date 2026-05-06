package com.example.exerciseapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exerciseapplication.domain.repository.FavoriteRepository

class HomeViewModelFactory(private val favoriteRepository: FavoriteRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(favoriteRepository) as T
    }
}