package com.example.exerciseapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exerciseapplication.data.domain.entities.FakeData
import com.example.exerciseapplication.data.domain.entities.MenuItem
import com.example.exerciseapplication.data.domain.repository.FavoriteRepository
import com.example.exerciseapplication.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    // Data gốc (Fake API)
    private val listFood = FakeData.getFoodList()
    private val listDrink = FakeData.getDrinkList()

    // LiveData UI
    private val _food = MutableStateFlow<List<MenuItem>>(emptyList())
    val food: Flow<List<MenuItem>> = _food.asStateFlow()

    private val _drink = MutableStateFlow<List<MenuItem>>(emptyList())
    val drink: Flow<List<MenuItem>> = _food.asStateFlow()

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    // Favorite từ Room
    val foodFavorite = favoriteRepository.getFavoriteList(AppConstants.FOOD)
    val drinkFavorite = favoriteRepository.getFavoriteList(AppConstants.DRINK)

    init {
        initData()
    }

    private fun initData() {
        _food.addSource(foodFavorite) { favorites ->
            updateUI(listFood, favorites, _food)
        }

        _drink.addSource(drinkFavorite) { favorites ->
            updateUI(listDrink, favorites, _drink)
        }
    }

    private fun updateUI(
        baseList: List<MenuItem>,
        favorites: List<MenuItem>,
        liveData: MutableLiveData<List<MenuItem>>
    ) {
        val favoriteIds = favorites.map { it.id }.toSet()

        liveData.value = baseList.map {
            it.copy(isFavorite = favoriteIds.contains(it.id))
        }
    }

    fun addFoodItem(name: String, price: Long, type: String, description: String) {
        listFood.add(createNewItem(listFood, name, price, type, description))
        refreshFood()
    }

    fun addDrinkItem(name: String, price: Long, type: String, description: String) {
        listDrink.add(createNewItem(listDrink, name, price, type, description))
        refreshDrink()
    }

    private fun createNewItem(
        list: List<MenuItem>,
        name: String,
        price: Long,
        type: String,
        description: String
    ): MenuItem {
        val nextId = (list.maxOfOrNull { it.id } ?: 0) + 1
        return MenuItem(nextId, name, price, type, false, description)
    }

    fun updateFoodItem(item: MenuItem) {
        updateItem(listFood, item)
        updateFavoriteIfNeeded(item)
        refreshFood()
    }

    fun updateDrinkItem(item: MenuItem) {
        updateItem(listDrink, item)
        updateFavoriteIfNeeded(item)
        refreshDrink()
    }

    private fun updateItem(list: MutableList<MenuItem>, item: MenuItem) {
        val index = list.indexOfFirst { it.id == item.id }
        if (index != -1) {
            list[index] = item
        }
    }

    fun deleteFood(item: MenuItem) {
        deleteItem(listFood, item)
        removeFavoriteIfNeeded(item)
        refreshFood()
    }

    fun deleteDrink(item: MenuItem) {
        deleteItem(listDrink, item)
        removeFavoriteIfNeeded(item)
        refreshDrink()
    }

    private fun deleteItem(list: MutableList<MenuItem>, item: MenuItem) {
        list.removeIf { it.id == item.id }
    }

    fun selectedFood(item: MenuItem) = toggleFavorite(item)

    fun selectedDrink(item: MenuItem) = toggleFavorite(item)

    private fun toggleFavorite(item: MenuItem) {
        val newItem = item.copy(isFavorite = !item.isFavorite)
        viewModelScope.launch {
            favoriteRepository.toggleFavorite(newItem)
        }
    }

    private fun updateFavoriteIfNeeded(item: MenuItem) {
        if (item.isFavorite) {
            viewModelScope.launch {
                favoriteRepository.updateFavorite(item)
            }
        }
    }

    private fun removeFavoriteIfNeeded(item: MenuItem) {
        if (item.isFavorite) {
            toggleFavorite(item)
        }
    }

    private fun refreshFood() {
        val favorites = foodFavorite.value ?: emptyList()
        updateUI(listFood, favorites, _food)
    }

    private fun refreshDrink() {
        val favorites = drinkFavorite.value ?: emptyList()
        updateUI(listDrink, favorites, _drink)
    }

    fun setLoadingFalse() {
        _isLoading.value = false
    }
}