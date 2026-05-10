package com.example.exerciseapplication.ui.beer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exerciseapplication.data.domain.entities.Wine
import com.example.exerciseapplication.data.domain.repository.WineRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WineViewModel(private val wineRepository: WineRepository) : ViewModel() {

    private val _wines = MutableStateFlow<List<Wine>>(emptyList())
    val wines: StateFlow<List<Wine>> = _wines

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    init {
        fetchWines()
    }

    fun fetchWines(){
        viewModelScope.launch {
            try {
                val wineList = wineRepository.getWines()
                Log.d("list", wineList.toString())
                _wines.emit(wineList)
            }catch (e: Exception){
                _message.emit(e.message ?: "Unknown error")
            }
        }
    }

}