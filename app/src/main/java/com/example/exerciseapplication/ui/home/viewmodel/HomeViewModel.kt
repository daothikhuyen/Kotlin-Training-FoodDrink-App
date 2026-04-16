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

    // giữ data
    private val listFood = mutableListOf(
        MenuItem(1, "Cá Hấp", 356000),
        MenuItem(2, "Phở Tái", 659000),
        MenuItem(3, "Cua Hấp", 10000),
        MenuItem(4, "Cơm Chiên", 456000),
        MenuItem(5, "Gà Nướng", 123000),
        MenuItem(6, "Bò Bít Tết", 68000),
    )

    private val listDrink = mutableListOf(
        MenuItem(7, "Nước Cam", 356000),
        MenuItem(8, "Nước Dâu", 43000),
        MenuItem(9, "Trà Chanh", 78000),
        MenuItem(10, "Trà Đào", 15000),
        MenuItem(11, "Cà Phê", 37000),
        MenuItem(12, "Cà Phê Đen", 38000),
    )

    // chỉnh sửa được dữ liệu
    private val _drink = MutableLiveData<List<MenuItem>?>()
    // chỉ đọc dữ liệu
    val drink: LiveData<List<MenuItem>?> = _drink

    private val _food = MutableLiveData<List<MenuItem>?>()
    val food: LiveData<List<MenuItem>?> = _food

    init {
        _food.value = listFood.toList()
        _drink.value = listDrink.toList()
    }

    fun addItem(isFood: Boolean, name: String, price: Int) {
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

}