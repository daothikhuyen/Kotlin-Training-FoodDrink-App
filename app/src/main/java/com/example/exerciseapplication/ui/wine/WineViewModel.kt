package com.example.exerciseapplication.ui.wine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exerciseapplication.data.domain.entities.WineEntity
import com.example.exerciseapplication.data.domain.repository.WineRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.Collections.emptyList

class WineViewModel(private val wineRepository: WineRepository) : ViewModel() {

    private val _wines = MutableStateFlow<List<WineEntity>>(emptyList())
    val wines: StateFlow<List<WineEntity>> = _wines

    private val _filteredWines = MutableStateFlow<List<WineEntity>>(emptyList())
    val filteredWines: StateFlow<List<WineEntity>> = _filteredWines

    private val _searchText = MutableStateFlow("")

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    init {
        fetchWines()
        searchWine()
    }

    fun fetchWines() {

        viewModelScope.launch {
            combine(
                wineRepository.getWines(), wineRepository.getCollectionWines()
            ) { wines, collections ->

                val collectionIds = collections.map { it.id }.toSet()

                wines.map { wine ->
                    wine.copy(
                        isCollected = collectionIds.contains(wine.id)
                    )
                }
            }.catch { e ->
                _message.emit(e.message ?: "Unknown error")
            }.collect {
                _wines.value = it
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    @OptIn(FlowPreview::class)
    fun searchWine() {
        viewModelScope.launch {
            combine(_searchText.debounce(500), _wines) { searchText, wines ->
                if (searchText.isBlank()) {
                    emptyList()
                } else {
                    wines.filter { it.winery.contains(searchText, ignoreCase = true) }
                }
            }.catch { e ->
                _message.emit(e.message ?: "Unknown error")
            }.collect {
                _filteredWines.value = it
            }
        }
    }

    fun toggleCollection(item: WineEntity) {
        viewModelScope.launch {
            try {
                val wineList = wineRepository.toggleCollection(item)
            } catch (e: Exception) {
                _message.emit(e.message ?: "Unknown error")
            }
        }
    }

}