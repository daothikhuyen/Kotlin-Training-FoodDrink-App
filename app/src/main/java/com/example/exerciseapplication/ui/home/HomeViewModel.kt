package com.example.exerciseapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exerciseapplication.domain.entities.FakeData
import com.example.exerciseapplication.domain.entities.MenuItem
import com.example.exerciseapplication.domain.repository.DrinkRepository
import com.example.exerciseapplication.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val foodRepository: FoodRepository, private val drinkRepository: DrinkRepository) : ViewModel() {

    // chỉ đọc dữ liệu
    val drink: LiveData<List<MenuItem>> = drinkRepository.getDrinkList()
    val food: LiveData<List<MenuItem>> = foodRepository.getFoodList()

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    val drinkFavorite: LiveData<List<MenuItem>> = drinkRepository.getFavoriteList()
    val foodFavorite: LiveData<List<MenuItem>> = foodRepository.getFavoriteList()

    init {
        initData()
    }

    private fun initData() {

        // chạy hàm này trên luồng đồng bộ (main thread)
        viewModelScope.launch() {
            val currentFood = foodRepository.getFoodListOnce()
            if (currentFood.isEmpty()) {
                foodRepository.insertAll(FakeData.getFoodList())
            }

            val currentDrink = drinkRepository.getDrinkListOnce()
            if (currentDrink.isEmpty()) {
                drinkRepository.insertAll(FakeData.getDrinkList())
            }
        }
    }

    fun setLoadingFalse() {
        _isLoading.value = false
    }

    // add new item
    fun addFoodItem(name: String, price: Long, type: String, description: String) {
        viewModelScope.launch {
            val item = MenuItem(name = name, price = price, type = type, description = description)
            foodRepository.insertAll(listOf(item))
        }
    }

    fun addDrinkItem(name: String, price: Long, type: String, description: String) {
        viewModelScope.launch {
            val item = MenuItem(name = name, price = price, type = type, description = description)
            drinkRepository.insertAll(listOf(item))
        }
    }

    // update
    fun updateFoodItem(item: MenuItem) {
        viewModelScope.launch {
            foodRepository.updateItem(item)
        }
    }

    fun updateDrinkItem(item: MenuItem) {
        viewModelScope.launch {
            drinkRepository.updateItem(item)
        }
    }

    fun deleteFood(item: MenuItem) {
        viewModelScope.launch {
            foodRepository.deleteItem(item.id)
        }
    }

    fun deleteDrink(item: MenuItem) {
        viewModelScope.launch {
            drinkRepository.deleteItem(item.id)
        }
    }


    // favorite
    fun selectedFood(item: MenuItem) {
        viewModelScope.launch {
           foodRepository.toggleFavorite(item.id)
        }
    }

    fun selectedDrink(item: MenuItem) {
        viewModelScope.launch {
            drinkRepository.toggleFavorite(item.id)
        }
    }
}