package com.example.exerciseapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exerciseapplication.model.FakeData
import com.example.exerciseapplication.model.MenuDrinkItem
import com.example.exerciseapplication.model.MenuFoodItem

class HomeViewModel : ViewModel() {

    // giữ data
    private val listFood = FakeData.getFoodList().toMutableList()
    private val listDrink = FakeData.getDrinkList().toMutableList()

    // chỉnh sửa được dữ liệu
    private val _drink = MutableLiveData<List<MenuDrinkItem>>()

    // chỉ đọc dữ liệu
    val drink: LiveData<List<MenuDrinkItem>> = _drink

    private val _food = MutableLiveData<List<MenuFoodItem>>()
    val food: LiveData<List<MenuFoodItem>> = _food

    private val _item = MutableLiveData<Any?>()
    val item: LiveData<Any?> = _item

    private var selectedFood: MenuFoodItem? = null
    private var selectedDrink: MenuDrinkItem? = null

    init {
        _food.value = listFood.toList()
        _drink.value = listDrink.toList()
    }

    fun addItem(isFood: Boolean, name: String, price: Int, type: String) {
        if (isFood) {
            val newItem = MenuFoodItem(listFood.size + 1, name, price, type, false, "Hay")
            listFood.add(newItem)
            _food.value = listFood.toList()
        } else {
            listDrink.add(MenuDrinkItem(listDrink.size + 1, name, price, type, false, "Hay"))
            _drink.value = listDrink.toList()
        }
    }

    fun updateItem(isFood: Boolean, item: Any) {
        if (isFood && item is MenuFoodItem) {
            val index = listFood.indexOfFirst { it.id == item.id }
            if (index != -1) {
                listFood[index] = item
                _food.value = listFood.toList()
            }
        } else if (!isFood && item is MenuDrinkItem) {
            val index = listDrink.indexOfFirst { it.id == item.id }
            if (index != -1) {
                listDrink[index] = item
                _drink.value = listDrink.toList()
            }
        }
    }

    fun deleteItem(isFood: Boolean, item: Any) {
        if (isFood && item is MenuFoodItem) {
            listFood.removeIf { it.id == item.id }
            _food.value = listFood.toList()
        } else if (!isFood && item is MenuDrinkItem) {
            listDrink.removeIf { it.id == item.id }
            _drink.value = listDrink.toList()
        }
    }

    fun onTabChanged(isFood: Boolean) {
        if (isFood) {
            _item.value = selectedFood
        } else {
            _item.value = selectedDrink
        }
    }

    fun selectOnlyOne(isFood: Boolean, item: Any) {

        if (isFood && item is MenuFoodItem) {

            val oldIndex = listFood.indexOfFirst { it.isSelect }
            val newIndex = listFood.indexOfFirst { it.id == item.id }

            if (oldIndex == newIndex) {
                val current = listFood[newIndex]
                val newItem = current.copy(isSelect = false)
                listFood[newIndex] = newItem

                selectedFood = null
                _item.value = null
            } else {
                if (oldIndex != -1) {
                    val oldItem = listFood[oldIndex]
                    listFood[oldIndex] = oldItem.copy(isSelect = false)
                }

                val current = listFood[newIndex]
                val newItem = current.copy(isSelect = true)
                listFood[newIndex] = newItem

                selectedFood = newItem
                _item.value = newItem
            }

            _food.value = listFood.toList()
        } else if (!isFood && item is MenuDrinkItem) {

            val oldIndex = listDrink.indexOfFirst { it.isSelect }
            val newIndex = listDrink.indexOfFirst { it.id == item.id }

            if (oldIndex == newIndex) {
                val current = listDrink[newIndex]
                val newItem = current.copy(isSelect = false)
                listDrink[newIndex] = newItem

                selectedDrink = null
                _item.value = null
            } else {
                if (oldIndex != -1) {
                    val oldItem = listDrink[oldIndex]
                    listDrink[oldIndex] = oldItem.copy(isSelect = false)
                }

                val current = listDrink[newIndex]
                val newItem = current.copy(isSelect = true)
                listDrink[newIndex] = newItem

                selectedDrink = newItem
                _item.value = newItem
            }

            _drink.value = listDrink.toList()
        }
    }
}