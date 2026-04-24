package com.example.exerciseapplication.ui.home

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exerciseapplication.model.FakeData
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem
import com.example.exerciseapplication.ui.detail.DetailActivity
import com.example.exerciseapplication.ui.home.HomeActivity.Companion.TAG_FOOD

class HomeViewModel : ViewModel() {

    // giữ data
    private val listFood = FakeData.getFoodList()
    private val listDrink = FakeData.getDrinkList()

    // chỉnh sửa được dữ liệu
    private val _drink = MutableLiveData<List<MenuDrinkItem>>()

    // chỉ đọc dữ liệu
    val drink: LiveData<List<MenuDrinkItem>> = _drink

    private val _food = MutableLiveData<List<MenuFoodItem>>()
    val food: LiveData<List<MenuFoodItem>> = _food

    init {
        _food.value = listFood.toList()
        _drink.value = listDrink.toList()
    }

    fun addFoodItem(name: String, price: Long, type: String, description: String) {
        val nextId = (listFood.maxOfOrNull { it.id } ?: 0) + 1

        listFood.add(MenuFoodItem(nextId, name, price, type, false, description))
        _food.value = listFood.toList()
    }

    fun addDrinkItem(name: String, price: Long, type: String, description: String) {
        val nextId = (listDrink.maxOfOrNull { it.id } ?: 0) + 1

        listDrink.add(MenuDrinkItem(nextId, name, price, type, false, description))
        _drink.value = listDrink.toList()
    }

    fun updateFoodItem(item: MenuFoodItem) {
        val index = listFood.indexOfFirst { it.id == item.id }
        if (index != -1) {
            listFood[index] = item
            _food.value = listFood.toList()
        }
    }

    fun updateDrinkItem(item: MenuDrinkItem) {
        val index = listDrink.indexOfFirst { it.id == item.id }
        if (index != -1) {
            listDrink[index] = item
            _drink.value = listDrink.toList()
        }
    }

    fun deleteFoodItem(item: MenuFoodItem) {
        listFood.removeIf { it.id == item.id }
        _food.value = listFood.toList()
    }

    fun deleteDrinkItem(item: MenuDrinkItem) {
        listDrink.removeIf { it.id == item.id }
        _drink.value = listDrink.toList()
    }

    fun selectedFood(item: MenuFoodItem) {
        val newList = listFood.map {
            if (it.id == item.id ) it.copy(isFavorite = !it.isFavorite) else it
        }

        listFood.clear()
        listFood.addAll(newList)
        _food.value = newList
    }

    fun selectedDrink(item: MenuDrinkItem) {
        val newList = listDrink.map {
            if (it.id == item.id) it.copy(isFavorite = !it.isFavorite) else it
        }

        listDrink.clear()
        listDrink.addAll(newList)
        _drink.value = newList
    }
}