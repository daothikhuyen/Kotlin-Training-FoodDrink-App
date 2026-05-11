package com.example.exerciseapplication.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.data.domain.repository.WineRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class CollectionViewModel(private val wineRepository: WineRepository) : ViewModel() {

    val collections = wineRepository.getCollectionWines()

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    fun toggleCollection(item: WineEntity){
        viewModelScope.launch {
            try {
                wineRepository.toggleCollection(item)
            }catch (e: Exception){
                _message.emit(e.message ?: "Unknown error")
            }
        }
    }

}