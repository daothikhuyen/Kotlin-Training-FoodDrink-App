package com.example.exerciseapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuDrinkItem(
    val id: Int,
    var name: String,
    var price: Int,
    var type: String,
    var isSelect: Boolean = false,
    var description: String? = null
) : Parcelable // cho phép truyền dữ liệu
