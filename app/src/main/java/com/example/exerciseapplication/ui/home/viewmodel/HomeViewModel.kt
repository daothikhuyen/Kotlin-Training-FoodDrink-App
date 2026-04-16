package com.example.exerciseapplication.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exerciseapplication.R
import com.example.exerciseapplication.model.MenuItem
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private var currentMode = 0
    private val listFood = listOf(
        MenuItem(1, R.string.fish),
        MenuItem(2, R.string.noodleSoup),
        MenuItem(3, R.string.crab),
        MenuItem(4, R.string.friedRice),
        MenuItem(5, R.string.grilledChicken),
        MenuItem(6, R.string.beefSteak),
        MenuItem(7, R.string.seafoodPizza),
    )

    private val listDrink = listOf(
        MenuItem(1, R.string.orangeJuice),
        MenuItem(2, R.string.strawberryJuice),
        MenuItem(3, R.string.lemonTea),
        MenuItem(4, R.string.peachTea),
        MenuItem(5, R.string.coffee),
        MenuItem(6, R.string.blackCoffee),
        MenuItem(7, R.string.milkCoffee)
    )

    private var drinkIndex = 0;
    private var foodIndex = 0;

    private val _drink = MutableLiveData<MenuItem>()
    val drink: LiveData<MenuItem> = _drink

    private val _food = MutableLiveData<MenuItem>()
    val food: LiveData<MenuItem> = _food

    private val _mode = MutableLiveData<Int>()
    val mode: LiveData<Int> = _mode
    fun radomItem() {
        drinkIndex = Random.nextInt(listDrink.size)
        foodIndex = Random.nextInt(listFood.size)
        updateData()
    }

    fun selectMode(isDrink: Boolean) {
        currentMode = if (isDrink) 0 else 1
        _mode.value = currentMode
        _mode.postValue()
    }


    fun next(isTagFood: Boolean) {
        if (isTagFood) {
            foodIndex = (foodIndex + 1) % listFood.size
        } else {
            drinkIndex = (drinkIndex + 1) % listDrink.size
        }
        updateData()
    }

    fun previous(isTagFood: Boolean) {
        if (isTagFood) {
            foodIndex = (foodIndex - 1 + listFood.size) % listFood.size
        } else {
            drinkIndex = (drinkIndex - 1 + listDrink.size) % listDrink.size
        }
        updateData()
    }

    private fun updateData() {
        _drink.value = listDrink[drinkIndex]
        _food.value = listFood[foodIndex]
        _mode.value = currentMode
    }
}