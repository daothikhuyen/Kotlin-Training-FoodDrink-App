package com.example.exerciseapplication.ui.home.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exerciseapplication.R
import com.example.exerciseapplication.model.MenuItem
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private var currentMode = 0
    private val listFood = mutableListOf(
        MenuItem(1, "Cá Hấp", 356.000),
        MenuItem(2, "Phở Tái", 659.000),
        MenuItem(3, "Cua Hấp", 10.000),
        MenuItem(4, "Cơm Chiên", 456.000),
        MenuItem(5, "Gà Nướng", 123.000),
        MenuItem(6, "Bò Bít Tết", 68.000),
    )

    private val listDrink = mutableListOf(
        MenuItem(1, "Nước Cam", 356.000),
        MenuItem(2, "Nước Dâu", 43.000),
        MenuItem(3, "Trà Chanh", 78.000),
        MenuItem(4, "Trà Đào", 15.000),
        MenuItem(5, "Cà Phê", 37.000),
        MenuItem(6, "Cà Phê Đen", 38.000),
    )

    private var drinkIndex = 0;
    private var foodIndex = 0;

    private val _drink = MutableLiveData<List<MenuItem>?>()
    val drink: LiveData<List<MenuItem>?> = _drink

    private val _food = MutableLiveData<List<MenuItem>?>()
    val food: LiveData<List<MenuItem>?> = _food

    private val _mode = MutableLiveData<Int>()

    fun radomItem() {
        drinkIndex = Random.nextInt(listDrink.size)
        foodIndex = Random.nextInt(listFood.size)
        updateData()
    }

    fun addItem(isFood: Boolean, name: String, price: Double) {
        if (isFood) {
            listFood.add(MenuItem(listFood.size + 1, name, price))
            _food.value = listFood.toList()
        } else {
            listDrink.add(MenuItem(listDrink.size + 1, name, price))
            _drink.value = listDrink.toList()
        }

    }

    fun updateItem(isFood: Boolean, item: MenuItem){
        if(isFood){
            val index = listFood.indexOfFirst { it.id == item.id }
            if(index != -1){
                listFood[index] = item
                _food.value = listFood.toList()
            }
        }else{
            val index = listDrink.indexOfFirst { it.id == item.id }
            if(index != -1){
                listDrink[index] = item
                _drink.value = listDrink.toList()
            }
        }
    }

    fun deleteItem(isFood: Boolean, item: MenuItem) {
        if (isFood) {
            listFood.removeIf { it.id == item.id }
            _food.value = listFood.toList()
        } else {
            listDrink.removeIf { it.id == item.id }
            _drink.value = listDrink.toList()
        }
    }

    private fun updateData() {
        _drink.value = listDrink.toList()
        _food.value = listFood.toList()
        _mode.value = currentMode
    }
}