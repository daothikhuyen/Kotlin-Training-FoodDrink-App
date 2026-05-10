package com.example.exerciseapplication.ui.beer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exerciseapplication.data.domain.repository.WineRepository

class WineViewModelFactory(private val wineRepository: WineRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WineViewModel(wineRepository) as T
    }
}