package com.example.exerciseapplication.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exerciseapplication.data.domain.repository.WineRepository
import com.example.exerciseapplication.ui.wine.WineViewModel

@Suppress("UNCHECKED_CAST")
class CollectionWineViewModelFactory(
    private val repository: WineRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(CollectionViewModel::class.java) -> {
                CollectionViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}