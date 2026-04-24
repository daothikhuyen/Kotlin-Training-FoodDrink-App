package com.example.exerciseapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuDrinkItem(
    val id: Int,
    var name: String,
    var price: Long,
    var type: String,
    var isFavorite: Boolean = false,
    var description: String? = null
) : Parcelable // cho phép truyền dữ liệu
