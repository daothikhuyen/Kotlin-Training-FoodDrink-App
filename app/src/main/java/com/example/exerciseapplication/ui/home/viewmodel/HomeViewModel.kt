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
        MenuItem(7, "Bún Bò Huế", 45000),
        MenuItem(8, "Mì Xào Hải Sản", 78000),
        MenuItem(9, "Lẩu Thái", 250000),
        MenuItem(10, "Gỏi Cuốn", 30000),
        MenuItem(11, "Chả Giò", 55000),
        MenuItem(12, "Bánh Xèo", 60000),
        MenuItem(13, "Bún Chả", 70000),
        MenuItem(14, "Hủ Tiếu Nam Vang", 65000),
        MenuItem(15, "Cơm Tấm Sườn", 50000),
        MenuItem(16, "Canh Chua Cá", 90000),
        MenuItem(17, "Ốc Hương Xào Bơ Tỏi", 120000),
        MenuItem(18, "Tôm Nướng Muối Ớt", 150000),
        MenuItem(19, "Hủ Tiếu Nam Vang", 65000),
    )

    private val listDrink = mutableListOf(
        MenuItem(7, "Nước Cam", 356000),
        MenuItem(8, "Nước Dâu", 43000),
        MenuItem(9, "Trà Chanh", 78000),
        MenuItem(10, "Trà Đào", 15000),
        MenuItem(11, "Cà Phê", 37000),
        MenuItem(12, "Cà Phê Đen", 38000),
        MenuItem(13, "Cà Phê Sữa", 40000),
        MenuItem(14, "Bạc Xỉu", 42000),
        MenuItem(15, "Sinh Tố Xoài", 50000),
        MenuItem(16, "Sinh Tố Dâu", 52000),
        MenuItem(17, "Trà Sữa Trân Châu", 45000),
        MenuItem(18, "Nước Ép Dưa Hấu", 35000),
    )

    // chỉnh sửa được dữ liệu
    private val _drink = MutableLiveData<List<MenuItem>>()
    // chỉ đọc dữ liệu
    val drink: LiveData<List<MenuItem>> = _drink

    private val _food = MutableLiveData<List<MenuItem>>()
    val food: LiveData<List<MenuItem>> = _food

    init {
        _food.value = listFood.toList()
        _drink.value = listDrink .toList()
    }

    fun addItem(isFood: Boolean, name: String, price: Int) {
        if (isFood) {
            val newItem= MenuItem(listFood.size + 1, name, price)
            listFood.add(newItem)
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
                Log.d(TAG, "listFood update: ${listFood[index]}")

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