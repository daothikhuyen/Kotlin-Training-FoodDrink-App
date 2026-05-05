package com.example.exerciseapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exerciseapplication.domain.repository.DrinkRepository
import com.example.exerciseapplication.domain.repository.FoodRepository

class HomeViewModelFactory(private val foodRepository: FoodRepository, private val drinkRepository: DrinkRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(foodRepository, drinkRepository) as T
    }
}